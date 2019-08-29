package study.java.v1;

import java.awt.event.KeyEvent;
import java.util.Random;

import study.java.util.GameUtils;

import com.rupeng.game.GameCore;

/**
 * 学习目标： mario和金币的碰撞检测
 * 1、首先定义一个工具类，根据游戏精灵的编号获取其所在矩形Rectangle
 * 2、开启一个线程，循环检测mario和所有金币是否碰撞，如果有碰撞，则让金币消失
 * 思考：运行该程序，发现控制台会有异常信息打印，为什么？
 */
public class Game08 implements Runnable {

	public static void main(String[] args) {
		Game08 game = new Game08();
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

		final int marioNum = 1;
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
		
		// 开启线程
		new Thread(new Runnable() {
			
			public void run() {
				while(true) {
					for (int i = 2; i < 12; i++) {
						if (GameUtils.getRectangle(i).intersects(GameUtils.getRectangle(marioNum))) {
							//GameCore.hideSprite(i);
							GameCore.removeSprite(i);
						}
					}
					GameCore.pause(5);
				}
			}
		}).start();

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
