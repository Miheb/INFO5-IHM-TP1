package com.uga.zj;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

public class RangeSliderUI extends BasicSliderUI {

	// color of the line between both thumbs
	public Color diffColor = Color.GRAY;
	public boolean uppSel;// high tumb selected
	public Rectangle rec_upp;// rec upper thumb
	public transient boolean loweDrag,uppDrag;

	public RangeSliderUI(RangeSlider b) {
		super(b);
	}

	@Override
	public void installUI(JComponent c) {
		rec_upp = new Rectangle();
		super.installUI(c);
	}

	@Override
	protected TrackListener createTrackListener(JSlider slider) {
		return new RangeTrackListener();
	}

	@Override
	protected ChangeListener createChangeListener(JSlider slider) {
		return new ChangeHandler();
	}
	// same taille like the other thumb
	@Override
	protected void calculateThumbSize() {
		super.calculateThumbSize();
		rec_upp.setSize(thumbRect.width, thumbRect.height );
	}

	@Override
	protected void calculateThumbLocation() {
		super.calculateThumbLocation();
		if ( slider.getOrientation() == JSlider.HORIZONTAL ) {
			rec_upp.x = xPositionForValue(slider.getValue() + slider.getExtent()) - (rec_upp.width / 2);
			rec_upp.y = trackRect.y;
		}
		else {
			rec_upp.x = trackRect.x;
			rec_upp.y = yPositionForValue(slider.getValue() + slider.getExtent()) - (rec_upp.height / 2);
		}
		if (slider.getSnapToTicks()) {
			int valueofsnap = slider.getValue()+ slider.getExtent();
			int tickSpacing = 0;
			if (slider.getMinorTickSpacing() > 0) {
				tickSpacing = slider.getMinorTickSpacing();
			} else if (slider.getMajorTickSpacing() > 0) {
				tickSpacing = slider.getMajorTickSpacing();
			}
			if (tickSpacing!=0) {
				if( valueofsnap != (slider.getValue()+ slider.getExtent()) ) {
					slider.setExtent(valueofsnap - slider.getValue());
				}
				if ( (slider.getValue()+ slider.getExtent() - slider.getMinimum()) % tickSpacing != 0 ) {
					valueofsnap =   (Math.round((float)(slider.getValue()+ slider.getExtent() - slider.getMinimum()) / (float)tickSpacing) * tickSpacing)+ slider.getMinimum();
				}
			}
		}
	}

	@Override
	public void paint( Graphics g, JComponent c )   {
		super.paint(g,c);
		if(uppSel) {
			if (g.getClipBounds().intersects(rec_upp)) {
				paintUpperThumb(g);
			}
			if (g.getClipBounds().intersects(thumbRect)) {
				paintLowerThumb(g);
			}
		}
		else {
			if (g.getClipBounds().intersects(rec_upp)) {
				paintUpperThumb(g);
			}
			if (g.getClipBounds().intersects(thumbRect)) {
				paintLowerThumb(g);
			}
		}
	}

	@Override
	protected Dimension getThumbSize() {
		return new Dimension(17, 20);
	}

	public void paintTrack(Graphics g) {
		super.paintTrack(g);
		int cy = (trackRect.height / 2) ;
		Color Anciencouleur = g.getColor();
		g.translate(trackRect.x, trackRect.y + cy);
		g.setColor(diffColor);
		int i = 0 ;
		for(i=0;i<4;i++) {
			g.drawLine(thumbRect.x + (thumbRect.width / 2) - trackRect.x, i, rec_upp.x + (rec_upp.width / 2) - trackRect.x, i);

		}
		g.translate(-trackRect.x, -(trackRect.y + cy));
		g.setColor(Anciencouleur);
	}


	public void paintLowerThumb(Graphics g)  {

		g.translate(thumbRect.x, thumbRect.y);
		if ( slider.isEnabled() ) {
			g.setColor(Color.WHITE);
		}


		if ( slider.getOrientation() == JSlider.HORIZONTAL ) {
			g.fillRect(1, 1, thumbRect.width-3, thumbRect.height-1-thumbRect.width / 2);
			Polygon polygon = new Polygon();
			polygon.addPoint(1, thumbRect.height-thumbRect.width / 2);
			polygon.addPoint(thumbRect.width / 2-1, thumbRect.height-1);
			polygon.addPoint(thumbRect.width-2, thumbRect.height-1-thumbRect.width / 2);
			g.fillPolygon(polygon);

			g.drawLine(0, 0, thumbRect.width-2, 0);
			g.drawLine(0, 1, 0, thumbRect.height-1-thumbRect.width / 2);
			g.drawLine(0, thumbRect.height-thumbRect.width / 2, thumbRect.width / 2-1, thumbRect.height-1);

			g.setColor(Color.blue);
			g.drawLine(thumbRect.width-1, 0, thumbRect.width-1, thumbRect.height-2-thumbRect.width / 2);
			g.drawLine(thumbRect.width-1, thumbRect.height-1-thumbRect.width / 2, thumbRect.width-1-thumbRect.width / 2, thumbRect.height-1);

			g.setColor(getShadowColor());
			g.drawLine(thumbRect.width-2, 1, thumbRect.width-2, thumbRect.height-2-thumbRect.width / 2);
			g.drawLine(thumbRect.width-2, thumbRect.height-1-thumbRect.width / 2, thumbRect.width-1-thumbRect.width / 2, thumbRect.height-2);
		}
		else {  // vertical

			g.fillRect(1, 1, thumbRect.width-1-thumbRect.height / 2, thumbRect.height-3);
			Polygon polygon = new Polygon();
			polygon.addPoint(thumbRect.width-thumbRect.height / 2-1, 0);
			polygon.addPoint(thumbRect.width-1, thumbRect.height / 2);
			polygon.addPoint(thumbRect.width-1-thumbRect.height / 2, thumbRect.height-2);
			g.fillPolygon(polygon);
			g.drawLine(0, 0, 0, thumbRect.height - 2);
			g.drawLine(1, 0, thumbRect.width-1-thumbRect.height / 2, 0);
			g.drawLine(thumbRect.width-thumbRect.height / 2-1, 0, thumbRect.width-1, thumbRect.height / 2);
			g.setColor(Color.blue);
			g.drawLine(0, thumbRect.height-1, thumbRect.width-2-thumbRect.height / 2, thumbRect.height-1);
			g.drawLine(thumbRect.width-1-thumbRect.height / 2, thumbRect.height-1, thumbRect.width-1, thumbRect.height-1-thumbRect.height / 2);
			g.setColor(getShadowColor());
			g.drawLine(1, thumbRect.height-2, thumbRect.width-2-thumbRect.height / 2,  thumbRect.height-2 );
			g.drawLine(thumbRect.width-1-thumbRect.height / 2, thumbRect.height-2, thumbRect.width-2, thumbRect.height-thumbRect.height / 2-1 );
		}
		g.translate(-thumbRect.x, -thumbRect.y);
	}

	public void paintUpperThumb(Graphics g)  {

		g.translate(rec_upp.x, rec_upp.y);
		if ( slider.isEnabled() ) {
			g.setColor(Color.WHITE);
		}

		if ( slider.getOrientation() == JSlider.HORIZONTAL ) {
			g.fillRect(1, 1, rec_upp.width-3, rec_upp.height-1-rec_upp.width / 2);
			Polygon p = new Polygon();
			p.addPoint(1, rec_upp.height-rec_upp.width / 2);
			p.addPoint(rec_upp.width / 2-1, rec_upp.height-1);
			p.addPoint(rec_upp.width-2, rec_upp.height-1-rec_upp.width / 2);
			g.fillPolygon(p);
			g.drawLine(0, 0, rec_upp.width-2, 0);
			g.drawLine(0, 1, 0, rec_upp.height-1-rec_upp.width / 2);
			g.drawLine(0, rec_upp.height-rec_upp.width / 2, rec_upp.width / 2-1, rec_upp.height-1);
			g.setColor(Color.black);
			g.drawLine(rec_upp.width-1, 0, rec_upp.width-1, rec_upp.height-2-rec_upp.width / 2);
			g.drawLine(rec_upp.width-1, rec_upp.height-1-rec_upp.width / 2, rec_upp.width-1-rec_upp.width / 2, rec_upp.height-1);
			g.setColor(getShadowColor());
			g.drawLine(rec_upp.width-2, 1, rec_upp.width-2, rec_upp.height-2-rec_upp.width / 2);
			g.drawLine(rec_upp.width-2, rec_upp.height-1-rec_upp.width / 2, rec_upp.width-1-rec_upp.width / 2, rec_upp.height-2);
		}
		else {
			g.fillRect(1, 1, rec_upp.width-1-rec_upp.height / 2, rec_upp.height-3);
			Polygon p = new Polygon();
			p.addPoint(rec_upp.width-rec_upp.height / 2-1, 0);
			p.addPoint(rec_upp.width-1, rec_upp.height / 2);
			p.addPoint(rec_upp.width-1-rec_upp.height / 2, rec_upp.height-2);
			g.fillPolygon(p);
			g.drawLine(0, 0, 0, rec_upp.height - 2);
			g.drawLine(1, 0, rec_upp.width-1-rec_upp.height / 2, 0);
			g.drawLine(rec_upp.width-rec_upp.height / 2-1, 0, rec_upp.width-1, rec_upp.height / 2);
			g.setColor(Color.black);
			g.drawLine(0, rec_upp.height-1, rec_upp.width-2-rec_upp.height / 2, rec_upp.height-1);
			g.drawLine(rec_upp.width-1-rec_upp.height / 2, rec_upp.height-1, rec_upp.width-1, rec_upp.height-1-rec_upp.height / 2);
			g.setColor(getShadowColor());
			g.drawLine(1, rec_upp.height-2, rec_upp.width-2-rec_upp.height / 2,  rec_upp.height-2 );
			g.drawLine(rec_upp.width-1-rec_upp.height / 2, rec_upp.height-2, rec_upp.width-2, rec_upp.height-rec_upp.height / 2-1 );
		}
		g.translate(-rec_upp.x, -rec_upp.y);
	}

	public void setUpperThumbLocation(int x, int y)  {
		Rectangle upperUnionRect = new Rectangle();
		upperUnionRect.setBounds( thumbRect );
		rec_upp.setLocation( x, y );
		SwingUtilities.computeUnion( rec_upp.x, rec_upp.y, rec_upp.width, rec_upp.height, upperUnionRect );
		slider.repaint();
	}

	public class RangeTrackListener extends TrackListener {
		public void mousePressed(MouseEvent e) {
			if (!slider.isEnabled()) {
				return;
			}
			boolean Pressed_upp = false;
			boolean Pressed_low = false;
			if (uppSel) {
				if (rec_upp.contains(e.getX(), e.getY())) {Pressed_upp = true;}
				else if (thumbRect.contains(e.getX(), e.getY())) {Pressed_low = true;}
			} else {
				if (thumbRect.contains(e.getX(), e.getY())) {Pressed_low = true;}
				else if (rec_upp.contains(e.getX(), e.getY())) {Pressed_upp = true;}
			}
			if(Pressed_upp) {
				uppSel = true;
				uppDrag = true;
				offset = e.getX() - rec_upp.x;
				return;
			}
			uppDrag = false;
			if(Pressed_low) {
				loweDrag = true;
				uppSel = false;
				offset = e.getX() - thumbRect.x;
				return;
			}
			loweDrag = false;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			loweDrag = false;
			uppDrag = false;
			slider.setValueIsAdjusting(false);
			super.mouseReleased(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int milieucurseur;
			if (!loweDrag && !uppDrag) {
				return;
			}
			if (!slider.isEnabled()) {
				return;
			}
			slider.setValueIsAdjusting(true);
			if (loweDrag) {
				int hm = xPositionForValue(slider.getMaximum() -slider.getExtent());
				int Gauchecurseur = e.getX() - offset;
				int lowertrack = trackRect.x;
				int upptrack = lowertrack + (trackRect.width - 1);
				if (drawInverted()) {lowertrack = hm;}
				else {upptrack = hm;}
				milieucurseur = Math.min(Math.max(e.getX() - offset, lowertrack - thumbRect.width / 2), upptrack - thumbRect.width / 2) + thumbRect.width / 2;
				int droitecurseur = Gauchecurseur + thumbRect.width;
				if (valueForXPosition(droitecurseur) < ((RangeSlider) slider).getUpperValue()) {
					setThumbLocation(Gauchecurseur, thumbRect.y);
					slider.setValue(valueForXPosition(milieucurseur));
				}
			}
			if (uppDrag) {
				int Gauchecurseur = e.getX() - offset;
				int lowertrack = trackRect.x;
				int upptrack = trackRect.x + trackRect.width - 1;
				int hm = xPositionForValue(slider.getMaximum());
				if (drawInverted()){lowertrack = hm;}
				else{upptrack = hm;}
				Gauchecurseur = Math.min(Math.max(Gauchecurseur, lowertrack - rec_upp.width / 2), upptrack - rec_upp.width / 2);
				int droitecurseur = Gauchecurseur;
				milieucurseur = droitecurseur + rec_upp.width / 2;
				if (valueForXPosition(Gauchecurseur) > slider.getValue()) {
					setUpperThumbLocation(droitecurseur, rec_upp.y);
					((RangeSlider) slider).setUpperValue(valueForXPosition(milieucurseur));
				}
			}
		}
	}

	public class ChangeHandler implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
			if (!loweDrag && !uppDrag) {
				calculateThumbLocation();
				slider.repaint();
			}
		}
	}
}
