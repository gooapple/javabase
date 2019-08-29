package study.java.v1;

import java.awt.event.KeyEvent;
import java.util.Random;

import com.rupeng.game.GameCore;

/**
 * 学习目标： 创建10个金币，位置随机
 * 思考：每次创建金币对象都创建一个Random对象，如果优化？
 * 局部变量和成员变量
 */
public class Game07 implements Runnable {

	public static void main(String[] args) {
		Game07 game = new Game07();
		GameCore.start(game);
	}
	
	private void createCoin(int n) {
		Random ran = new Random();
		
		int x = ran.nextInt(GameCore.getGameWidth()-50);
		int y = ran.nextInt(GameCore.getGameHeight()-50);
		
		GameCore.createSprite(n, "coin");
		GameCore.setSpritePosition(n, x, y);
		GameCore.playSpriteAnimate(n, "rotate", true);
	}

	public void run() {
		// 设置窗体的标题和大小
		GameCore.setGameTitle("小游戏");
		GameCore.setGameSize(600, 400);

		int marioNum = 1;
		String spriteName = "mario";
		// 创建一个mario游戏精灵
		GameCore.createSprite(marioNum, spriteName);
		GameCore.playSpriteAnimate(marioNum, "walk", true);
		GameCore.setSpritePosition(marioNum, 100, 50);
		GameCore.setSpriteFlipX(marioNum, true);
		
		//创建10个金币，位置随机
		for (int i = 2; i < 12; i++) {
			this.createCoin(i);
		}

		int x = 100, y = 50;
		int speed = 2;
		// 按键控制maro移动
		while (true) {
			int key = GameCore.getPressedKeyCode();
			if (key == KeyEvent.VK_LEFT) {
				GameCore.setSpriteFlipX(marioNum, false);
				x -= speed;
				GameCore.setSpritePosition(marioNum, x, y);
			} else if (key == KeyEvent.VK_RIGHT) {
				GameCore.setSpriteFlipX(marioNum, true);
				x += speed;
				GameCore.setSpritePosition(marioNum, x, y);
			} else if (key == KeyEvent.VK_UP) {
				y -= speed;
				GameCore.setSpritePosition(marioNum, x, y);
			} else if (key == KeyEvent.VK_DOWN) {
				y += speed;
				GameCore.setSpritePosition(marioNum, x, y);
			}
			GameCore.pause(5);
		}

	}

}
