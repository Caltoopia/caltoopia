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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * M5 Project Logo: a Penrose Tiling with five M:s distributed aperoidically.
 * Used as background in the M5 MappingTool
 */
public class M5Logo extends JPanel {
	private static final long serialVersionUID = 1L;
	private final double delta=Math.PI/5;
	private final double radius0=16;               // Scale factor, how large the smallest objects
	private final double phi=(1+Math.sqrt(5))/2;   // Golden ratio 1.618
	
	private final int DIAMONDS01234=0x1F;
	private final int DIAMONDS1234=0x1E;
	private final int DIAMONDS134=0x1A;
	private final int DIAMONDS124=0x16;
	private final int DIAMONDS023=0x0D;
	private final int C_TILE=0;
	private final int A_TILE=1;
	private final int L_TILE=2;
	private final int R_TILE=3;
	private final int D_TILE=4;
	private final int mDiamondSets[]={DIAMONDS01234, DIAMONDS1234, DIAMONDS124, DIAMONDS134, DIAMONDS023};
	private final int mSubTileTypes[][]={
			{A_TILE, A_TILE, A_TILE, A_TILE, A_TILE},
			{A_TILE, A_TILE, L_TILE, R_TILE, A_TILE},
			{L_TILE, R_TILE, L_TILE, R_TILE, A_TILE},
			{R_TILE, A_TILE, L_TILE, R_TILE, L_TILE},
			{D_TILE, R_TILE, L_TILE, R_TILE, L_TILE}
	};
	private Path2D.Double mOutLine;
	private Path2D.Double mPentagon;
	private Path2D.Double mMTile;
	private Color mBackground=new Color(0xEC,0xEC,0xEC);
		
	public M5Logo() {
		double x1=radius0*Math.cos(delta/2);
		double y1=radius0*Math.sin(delta/2);

		mOutLine=new Path2D.Double();
		mOutLine.moveTo(-x1, y1);
		mOutLine.lineTo(0,radius0);
		mOutLine.lineTo(x1, y1);
		
		double x2=radius0*Math.cos(3*delta/2);
		double y2=-radius0*Math.sin(3*delta/2);
		
		mPentagon=(Path2D.Double) mOutLine.clone();
		mPentagon.lineTo(x2,y2);
		mPentagon.lineTo(-x2,y2);
		mPentagon.lineTo(-x1,y1);

		double omega=2*Math.PI/5;
		double width=(double) radius0/4; /* y1*Math.sin(omega/2); */
		double t=Math.tan(omega/2);
		double y3=y2+x2/t;
		double y4=y3+width/Math.sin(omega/2);
		double dx=width*Math.cos(delta/2);
		double temp=Math.tan(delta/2);
		double y5=(y4*t+y2*temp-x2+dx)/(t+temp);
		double x5=(y4-y5)*t;
		
		mMTile=new Path2D.Double();
		mMTile.moveTo(x1, y1);
		mMTile.lineTo(x2,y2);
		mMTile.lineTo(0,y3);
		mMTile.lineTo(-x2,y2);
		mMTile.lineTo(-x1,y1);
		mMTile.lineTo(-x1+dx,y1);
		mMTile.lineTo(-x5,y5);
		mMTile.lineTo(0,y4);
		mMTile.lineTo(x5,y5);
		mMTile.lineTo(x1-dx,y1);
		mMTile.lineTo(x1,y1);

//		double dx=4*x2-2*x1;
//		double dy=dx/2/Math.tan(delta/2);
//		mMTile=new Path2D.Double();
//		mMTile.moveTo(0, y1-dy);
//		mMTile.lineTo(x2-dx,y2);
//		mMTile.lineTo(x2,y2);
//		mMTile.lineTo(x1,y1);
//		mMTile.lineTo(x1-dx,y1);
//		mMTile.lineTo(x2-dx/2,y2+dy);
//		mMTile.lineTo(dx/2,y1);
//		mMTile.lineTo(-dx/2,y1);
//		mMTile.lineTo(-x2+dx/2,y2+dy);
//		mMTile.lineTo(-x1+dx,y1);
//		mMTile.lineTo(-x1,y1);
//		mMTile.lineTo(-x2,y2);
//		mMTile.lineTo(-x2+dx,y2);
//		mMTile.lineTo(0, y1-dy);
		
		setBackground(mBackground);
	}
	
	private void paintPentagonTile(Graphics2D g, int index, double radius, int tileType) {
		double gap=radius*phi;
		int r=(int) (gap*phi);
		if (g.hitClip(-r, -r, 2*r, 2*r)) {
			double nextRadius=radius/(phi*phi);
			
			if (index>0) {
				// Paint center tile
				g.rotate(Math.PI);
				paintPentagonTile(g,index-1,nextRadius,C_TILE);
				
				// Paint diamonds
				int setOfDiamonds=mDiamondSets[tileType];
				for (int i=0; i<5; ++i) {
					g.translate(0, radius);

					if ((setOfDiamonds & (1<<i))!=0) {
						paintDiamond(g, index, radius);
					}
					else if (index>1) {
						paintDiamond(g, index-1, nextRadius);
					}
					g.translate(0, -radius);

					g.rotate(2*delta);
				}
				g.rotate(-Math.PI);
			}
			for (int i=0; i<5; ++i) {
				int subTileType[]=mSubTileTypes[tileType];
				// Paint surrounding tiles
				g.translate(0,gap);

				if (index==0) {
					if (tileType==C_TILE) {
						g.fill(mMTile);
						g.draw(mOutLine);
					}
					else {
						g.draw(mPentagon);
					}
				}
				else {
					paintPentagonTile(g,index-1,nextRadius,subTileType[i]);
				}

				g.translate(0,-gap);
				g.rotate(2*delta);
			}		
		}
	}
	
	private void paintDiamond(Graphics2D g, int index, double radius) {
		paintArm(g,index,radius);
		
		if (index>1) {
			double phi2=phi*phi;
			double nextRadius=radius/phi2;
			double r=nextRadius/phi2;
			double x1=nextRadius*Math.cos(delta/2);
			double y1=radius+nextRadius*Math.sin(delta/2);
			
			g.translate(x1, y1);
			g.rotate(delta);
			paintArm(g,index-1,nextRadius);
			g.rotate(-delta);
			
			g.translate(-2*x1, 0);
			
			g.rotate(-delta);
			paintArm(g,index-1,nextRadius);
			g.rotate(delta);
			g.translate(x1, -y1);
			
			if (index>2) {
				y1=radius+nextRadius;
				
				g.translate(0, y1);
				paintArm(g,index-2,r);
				g.translate(0, -y1);
			}
		}
	}
	
	private void paintArm(Graphics2D g, int index, double radius) {
		double nextRadius=radius/(phi*phi);
		while (index>1) {
			double r=nextRadius/(phi*phi);
			g.translate(0,radius);
			paintPentagonTile(g,index-2,r,D_TILE);
			g.translate(0,-radius);
			radius=nextRadius;
			nextRadius=r;
			--index;
		}
		g.translate(0,radius);
		g.draw(mPentagon);
		g.translate(0,-radius);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.white);
		
		int w=getWidth();
		int h=getHeight();
		int d2=w*w + h*h;
		double innerRadius=radius0*Math.cos(delta);
		int n=2*(int) Math.ceil(Math.log(d2/innerRadius/innerRadius)/8/Math.log(phi));
		double r=radius0*Math.pow(phi, 2*n);
		paintPentagonTile((Graphics2D) g, n, r, C_TILE);
	}

	private void createFrame() {
		JFrame frame = new JFrame("M5 Logo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create user interface on the event-dispatching thread.
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					M5Logo app=new M5Logo();
					app.createFrame();
				}
			});
		} catch (Exception e) {
		}				
	}
}