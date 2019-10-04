import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.SliderUI;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.*;
 
 
public class Slider  {
    private static long[] times = new long[2];
    private static int index = 0;
    private static long stime = 0;
    private static long etime = 0; 
    public static void main(String[] args) {
        final JFrame frame_1 = new JFrame();
        final JPanel panel = new JPanel();
        frame_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_1.setSize(400,400);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int)tk.getScreenSize().getWidth();
         
        final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 190, 1);
      //  Component comp =;
	//	sl.add(comp );
        slider.setSnapToTicks(true);
        slider.setSize(100, 20);
 
        slider.setFocusable(false);
        slider.addChangeListener(new ChangeListener() {
 
            @Override
            public void stateChanged(ChangeEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("Value is:"+slider.getValue());
            }
             
        }); 
        slider.addMouseListener(new MouseListener() {
 
           
 
            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub
                stime = System.currentTimeMillis();
 
                int x = arg0.getX();
                System.out.println("X:"+x);
         
            }
 
            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub
                etime = System.currentTimeMillis();
                if ( (etime - stime) < 60 ) {
                     
                }
                else {
                	slider.setValue(arg0.getX());
                }
            }

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
             
        });
        
             
        
        panel.add(slider);
        frame_1.add(panel); 
        frame_1.setVisible(true);
         
    }
 
}