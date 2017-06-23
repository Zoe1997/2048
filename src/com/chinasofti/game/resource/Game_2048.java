package com.chinasofti.game.resource;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * 游戏显示面板,用于显示游戏画面内容
 */
@SuppressWarnings("serial")
public class Game_2048 extends JPanel{
	Background background;
	GameService gameService;
	public Game_2048(){
		background = new Background();
		gameService = new GameService();
	}
	
	@Override
	public void paint(Graphics g){
		background.drawBackground(g);
		gameService.gamePaint(g);
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
}
