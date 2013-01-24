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

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.util.collections.Pair;
import org.caltoopia.visual.util.CustomGraphComponent;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

public class NetworkView extends CustomGraphComponent {

	private static final long serialVersionUID = 1L;
	private Map<ActorInstance,Object> mActor2Cell=new HashMap<ActorInstance,Object>();
	private Map<Object,ActorInstance> mCell2Actor=new HashMap<Object,ActorInstance>();

	public NetworkView(mxGraph graph) {
		super(graph);
		getGraphControl().addMouseListener(new MouseInputAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()==2) {
					Object cell=getCellAt(e.getX(), e.getY());
					if (cell!=null) {
						cellDoubleClicked(cell);
					}
				}
			}			
		});
	}

	public ActorInstance getActor(Object cell) {
		return mCell2Actor.get(cell);
	}

	public Object getCell(ActorInstance actor) {
		return mActor2Cell.get(actor);
	}
	
	public boolean isGroup(Object cell) {
		return getGraph().getModel().getChildCount(cell)>0;
	}
	
	public Collection<ActorInstance> getActorsInGroup(Object groupCell) {
		List<ActorInstance> actors=new ArrayList<ActorInstance>();
		for (Object cell: mxGraphModel.getChildVertices(getGraph().getModel(), groupCell)) {
			ActorInstance actor=mCell2Actor.get(cell);
			assert(actor!=null);
			actors.add(actor);
		}
		return actors;
	}
	
	public Object getGroup(Object cell) {
		Object defaultParent=getGraph().getDefaultParent();
		mxIGraphModel model=getGraph().getModel();
		Object parent=model.getParent(cell);
		return (parent!=defaultParent)? parent : cell;
	}
	
	public void doLayoutGraph(boolean animate)
	{
		doLayoutGraph(new mxHierarchicalLayout(graph, JLabel.WEST), animate);
	}
	
	public void doLayoutCell(Object cell, boolean animate) {
		doLayoutCell(cell, new mxHierarchicalLayout(graph, JLabel.WEST), animate);
	}
	
	public void setActorFillColor(ActorInstance actor, String colorCode) {
		Object cell=getCell(actor);
		assert(cell!=null);
		setCellFillColor(cell, colorCode);
	}
	
	public void setCellFillColor(Object cell, String colorCode) {
		Object cells[]={cell};
		getGraph().setCellStyles(mxConstants.STYLE_FILLCOLOR, colorCode,cells);
	}
	
	public Object group(Collection<ActorInstance> actors) {
		Object defaultParent=getGraph().getDefaultParent();
		mxIGraphModel model=getGraph().getModel();
		
		// Find actors and group cells
		Set<Object> subGroupCells=new HashSet<Object>();
		Set<Object> actorsAndEdges=new HashSet<Object>();
		for (ActorInstance actor: actors) {
			Object cell=mActor2Cell.get(actor);
			assert(cell!=null);
			
			actorsAndEdges.add(cell);
			
			// Also include edges between actors in the group
			for (Object edge: graph.getOutgoingEdges(cell)) {
				Object neighborCell=getTerminus(edge,cell,graph);
				ActorInstance neighborActor=mCell2Actor.get(neighborCell);
				if (actors.contains(neighborActor)) {
					actorsAndEdges.add(edge);
				}
			}

			// Find (sub-) groups that are grouped into the new cell  
			Object parent=model.getParent(cell);
			if (parent!=defaultParent) {
				subGroupCells.add(parent);
			}
		}
		
		// Ungroup possible sub-group cells
		if (!subGroupCells.isEmpty()) {
			getGraph().ungroupCells(subGroupCells.toArray());
		}
		
		// Form new group
		return getGraph().groupCells(null, /* create new group cell */
                                     10,   /* border */
                                     actorsAndEdges.toArray());
	}
	
	public void ungroup(Object cell) {
		Object cells[]={ cell };
		getGraph().ungroupCells(cells);
	}
	
	private Object getTerminus(Object edge, Object source, mxGraph graph) {
		Object edges[]={edge};
		Object terminus[]=graph.getOpposites(edges, source);
		assert(terminus.length==1);
		return terminus[0];
	}
	
	public Set<ActorInstance> getSelectedActors() {
		Object selectedCells[]=getGraph().getSelectionCells();
		Set<ActorInstance> selectedActors=new HashSet<ActorInstance>();
		for (Object cell: selectedCells) {
			ActorInstance actor=mCell2Actor.get(cell);
			if (actor!=null)
				selectedActors.add(actor);
			else {
				cell=getGroup(cell);
				if (isGroup(cell)) {
					selectedActors.addAll(getActorsInGroup(cell));
				}
			}
		}
		return selectedActors;
	}
	
	protected void cellDoubleClicked(Object cell) {
		// Default implementation does nothing
	}
	
	public void createNetwork(Network network) {
		mxGraph graph=getGraph();
		Object parent = graph.getDefaultParent();
		Set<Pair<Object,Object>> edgeSet=new HashSet<Pair<Object,Object>>();
		
		mActor2Cell=new HashMap<ActorInstance,Object>();
		mCell2Actor=new HashMap<Object,ActorInstance>();
		
		graph.getModel().beginUpdate();
		try
		{
			int i=0;
			for (ActorInstance actor: network.getActors()) {
				Object cell=graph.insertVertex(parent, null, actor.getInstanceName(), 20+50*i, 20+100*i, 80, 30);
				mActor2Cell.put(actor, cell);
				mCell2Actor.put(cell, actor);
			}
			for (Connection e: network.getConnections()) {
				Object u=mActor2Cell.get(e.getProducerPort().getActor());
				Object v=mActor2Cell.get(e.getConsumerPort().getActor());
				if (edgeSet.add(Pair.create(u, v))) {
					graph.insertEdge(parent, null, null, u, v);
				}
				// else: don't create parallel edges in network view
			}
		}
		finally
		{
			graph.getModel().endUpdate();
		}
	}
}
