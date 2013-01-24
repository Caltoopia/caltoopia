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

package org.caltoopia.frontend.ui.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CaltoopiaMainTab extends AbstractLaunchConfigurationTab {

	public static final String CONFIG_ID_TOP_NETWORK = "config_id_topnetwork";
	
	public static final String CONFIG_ID_PROJECT = "config_id_project";
	
	public static final String CONFIG_ID_OUTPUT_FOLDER = "config_id_output_folder";
	
	public static final String CONFIG_ID_PATHS = "config_id_paths";
	
	public static final String CONFIG_ID_VISUALIZE = "config_id_visualize";
	
	public static final String CONFIG_ID_DOT = "config_id_dot";

	public static final String CONFIG_ID_CDT = "config_id_cdt";
	
	public static final String CONFIG_ID_CALSIM = "config_id_calsim";
	
	public static final String CONFIG_ID_WORKING_FOLDER = "config_id_working_folder";
	
	public static final String CONFIG_ID_RUN_OPTIONS = "config_id_run_options";
	
	private static final String DEFAULT_TOP_NETWORK = "<select top network>";
	
	private static final String DEFAULT_PROJECT = "<select project>";
	
	private static final String DEFAULT_OUTPUT_FOLDER = "<select output folder>";
	
	public static final String CONFIG_ID_DEBUG = "config_id_debug";
	
	private static final String DEBUG_TYPE_NONE = "None";
	
	private static final String DEBUG_TYPE_USER = "User";
	
	private static final String DEBUG_TYPE_ACTIONUSER = "Actions & User";
	
	public static final String CONFIG_ID_ANALYSIS = "config_id_analysis";
	
	private static final String NONE_ANALYSIS = "None";
	
	private static final String STATIC_CLASSIFICATION= "Static (SDF/CSDF)";
	
	private static final String MCDF_CLASSIFICATION = "Dynamic (MCDF)";
	
	private static final String FSMSADF_CLASSIFICATION = "Dynamic (SADF)";
	
	private static final String STATIC_ANALYSIS = "SDF/CSDF with analysis";
	
	private static final String MCDF_ANALYSIS = "MCDF with analysis";
	
	private static final String FSMSADF_ANALYSIS = "FSMSADF with analysis";
	
    private Composite composite;
	
	private Text outputFolderText;
		
	private Combo projectCombo;

	private Combo debugCombo;
	
	private Combo analysisCombo;
	
	private Combo topCombo;
	
	private Button visualizeButton;

	private Button dotButton;

	private Button cdtButton;
	
	private Button calsimButton;
	
	private Text workingFolderText;
	
	private Text runOptionsText;	
			
	private Map<String, List<String>> projectPaths = new HashMap<String, List<String>>();
		
	public void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		setControl(composite);

		Font font = parent.getFont();
		composite.setFont(font);

		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 0;

		composite.setLayout(layout);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		composite.setLayoutData(data);
		
		final Group group = new Group(composite, SWT.NONE);
		group.setFont(font);
		group.setText("&Options:");
		group.setLayout(new GridLayout(4, false));
		data = new GridData(SWT.FILL, SWT.TOP, true, false);
		group.setLayoutData(data);
		
		outputFolderText = createText(group, "Output folder", DEFAULT_OUTPUT_FOLDER, 2);
		createFolderSelection(group, outputFolderText);
        
		Label projectLbl = new Label(group, SWT.NONE);
	    projectLbl.setFont(composite.getFont());
	    projectLbl.setText("Project: ");
		data = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		projectLbl.setLayoutData(data);
				 
		projectCombo = new Combo(group, SWT.VERTICAL | SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		
        Label topLbl = new Label(group, SWT.NONE);
	    topLbl.setFont(composite.getFont());
	    topLbl.setText("Top Network: ");
		data = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		topLbl.setLayoutData(data);

		topCombo = new Combo(group, SWT.DROP_DOWN | SWT.READ_ONLY);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint=100;
		data.grabExcessHorizontalSpace = true;
		topCombo.setLayoutData(data);
		topCombo.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {            
          	  	updateLaunchConfigurationDialog();
            }
          });
        topCombo.setText(DEFAULT_TOP_NETWORK);
        topCombo.pack();
                             

        initProjectCombo();
       
        topCombo.add("No project selected");
   
        projectCombo.select(0);
              
        projectCombo.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {          
            	initTopCombo(projectCombo.getText());
          	  	updateLaunchConfigurationDialog();
            }
          });
        projectCombo.pack(true);
        
		visualizeButton = new Button(group, SWT.CHECK);
		visualizeButton.setText("Show Graph");
		visualizeButton.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				updateLaunchConfigurationDialog();					
			}

			public void widgetDefaultSelected(SelectionEvent e) {}
		});		
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 1;
		visualizeButton.setLayoutData(data);

		dotButton = new Button(group, SWT.CHECK);
		dotButton.setText("Generate dot-file");
		dotButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				updateLaunchConfigurationDialog();					
			}

			public void widgetDefaultSelected(SelectionEvent e) {}
		});		
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 1;
		dotButton.setLayoutData(data);

		cdtButton = new Button(group, SWT.CHECK);
		cdtButton.setText("Create CDT Project");
		cdtButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				updateLaunchConfigurationDialog();
			}

			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 1;
		cdtButton.setLayoutData(data);		
        
		calsimButton = new Button(group, SWT.CHECK);
		calsimButton.setText("Simulation");
		calsimButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				updateLaunchConfigurationDialog();
			}

			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 1;
		calsimButton.setLayoutData(data);
		
		workingFolderText = createText(group, "Working folder:", "", 2);
		createFolderSelection(group, workingFolderText);
		runOptionsText = createText(group, "Run options:", "", 3);
		
		Label debugLbl = new Label(group, SWT.LEFT);
	    debugLbl.setFont(composite.getFont());
	    debugLbl.setText("Debug: ");
		data.horizontalSpan = 1;
		data = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		debugLbl.setLayoutData(data);
				 
		debugCombo = new Combo(group, SWT.VERTICAL | SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);

        initDebugCombo();	
		debugCombo.addListener(SWT.Selection, new Listener() {
		    public void handleEvent(Event e) {            
		  	  	updateLaunchConfigurationDialog();
		    }
		  });


        Label analysisLbl = new Label(group, SWT.LEFT);
        analysisLbl.setFont(composite.getFont());
        analysisLbl.setText("Classification and Analysis:");
		data.horizontalSpan = 1;
		data = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		analysisLbl.setLayoutData(data);
				 
		analysisCombo = new Combo(group, SWT.VERTICAL | SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
        initAnalysisCombo();
        analysisCombo.addListener(SWT.Selection, new Listener() {
		    public void handleEvent(Event e) {            
		  	  	updateLaunchConfigurationDialog();
		    }
		  });

        group.pack(true);
	}
	
	protected void initTopCombo(String text) {    	
    	String projectName = text;            	
    	topCombo.removeAll();	  
    	if (projectPaths.get(projectName) != null ) {
    		for (String top : ActorDirectory.getTopNetworks(projectPaths.get(projectName).get(0))) {
  	  			topCombo.add(top);
  	  		}
    	}
	}

	private Text createText(Group group, String label, String defaultValue, int span) {
		Label lbl = new Label(group, SWT.NONE);
		lbl.setFont(composite.getFont());
		lbl.setText(label);
		GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		lbl.setLayoutData(data);

		Text text = new Text(group, SWT.BORDER | SWT.SINGLE);
		text.setFont(composite.getFont());
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = span;
		text.setLayoutData(data);
		text.setText(defaultValue);
		return text;
	}
	
	private void createFolderSelection(final Group group, final Text text) {
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();				
			}		
		});

		Button buttonBrowse = new Button(group, SWT.PUSH);
		buttonBrowse.setFont(composite.getFont());
		GridData data = new GridData(SWT.FILL, SWT.CENTER, false, false);
		buttonBrowse.setLayoutData(data);
		buttonBrowse.setText("&Browse...");
		
		buttonBrowse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				browseFolder(group.getShell(), text);
			}
		});
	}
	
	private void browseFolder(Shell shell, final Text text) {
		DirectoryDialog dialog = new DirectoryDialog(shell, SWT.NONE);
		dialog.setMessage("Select output folder:");
		if (getFolderFromText()) {
			dialog.setFilterPath(text.getText());
		}

		String dir = dialog.open();
		if (dir != null) {
			text.setText(dir);
			updateLaunchConfigurationDialog();
		}
	}
	
	private void initProjectCombo() {
        IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();           
        projectCombo.removeAll();
 
        for (IProject proj : myWorkspaceRoot.getProjects()) {  
        	if (proj.isOpen()) {
        		List<String> paths = new ArrayList<String>();
                        
        		System.out.print("Project: " + proj.getName());
        		System.out.print(" [" + proj.getLocation() + "]");
        		System.out.println();
        	
        		paths.add(proj.getLocation().toString());
        		try {
        			for (IProject refProj : proj.getReferencedProjects()) {
        				System.out.print("		Referenced: " + refProj.getName());
        				System.out.print(" [" + refProj.getLocation() + "]");
        				System.out.println();            
        				paths.add(refProj.getLocation().toString());
        			}
        		
        		} catch (Exception x) {
        		
        		}
        		projectCombo.add(proj.getName());        
        		projectPaths.put(proj.getName(), paths);
        	}
        }
        projectCombo.setText(DEFAULT_PROJECT);
	}
	
	private void initDebugCombo() {
        debugCombo.removeAll();
        //Order important!!!
        debugCombo.add(DEBUG_TYPE_NONE); 		//0
        debugCombo.add(DEBUG_TYPE_USER); 		//1
        debugCombo.add(DEBUG_TYPE_ACTIONUSER); 	//2
        debugCombo.select(CompilationSession.DEBUG_TYPE_DEFAULT);
	}
	
	private void initAnalysisCombo() {
        analysisCombo.removeAll();
        //Order important!!!
        analysisCombo.add(NONE_ANALYSIS); 			//0
        analysisCombo.add(STATIC_CLASSIFICATION); 	//1
        analysisCombo.add(MCDF_CLASSIFICATION); 	//2
        analysisCombo.add(FSMSADF_CLASSIFICATION); 	//3
        analysisCombo.add(STATIC_ANALYSIS); 		//4
        analysisCombo.add(MCDF_ANALYSIS); 			//5
        analysisCombo.add(FSMSADF_ANALYSIS); 		//6
        analysisCombo.select(0);
	}
	
	private boolean getFolderFromText() {
		String value = outputFolderText.getText();
		File file = new File(value);
		if (file.isDirectory()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(CONFIG_ID_OUTPUT_FOLDER, DEFAULT_OUTPUT_FOLDER);
		configuration.setAttribute(CONFIG_ID_PROJECT, DEFAULT_PROJECT);
		configuration.setAttribute(CONFIG_ID_TOP_NETWORK, DEFAULT_TOP_NETWORK);
		configuration.setAttribute(CONFIG_ID_VISUALIZE, false);
		configuration.setAttribute(CONFIG_ID_CDT, false);
		configuration.setAttribute(CONFIG_ID_DOT, false);
		configuration.setAttribute(CONFIG_ID_CALSIM, false);
		configuration.setAttribute(CONFIG_ID_DEBUG, CompilationSession.DEBUG_TYPE_DEFAULT);
		configuration.setAttribute(CONFIG_ID_ANALYSIS, 0);
	}

	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			outputFolderText.setText(configuration.getAttribute(CONFIG_ID_OUTPUT_FOLDER, DEFAULT_OUTPUT_FOLDER));
			initProjectCombo();
			projectCombo.setText(configuration.getAttribute(CONFIG_ID_PROJECT, DEFAULT_PROJECT));
			String top = configuration.getAttribute(CONFIG_ID_TOP_NETWORK, DEFAULT_TOP_NETWORK);			
        	initTopCombo(projectCombo.getText());
        	topCombo.setText(top);
        	
			visualizeButton.setSelection(configuration.getAttribute(CONFIG_ID_VISUALIZE, false));
			cdtButton.setSelection(configuration.getAttribute(CONFIG_ID_CDT, false));
			dotButton.setSelection(configuration.getAttribute(CONFIG_ID_DOT, false));
			calsimButton.setSelection(configuration.getAttribute(CONFIG_ID_CALSIM, false));
			workingFolderText.setText(configuration.getAttribute(CONFIG_ID_WORKING_FOLDER, ""));
			runOptionsText.setText(configuration.getAttribute(CONFIG_ID_RUN_OPTIONS, ""));
			debugCombo.select(configuration.getAttribute(CONFIG_ID_DEBUG, CompilationSession.DEBUG_TYPE_DEFAULT));
			analysisCombo.select(configuration.getAttribute(CONFIG_ID_ANALYSIS, 0));
		} catch (Exception x) {
			this.setErrorMessage(x.getLocalizedMessage());
		}
	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(CONFIG_ID_OUTPUT_FOLDER, outputFolderText.getText());
		configuration.setAttribute(CONFIG_ID_PROJECT, projectCombo.getText());
		configuration.setAttribute(CONFIG_ID_TOP_NETWORK, topCombo.getText());
		configuration.setAttribute(CONFIG_ID_DEBUG, debugCombo.getSelectionIndex());
		configuration.setAttribute(CONFIG_ID_ANALYSIS, analysisCombo.getSelectionIndex());
		configuration.setAttribute(CONFIG_ID_PATHS, projectPaths.get(projectCombo.getText()));
		configuration.setAttribute(CONFIG_ID_VISUALIZE, visualizeButton.getSelection());
		configuration.setAttribute(CONFIG_ID_DOT, dotButton.getSelection());
		configuration.setAttribute(CONFIG_ID_CDT, cdtButton.getSelection());
		configuration.setAttribute(CONFIG_ID_CALSIM, calsimButton.getSelection());
		configuration.setAttribute(CONFIG_ID_WORKING_FOLDER, workingFolderText.getText());
		configuration.setAttribute(CONFIG_ID_RUN_OPTIONS, runOptionsText.getText());
	}

	public boolean isValid(ILaunchConfiguration configuration) {
		this.setErrorMessage(null); //Start by reseting the error message
		
		try {
			String outputFolder = configuration.getAttribute(CONFIG_ID_OUTPUT_FOLDER, DEFAULT_OUTPUT_FOLDER);
			if (outputFolder.equals(DEFAULT_OUTPUT_FOLDER) || outputFolder.equals("")) {
				this.setErrorMessage("Invalid entry for 'output folder'!");
				return false;
			}
			String projectName = configuration.getAttribute(CONFIG_ID_PROJECT, DEFAULT_PROJECT);
			if (projectName.equals(DEFAULT_PROJECT) || projectName.equals("")) {
				this.setErrorMessage("Invalid entry for 'project'!");
				return false;
			}
			String topNetwork = configuration.getAttribute(CONFIG_ID_TOP_NETWORK, DEFAULT_TOP_NETWORK);
			if (topNetwork.equals(DEFAULT_TOP_NETWORK) || topNetwork.equals("")) {
				this.setErrorMessage("Invalid entry for 'top network'!");
				return false;
			}
			return true;
		} catch (Exception x) {
			this.setErrorMessage(x.getLocalizedMessage());
			return false;
		}
	}
	
	public boolean canSave() {
		boolean result = true; //Start by reseting the error message
		this.setErrorMessage(null);
		
		if (outputFolderText.getText().equals(DEFAULT_OUTPUT_FOLDER) || outputFolderText.getText().isEmpty()) {
			this.setErrorMessage("Invalid entry for 'output folder'!");
			result = false;
		} 
		if (projectCombo.getText().equals(DEFAULT_PROJECT) || projectCombo.getText().isEmpty()) {
			this.setErrorMessage("Invalid entry for 'project'!");
			result = false;
		}
		if (topCombo.getText().equals(DEFAULT_TOP_NETWORK) || topCombo.getText().isEmpty()) {
			this.setErrorMessage("Invalid entry for 'top network'!");
			result = false;
		}
		
		return result;		
	}
	
	public String getName() {
		return "Compile and Run";
	}

}
