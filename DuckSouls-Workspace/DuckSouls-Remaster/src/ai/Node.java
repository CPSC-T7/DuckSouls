package ai;

import java.awt.Point;

/**
 * 
 * Represents a point on a tile array for determining path lengths between points
 * @author colinauyeung
 *
 */
public class Node {
	
	
	private Point position = null; 
	private double G = 0; //G cost - distance from it's parent node + parent's H cost
	private double H = 0; //H cost - the distance from the goal node	
	private double F = 0; //G cost + H cose
	private Node parent = null; //The node from which this node is generated
	private boolean canMove = true;
	
	/*
	 * 
	 * Constructors
	 * 
	 */
	
	/**
	 * Creates an node
	 * 
	 * @param position
	 * 			The position that the node represents
	 * @param goal
	 * 			The position that the path this is generated for wants to end at
	 */
	public Node(Point position, Point goal) {
		this.position = position;
		this.H = Math.hypot((this.position.getX() - goal.getX()), (this.position.getY() - goal.getY()));

	}//End of Constructor
	
	/**
	 * Given a parent node, 
	 * if this parent would give this node a lower G cost, then make it this node's parent
	 * 
	 * @param potentialParent
	 * 			A node which could be the parent of this node
	 */
	public void update(Node potentialParent) {
		//Calculate the G cost of this node if potentialParent becomes this node's parent
		double tempG = Math.hypot((this.position.getX() - potentialParent.getPoint().getX()), 
				(this.position.getY() - potentialParent.getPoint().getY())) + potentialParent.getH();
		
		//If this g cost is better or if this node does not have a parent
		if(parent == null || tempG <= this.G) {
			
			//update values
			this.parent = potentialParent;
			this.G = tempG;
			this.F = this.H + this.G;
			
		}
		
	}//End of update
	
	/**
	 * Gets the position of this node
	 * @return
	 * 	Point which represents this node's position
	 */
	public Point getPoint() {
		return new Point(this.position.x, this.position.y);
	}
	
	/**
	 * Get the H cost of this node 
	 * @return
	 * 		double - H cost of the node
	 */
	public double getH() {
		return this.H;
	}
	
	/**
	 * Get the G cost of this node 
	 * @return
	 * 		double - G cost of the node
	 */
	public double getG() {
		return this.G;
	}
	
	/**
	 * Get the F cost of this node 
	 * @return
	 * 		double - F cost of the node
	 */
	public double getF() {
		return this.F;
	}
	
	public void notMovable() {
		this.canMove = false;
	}
	
	public boolean isMovable() {
		return this.canMove;
	}
	
	public Node getParent() {
		return this.parent;
	}

}
