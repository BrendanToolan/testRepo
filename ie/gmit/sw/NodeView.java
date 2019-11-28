package ie.gmit.sw;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class NodeView extends JComponent{
	private static final long serialVersionUID = 1L;
	private Node<Integer>[][] model = null; //This is really the same as Object[][]
	private Visitor<Integer> visitor = new Visitor<>();
	
	public NodeView(Node<Integer>[][] model) {
		this.model = model;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int row = 0; row < model.length; row++) {
			for (int col = 0; col < model[row].length; col++) {
				model[row][col].accept(visitor);
				g.setColor(new Color(model[row][col].getValue()));
				g.drawRect(col, row, 1, 1);
			}
		}
	}
	
	public void setVisitor(Visitor<Integer> visitor) {
		this.visitor = visitor;
	}
	
	public Collection<Number> getModel(){
		//This converts the 2D Node<Integer> array to a 1D array and then sends all the ints to a list
		return Arrays.stream(model).flatMap(i -> Arrays.stream(i)).map(e -> e.getValue()).collect(Collectors.toList());
	}
}