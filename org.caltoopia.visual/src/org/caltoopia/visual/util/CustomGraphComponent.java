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
package org.caltoopia.visual.util;

import java.awt.Color;
import java.awt.Dimension;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;

public class CustomGraphComponent extends mxGraphComponent implements Zoomable {

	private static final long serialVersionUID = 1L;

	public CustomGraphComponent(mxGraph graph) {
		super(graph);

		getViewport().setOpaque(true);
		getViewport().setBackground(Color.white);

		setViewMode();
		this.setToolTips(true);
		
		getConnectionHandler().setEnabled(false);
		mxRubberband rubber=new mxRubberband(this);
	}
	
	public void setViewMode() {
		mxGraph graph=getGraph();
		
		graph.setCellsLocked(true);
		graph.setCellsBendable(false); /* doesn't stop edges from being bent... */
		
		// Implied by CellsLocked?
//		graph.setCellsEditable(false);
//		graph.setCellsResizable(false);
//		graph.setCellsCloneable(false);
//		graph.setCellsDisconnectable(false);
//		graph.setCellsDeletable(false);
	}
	
	public void setLayoutMode() {
		mxGraph graph=getGraph();
		
		graph.setCellsLocked(false);
		graph.setCellsBendable(true);
		graph.setCellsEditable(false);
		graph.setCellsResizable(false);
		graph.setCellsCloneable(false);
		graph.setCellsDisconnectable(false);
		graph.setCellsDeletable(false);
	}
	
	public void doLayoutGraph(mxIGraphLayout layout, boolean animate)
	{
//		mxGraph graph=getGraph();
//		Object cell = graph.getSelectionCell();
//
//		if (cell == null
//				|| graph.getModel().getChildCount(cell) == 0)
//		{
//			cell = graph.getDefaultParent();
//		}
		doLayoutCell(getGraph().getDefaultParent(), layout, animate);		
	}
	
	public void doLayoutCell(Object cell, mxIGraphLayout layout, boolean animate) {
		mxGraph graph = getGraph();
		
		assert(cell!=null);
		graph.getModel().beginUpdate();
		try
		{
			layout.execute(cell);
		}
		finally
		{
			if (animate) {
				animateAndEndUpdate();
			}
			else {
				graph.getModel().endUpdate();
			}
		}	
	}
	
	public void animateAndEndUpdate() {
		mxMorphing morph = new mxMorphing(this, 20,
				1.2, 20);

		morph.addListener(mxEvent.DONE, new mxIEventListener()
		{
			public void invoke(Object sender, mxEventObject evt)
			{
				mxGraph graph = getGraph();
				graph.getModel().endUpdate();
				CustomGraphComponent.this.repaint(); // Seems to be necessary to paint properly
			}
		});

		morph.startAnimation();
	}
	
	public void zoomToFit() {
		mxRectangle r=getGraph().getGraphBounds();
		Dimension d=getSize();
		double factor=Math.min(d.getWidth()/r.getWidth(), d.getHeight()/r.getHeight());
		
		zoom(factor);
	}
	
//	protected String getToolTipForCell(Object cell) {
//		Object value=getGraph().getModel().getValue(cell);
//		return (value!=null)? value.toString() : "";
//	}	
}
