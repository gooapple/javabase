package study.java.v1;

import com.rupeng.game.GameCore;

/**
 * 作业：让mario左右来回走动，当走到左右边界时，翻转
 */
public class Game04Homework implements Runnable {

	public static void main(String[] args) {
		Game04Homework game = new Game04Homework();
		GameCore.start(game);
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

		// mario来回走
		int x = 100;
		int speed = 20;
		boolean isFlip = false;

		while (true) {
			x += speed;

			if (x >= GameCore.getGameWidth() - 30 || x <= 0) {
				speed = -speed;
				GameCore.setSpriteFlipX(marioNum, isFlip);
				isFlip = !isFlip;
			}

			GameCore.setSpritePosition(marioNum, x, 50);
			GameCore.pause(200);
		}

		// GameCore.pause(1000000000);
	}

}
