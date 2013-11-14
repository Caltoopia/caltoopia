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

package org.caltoopia.codegen.transformer.analysis;

import java.io.File;
import java.io.PrintStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.PortPeek;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcCall;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeConstructor;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeProc;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.IrXmlPrinter;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.IrTransformer.IrPassTypes;
import org.caltoopia.codegen.transformer.TransUtil.HowLiteral;
import org.caltoopia.codegen.transformer.TransUtil;

public class IrVariableAnnotation extends IrReplaceSwitch {

	private class UsedInBody extends IrReplaceSwitch {
		private String searchID=null;
		private boolean found=false;
		public void setId(String id) {
			this.searchID=id;
		}
		public boolean getFound() {
			return found;
		}
		
		@Override
		public Expression caseVariableExpression(VariableExpression var) {
			if(var.getVariable().getId().equals(searchID))
				found=true;
			return super.caseVariableExpression(var);
		}
	}
	private Map<String,Boolean> foundMap = new HashMap<String,Boolean>();
	private boolean isUsedInBody(String id, Action a) {
		if(foundMap.containsKey(id))
			return foundMap.get(id);

		UsedInBody u = new UsedInBody();
		u.setId(id);
		//Used in body of action or ...
		for(Statement s : a.getStatements())
			u.doSwitch(s);
		//directly in port write expressions or ..
		for(PortWrite p : a.getOutputs())
			u.doSwitch(p);
		//part of initValue in declarations
		for(Declaration d : a.getDeclarations())
			u.doSwitch(d);
		foundMap.put(id, u.getFound());
		return u.getFound();
	}

	private Actor currentActor;
	private Network currentNetwork;
	private Action currentAction;
	private Namespace currentNamespace;
	private PortRead currentRead;
	private PortWrite currentWrite;
	private Map<String,String> idInVarAccessMap = new HashMap<String,String>();
	private Map<String,String> idOutVarAccessMap = new HashMap<String,String>();
	
	private PrintStream serr = null; 
	private CompilationSession session;

	public IrVariableAnnotation(Node node, CompilationSession session, boolean errPrint) {
		if(!errPrint) {
			serr = new PrintStream(new OutputStream(){
			    public void write(int b) {
			        //NO-OP
			    }
			});
		} else {
			serr = System.err;
		}
		this.session = session;
		this.doSwitch(node);
	}
	
	public enum VarType {
		unknown,
		//Functions and procedures of different flavors
		func,
		proc,
		actorFunc,
		externFunc,
		externProc,
		importFunc,
		importProc,
		//Normal variables of different flavors
        externBuiltInTypeVar,
        externBuiltInListTypeVar,
        externOtherTypeVar, //TODO frontend won't allow user types in external declarations
        externOtherListTypeVar,  //TODO frontend won't allow user types in external declarations
		importConstVar,
		constVar,
		generatorVar,
        blockVar,
        blockConstVar,
		funcVar,
		funcInParamVar,
		procVar,
		procInParamVar,
		procOutParamVar,
		actorVar,
		actorParamVar,
		actorConstParamVar,
		actionVar,
		actionInitInDepVar,
		syncVar, //The value of this input token is never used in the action
		peekVar,
		inPortPeekVar, //Both read and peek
		inPortVar,
		inOutPortVar, //input var used on output directly
		inOutPortPeekVar, //input var used on output directly and in guard
		outPortVar, //Only when directly used as variable on port, not if part of some expression giving the output port expression
		//Declarations
		declarationType,
		memberDeclType
	};
	
	public enum VarAccess {
		unknown,
		//Port accesses
		scalarSingle,
		scalarInterleaved,
		listSingle,
		listInterleaved, //Is this possible in the syntax???
		scalarUserTypeSingle,
		scalarUserTypeInterleaved,
		listUserTypeSingle,
		listUserTypeInterleaved, //Is this possible in the syntax???
		//Member accesses
		inlinedMember,
		refMember
	};

    public enum VarLocalAccess {
        unknown,
        scalar,                     //scalar builtin type
        string,                     //i.e. one dimension char array (FIXME add arrays of strings)
        list,                       //list of builtin type
        listMultiList,              //list multi-dim (only single list after index) of builtin type
        listMulti,                  //list multi-dim (still multi-dim after index) of builtin type
        scalarUserType,             //scalar user type
        listUserType,               //list of user type
        listMultiUserTypeList,      //list multi-dim (only single list after index) of user type
        listMultiUserType,          //list multi-dim (still multi-dim after index) of user type
        listSingle,                 //list of builtin type (indexed to scalar)
        listMultiSingle,            //list multi-dim (indexed to scalar) of builtin type
        listUserTypeSingle,         //list of user type (indexed to scalar)
        listMultiUserTypeSingle,    //list multi-dim (indexed to scalar) of user type
        //when a scalar user type's member
        memberScalar,
        memberString,
        memberList,
        memberListMultiList,
        memberListMulti,
        memberScalarUserType,
        memberListUserType,
        memberListMultiUserTypeList,
        memberListMultiUserType,
        memberListSingle,
        memberListMultiSingle,
        memberListUserTypeSingle,
        memberListMultiUserTypeSingle,
        //when a list user type's member
        listMemberScalar,
        listMemberString,
        listMemberList,
        listMemberListMultiList,
        listMemberListMulti,
        listMemberScalarUserType,
        listMemberListUserType,
        listMemberListMultiUserTypeList,
        listMemberListMultiUserType,
        listMemberListSingle,
        listMemberListMultiSingle,
        listMemberListUserTypeSingle,
        listMemberListMultiUserTypeSingle,
        //assignment ref and expr same variable
        self,
        //assignment of temp (codegenerator only) variable
        temp,
        //Member accesses
        inlinedMember,
        refMember
    };

    public enum VarAssign {
		unknown,
        assigned,
		movedInitAssigned,
		movedRetAssigned
		//TODO add more types of assignment when needed, e.g. if member is assigned, assigned due to procedure output, etc
	};
	
    public enum VarLiteral {
        unknown,
        assignedLit,
        assignedListLit,
        assignedListLitwLVEoFC,
        assignedListLitwTC,
        assignedTypeConstructLit,
        assignedTypeConstruct,
        assignedInitLit,
        assignedInitListLit,
        assignedInitListLitwLVEoFC,
        assignedInitListLitwTC,
        assignedInitTypeConstructLit,
        assignedInitTypeConstruct,
        initializedLit,
        initializedListLit,
        initializedListLitwLVEoFC,
        initializedListLitwTC,
        initializedTypeConstructLit,
        initializedTypeConstruct
    };

    private VarType annotatePortVar(Declaration variable, Action a) {
		VarType t = VarType.unknown;
		if(!a.getOutputs().isEmpty()) {
			//See if declaration is used as output variable expression
			for(PortWrite p : a.getOutputs()) {
				if(!p.getExpressions().isEmpty()) {
					for(Expression e : p.getExpressions()) {
						if(e instanceof VariableExpression && UtilIR.getDeclarationTransformed(((VariableExpression)e).getVariable()).getId().equals(variable.getId())) {
							t = VarType.outPortVar;
							break;
						}
					}
					if(t != VarType.unknown) {
						break;
					}
				}
			}
		}
		if(!a.getInputs().isEmpty()) {
			//See if the declaration is used as input port variable (this does not actually guarantee any statements using it)
			for(PortRead p : a.getInputs()) {
				if(!p.getVariables().isEmpty()) {
					for(VariableReference v : p.getVariables()) {
						if(UtilIR.getDeclaration(v.getDeclaration()).getId().equals(variable.getId())) {
							if(t == VarType.outPortVar)
								t = VarType.inOutPortVar;
							else
								t = VarType.inPortVar;
							break;
						}
					}
					if(t == VarType.inPortVar || t == VarType.inOutPortVar) {
						break;
					}
				}
			}
			//Now we need to verify if it is used in any statements
			boolean found = false;
			if(t==VarType.inPortVar) {
				found = isUsedInBody(variable.getId(),a);
				if(!found) {
					//Not found then used as sync token (or we will discover below if used in guard)
					t=VarType.syncVar;
				}
			}
			//Check if is used as a guard
			if(!a.getGuards().isEmpty()) {
				for(Guard g : a.getGuards()) {
					if(!g.getPeeks().isEmpty()) {
						for(PortPeek p : g.getPeeks()) {
							Declaration d = p.getVariable().getDeclaration();
							if(UtilIR.getDeclaration(d).getId().equals(variable.getId())) {
								if(t==VarType.inPortVar) {
									t = VarType.inPortPeekVar;
								} else if(t==VarType.inOutPortVar) {
									t = VarType.inOutPortPeekVar;
								} else {
									t = VarType.peekVar;
								}
								break;
							}
						}
						if(t == VarType.peekVar || t == VarType.inPortPeekVar || t == VarType.inOutPortPeekVar) {
							break;
						}
					}
				}
			}
		}
		return t;
	}
	
	private boolean foundInSwitch;
	private List<PortRead> LookForReadSwitch;
	
	private VarType findVariableType(Declaration inDecl) {
		Declaration decl = UtilIR.getDeclarationTransformed(inDecl); //Any of: (import, forward,) external, variable
		VarType t = VarType.unknown;
		if(decl instanceof Variable) {
			Variable variable = (Variable) decl;
			if(variable.getInitValue() instanceof LambdaExpression) {
				if(inDecl instanceof VariableImport) {
					t = VarType.importFunc;
				} else {
				    if(currentActor==null) {
				        t = VarType.func;
				    } else {
                        t = VarType.actorFunc;
				    }
				}
			} else if(variable.getInitValue() instanceof ProcExpression) {
				if(inDecl instanceof VariableImport) {
					t = VarType.importProc;
				} else {
					t = VarType.proc;
				}
			} else {
				if(inDecl instanceof VariableImport) {
					t = VarType.importConstVar;
                    TransUtil.setNamespaceAnnotation(inDecl, variable.getScope());
				} else if(variable.getScope() instanceof Actor) {
					if(variable.isConstant() && variable.getInitValue()!=null) {
						if(variable.isParameter())
							t = VarType.actorConstParamVar;
						else {
							t = VarType.constVar;
	                        TransUtil.setNamespaceAnnotation(inDecl, variable.getScope());
						}
					} else {
						if(variable.isParameter())
							t = VarType.actorParamVar;
						else
							t = VarType.actorVar;
					}
				} else if(variable.getScope() instanceof Namespace) {
					if(variable.isConstant() && variable.getInitValue()!=null) {
							t = VarType.constVar;
							TransUtil.setNamespaceAnnotation(inDecl, variable.getScope());
					} else {
						System.err.println("[IrAnnotate] Did not expect a non-const var in namespace "+ ((Namespace)variable.getScope()).getName() + " and of class " + variable.getClass());
					}
				} else if(variable.getScope() instanceof Network) {
					if(variable.isConstant() && variable.getInitValue()!=null) {
							t = VarType.constVar;
                            TransUtil.setNamespaceAnnotation(inDecl, variable.getScope());
					} else {
						System.err.println("[IrAnnotate] Did not expect a non-const var in network of class " + variable.getClass());
					}
				} else if(variable.getScope() instanceof Variable && ((Variable)variable.getScope()).getInitValue() instanceof LambdaExpression) {
					if(variable.isParameter()) {
						t = VarType.funcInParamVar;
					} else {
						t = VarType.funcVar;
					}
				} else if(variable.getScope() instanceof LambdaExpression) {
					if(variable.isParameter()) {
						t = VarType.funcInParamVar;
					} else {
						t = VarType.funcVar;
					}
				} else if(variable.getScope() instanceof Variable && ((Variable)variable.getScope()).getInitValue() instanceof ProcExpression) {
					//FIXME should check for in/out
					t = VarType.procInParamVar;
				} else if(variable.getScope() instanceof ProcExpression) {
					//FIXME should check for in/out
					t = VarType.procInParamVar;
				} else if(variable.getScope() instanceof Block && variable.getScope().getOuter() instanceof ProcExpression) {
					t = VarType.procVar;
				} else if(variable.getScope() instanceof Action) {
					Action a = (Action) variable.getScope();
					t = annotatePortVar(variable, a);
					if(t == VarType.unknown) {
						//Check if the initValue contains ref to any in-port variable
						if(variable.getInitValue()==null) {
							t = VarType.actionVar;
						} else {
							foundInSwitch=false;
							LookForReadSwitch = a.getInputs();
							new IrReplaceSwitch() {
								@Override
								public Expression caseVariableExpression(VariableExpression var) {
									for(PortRead r : LookForReadSwitch) {
										for(VariableReference vr : r.getVariables()) {
											if(vr.getDeclaration().getId().equals(var.getVariable().getId())) {
												foundInSwitch=true;
											}
										}
									}
									return super.caseVariableExpression(var);
								}
							}.doSwitch(variable.getInitValue());
							if(foundInSwitch) {
								t = VarType.actionInitInDepVar;
							} else {
								t = VarType.actionVar;
							}
						}
					}
				} else if(variable.getScope() instanceof PortWrite) {
					t = annotatePortVar(variable, currentAction);
					if(t == VarType.unknown) {
						t = VarType.actionVar;
					}
				} else if(variable.getScope() instanceof Generator) {
					t = VarType.generatorVar;
				} else if(variable.getScope() instanceof Block) {
                    if(variable.isConstant() && variable.getInitValue()!=null) {
                        t = VarType.blockConstVar;
                    } else {
                        t = VarType.blockVar;
                    }
				} else {
					serr.println("[IrAnnotate] Did not expect a normal var in scope "+variable.getScope().getClass()+ " and of name " + variable.getName());
				}
			}
		} else if(decl instanceof VariableExternal){
			VariableExternal variable = (VariableExternal) decl;
			Type type = variable.getType();
			if(type instanceof TypeLambda) {
				t = VarType.externFunc;
			} else if(type instanceof TypeProc) {
				t = VarType.externProc;
			//TODO any more types that should be classified as built in, how about lists?
			} else if(type instanceof TypeBool || type instanceof TypeInt || type instanceof TypeUint || type instanceof TypeFloat) {
				t = VarType.externBuiltInTypeVar;
			} else if(type instanceof TypeList) {
			    Type list = type;
			    while(list instanceof TypeList) {
			        list = ((TypeList)list).getType();
			    }
			    if(list instanceof TypeBool || list instanceof TypeInt || list instanceof TypeUint || list instanceof TypeFloat) {
			        t = VarType.externBuiltInListTypeVar;
			    } else if(list instanceof TypeUser) {
                    t = VarType.externOtherListTypeVar;
			    } else {
	                serr.println("[IrAnnotate] Did not expect an external list variable of type "+list.getClass());
			    }
            } else if(type instanceof TypeUser) {
                t = VarType.externOtherTypeVar;
            } else {
				serr.println("[IrAnnotate] Did not expect an external variable of type "+type.getClass());
			}
		} else {
		    if(decl == null && inDecl!=null && inDecl.getName().startsWith("dprint")) {
		        t = VarType.externProc; //FIXME special case until we have dropped legacy CPrinter and can change getDeclaration()
		    } else {
		        serr.println("[IrAnnotate] Did not expect a variable of class "+ (inDecl==null?"none":inDecl.getClass()));
		    }
		}
		return t;
	}

	private VarAccess findPortAccess(Type type, boolean isRepeat, int size) {
		VarAccess va = VarAccess.unknown;
		if(UtilIR.isList(type)) {
			if(UtilIR.isRecord(((TypeList)UtilIR.getType(type)).getType())) {
				va = VarAccess.listUserTypeSingle;
			} else {
				va = VarAccess.listSingle;
			}
		} else {
			if(UtilIR.isRecord(type)) {
				if(isRepeat) {
					va = VarAccess.listUserTypeSingle;
				} else {
					va = VarAccess.scalarUserTypeSingle;
				}
			} else {
				if(isRepeat) {
					va = VarAccess.listSingle;
				} else {
					va = VarAccess.scalarSingle;
				}
			}
		}
		if(size>1) {
			switch(va) {
			case listUserTypeSingle:
				va = VarAccess.listUserTypeInterleaved;
				break;
			case listSingle:
				va = VarAccess.listInterleaved;
				break;
			case scalarUserTypeSingle:
				va = VarAccess.scalarUserTypeInterleaved;
				break;
			case scalarSingle:
				va = VarAccess.scalarInterleaved;
				break;
			}
		}
		return va;
	}
	
    private VarLocalAccess findLocalAccess(Declaration decl, List<Expression> index, List<Member> member) {
        VarLocalAccess vla = VarLocalAccess.unknown;
        int dim = (index != null)?index.size():0;
        if(decl instanceof Variable) {
            Type type = UtilIR.getType(((Variable)decl).getType());
            if(member !=null && member.size()>0){
                Declaration mv = null;
                List<Expression> mi = null;
                for(Member mm:member) {
                    if(UtilIR.isList(type)) {
                        while(type instanceof TypeList) {
                            dim--;
                            type = ((TypeList)type).getType();
                        }
                        type = UtilIR.getType(type);
                    }
                    if(type instanceof TypeRecord) {
                        for(Variable m:((TypeRecord)type).getMembers()) {
                            if(m.getName().equals(mm.getName())) {
                                mv = m;
                                mi = mm.getIndex();
                                type = m.getType();
                                break;
                            }
                        }
                    }
                }
                if(mv != null) {
                    boolean varIsList = (index != null)?index.size()>0:false;
                    vla = findLocalAccess(mv,mi,null);
                    switch (vla) {
                    case unknown:
                    case inlinedMember:
                    case refMember:
                    case self:
                        break;
                    case scalarUserType:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberScalarUserType;
                        } else {
                            vla = VarLocalAccess.memberScalarUserType;
                        }
                        break;
                    case scalar:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberScalar;
                        } else {
                            vla = VarLocalAccess.memberScalar;
                        }
                        break;
                    case string:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberString;
                        } else {
                            vla = VarLocalAccess.memberString;
                        }
                        break;
                    case list:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberList;
                        } else {
                            vla = VarLocalAccess.memberList;
                        }
                        break;
                    case listMulti:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberListMulti;
                        } else {
                            vla = VarLocalAccess.memberListMulti;
                        }
                        break;
                    case listMultiList:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberListMultiList;
                        } else {
                            vla = VarLocalAccess.memberListMultiList;
                        }
                        break;
                    case listMultiSingle:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberListMultiSingle;
                        } else {
                            vla = VarLocalAccess.memberListMultiSingle;
                        }
                        break;
                    case listMultiUserType:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberListMultiUserType;
                        } else {
                            vla = VarLocalAccess.memberListMultiUserType;
                        }
                        break;
                    case listMultiUserTypeList:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberListMultiUserTypeList;
                        } else {
                            vla = VarLocalAccess.memberListMultiUserTypeList;
                        }
                        break;
                    case listMultiUserTypeSingle:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberListMultiUserTypeSingle;
                        } else {
                            vla = VarLocalAccess.memberListMultiUserTypeSingle;
                        }
                        break;
                    case listSingle:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberListSingle;
                        } else {
                            vla = VarLocalAccess.memberListSingle;
                        }
                        break;
                    case listUserType:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberListUserType;
                        } else {
                            vla = VarLocalAccess.memberListUserType;
                        }
                        break;
                    case listUserTypeSingle:
                        if(varIsList) {
                            vla = VarLocalAccess.listMemberListUserTypeSingle;
                        } else {
                            vla = VarLocalAccess.memberListUserTypeSingle;
                        }
                        break;
                    default:
                    }
                    return vla;
                }
            } else {
                if(UtilIR.isList(type)) {
                    int tDim = 0;
                    while(type instanceof TypeList) {
                        tDim++;
                        type = ((TypeList)type).getType();
                    }
                    if(UtilIR.isRecord(type)) {
                        if(tDim>1) {
                            if(dim<(tDim-1)) {
                                vla = VarLocalAccess.listMultiUserType;
                            } else if(dim<tDim) {
                                vla = VarLocalAccess.listMultiUserTypeList;
                            } else {
                                vla = VarLocalAccess.listMultiUserTypeSingle;
                            }
                        } else {
                            if(dim<tDim) {
                                vla = VarLocalAccess.listUserType;
                            } else {
                                vla = VarLocalAccess.listUserTypeSingle;
                            }
                        }
                    } else {
                        if(tDim>1) {
                            if(dim<(tDim-1)) {
                                vla = VarLocalAccess.listMulti;
                            } else if(dim<tDim) {
                                vla = VarLocalAccess.listMultiList;
                            } else {
                                vla = VarLocalAccess.listMultiSingle;
                            }
                        } else {
                            if(dim<tDim) {
                                vla = VarLocalAccess.list;
                            } else {
                                vla = VarLocalAccess.listSingle;
                            }
                        }
                    }
                } else {
                    if(UtilIR.isRecord(type)) {
                        vla = VarLocalAccess.scalarUserType;
                    } else if(type instanceof TypeString) {
                        vla = VarLocalAccess.string;
                    } else {
                        vla = VarLocalAccess.scalar;
                    }
                }
            }
        }
        return vla;
    }

	@Override
	public Expression caseVariableExpression(VariableExpression var) {
		VarType t = findVariableType(var.getVariable());
		TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION,"VarType",t.name());
		TransUtil.copyNamespaceAnnotation(var, var.getVariable());
		VarAccess va = VarAccess.unknown;
		//Put the access annotation in the map, will be replicated in caseAction to all variables, var ref and exp refering to the same id
		if(currentWrite!=null) {
			switch(t) {
			case inOutPortVar:
			case inOutPortPeekVar:
			case outPortVar:
				va = findPortAccess(var.getType(),currentWrite.getRepeat()!=null,currentWrite.getExpressions().size());
				idOutVarAccessMap.put(var.getVariable().getId(), va.name());
			}
		}
		Declaration decl = UtilIR.getDeclarationTransformed(var.getVariable());
		VarLocalAccess vla = findLocalAccess(decl,var.getIndex(),var.getMember());
        TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION,"VarLocalAccess",vla.name());
		return super.caseVariableExpression(var);
	}
	
	@Override
	public VariableReference caseVariableReference(VariableReference var) {
		VarType t = findVariableType(var.getDeclaration());
		TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION,"VarType",t.name());
        TransUtil.copyNamespaceAnnotation(var, var.getDeclaration());
		VarAccess va = VarAccess.unknown;
		//Put the access annotation in the map, will be replicated in caseAction to all variables, var ref and exp refering to the same id
		if(currentRead!=null) {
			va = findPortAccess(var.getType(),currentRead.getRepeat()!=null,currentRead.getVariables().size());
			idInVarAccessMap.put(var.getDeclaration().getId(), va.name());
		}
        Declaration decl = UtilIR.getDeclarationTransformed(var.getDeclaration());
        VarLocalAccess vla = findLocalAccess(decl,var.getIndex(),var.getMember());
        TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION,"VarLocalAccess",vla.name());
		return super.caseVariableReference(var);
	}

    @Override
    public Statement caseProcCall(ProcCall call) {
        Declaration p = call.getProcedure() instanceof ForwardDeclaration?((ForwardDeclaration)call.getProcedure()).getDeclaration():call.getProcedure();
        VarType t = findVariableType(p);
        TransUtil.setAnnotation(p,IrTransformer.VARIABLE_ANNOTATION,"VarType",t.name());
        TransUtil.copyNamespaceAnnotation(call, p);
        return super.caseProcCall(call);
    }

    @Override
	public Declaration caseVariable(Variable var) {
		VarType t = findVariableType(var);
		TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION,"VarType",t.name());
        VarLiteral l = null;
        if(var.getInitValue() != null) {
            HowLiteral h = TransUtil.isLiteralExpression(var.getInitValue());
            if(h.total) {
                if(h.list) {
                    if(h.typeConstruct) {
                        l = VarLiteral.initializedListLitwTC;
                    } else {
                        if(h.containsListType) {
                            l = VarLiteral.initializedListLitwLVEoFC;
                        } else {
                            l = VarLiteral.initializedListLit;
                        }
                    }
                } else if(h.typeConstruct) {
                    l = VarLiteral.initializedTypeConstructLit;
                } else if(h.builtin) {
                    l = VarLiteral.initializedLit;
                }
            } else {
                if(h.typeConstruct) {
                    l = VarLiteral.initializedTypeConstruct;
                }
            }
            if(l != null) {
                TransUtil.setAnnotation(var.getInitValue(),IrTransformer.VARIABLE_ANNOTATION, 
                        "VarLiteral",l.name());
                VarLiteral current = VarLiteral.valueOf(TransUtil.getAnnotationArg(var, IrTransformer.VARIABLE_ANNOTATION, "VarLiteral"));
                switch(current) {
                case assignedLit:
                    l = VarLiteral.assignedInitLit;
                    break;
                case assignedListLit:
                    l = VarLiteral.assignedInitListLit;
                    break;
                case assignedListLitwTC:
                    l = VarLiteral.assignedInitListLitwTC;
                    break;
                case assignedListLitwLVEoFC:
                    l = VarLiteral.assignedInitListLitwLVEoFC;
                    break;
                case assignedTypeConstructLit:
                    l = VarLiteral.assignedInitTypeConstructLit;
                    break;
                case assignedTypeConstruct:
                    l = VarLiteral.assignedInitTypeConstruct;
                    break;
                case unknown:
                    break;
                default:
                    //>>>>> NB! leaving current function <<<<<<
                    return super.caseVariable(var);
                }
                TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION, 
                        "VarLiteral",l.name());
            }
        }
		return super.caseVariable(var);
	}
	
    @Override
    public EObject caseVariableImport(VariableImport var) {
        VarType t = findVariableType(var);
        TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION,"VarType",t.name());
        return super.caseVariableImport(var);
    }
    
    @Override
    public Declaration caseVariableExternal(VariableExternal var) {
        VarType t = findVariableType(var);
        TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION,"VarType",t.name());
        return super.caseVariableExternal(var);
    }
    
	@Override
	public TypeDeclaration caseTypeDeclaration(TypeDeclaration decl) {
		TransUtil.setAnnotation(decl,IrTransformer.VARIABLE_ANNOTATION,"VarType",VarType.declarationType.name());
		super.caseTypeDeclaration(decl);
		return decl;
	}
	
    @Override
    public Type caseTypeRecord(TypeRecord decl) {
        for(Declaration m : decl.getMembers()) {
            TransUtil.setAnnotation(m,IrTransformer.VARIABLE_ANNOTATION,"VarType",VarType.memberDeclType.name());
        }
        return decl;
    }

    @Override
	public AbstractActor caseActor(Actor obj) {
		currentActor = obj;
		AbstractActor ret = super.caseActor(obj);
		currentActor = null;
		return ret;
	}
	
	@Override
	public Action caseAction(Action obj) {
		currentAction = obj;
		Action ret = super.caseAction(obj);
		//Need to run over second time to copy VarAccess from definitions to variable, variable ref and expr
		new IrReplaceSwitch(){
			@Override
			public VariableReference caseVariableReference(VariableReference var) {
				if(idInVarAccessMap.containsKey(var.getDeclaration().getId()))
					TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION, 
							"VarAccessIn",idInVarAccessMap.get(var.getDeclaration().getId()));
				if(idOutVarAccessMap.containsKey(var.getDeclaration().getId()))
					TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION, 
							"VarAccessOut",idOutVarAccessMap.get(var.getDeclaration().getId()));
				return var;
			}
			
			@Override
			public VariableExpression caseVariableExpression(VariableExpression var) {
				if(idInVarAccessMap.containsKey(var.getVariable().getId()))
					TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION, 
							"VarAccessIn",idInVarAccessMap.get(var.getVariable().getId()));
				if(idOutVarAccessMap.containsKey(var.getVariable().getId()))
					TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION, 
							"VarAccessOut",idOutVarAccessMap.get(var.getVariable().getId()));
				return var;
			}
			
			@Override
			public Variable caseVariable(Variable var) {
				if(idInVarAccessMap.containsKey(var.getId()))
					TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION, 
							"VarAccessIn",idInVarAccessMap.get(var.getId()));
				if(idOutVarAccessMap.containsKey(var.getId()))
					TransUtil.setAnnotation(var,IrTransformer.VARIABLE_ANNOTATION, 
							"VarAccessOut",idOutVarAccessMap.get(var.getId()));
				return var;
			}

		}.doSwitch(obj);
		currentAction = null;
		return ret;
	}

	@Override
	public PortRead casePortRead(PortRead obj) {
		currentRead = obj;
		PortRead ret = super.casePortRead(obj);
		currentRead = null;
		return ret;
	}

	@Override
	public PortWrite casePortWrite(PortWrite obj) {
		currentWrite = obj;
		PortWrite ret = super.casePortWrite(obj);
		currentWrite = null;
		return ret;
	}

	@Override
	public Statement caseAssign(Assign assign) {
        boolean retValue = TransUtil.getAnnotationArg(assign.getTarget().getDeclaration(), IrTransformer.VARIABLE_ANNOTATION, "VarAssign").equals(IrVariableAnnotation.VarAssign.movedRetAssigned.name());
        if(!retValue) {
            TransUtil.setAnnotation(assign.getTarget().getDeclaration(),IrTransformer.VARIABLE_ANNOTATION, 
                    "VarAssign",VarAssign.assigned.name());
        }
        TransUtil.setAnnotation(assign.getTarget(),IrTransformer.VARIABLE_ANNOTATION, 
                "VarAssign",VarAssign.assigned.name());
        foundInSwitch=false;
        final String target = assign.getTarget().getDeclaration().getId();
        if(target != null) {
            new IrReplaceSwitch() {
                @Override
                public Declaration caseVariable(Variable var) {
                    if(target.equals(var.getId())) {
                        foundInSwitch = true;
                    }
                    return super.caseVariable(var);
                }
                @Override
                public Declaration caseForwardDeclaration(ForwardDeclaration decl) {
                    doSwitch(decl.getDeclaration());
                    return decl;
                }
            }.doSwitch(assign.getExpression());
            if(foundInSwitch) {
                TransUtil.setAnnotation(assign,IrTransformer.VARIABLE_ANNOTATION, 
                        "VarLocalAccess",VarLocalAccess.self.name());
            }
        }
        VarLiteral l = null;
        HowLiteral h = TransUtil.isLiteralExpression(assign.getExpression());
        if(h.total) {
            if(h.list) {
                if(h.typeConstruct) {
                    l = VarLiteral.initializedListLitwTC;
                } else {
                    if(h.containsListType) {
                        l = VarLiteral.initializedListLitwLVEoFC;
                    } else {
                        l = VarLiteral.initializedListLit;
                    }
                }
            } else if(h.literalTypeConstruct) {
                l = VarLiteral.assignedTypeConstructLit;
            } else if(h.builtin) {
                l = VarLiteral.assignedLit;
            }
        } else {
            if(h.typeConstruct) {
                l = VarLiteral.assignedTypeConstruct;
            }
        }
        if(l != null) {
            TransUtil.setAnnotation(assign.getTarget(),IrTransformer.VARIABLE_ANNOTATION, 
                    "VarLiteral",l.name());
            TransUtil.setAnnotation(assign.getExpression(),IrTransformer.VARIABLE_ANNOTATION, 
                    "VarLiteral",l.name());
            VarLiteral current = VarLiteral.valueOf(TransUtil.getAnnotationArg(assign.getTarget().getDeclaration(), IrTransformer.VARIABLE_ANNOTATION, "VarLiteral"));
            switch(current) {
            case initializedLit:
                l = VarLiteral.assignedInitLit;
                break;
            case initializedListLit:
                l = VarLiteral.assignedInitListLit;
                break;
            case initializedListLitwTC:
                l = VarLiteral.assignedInitListLitwTC;
                break;
            case initializedListLitwLVEoFC:
                l = VarLiteral.assignedInitListLitwLVEoFC;
                break;
            case initializedTypeConstructLit:
                l = VarLiteral.assignedInitTypeConstructLit;
                break;
            case initializedTypeConstruct:
                l = VarLiteral.assignedInitTypeConstruct;
                break;
            case unknown:
                break;
            default:
                //>>>>> NB! leaving current function <<<<<<
                return super.caseAssign(assign);
            }
            TransUtil.setAnnotation(assign.getTarget().getDeclaration(),IrTransformer.VARIABLE_ANNOTATION, 
                    "VarLiteral",l.name());
        }
		return super.caseAssign(assign);
	}

	@Override
	public AbstractActor caseNetwork(Network obj) {
		currentNetwork = obj;
		for(ActorInstance a : obj.getActors()) {
			AbstractActor actor=null;
			try {
				System.out.println("[IrAnnotateVariable] Read in actor instance '" + a.getName() + "' of class " + ((TypeActor) a.getType()).getName());
				actor = (AbstractActor) ActorDirectory.findTransformedActor(a);
			} catch (DirectoryException x) {
				//serr.println("[IrAnnotateVariable] Internal error could not get actor of type " + a.getType().toString());
			}
			if(actor!=null && !(actor instanceof ExternalActor)) {
				actor = (AbstractActor) doSwitch(actor);
				String path = TransUtil.getPath(actor);
				TransUtil.AnnotatePass(actor, IrPassTypes.Variable, "0");
				ActorDirectory.addTransformedActor(actor, a, path);
			}
		}
		AbstractActor ret = super.caseNetwork(obj);
		String path = TransUtil.getPath(ret);
		TransUtil.AnnotatePass(ret, IrPassTypes.Variable, "0");
		ActorDirectory.addTransformedActor(ret, null, path);
		currentNetwork = null;
		return ret;
	}
	
	@Override
	public Namespace caseNamespace(Namespace obj) {
		currentNamespace = obj;
		Namespace ret = super.caseNamespace(obj);
		currentNamespace = null;
		return ret;
	}

}