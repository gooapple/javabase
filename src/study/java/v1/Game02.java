package study.java.v1;

import com.rupeng.game.GameCore;


/**
 * 学习目标：
 * 修改游戏窗口的标题
 * 更改窗口的大小
 */
public class Game02 implements Runnable {
	
	public static void main(String[] args) {
		Game02 game = new Game02();
		GameCore.start(game);
	}

	public void run() {	
		//在此处添加必要的代码
		GameCore.setGameTitle("小游戏");
		GameCore.setGameSize(400, 300);
		
		GameCore.pause(1000000000);
	}

}
