package study.java.v1;

import com.rupeng.game.GameCore;

/**
 * 学习目标： 让mario向右边移动一段距离
 * 作业：让mario左右来回走动，当走到左右边界时，翻转
 */
public class Game04 implements Runnable {

	public static void main(String[] args) {
		Game04 game = new Game04();
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

		//mario向右移动一段距离
		int x = 100;
		for (int i = 1; i <= 20; i++) {
			x += 10;
			GameCore.setSpritePosition(marioNum, x, 50);
			GameCore.pause(200);
		}
		

		GameCore.pause(1000000000);
	}

}
