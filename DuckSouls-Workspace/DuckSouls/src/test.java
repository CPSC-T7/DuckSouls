
public class test {
	public static void main(String[] args) {
		Mapfile map1file = new Mapfile("map000.txt");
		Mapfile map2file = new Mapfile("map001.txt");
		Mapfile map3file = new Mapfile("map002.txt");
		Map map1 = new Map();
		map1.loadMap(map1file.getMap());
		map1.print();
		map1.clearMap();
		map1.loadMap(map2file.getMap());
		map1.print();
	}
}
