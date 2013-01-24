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

package org.caltoopia.visual.network;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.caltoopia.analysis.air.Network;
import org.caltoopia.visual.util.GuiAction;
import org.caltoopia.visual.util.GuiActionKit;
import org.caltoopia.visual.util.Zoomable.ZoomInAction;
import org.caltoopia.visual.util.Zoomable.ZoomOutAction;
import org.caltoopia.visual.util.Zoomable.ZoomToFitAction;


/**
 * The controller of a model-view-controller "triad". The Model is an AIR Network object
 * and the view is a NetworkView.
 */
public class NetworkController {

	private Network mNetworkModel;
	private NetworkView mNetworkView;
	private JFrame mAppFrame;
	protected GuiActionKit mActions;

	public NetworkController(NetworkView view) {
		mNetworkView=view;
	}
	
	/**
	 * @param network
	 * Sets the network, creates the view and updates the rendering of the view
	 */
	public void setNetwork(Network network) {
		// TODO: what if changing networks...
		if (mNetworkModel!=null) {
		}
		
		mNetworkModel=network;
		
		if (mNetworkView!=null) {
			mNetworkView.createNetwork(network);
			mNetworkView.doLayoutGraph(false);
			if (mNetworkView.getWidth()<100 || mNetworkView.getHeight()<100) {
				// Way to small viewport
				int newWidth=Math.min(mNetworkView.getWidth(), 400);
				int newHeight=Math.min(mNetworkView.getHeight(), 320);
				mNetworkView.setSize(new Dimension(newWidth,newHeight));
			}
			mNetworkView.zoomToFit();
		}
		
		if (mAppFrame!=null) {
			mAppFrame.setTitle(getTitle());
		}
		
		if (mActions!=null) {
			mActions.stateChanged();
		}
	}

	public Network getNetworkModel() {
		return mNetworkModel;
	}
	
	public NetworkView getNetworkView() {
		return mNetworkView;
	}
	
	public GuiActionKit getActions() {
		return mActions;
	}
	
	public JFrame createFrame() {
		mAppFrame=new JFrame(getTitle());
		mAppFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mAppFrame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosed(WindowEvent e) {
			}
		});
		mAppFrame.setContentPane(createGUI());
		mAppFrame.pack();
		mAppFrame.setVisible(true);
		
		return mAppFrame;
	}
	
	protected JPanel createGUI() {
		JPanel pane=new JPanel();
		pane.setLayout(new BorderLayout());
		
		pane.add(mNetworkView, BorderLayout.CENTER);

		mActions=new GuiActionKit(pane);
		createActions();
	
		JComponent toolbar=createToolbar();
		if (toolbar!=null)
			pane.add(toolbar, BorderLayout.NORTH);
		
		JComponent statusLine=createStatusLine();
		if (statusLine!=null)
			pane.add(createStatusLine(), BorderLayout.SOUTH);
		
		return pane;
	}
	
	
	protected void createActions() {
		GuiAction action=new ZoomInAction(mNetworkView){
 			private static final long serialVersionUID = 4667934474532319123L;

			@Override
			public void stateChanged() {
				mZoomable=mNetworkView;
				setEnabled(mZoomable!=null);
			}
		};
		action.stateChanged();
		mActions.add(action);
		
		action=new ZoomOutAction(mNetworkView){
			private static final long serialVersionUID = -3474663316738385290L;

			@Override
			public void stateChanged() {
				mZoomable=mNetworkView;
				setEnabled(mZoomable!=null);
			}
		};
		action.stateChanged();
		mActions.add(action);
		
		action=new ZoomToFitAction(mNetworkView){
			private static final long serialVersionUID = -4848527129152742414L;

			@Override
			public void stateChanged() {
				mZoomable=mNetworkView;
				setEnabled(mZoomable!=null);
			}
		};
		action.stateChanged();
		mActions.add(action);
		
		// TODO: Move to caller
		mActions.addDefaultKeyBindings();
	}
	
	protected JComponent createToolbar() {		
		JPanel toolbar=new JPanel();
		
		for (GuiAction action: mActions) {
			JButton button=new JButton(action);
			toolbar.add(button);
		}
		
		return toolbar;
	}
	
	public void classify() {
		
	}
	protected JComponent createStatusLine() {
		return null;
	}	
	
	protected String getTitle() {
		return (mNetworkModel!=null)? mNetworkModel.getName()+" - Dataflow Network" : "Dataflow Network";
	}
}
