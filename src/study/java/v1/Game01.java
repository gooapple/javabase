package study.java.v1;

import com.rupeng.game.GameCore;


/**
 * 
 * 如鹏游戏引擎的使用
 * 注意：我们的目的不是使用Java开发游戏，而是通过游戏学习Java相关的基础语法知识
 * 
 * 学习目标：启动一个游戏窗口，并且让窗口不消失
 */
public class Game01 implements Runnable {
	
	public static void main(String[] args) {
		Game01 game = new Game01();
		GameCore.start(game);
	}

	public void run() {	
		GameCore.pause(1000000000);
	}

}
