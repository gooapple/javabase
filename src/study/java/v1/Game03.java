package study.java.v1;

import com.rupeng.game.GameCore;


/**
 * 学习目标：
 * 在窗体上创建一个mario游戏精灵
 * 让精灵左右翻转
 */
public class Game03 implements Runnable {
	
	public static void main(String[] args) {
		Game03 game = new Game03();
		GameCore.start(game);
	}

	public void run() {	
		//设置窗体的标题和大小
		GameCore.setGameTitle("小游戏");
		GameCore.setGameSize(600, 400);
		
		int marioNum = 1;
		String spriteName = "mario";
		//创建一个mario游戏精灵
		GameCore.createSprite(marioNum, spriteName);
		GameCore.playSpriteAnimate(marioNum, "walk", true);
		GameCore.setSpritePosition(marioNum, 100,50);
		GameCore.setSpriteFlipX(marioNum, true);
		
		GameCore.pause(1000000000);
	}

}
