package com.chinasofti.game.resource;
import java.awt.Graphics;
/**
 * Data类用于储存游戏中的分数信�?
 * 定义了绘制得分�?�?��分计分板、分数数字的显示分数的功�?
 *
 */
public class Data {
	public static final int SCORE_X = 80; // 得分计分板图片的原点x坐标
	public static final int SCORE_Y = 20; // 得分计分板图片的原点y坐标
	public static final int HIGHTSCORE_X = 280; // �?��分数计分板的原点x坐标
	public static final int HIGHTSCORE_Y = 20;  // �?��分数计分板的原点y坐标
	public static final int SIZE_NUM = 21;// 数字图片中一个数字占的像素大小是21(宽高都是)
	public static final int SIZE_SCORE = 140;// 分数与最高分图片宽度 都是140像素
	
	private int score;//当前得分
	private int hightScore;//�?���?
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getHightScore() {
		return hightScore;
	}
	public void setHightScore(int hightScore) {
		this.hightScore = hightScore;
	}
	//实现计分板功能，包括绘制计分板背景图和得分�?�?��分数据显示功�?  
	public void drawData(Graphics g){
		g.drawImage(Resource.img_score, SCORE_X,SCORE_Y, null);
		g.drawImage(Resource.img_highScore,HIGHTSCORE_X,HIGHTSCORE_Y,null);
		drawScore(g);
		drawHightScore(g);
	}
	//显示得分数据
	public void drawScore(Graphics g){
		String score_str = score+"";//转成字符串，用于计算画出的字符串长度
		for(int i=0;i<score_str.length();i++){
			int scoreBit = score_str.charAt(i)-'0'; 
			int numPos = scoreBit*SIZE_NUM; //计算出字符在数字图片的位�?
			int mid = SCORE_X+SIZE_SCORE/2; //计算出计分板正中的横坐标�? score图片的原点x值加图片的一�?
			/* drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)
			 * 将img图片对象，根据源图片(即img)左上�?sx1,sy1)和右下角(sx2,sy2)两个坐标
			 * 截取出矩形图片，画在目标矩阵�?根据左上�?dx1,dy1)坐标和右下角(dx2,dy2)定位) */
			g.drawImage(
					Resource.img_num, 
				    //  中间点位置，再左移的画出的字符长度的�?��，得到字符串图片�?��画的起始位置x值，
					//  然后再根据是第i个字符右偏移i个字符图片长�?i�?�?��)得到目标矩阵左上角x�?
					mid-score_str.length()*SIZE_NUM/2+i*SIZE_NUM, 
					SCORE_Y+50, 
					mid-score_str.length()*SIZE_NUM/2+i*SIZE_NUM+SIZE_NUM, 
					SCORE_Y+50+SIZE_NUM, 
					numPos, 
					0, 
					numPos+SIZE_NUM, 
					SIZE_NUM, 
					null);
		}
	}
	//显示�?��分数�?  实现动�?显示居中效果
	public void drawHightScore(Graphics g){
		String hightScore_str = hightScore+"";
		for(int i=0;i<hightScore_str.length();i++){
			int bit = hightScore_str.charAt(i)-'0';
			int numPos = bit*SIZE_NUM;
			int mid = HIGHTSCORE_X+SIZE_SCORE/2;
			g.drawImage(
					Resource.img_num,
					mid-hightScore_str.length()*SIZE_NUM/2+i*SIZE_NUM,
					HIGHTSCORE_Y + 50,
					mid-hightScore_str.length()*SIZE_NUM/2+i*SIZE_NUM+SIZE_NUM,
					HIGHTSCORE_Y + 50 + SIZE_NUM,
					numPos,
					0,
					numPos+SIZE_NUM,
					SIZE_NUM,
					null);
		}
	}
}
