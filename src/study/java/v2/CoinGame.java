package study.java.v2;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.rupeng.game.GameCore;
import com.rupeng.oogame.GameSprite;
import com.rupeng.oogame.GameText;

public class CoinGame implements Runnable {
	

	private ArrayList<Coin> coins;
	private GameSprite mario;
	private int count;
	private GameText txtCount;

	public static void main(String[] args) {
		GameCore.start(new CoinGame());
	}

	public void run() {
		GameCore.setGameTitle("小游戏");
		Random ran = new Random();
		
		
		coins = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Coin c = new Coin(ran.nextInt(GameCore.getGameWidth()-50),ran.nextInt(GameCore.getGameHeight()-50));
			coins.add(c);
		}
		
		count = 0;
		txtCount = new GameText("金币数量："+count);
		txtCount.setFontSize(15);
		txtCount.setPosition(900, 10);
		
		GameCore.asyncRun(new Runnable() {

			public void run() {
//				mario = new GameSprite("mario");
				mario = new FastGameSprite("mario");
				mario.setPosition(20, 300);
				mario.playAnimate("walk");

				while (true) {
					int key = GameCore.getPressedKeyCode();
					if (key == KeyEvent.VK_LEFT) {
						mario.setFlipX(false);
						mario.moveLeft();
					} else if (key == KeyEvent.VK_RIGHT) {
						mario.setFlipX(true);
						mario.moveRight();
					} else if (key == KeyEvent.VK_UP) {
						mario.moveUp();
					} else if (key == KeyEvent.VK_DOWN) {
						mario.moveDown();
					}
					GameCore.pause(5);
				}
			}
		});
		
		GameCore.asyncRun(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					for (Coin c : coins) {
						if(!c.isRemoved() && mario!=null && mario.isIntersectWith(c)) {
							c.remove();
							count++;
							txtCount.setText("金币数量："+count);
						}
					}
					GameCore.pause(5);
				}
			}
		});

		GameCore.pause(1000000000);
	}

}
