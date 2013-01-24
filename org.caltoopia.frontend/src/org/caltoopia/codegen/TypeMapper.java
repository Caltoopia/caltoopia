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

package org.caltoopia.codegen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.caltoopia.ast2ir.Instantiator;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrReplaceSwitch;

public class TypeMapper {
	private class tmc {
		public boolean inlined;
		public boolean deepInlined;
		public boolean onPort;
		public int count;
		public Set<String> actors;
		public TypeDeclaration type;
		public Type member;
		
		public tmc(boolean isInline, boolean isDeepInline) {
			this.inlined = isInline;
			this.deepInlined = isDeepInline;
			this.count = 0;
			this.actors = new HashSet<String>();
			this.onPort = false;
		}
		
		public tmc(boolean isInline, boolean isDeepInline, TypeDeclaration type, Type member) {
			this(isInline,isDeepInline);
			this.type = type;
			this.member = member;
		}
		
		public void visited() {
			this.count++;
			if(currentActor!=null)
				this.actors.add(currentActor.getNamespace() + currentActor.getName());
		}
		
		public void setInlined(boolean isInline) {
			this.inlined = isInline;			
		}

		public void setDeepInlined(boolean isInline) {
			this.deepInlined = isInline;			
		}
	}

	private static final String pstr = "____PORTTYPE";
	
	private TypeActor currentActor = null;
	private Map<String,tmc> typeMember = new HashMap<String,tmc>();
	
	private void updateTypeMemberInlined(String name, boolean isInlined) {
		tmc o = typeMember.get(name);
		if(o!=null) {
			o.visited();
			o.setInlined(isInlined);
			typeMember.put(name, o);
		}
	}
	
	private void updateTypeOnPort(String name, boolean onPort) {
		tmc o = typeMember.get(name);
		if(o!=null) {
			o.visited();
			o.onPort = onPort;
			typeMember.put(name, o);
		}
	}

	private void visited(String name) {
		if(typeMember.containsKey(name))
			typeMember.get(name).visited();
	}
	
	private void init() {
		//Decide default which struct members are by-ref and which are inline
		for(TypeDeclaration t : ActorDirectory.findAllTypeDeclarations()) {
			if(t.getType() instanceof TypeRecord) {
				typeMember.put(t.getName()+pstr, new tmc(true,true));
				for(Variable m : ((TypeRecord)t.getType()).getMembers()) {
					typeMember.put(t.getName()+"____"+m.getName(), new tmc(true,true,t,m.getType()));
				}
			}
		}
	}

	private void collect(Network n) {
		typeUsageSwitch s = new typeUsageSwitch();
		s.doSwitch(n);
	}
	
	private void scan() {
		Set<String> temp = typeMember.keySet();
		for(String t : temp) {
			if(!t.endsWith(pstr)) {
				tmc member = typeMember.get(t);
				Type type = member.member;
				while(type instanceof TypeList)
					type=((TypeList)type).getType();
				if(type instanceof TypeUser) {
					TypeDeclaration memberTdecl = UtilIR.getTypeDeclaration(type);
					tmc portTypeUsers = typeMember.get(memberTdecl.getName()+pstr);
					Set<String> common = new HashSet<String>();
					common.addAll(portTypeUsers.actors);
					common.retainAll(member.actors);
					//At least one actor have a port with this member's type and use the member
					//then we don't inline that member
					if(!common.isEmpty()) {
						member.setInlined(false);
						member.setDeepInlined(false);
						for(tmc parent : typeMember.values()) {
							if(parent.member instanceof TypeUser && UtilIR.getTypeDeclaration(parent.member).getId().equals(UtilIR.getTypeDeclaration(member.type).getId())) {
								parent.setDeepInlined(false);
							}
						}
					}
				}
			}
		}
	}
	
	public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
		List<T> list = new ArrayList<T>(c);
		java.util.Collections.sort(list);
		return list;
	}

	public void print() {
		Collection<String> temp = typeMember.keySet();
		List<String> sorted = asSortedList(temp);
		
		final String spaces = "                                                                              ";
		for(String t : sorted) {
			System.out.println(t + spaces.substring(max(t.length(),0)) 
					+ (typeMember.get(t).inlined?"Y":"N") 
					+ (typeMember.get(t).deepInlined?"Y":"N") 
					+ (typeMember.get(t).onPort?"Y":"N"));
		}
	}
	
	private int max(int length, int i) {
		return length<i?i:length;
	}

	private class typeUsageSwitch extends IrReplaceSwitch  {				
/*
		@Override
		public AbstractActor caseActor(Actor a) {
			currentActor = a;
			AbstractActor ret = super.caseActor(a);
			currentActor = null;
			return ret;
		}

		@Override
		public AbstractActor caseAbstractActor(AbstractActor a) {
			if(a instanceof Actor)
				currentActor = (Actor)a;
			AbstractActor ret = super.caseAbstractActor(a);
			currentActor = null;
			return ret;
		}
*/
		@Override
		public ActorInstance caseActorInstance(ActorInstance a) {
			AbstractActor actor = Instantiator.instantiate(a, null);
			ActorInstance ret = a;
			if(actor instanceof Actor) {
				currentActor = ((Actor)actor).getType();
				doSwitch(actor);
				currentActor = null;
			}
			return ret;
		}

		@Override
		public Port casePort(Port p) {
			Type t = p.getType();
			if(t instanceof TypeUser) {
				TypeDeclaration decl = UtilIR.getTypeDeclaration(t);
				if(decl.getType() instanceof TypeRecord) {
					updateTypeOnPort(decl.getName()+pstr, true);
				}
			}
			return super.casePort(p);
		}
		
		@Override
		public Expression caseVariableExpression(VariableExpression var) {
			Declaration decl = UtilIR.getDeclaration(var.getVariable());
			if(decl instanceof Variable) {
				Type t = ((Variable) decl).getType();
				while(t instanceof TypeList)
					t=((TypeList)t).getType();
				if(t instanceof TypeUser) {
					TypeDeclaration tdecl = UtilIR.getTypeDeclaration(t);
					if(tdecl.getType() instanceof TypeRecord && var.getMember()!=null && !var.getMember().isEmpty()) {
						for(Member m : var.getMember()) {
							visited(tdecl.getName() + "____" + m.getName());
							t = m.getType();
							while(t instanceof TypeList)
								t=((TypeList)t).getType();
							if(!(t instanceof TypeUser))
								break;
							tdecl = UtilIR.getTypeDeclaration(t);
						}
					}
				}
			}
			return super.caseVariableExpression(var);
		}
		@Override
		public VariableReference caseVariableReference(VariableReference var) {
			Variable decl = var.getDeclaration();
			Type t = decl.getType();
			while(t instanceof TypeList)
				t=((TypeList)t).getType();
			if(t instanceof TypeUser) {
				TypeDeclaration tdecl = UtilIR.getTypeDeclaration(t);
				if(tdecl.getType() instanceof TypeRecord && var.getMember()!=null && !var.getMember().isEmpty()) {
					for(Member m : var.getMember()) {
						visited(tdecl.getName() + "____" + m.getName());
						t = m.getType();
						while(t instanceof TypeList)
							t=((TypeList)t).getType();
						if(!(t instanceof TypeUser))
							break;
						tdecl = UtilIR.getTypeDeclaration(t);
					}
				}
			}
			return super.caseVariableReference(var);
		}
	}


	public TypeMapper(Network n) {
		init();
		collect(n);
		scan();
	}

	public boolean isMemberInlined(TypeDeclaration type, Variable member) {
		return typeMember.get(UtilIR.getTypeDeclaration(type).getName() + "____" + member.getName()).inlined;
	}
}