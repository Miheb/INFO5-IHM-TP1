package com.uga.zj;//////////////////////////////////////////////////////////////////////////////
// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////


/* imports *****************************************************************/

import javafx.scene.shape.Circle;

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
	Vector<Shape> shapes = new Vector<Shape>();
	MarkingMenu menu;
	MarkingMenu menu2;

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
			switch (e.getButton()) {
				case 1:
					if(menu!=null && menu.active==true){
						int number = menu.getSelectedItem(e.getPoint());
						switch (number) {
							case 0:
								Point cur = e.getPoint();
								menu2 = new MarkingMenu(listColor, cur);
								menu.active = false;
								panel.repaint();
								break;
								default:
						}
					}
					else if(menu!=null && menu.active==false && menu2!=null && menu2.active == true){
						int number = menu2.getSelectedItem(e.getPoint());
						switch(number){
							case 0:
								color = Color.BLACK;
								break;
							case 1:
								color = Color.RED;
								break;
							case -2:
								color = Color.YELLOW;
								break;
							case -1:
								color = Color.BLUE;
								break;
							default:
						}
						menu = null;
						menu2 = null;
						panel.repaint();
					}
					break;

				case 3:
					if(menu==null) {
						o = e.getPoint();
						menu = new MarkingMenu(list, o);
						panel.repaint();
					}
					else{
						menu=null;
						menu2=null;
						panel.repaint();
					}
					break;
				default:
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) { o = e.getPoint(); }
		public void mouseReleased(MouseEvent e) { shape = null; }
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			Point cur = e.getPoint();
			if(menu !=null && menu.active)
				menu.setCur(cur);
			if(menu2 != null && menu2.active)
				menu2.setCur(cur);
			panel.repaint();
		}
	}
	
	Tool tools[] = {
		new Tool("pen") {
			public void mouseDragged(MouseEvent e) {
				Path2D.Double path = (Path2D.Double)shape;
				if(path == null) {
					path = new Path2D.Double();
					path.moveTo(o.getX(), o.getY());
					shapes.add(shape = path);
				}
				path.lineTo(e.getX(), e.getY());
				panel.repaint();
			}
		},
		new Tool("rect") {
			public void mouseDragged(MouseEvent e) {
				Rectangle2D.Double rect = (Rectangle2D.Double)shape;
				if(rect == null) {
					rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
					shapes.add(shape = rect);
				}
				rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()),
				             abs(e.getX()- o.getX()), abs(e.getY()- o.getY()));
				panel.repaint();
			}
		},
        new Tool("ellipse") {
            public void mouseDragged(MouseEvent e) {
                Ellipse2D.Double ellipse = (Ellipse2D.Double)shape;
                if(ellipse == null) {
                    ellipse = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
                    shapes.add(shape = ellipse);
                }
                ellipse.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()),
                        abs(e.getX()- o.getX()), abs(e.getY()- o.getY()));
                panel.repaint();
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
				
				g2.setColor(color);
				for(Shape shape: shapes) {
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
