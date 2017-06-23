package com.chinasofti.game.control;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import com.chinasofti.game.resource.*;
/**
 *控制类，用于处理键盘操作，包括上下左右移动和消除 
 */
public class GameControl extends KeyAdapter{
	Game_2048 game;
	
	public GameControl(Game_2048 game){
		this.game = game;
	}
	//响应键盘按下操作
	@Override
	public void keyPressed(KeyEvent e){
		int isMove;//临时变量存储GameService 移动方法返回值,0表示没有移动过,1表示移动了
		int isRemove;//临时变量存储GameService 消除方法返回值,0表示没有消除过,1表示消除了
		int choice;//临时变量存储 弹出的选择窗口 选择按钮的返回值
		
		GameService gameService=game.getGameService();
		
		
		switch(e.getKeyCode()){
		case 37://响应小键盘左键
			isMove=gameService.moveLeft();
			isRemove=gameService.removeLeft();
			if(isMove==1||isRemove==1){
				//如果移动或消除了,则生成新的方块,否则不操作
				gameService.newBlock();
			}
		break;
		case 65://响应键盘A键 左移
			isMove=gameService.moveLeft();
			isRemove=gameService.removeLeft();
			if(isMove==1||isRemove==1){
				//如果移动或消除了,则生成新的方块,否则不操作
				gameService.newBlock();
			}
		break;
		case 38://响应小键盘上键
			isMove=gameService.moveUp();
			isRemove=gameService.removeUp();
			if(isMove==1||isRemove==1){
				gameService.newBlock();
			}
			break;
		case 87://响应键盘W键 上移
			isMove=gameService.moveUp();
			isRemove=gameService.removeUp();
			if(isMove==1||isRemove==1){
				gameService.newBlock();
			}
			break;
		case 39://响应小键盘右键
			isMove=gameService.moveRight();
			isRemove=gameService.removeRight();
			if(isMove==1||isRemove==1){
				gameService.newBlock();
			}
			break;
		case 68://响应键盘D键 右移
			isMove=gameService.moveRight();
			isRemove=gameService.removeRight();
			if(isMove==1||isRemove==1){
				gameService.newBlock();
			}
			break;
		case 40://响应小键盘下键
			isMove=gameService.moveDown();
			isRemove=gameService.removeDown();
			if(isMove==1||isRemove==1){
				gameService.newBlock();
			}
			break;
		case 83://响应键盘S键 下移
			isMove=gameService.moveDown();
			isRemove=gameService.removeDown();
			if(isMove==1||isRemove==1){
				gameService.newBlock();
			}
			break;
		case 27://响应键盘esc键
			//如果选择OK,返回0  选择CANCEL返回2
			choice=JOptionPane.showConfirmDialog(null, "    重新开始游戏？ ","游戏提示",JOptionPane.OK_CANCEL_OPTION);
			if(choice==0){
				gameService.start();
			}
			break;
		}
		game.repaint();//移动后需要更新游戏界面,尽快调用Game_2048的paint方法重画
		gameService.isGameOver();
		game.repaint();
	}
}
