package com.chinasofti.game.test;

import javax.swing.JFrame;

import com.chinasofti.game.resource.*;
import com.chinasofti.game.control.*;
import javax.swing.JLabel;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(500,600);
		frame.setResizable(false);//不可改变窗体大小
		frame.setLocationRelativeTo(null);//设置位置居中
		frame.setTitle("Java_2048");//设置标题
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭窗口同时退出程序
		Game_2048 game = new Game_2048();
		frame.getContentPane().add(game);
		GameControl control = new GameControl(game);
		game.setLayout(null);
		frame.addKeyListener(control);
		frame.setVisible(true);//显示窗体
	}
}
