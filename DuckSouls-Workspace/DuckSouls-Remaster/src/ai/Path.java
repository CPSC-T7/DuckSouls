package ai;

import java.awt.Point;
import java.util.ArrayList;

import entities.Entity;
import world.Room;

public class Path {
	
	/*
	 * 
	 * Instance Variables
	 * 
	 */
	private ArrayList<Node> path = null;
	private Point start = null;
	
	/*
	 * 
	 * Constructors 
	 * 
	 */
	public Path(Room room, Point start, Point goal) throws NotAvaliablePathExecption {
		
		//Set up control lists
		this.start = start;
		Node[][] roomNodes = new Node[room.getInternalWidth() + 2][room.getInternalHeight() + 2];
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		
		//For every x,y within the room size...
		for(int x = 0; x<room.getInternalWidth() + 2; x++) {
			for(int y = 0; y<room.getInternalHeight() + 2; y++) {
				
				//Create a node in at point x,y in roomNodes
				roomNodes[x][y] = new Node(new Point(x,y), goal);
				
				//If the tile in the room cannot be moved to, set the node to not movable
				if(!room.tileAt(new Point(x,y)).getCanWalkOn()) {
					
					roomNodes[x][y].notMovable();
					
				}
				
				//If the tile has an enemy, set the node to not movable
				for(Entity entity: room.getEnemyList()) {
					
					if(entity.getPosition().x == x && entity.getPosition().y == y) {
						
						roomNodes[x][y].notMovable();
					}
				}
				
			}//End of for y loop
		}//End of For for x loop
		
		//Update the starting node and add it to the open list
		roomNodes[start.x][start.y].update(roomNodes[start.x][start.y]);
		open.add(roomNodes[start.x][start.y]);
		
		//While the closed list does not contain the goal tile...
		while(!closed.contains(roomNodes[goal.x][goal.y])) {
			
			//If there are no open tiles throw an exception
			if(open.size() == 0) {
				throw new NotAvaliablePathExecption();
			}
			
			int lowest = 0;
			
			//For all nodes in the open list...
			for(int node = 0; node<open.size(); node++) {
				
				//If that node has a lower F cost than the current lowest node, change it to the lowest node
				if(open.get(node).getF() < open.get(lowest).getF()) lowest = node;
				
				else { 
					
					//If this node and the current lowest node have the same F cost...
					if(open.get(node).getF() == open.get(lowest).getF()) {
						
						//If it has a lower H cost than the current lowest node, set it to the lowest node
						if(open.get(node).getH() < open.get(lowest).getH()) lowest = node;
					}
				}
			}
			
			//Update and add to the open list the node to the right of the lowest node if it exists and is movable
			if(open.get(lowest).getPoint().x+1 >= 0 && open.get(lowest).getPoint().x+1 < room.getInternalWidth() + 2) {
				
				if(roomNodes[open.get(lowest).getPoint().x+1][open.get(lowest).getPoint().y].isMovable()
						&& !closed.contains(roomNodes[open.get(lowest).getPoint().x+1][open.get(lowest).getPoint().y])) {
					
					roomNodes[open.get(lowest).getPoint().x+1][open.get(lowest).getPoint().y].update(open.get(lowest));
					open.add(roomNodes[open.get(lowest).getPoint().x+1][open.get(lowest).getPoint().y]);
					
				}
				
			}
			
			//Update and add to the open list the node to the left of the lowest node if it exists and is movable
			if(open.get(lowest).getPoint().x-1 >= 0 && open.get(lowest).getPoint().x-1 < room.getInternalWidth() + 2) {
				
				if(roomNodes[open.get(lowest).getPoint().x-1][open.get(lowest).getPoint().y].isMovable()
						&& !closed.contains(roomNodes[open.get(lowest).getPoint().x-1][open.get(lowest).getPoint().y])) {
					
					roomNodes[open.get(lowest).getPoint().x-1][open.get(lowest).getPoint().y].update(open.get(lowest));
					open.add(roomNodes[open.get(lowest).getPoint().x-1][open.get(lowest).getPoint().y]);
					
				}
				
			}
			
			//Update and add to the open list the node to the bottom of the lowest node if it exists and is movable
			if(open.get(lowest).getPoint().y + 1 >= 0 && open.get(lowest).getPoint().y+1 < room.getInternalHeight() + 2) {
				
				if(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y+1].isMovable()
						&& !closed.contains(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y+1])) {
					
					roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y+1].update(open.get(lowest));
					open.add(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y+1]);
					
				}
				
			}
			
			//Update and add to the open list the node to the top of the lowest node if it exists and is movable
			if(open.get(lowest).getPoint().y - 1 >= 0 && open.get(lowest).getPoint().y-1 < room.getInternalHeight() + 2) {
				
				if(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y-1].isMovable()
						&& !closed.contains(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y-1])) {
					
					roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y+-1].update(open.get(lowest));
					open.add(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y-1]);
					
				}
				
			}
			
			//Remove the lowest node from the open list and add it to the closed list
			closed.add(open.get(lowest));
			open.remove(lowest);
		}
	
		//Generate the path
		this.path = generatePath(roomNodes[goal.x][goal.y]);
		
		
		for(Node[] column: roomNodes) {
			for(Node node: column) {
				System.out.println(node.getPoint().x + "," + node.getPoint().y + "," + node.getF());
			}
		}
		System.out.println();

		
		
	}//End of constructor
	
	/**
	 * Returns an arraylist contain all the nodes along a path based on parenthood
	 * 
	 * @param Node at the end of desired path
	 * @return
	 * 		Arraylist of nodes along the desired path
	 */
	private ArrayList<Node> generatePath(Node node){
		
		//If a node is null or it doesn't have parent...
		if(node == null || node.getParent() == null) {
			
			//Return an empty list
			return new ArrayList<Node> ();
		}
		
		else { 
			
			//If it's parent is itself
			if(node.getParent().equals(node)) {
				
				//Return a list with just itself as an element
				ArrayList<Node> list = new ArrayList<Node>();
				list.add(node);
				return list;
				
			} 
			
			//In all other cases..
			else {
				
				//Add itself to a list
				ArrayList<Node> list = new ArrayList<Node>();
				list.add(node);
				
				//Then recursively call the method on the parent and add the resulting list to the current one
				list.addAll(generatePath(node.getParent()));
				
				//Return the list
				return list;
			}
		}
	}//End of generatePath
	
	/**
	 * Returns the recommended next position based on the path
	 * 
	 * @return
	 * 		Point, which is the point after the starting point on the path
	 */
	public Point getNext() {
		int currentdex = 0;
		
		//For all nodes in the current path
		for(int index = 0; index<path.size(); index++) {
			
			//If this node's parent is the start of the path..
			if(path.get(index).getParent().getPoint().x == this.start.x && path.get(index).getParent().getPoint().y == this.start.y) {
				
				//And it is not it's own parent...
				if(path.get(index) != path.get(index).getParent()) {
					
					//Save it's index
					currentdex = index;
				}
			}
		}
		
		//Return the point of the saved node
		Point thisPoint = path.get(currentdex).getPoint(); 
		return thisPoint;
		
	}//End of getNext
	
	/**
	 * Returns the length of the path
	 * 
	 * @return 
	 * 		Length of the path
	 */
	public int getPathLength() {
		
		return this.path.size();
		
	}//End of getPathLength
	
}//End of Path 
