/* 
 * Copyright (c) Ericsson AB, 2013
 * All rights reserved.
 *
 * License terms:
 *
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 *     * Redistributions of source code must retain the above 
 *       copyright notice, this list of conditions and the 
 *       following disclaimer.
 *     * Redistributions in binary form must reproduce the 
 *       above copyright notice, this list of conditions and 
 *       the following disclaimer in the documentation and/or 
 *       other materials provided with the distribution.
 *     * Neither the name of the copyright holder nor the names 
 *       of its contributors may be used to endorse or promote 
 *       products derived from this software without specific 
 *       prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.caltoopia.codegen.printer;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

/*
 * This class generates a string for a type.
 * 
 * Quality: 4, works but its a bit too complex for its own good
 */
public class CBuildTypeName extends IrSwitch<Boolean> {
    String typeStr="";
    Type type;
    ITypeCallbacks cb=null;
    int dim=0;
    boolean array;

    /*
     * Constructor for building a long string containing the 
     * c-code of a type. The action is printed as a c type
     * which should be embedded with declarations or expressions.
     * 
     * type: type to be printed
     * cb: a callback implementation of an interface that handles
     *     what extra to print for lists, user type, and pre/post.
     *     Some classes to inherit/use are declared in CPrintUtil.
     * array: even if list not always want to print the array part
     *        of type name. True means print full type name with "array"
     *        false only the element type name.
     */
    public CBuildTypeName(Type type, ITypeCallbacks cb, boolean array) {
        typeStr="";
        dim = 0;
        this.type = type;
        this.array = array;
        if(cb == null) {
            this.cb = new CPrintUtil.dummyCB();
        } else {
            this.cb = cb;
        }
    }
    
    /*
     * Do the actual generation of the type string, use as:
     * new CBuildTypeName(...).toStr()
     */
    public String toStr() {
        Boolean res = doSwitch(type);
        if(!res) {
            CodegenError.err("Type name builder", typeStr);
        }
        /*
         * If is a list and we want array in type name print it.
         * The c-structure for arrays are defined as __array4. 
         * The 4 is that we support maximum 4 dimension sizes.
         * The number is to allow for optimization/extension
         * to support fewer and more dimensions with other
         * metadata structs.
         */
        return ((dim==0)||!array)?typeStr:"__array4"+typeStr;
    }
    
    /*
     * Print the final element type string, use as:
     * new CBuildTypeName(...).toFinalTypeStr()
     * NB! Must have called toStr() first.
     */
    public String toFinalTypeStr() {
        return typeStr;
    }

    //------------------------------------------------------

    private void enter(EObject obj) {}
    private void leave() {}

    //----------------- caseX-----
    @Override
    public Boolean caseTypeUser(TypeUser type) {
        enter(type);
        TypeDeclaration decl = UtilIR.getTypeDeclaration(type);
        typeStr += cb.preTypeFn(type);
        typeStr += (decl.getName() + "_t");
        typeStr += cb.postTypeFn(type);
        leave();
        return true;
    }
    
    @Override
    public Boolean caseTypeBool(TypeBool type) {
        enter(type);
        typeStr += cb.preTypeFn(type);
        typeStr += ("bool_t");
        typeStr += cb.postTypeFn(type);
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeInt(TypeInt type) {
        enter(type);
        typeStr += cb.preTypeFn(type);
        int sz = (int) (type.getSize() instanceof IntegerLiteral ? ((IntegerLiteral)type.getSize()).getValue() : (type.getSize()==null ? -1:-2));
        switch(sz) {
        case 32:
        case -1:
            //When no size specified use 32 bit
            typeStr += ("int32_t");
            break;
        case 16:
            typeStr += ("int16_t");
            break;
        case 8:
            typeStr += ("int8_t");
            break;
        default:
            //Also for sizes that don't is even 8, 16 or 32 use 32 bit, 
            //this is new behavior since previous codegen have all used 32 bit.
            typeStr += ("int32_t /*FIXME actually sz="+sz+"*/");
        }
        typeStr += cb.postTypeFn(type);
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeUint(TypeUint type) {
        enter(type);
        typeStr += cb.preTypeFn(type);
        int sz = (int) (type.getSize() instanceof IntegerLiteral ? ((IntegerLiteral)type.getSize()).getValue() : (type.getSize()==null ? -1:-2));
        switch(sz) {
        case 32:
        case -1:
            //When no size specified use 32 bit
            typeStr += ("uint32_t");
            break;
        case 16:
            typeStr += ("uint16_t");
            break;
        case 8:
            typeStr += ("uint8_t");
            break;
        default:
            //Also for sizes that don't is even 8, 16 or 32 use 32 bit, 
            //this is new behavior since previous codegen have all used 32 bit.
            typeStr += ("uint32_t /*FIXME actually sz="+sz+"*/");
        }
        typeStr += cb.postTypeFn(type);
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeFloat(TypeFloat type) {
        enter(type);
        typeStr += cb.preTypeFn(type);
        typeStr += ("double");
        typeStr += cb.postTypeFn(type);
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeList(TypeList type) {
        enter(type);
        typeStr += cb.preTypeFn(type);
        doSwitch(type.getType());
        typeStr += cb.postTypeFn(type);
        //Keep track of how many dimensions in we are which is available for the callback function
        dim++;
        typeStr += cb.listTypeFn(type, dim);
        leave();
        return true;
    }


    @Override
    public Boolean caseTypeString(TypeString type) {
        enter(type);
        //Strings are printed as single dim char array
        typeStr += cb.preTypeFn(type);
        if(!array) {
            typeStr += "char";
        } else {
            typeStr += "__array4char";
        }
        typeStr += cb.postTypeFn(type);
        leave();
        return true;
    }

    
    @Override
    public Boolean caseTypeRecord(TypeRecord struct) {
        enter(struct);
        //We should never need to print a record directly, instead we print a string that will generate compile errors
        typeStr += ("SOMEONE PRINTING A RECORD DIRECTLY????");
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeExternal(TypeExternal type) {
        enter(type);
        typeStr += cb.preTypeFn(type);
        typeStr += (type.getName());
        typeStr += cb.postTypeFn(type);
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeUndef(TypeUndef type) {
        enter(type);
        typeStr += cb.preTypeFn(type);
        typeStr += ("void");
        typeStr += cb.postTypeFn(type);
        leave();
        return true;
    }
    
    @Override
    public Boolean caseType(Type type) {
        assert(false);
        return null;
    }

}
