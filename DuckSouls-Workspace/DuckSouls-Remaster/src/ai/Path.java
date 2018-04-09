package ai;

import java.awt.Point;
import java.util.ArrayList;

import entities.Entity;
import world.Room;

public class Path {
	
	ArrayList<Node> path = null;
	Point start = null;
	
	public Path(Room room, Point start, Point goal) throws NotAvaliablePathExecption {
		this.start = start;
		Node[][] roomNodes = new Node[room.getInternalWidth() + 2][room.getInternalHeight() + 2];
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		for(int x = 0; x<room.getInternalWidth() + 2; x++) {
			for(int y = 0; y<room.getInternalHeight() + 2; y++) {
				roomNodes[x][y] = new Node(new Point(x,y), goal);
				if(!room.tileAt(new Point(x,y)).getCanWalkOn()) {
					roomNodes[x][y].notMovable();
				}
				
				for(Entity entity: room.getEnemyList()) {
					if(entity.getPosition().x == x && entity.getPosition().y == y) {
						roomNodes[x][y].notMovable();
					}
				}
				
			}
		}
		
		roomNodes[start.x][start.y].update(roomNodes[start.x][start.y]);
		open.add(roomNodes[start.x][start.y]);
		while(!closed.contains(roomNodes[goal.x][goal.y])) {
			if(open.size() == 0) {
				throw new NotAvaliablePathExecption();
			}
			int lowest = 0;
			for(int node = 0; node<open.size(); node++) {
				if(open.get(node).getF() < open.get(lowest).getF()) lowest = node;
				else { 
					if(open.get(node).getF() == open.get(lowest).getF()) {
						if(open.get(node).getH() < open.get(lowest).getH()) lowest = node;
					}
				}
			}
			if(open.get(lowest).getPoint().x+1 >= 0 && open.get(lowest).getPoint().x+1 < room.getInternalWidth() + 2) {
				if(roomNodes[open.get(lowest).getPoint().x+1][open.get(lowest).getPoint().y].isMovable()
						&& !closed.contains(roomNodes[open.get(lowest).getPoint().x+1][open.get(lowest).getPoint().y])) {
					roomNodes[open.get(lowest).getPoint().x+1][open.get(lowest).getPoint().y].update(open.get(lowest));
					open.add(roomNodes[open.get(lowest).getPoint().x+1][open.get(lowest).getPoint().y]);
				}
			}
			
			if(open.get(lowest).getPoint().x-1 >= 0 && open.get(lowest).getPoint().x-1 < room.getInternalWidth() + 2) {
				if(roomNodes[open.get(lowest).getPoint().x-1][open.get(lowest).getPoint().y].isMovable()
						&& !closed.contains(roomNodes[open.get(lowest).getPoint().x-1][open.get(lowest).getPoint().y])) {
					roomNodes[open.get(lowest).getPoint().x-1][open.get(lowest).getPoint().y].update(open.get(lowest));
					open.add(roomNodes[open.get(lowest).getPoint().x-1][open.get(lowest).getPoint().y]);
				}
			}
			
			if(open.get(lowest).getPoint().y + 1 >= 0 && open.get(lowest).getPoint().y+1 < room.getInternalHeight() + 2) {
				if(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y+1].isMovable()
						&& !closed.contains(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y+1])) {
					roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y+1].update(open.get(lowest));
					open.add(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y+1]);
				}
			}
			
			if(open.get(lowest).getPoint().y - 1 >= 0 && open.get(lowest).getPoint().y-1 < room.getInternalHeight() + 2) {
				if(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y-1].isMovable()
						&& !closed.contains(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y-1])) {
					roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y+-1].update(open.get(lowest));
					open.add(roomNodes[open.get(lowest).getPoint().x][open.get(lowest).getPoint().y-1]);
				}
			}
			
			closed.add(open.get(lowest));
			open.remove(lowest);
		}
	
		this.path = generatePath(roomNodes[goal.x][goal.y]);

		
		
	}
	
	private ArrayList<Node> generatePath(Node node){
		if(node == null || node.getParent() == null) {
			return new ArrayList<Node> ();
		}
		else { 
			if(node.getParent().equals(node)) {
				ArrayList<Node> list = new ArrayList<Node>();
				list.add(node);
				return list;
			} 
			else {
				ArrayList<Node> list = new ArrayList<Node>();
				list.add(node);
				list.addAll(generatePath(node.getParent()));
				return list;
			}
		}
	}
	
	public Point getNext() {
		int currentdex = 0;
		for(int index = 0; index<path.size(); index++) {
			if(path.get(index).getParent().getPoint().x == this.start.x && path.get(index).getParent().getPoint().y == this.start.y) {
				if(path.get(index) != path.get(index).getParent()) {
					currentdex = index;
				}
			}
		}
		return new Point(path.get(currentdex).getPoint().x, path.get(currentdex).getPoint().y);
		
	}
}
