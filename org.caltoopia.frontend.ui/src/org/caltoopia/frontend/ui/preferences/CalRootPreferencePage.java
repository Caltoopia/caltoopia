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

package org.caltoopia.frontend.ui.preferences;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.xtext.ui.editor.preferences.LanguageRootPreferencePage;

public class CalRootPreferencePage extends LanguageRootPreferencePage {

	public static final String RUNTIME_ENVIRONMENT_VARIABLE = "CALTOOPIA_RUNTIME_HOME";
	
    public static final String CALVIN_RUNTIME_ENVIRONMENT_VARIABLE = "CALTOOPIA_CALVIN_RUNTIME_HOME";

    public static final String CALSIM_ENVIRONMENT_VARIABLE = "CALSIM_HOME";
	
	public static final String SYSTEMC_ENVIRONMENT_VARIABLE = "SYSTEMC_HOME";
	
	public static final String SDF3_ENVIRONMENT_VARIABLE = "SDF3_HOME";
	
	@Override
	protected void createFieldEditors() {
        addField(new StringButtonFieldEditor("rts", "&Run-time installation:", getFieldEditorParent()) {
            protected String changePressed() {
                return browseFolder(this.getShell());
            }
        });
        
        addField(new StringButtonFieldEditor("calvinrts", "&Calvin run-time installation:", getFieldEditorParent()) {
            protected String changePressed() {
                return browseFolder(this.getShell());
            }
        });
        
		addField(new StringButtonFieldEditor("calsim", "&CALSim installation:", getFieldEditorParent()) {
			protected String changePressed() {
				return browseFolder(this.getShell());
			}
		});

		addField(new StringButtonFieldEditor("systemc", "&SystemC installation:", getFieldEditorParent()) {
			protected String changePressed() {
				return browseFolder(this.getShell());
			}
		});
		addField(new StringButtonFieldEditor("sdf3", "&SDF3 installation:", getFieldEditorParent()) {
			protected String changePressed() {
				return browseFolder(this.getShell());
			}
		});
	}
	
	@Override
	public void init(IWorkbench workbench) {
        getPreferenceStore().setDefault("rts", RUNTIME_ENVIRONMENT_VARIABLE);
        getPreferenceStore().setDefault("calvinrts", CALVIN_RUNTIME_ENVIRONMENT_VARIABLE);
	    getPreferenceStore().setDefault("calsim", CALSIM_ENVIRONMENT_VARIABLE);
	    getPreferenceStore().setDefault("systemc", SYSTEMC_ENVIRONMENT_VARIABLE);
	    getPreferenceStore().setDefault("sdf3", SDF3_ENVIRONMENT_VARIABLE);
	}
	
	private String browseFolder(Shell shell) {
		DirectoryDialog dialog = new DirectoryDialog(shell, SWT.NONE);
		dialog.setMessage("Select output folder:");		
		return dialog.open();
	}
}
