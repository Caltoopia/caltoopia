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

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

public interface Zoomable {

	void zoomIn();
	void zoomOut();
	void zoomToFit();
	
	public class ZoomInAction extends GuiAction {
		private static final long serialVersionUID = 1L;
		protected Zoomable mZoomable;
		
		public ZoomInAction(Zoomable zoomable) {
			super("Zoom In");
			mZoomable=zoomable;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mZoomable.zoomIn();
		}
		
		@Override
		public void addDefaultKeyBindings(JComponent component) {
			addKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.CTRL_DOWN_MASK),component);
			addKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, InputEvent.CTRL_DOWN_MASK),component);
		}		
	}

	public class ZoomOutAction extends GuiAction {
		private static final long serialVersionUID = 1L;
		protected Zoomable mZoomable;
		
		public ZoomOutAction(Zoomable zoomable) {
			super("Zoom Out");
			mZoomable=zoomable;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mZoomable.zoomOut();
		}
		
		@Override
		public void addDefaultKeyBindings(JComponent component) {
			addKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK),component);
			addKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, InputEvent.CTRL_DOWN_MASK),component);
		}
	}
	
	public class ZoomToFitAction extends GuiAction {
		private static final long serialVersionUID = 1L;
		protected Zoomable mZoomable;
		
		public ZoomToFitAction(Zoomable zoomable) {
			super("Zoom To Fit");
			mZoomable=zoomable;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mZoomable.zoomToFit();
		}
		
		@Override
		public void addDefaultKeyBindings(JComponent component) {
		}
	}
}
