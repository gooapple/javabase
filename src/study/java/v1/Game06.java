package study.java.v1;

import java.awt.event.KeyEvent;

import com.rupeng.game.GameCore;

/**
 * 学习目标： 定义一个创建金币的方法，并创建一个金币
 */
public class Game06 implements Runnable {

	public static void main(String[] args) {
		Game06 game = new Game06();
		GameCore.start(game);
	}
	
	private void createCoin() {
		int n = 2;
		GameCore.createSprite(n, "coin");
		GameCore.setSpritePosition(n, 200, 100);
		GameCore.playSpriteAnimate(2, "rotate", true);
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
		
		//创建一个金币
		this.createCoin();	

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
