package study.java.util;

import com.rupeng.game.GameCore;

import java.awt.Rectangle;


public class GameUtils {

	
	public static Rectangle getRectangle(int num) {
		int x = GameCore.getSpriteX(num);
		int y = GameCore.getSpriteY(num);
		int w = GameCore.getSpriteWidth(num);
		int h = GameCore.getSpriteHeight(num);
		
		return new Rectangle(x, y, w, h);
	}
}
