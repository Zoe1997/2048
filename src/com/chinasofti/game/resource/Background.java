package com.chinasofti.game.resource;
import java.awt.Graphics;
/**
 * 背景类，定义了背景和次背景方格的数据，和绘制背景、次背景方格的方�?
 *
 */
public class Background {
	//定义次背景方格坐标�?，定义成常量是因为该坐标位置是固定不变的
	public static final int FG_X = 50;//次背景方格原点x�?   
	public static final int FG_Y = 120;//次背景方格原点y�?

	/**
	 * 画背景和次方格背�?
	 * @param g  画笔
	 */
	public void drawBackground(Graphics g){
		g.drawImage(Resource.img_bg, 0, 0, null);
		g.drawImage(Resource.img_fg,FG_X,FG_Y,null);
	}
}
