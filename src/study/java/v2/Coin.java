package study.java.v2;

import com.rupeng.oogame.GameSprite;

public class Coin extends GameSprite {

	public Coin(int x, int y) {
		super("coin");
		this.setPosition(x, y);
		this.playAnimate("rotate");
	}
	
}
