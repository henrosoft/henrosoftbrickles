package slickbrickles.util;

import org.newdawn.slick.Graphics;

public class UserInfo {
	private final String name;
	private int life = 3;

	public UserInfo(String user) {
		name = user;
	}

	public void init() {
		reset();
	}

	public int getLives() {
		return life;
	}

	public void loseLife() {
		life--;
	}

	public void reset() {
		life = 3;
	}

	public void render(Graphics g) {
		return;
	}
}
