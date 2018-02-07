import map.Map;
import utils.Utilities;

import battle.BattleWorldTest;

public class test {
	public static void main(String[] args) {
		Map map1 = new Map();
		map1.initalization(0, 2);
		int count = 0; 
		do {
			Utilities.clearConsole();
			map1.turnLoop();
			if(map1.isEnemyNear())
				
				BattleWorldTest.battleLoop();
				count = 20;
				
			count += 1;
		}while (count<20);
	}
}
