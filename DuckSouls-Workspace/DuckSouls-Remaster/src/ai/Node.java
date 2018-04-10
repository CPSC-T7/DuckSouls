package ai;

import java.awt.Point;

public class Node {
	private Point position = null; 
	private double G = 0; //distance from starting node
	private double H = 0; //distance from end node
	private double F = 0; //G + H
	private Node parent = null;
	private boolean canMove = true;
	
	public Node(Point position, Point goal) {
		this.position = position;
		this.H = Math.hypot((this.position.getX() - goal.getX()), (this.position.getY() - goal.getY()));

	}
	
	public void update(Node potentialParent) {
		double tempG = Math.hypot((this.position.getX() - potentialParent.getPoint().getX()), (this.position.getY() - potentialParent.getPoint().getY())) + potentialParent.getH();
		if(parent == null || tempG <= this.G) {
			this.parent = potentialParent;
			this.G = tempG;
			this.F = this.H + this.G;
		}
	}
	
	public Point getPoint() {
		return new Point(this.position.x, this.position.y);
	}
	
	public double getH() {
		return this.H;
	}
	
	public double getG() {
		return this.G;
	}
	
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
