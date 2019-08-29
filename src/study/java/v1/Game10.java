package study.java.v1;

import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import study.java.util.GameUtils;

import com.rupeng.game.GameCore;

/**
 * 学习目标： 增强for循环的坑及解决办法
 * 作业：在游戏窗口的右上角显示mario吃到的金币数量，当吃完金币，播放音乐
 */
public class Game10 implements Runnable {

	private CopyOnWriteArrayList<Integer> coinNums;

	public static void main(String[] args) {
		Game10 game = new Game10();
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
		
		coinNums = new CopyOnWriteArrayList<>();
		
		//创建10个金币，位置随机
		for (int i = 2; i < 12; i++) {
			this.createCoin(i);
			coinNums.add(i);
		}
		
		// 开启线程检测mario和金币的碰撞		
		new Thread(new Runnable() {
			
			public void run() {
				while(true) {
					for (Integer i : coinNums) {
						if (GameUtils.getRectangle(i).intersects(GameUtils.getRectangle(marioNum))) {
							//GameCore.hideSprite(i);
							GameCore.removeSprite(i);
							coinNums.remove(i);
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
