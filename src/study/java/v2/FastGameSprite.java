package study.java.v2;

import com.rupeng.oogame.GameSprite;

public class FastGameSprite extends GameSprite {

	public FastGameSprite(String spriteName) {
		super(spriteName);
	}

	public void moveLeft() {
		this.moveLeft(5);
	}

	@Override
	public void moveRight() {
		this.moveRight(5);
	}

	@Override
	public void moveUp() {
		this.moveUp(5);
	}

	@Override
	public void moveDown() {
		this.moveDown(5);
	}

}
