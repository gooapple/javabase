package study.java.v1;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import study.java.util.GameUtils;

import com.rupeng.game.GameCore;

/**
 * 学习目标： 使用集合来保存金币的编号，将已碰到的金币的编号移除集合
 */
public class Game09 implements Runnable {

	private ArrayList<Integer> coinNums;

	public static void main(String[] args) {
		Game09 game = new Game09();
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
		
		coinNums = new ArrayList<>();
		
		//创建10个金币，位置随机
		for (int i = 2; i < 12; i++) {
			this.createCoin(i);
			coinNums.add(i);
		}
		
		// 开启线程检测mario和金币的碰撞
		new Thread(new Runnable() {
			
			public void run() {
				while(true) {
					for (int i=0;i<coinNums.size();i++) {
						int n = coinNums.get(i);
						if (GameUtils.getRectangle(n).intersects(GameUtils.getRectangle(marioNum))) {
							//GameCore.hideSprite(i);
							GameCore.removeSprite(n);
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
