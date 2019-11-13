package com.uga.zj;//////////////////////////////////////////////////////////////////////////////
// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////


/* imports *****************************************************************/

import javafx.scene.shape.Circle;
import javafx.util.Pair;

import static java.lang.Math.*;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Vector;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


/* paint *******************************************************************/

class Paint extends JFrame {
	String[] list  = {"color","shape","other"};
	String[] listColor = {"Black","RED","Yellow","BLUE"};
	String[] listShape = {"pen","rect","ellipse"};
	Vector<Pair<Shape,Color>> shapes = new Vector<>();
	MarkingMenu menu;
	MarkingMenu menu2;
	MarkingMenu menu3;
	boolean isDraw = false;

	class Tool extends AbstractAction
	           implements MouseInputListener {
	    Point o;
		Shape shape;

		public Tool(String name) { super(name); }
		public void actionPerformed(ActionEvent e) {
			System.out.println("using tool " + this);
			panel.removeMouseListener(tool);
			panel.removeMouseMotionListener(tool);
			tool = this;
			panel.addMouseListener(tool);
			panel.addMouseMotionListener(tool);
		}
		public void mouseClicked(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			switch (e.getButton()) {
				case 1:
					o = e.getPoint();
					isDraw = true;
					break;

				case 3:
					o = e.getPoint();
					menu = new MarkingMenu(list, o);
					panel.repaint();
					break;
				default:
			}
		}
		public void mouseReleased(MouseEvent e) {
			isDraw = false;
			shape = null;
			menu = null;
			if(menu2 !=null){
				Point cur = e.getPoint();
				if(menu2.isInMenu(cur)){
					int num = menu2.getSelectedItem(cur);
					switch (num){
						case 0:
							color = Color.BLACK;
							j.setSelectedIndex(0);
							break;
						case 1:
							color = Color.RED;
							j.setSelectedIndex(2);
							break;
						case -2:
							color = Color.YELLOW;
							j.setSelectedIndex(1);
							break;
						case -1:
							color = Color.BLUE;
							j.setSelectedIndex(3);
							break;
						default:
					}
				}else{
					menu2 = null;
				}
			}
			menu2 = null;
			if(menu3!=null){
				Point cur = e.getPoint();
				if(menu3.isInMenu(cur)){
					int num = menu3.getSelectedItem(cur);
					System.out.println(num);

					switch (num){
						case 0:
							panel.removeMouseListener(tool);
							panel.removeMouseMotionListener(tool);
							tool = tools[0];
							panel.addMouseListener(tool);
							panel.addMouseMotionListener(tool);
							break;
						case 1:
						case -2:
							panel.removeMouseListener(tool);
							panel.removeMouseMotionListener(tool);
							tool = tools[1];
							panel.addMouseListener(tool);
							panel.addMouseMotionListener(tool);
							break;
						case -1:
							panel.removeMouseListener(tool);
							panel.removeMouseMotionListener(tool);
							tool = tools[2];
							panel.addMouseListener(tool);
							panel.addMouseMotionListener(tool);
							break;
					}
				}
			}
			menu3 = null;
			panel.repaint();
		}
		public void mouseDragged(MouseEvent e) {
			Point cur = e.getPoint();
			if (menu != null && menu.active) {
				menu.setCur(cur);
				if(menu.isInMenu(cur)){
					int num = menu.getSelectedItem(cur);
					switch (num) {
						case 0:
							menu2 = new MarkingMenu(listColor,new Point((int)cur.getX() + 70,(int)cur.getY() + 70));
							menu.active = false;
							break;
						case 1:
						case -2:
							menu3 = new MarkingMenu(listShape,new Point((int)cur.getX() + 70,(int)cur.getY() + 70));
							menu.active = false;
							break;
					}
				}
			}
			if (menu2 != null && menu2.active) {
				menu2.setCur(cur);
				if(!menu2.isInMenu(cur)){
					menu2 = null;
					menu.active = true;
				}
			}
			if (menu3 != null && menu3.active) {
				menu3.setCur(cur);
				if(!menu3.isInMenu(cur)){
					menu3 = null;
					menu.active = true;
				}
			}
			panel.repaint();
		}
		public void mouseMoved(MouseEvent e) {}
	}

	
	Tool tools[] = {
		new Tool("pen") {
			public void mouseDragged(MouseEvent e) {
				if(isDraw) {
					Path2D.Double path = (Path2D.Double) shape;
					if (path == null) {
						path = new Path2D.Double();
						path.moveTo(o.getX(), o.getY());
						shapes.add(new Pair(shape = path, color));
					}
					path.lineTo(e.getX(), e.getY());
					panel.repaint();
				}else{
					super.mouseDragged(e);
				}
			}
		},
		new Tool("rect") {
			public void mouseDragged(MouseEvent e) {
				if(isDraw) {
					Rectangle2D.Double rect = (Rectangle2D.Double) shape;
					if (rect == null) {
						rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
						shapes.add(new Pair(shape = rect, color));
					}
					rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()),
							abs(e.getX() - o.getX()), abs(e.getY() - o.getY()));
					panel.repaint();
				}
				else{
					super.mouseDragged(e);
				}
			}
		},
        new Tool("ellipse") {
            public void mouseDragged(MouseEvent e) {
            	if(isDraw) {
					Ellipse2D.Double ellipse = (Ellipse2D.Double) shape;
					if (ellipse == null) {
						ellipse = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
						shapes.add(new Pair(shape = ellipse, color));
					}
					ellipse.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()),
							abs(e.getX() - o.getX()), abs(e.getY() - o.getY()));
					panel.repaint();
				}
            	else{
            		super.mouseDragged(e);
				}
            }
        }

	};
	Tool tool;

	JPanel panel;

	JComboBox<String> j;
	Color color = Color.BLACK;
	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		add(new JToolBar(){{
			for(AbstractAction tool: tools) {
				add(tool);
			}
			this.setLayout(new GridLayout());
			j = new JComboBox<String>(new String[]{"BLACK", "YELLOW", "RED", "BLUE"});
			add(j);
		}}, BorderLayout.NORTH);

		add(panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);	
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				                    RenderingHints.VALUE_ANTIALIAS_ON);
		
				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());

				for(Pair item: shapes) {
					Shape shape = (Shape) item.getKey();
					Color c = (Color) item.getValue();
					g2.setColor(c);
					g2.draw(shape);
				}
				if(menu !=null) {
					menu.setUI(g2);
					menu.updateUI(g2);
				}
				if(menu2 !=null){
					menu2.setUI(g2);
					menu2.updateUI(g2);
				}
				if(menu3 !=null){
					menu3.setUI(g2);
					menu3.updateUI(g2);
				}
			}
		});
		j.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					try {
						Field field = Color.class.getField((String) j.getSelectedItem());
						color = (Color) field.get(null);
					}catch (Exception e1){
						color = Color.BLACK;
					}
				}
			}
		});

		pack();
		setVisible(true);
	}


/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Paint paint = new Paint("paint");
			}
		});
	}
}
