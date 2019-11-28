package ie.gmit.sw;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class AppWindow {
	private NodeView nodes;
	private Dimension d = null;
	private JToolBar toolBar = new JToolBar("Node Toolbar");
	private JButton btnNegative = new JButton();
    private JButton btnRandomise = new JButton();
    
    private Visitor<Integer> visitor = new Visitor<>();
    
    private void init() throws Exception {
		JFrame frame = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout());
    	
		JToolBar toolBar = getToolBar();
		frame.add(toolBar, "North");
		
		nodes = getNodeViewer();
		nodes.setPreferredSize(d);
		nodes.setMinimumSize(d);
		nodes.setMaximumSize(d);
		nodes.setVisitor(visitor);
		
		frame.add(nodes);
		frame.setSize(d);

		frame.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width - d.getWidth()) / 2,
						  (int) (Toolkit.getDefaultToolkit().getScreenSize().height - d.getHeight()) / 2);
		frame.pack();
		frame.setVisible(true);
    }
    
    private JToolBar getToolBar() throws Exception {
        toolBar.add(btnNegative, "East");
		toolBar.add(btnRandomise, "Last");

		btnNegative.setText("Negative");
		btnNegative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	visitor.setCommand(pixel -> ~pixel); //Negative
            	nodes.repaint();
			}
        });

		btnRandomise.setText("Randomise");
		btnRandomise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	visitor.setCommand(pixel -> (pixel ^ new Random().nextInt())); //Random
            	nodes.repaint();
			}
        });
		
		return toolBar;
    }
    
    private NodeView getNodeViewer() throws Exception {
		File f = new File("ireland.png");
		BufferedImage image = ImageIO.read(f);   
		
		/*
		 * Java does not support generic arrays as arrays are covariant and this can lead to runtime 
		 *  	String [] s = new String[10];
		 *		Object [] objs = s;
		 *		objs[0] = new Date(); //Runtime ArrayStoreException.
		 * 
		 * Java does allow the creation of arrays of unbounded wildcard instantiations <?>, as these are 
		 * essentially the same as Object[]:
		 *      Node<?>[][] model = new Node<?>[image.getHeight()][image.getWidth()];
		 * 
		 * The following workaround can also be applied but will generate a warning from the compiler.
		 */
		@SuppressWarnings("unchecked")
		Node<Integer>[][] model = new Node[image.getHeight()][image.getWidth()];
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				model[y][x] = new Node<>(new Integer(image.getRGB(x, y)));
			}
		}

		d = new Dimension(image.getWidth(), image.getHeight());
		return new NodeView(model);
    }
    
	public static void main(String[] args) throws Exception {
		new AppWindow().init();
	}
}