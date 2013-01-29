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

package org.caltoopia.visual.test;

import java.awt.Dimension;
import javax.swing.SwingUtilities;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.test.TestFrontEnd;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.visual.network.NetworkController;
import org.caltoopia.visual.network.NetworkView;
import com.mxgraph.view.mxGraph;

public class TestNetworkView {

	private TestFrontEnd mFrontEnd;
	private Network mNetwork;
	public String pathToDetectorFSMs;
	public TestNetworkView() {
		mFrontEnd=TestFrontEnd.createStandAlone();
	}
	
	public void test(String args[]) {
		if (mFrontEnd.parseCommandLine(args)) {
			ActorDirectory.initCompilation(mFrontEnd.getCompilationSession());
			mNetwork=mFrontEnd.elaborate();
			pathToDetectorFSMs = args[4];
			if (mNetwork!=null) {
				// Create user interface on the event-dispatching thread.
				try {
					SwingUtilities.invokeAndWait(new Runnable(){
						@Override
						public void run() {
							test(mNetwork);
						}						
					});
				} catch (Exception e) {
				}			
			}
		}
	}
	
	protected void test(Network network) {
		mxGraph graph=new mxGraph();
		NetworkView view=new NetworkView(graph);
		NetworkController ctrl=new NetworkController(view);
		
		view.setPreferredSize(new Dimension(800,600));
		ctrl.setNetwork(network);
		ctrl.createFrame();
		view.zoomToFit();
	}
			
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestNetworkView().test(args);
	}
}
