package com.chinasofti.game.resource;
import java.awt.Graphics;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.TimeZone;

import javax.swing.JOptionPane;
/**
 * 实现游戏主要显示功能和主要流程处理功�?
 */
public class GameService {
	private Data data;		//游戏中的分数信息
	private int[][] gameMap;//存储游戏中对应的4*4 方格的数字图片的索引编号
	public static final int SIZE = 100;// �?��次背景方格大�?
	long starttime;
	
	//初始化成�?
	public GameService(){
		this.data = new Data();
		this.gameMap = new int[4][4];
		this.start();
	}
	/**
	 * 画出游戏面板上的内容：1.分数板图片以及分数数�?  2，次背景方格内的数字图片
	 * @param g    画笔
	 * blockIndex  方块中数字图片索引编号，总共1~11分别为
	 * 			     1：数2   2：数4   3：数8  4：数16	5:数32		6：数64
	 * 				 7:数128	8：数256 9：数512	10：数1024		11：数2048
	 */
	public void gamePaint(Graphics g){
		
		data.drawData(g);//画分数板区域功能
		//画次背景方格内数字图片功能
		int blockIndex;//临时变量存储gameMap遍历出来的数字图片索引编号
		for(int i=0;i<4;i++){//i表示列
			for(int j=0;j<4;j++){//j表示行
				if(gameMap[i][j]!=0){
					blockIndex=gameMap[i][j];
					switch(blockIndex){
					//drawNumPic(img,x,y,null)中 参数x表示图形原点行坐标,y表示图形原点纵坐标
					case 1://i表示列  j表示行
						drawNumPic(g,Resource.img_2,j,i);break;
					case 2:
						drawNumPic(g,Resource.img_4,j,i);break;
					case 3:
						drawNumPic(g,Resource.img_8,j,i);break;
					case 4:
						drawNumPic(g,Resource.img_16,j,i);break;
					case 5:
						drawNumPic(g,Resource.img_32,j,i);break;
					case 6:
						drawNumPic(g,Resource.img_64,j,i);break;
					case 7:
						drawNumPic(g,Resource.img_128,j,i);break;
					case 8:
						drawNumPic(g,Resource.img_256,j,i);break;
					case 9:
						drawNumPic(g,Resource.img_512,j,i);break;
					case 10:
						drawNumPic(g,Resource.img_1024,j,i);break;
					case 11:
						drawNumPic(g,Resource.img_2048,j,i);break;
					}
				}
			}
		}
	}
	/**
	 * 根据传入的画笔等参数画出图片数字
	 * @param g     传入的画笔?
	 * @param img   需要画出的图片对象?
	 * @param x		数字图片在该行的第几个格�? 对应二维数组gameMap[i][j] 的j�?
	 * @param y		数字图片在该列的第几个格�? 对应二维数组gameMap[i][j] 的i�?
	 */
	public void drawNumPic(Graphics g,Image img,int x,int y){
		g.drawImage(img,Background.FG_X+x*SIZE,Background.FG_Y+y*SIZE,null);
	}
	//开始游戏功能，分数和最高分数0，随机生成2个方块?
	public void start(){
		gameMap = new int[4][4];
		data.setScore(0);
		data.setHightScore(0);
		newBlock();
		newBlock();
		starttime=System.currentTimeMillis();
	}
	/**
	 * 在4*4方格随机位置生成方块，如果位置上存在方块则再生成，直到不重复，方块图片为2或4，生成方块4的概率为1/8 
	 */
	public void newBlock(){
		Random ran = new Random();
		int i,j;//生成方块的横、纵坐标位置
		do{// 获取新坐标，产生一个0~3的随机数，用于确定新生成方块的i和j的位置
		 i = ran.nextInt(4);
		 j = ran.nextInt(4);
		}while(gameMap[i][j]!=0);//如果方块位置已被占用，随机另一个位置
		//确定生成位置后，确定随机生成的是方块图片2还是4
		int index = ran.nextInt(8);
		if(index!=0){
			gameMap[i][j] = 1;
		}else{
			gameMap[i][j] = 2;
		}
	}
	 //左移
	public int moveLeft(){
		//System.out.println("左移操作");
		int isMove=0;//定义移动标记0为未移动,1为移动过
		for(int i=0;i<4;i++){
			//j对应数字图片4列,从1开始，3结束 第一列不需要左移动
			for(int j=1;j<4;j++){
				int mov_i=i;//当前图片位置的横坐标值
				int mov_j=j;//当前图片位置的纵坐标值
				/*
				 * 如果当前的格子编号值不为0 并且左边一个格子编号为0 左移动:
				 * 1.左移动则当前图片位置的纵坐标mov_j减少,将mov_j位置的图片索引赋值给mov_j-1位置,
				 * mov_j位置的图片索引改为0
				 * 2.如果代表当前图片位置的纵坐标大于1,表示图片还能左移,还需要移动后将代表当前格子
				 * 位置的纵坐标减1
				 * 3.将移动标记isMove赋值为1,表示有格子移动过
				 * 4.循环上面3个步骤,直到判断条件不成立
				 */
				while(gameMap[mov_i][mov_j]!=0&&gameMap[mov_i][mov_j-1]==0){
					gameMap[mov_i][mov_j-1]=gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j]=0;
					if(mov_j>1){
						//如果mov_j的值大于1则还可以左移,在左移完之后给当前代表前格子的列值mov_j值减去1
						mov_j--;
					}
					isMove=1;
				}
			}
		}
		return isMove;
	}
	
	//右移
	public int moveRight(){
		int isMove=0;
		for(int i=0;i<4;i++){
			for(int j=2;j>=0;j--){
				int mov_i=i;
				int mov_j=j;
				while(gameMap[mov_i][mov_j]!=0&&gameMap[mov_i][mov_j+1]==0){
					gameMap[mov_i][mov_j+1]=gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j]=0;
					if(mov_j<2){
						//如果mov_j的值小于2则还可以右移,在右移完之后给当代表前格子的列值mov_j值增加1
						mov_j++;
					}
					isMove=1;
				}
			}
		}
		return isMove;
	}
	
	//下移
	public int moveDown(){
		int isMove=0;
		for(int i=2;i>=0;i--){
			for(int j=0;j<4;j++){
				int mov_i=i;
				int mov_j=j;
				while(gameMap[mov_i][mov_j]!=0&&gameMap[mov_i+1][mov_j]==0){
					gameMap[mov_i+1][mov_j]=gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j]=0;
					if(mov_i<2){
						//如果mov_i的值小于2则还可以下移,在下移完之后给当代表前格子的行值mov_i值增加1
						mov_i++;
					}
					isMove=1;
				}
			}
		}
		return isMove;
	}
	
	//上移
	public int moveUp(){
		int isMove=0;
		for(int i=1;i<4;i++){
			for(int j=0;j<4;j++){
				int mov_i=i;
				int mov_j=j;
				while(gameMap[mov_i][mov_j]!=0&&gameMap[mov_i-1][mov_j]==0){
					gameMap[mov_i-1][mov_j]=gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j]=0;
					if(mov_i>1){
						//如果mov_i的值大于1则还可以上移,在上移完之后给当代表前格子的行值mov_i值减去1
						mov_i--;
					}
					isMove=1;
				}
			}
		}
		return isMove;
	}
	
	//左消�? 在左消除之前已经调用了左移动，所以，格子都已经左移到左边
	public int removeLeft(){
		int isRemove=0;
		for(int i=0;i<4;i++){
			for(int j=0;j<3;j++){
				//对应数字图片4列,为3的格子不需要尝试合并,右边没有格子了,且从值为0的格子开始左消
				/*
				 * 如果当前的格子索引编号值不为0 并且右边一个格子索引编号等于当前格子索引编号 左消除:
				 * 1.左消除则当前图片位置的纵坐标值mov_j位置的图片索引赋值增加1,mov_j+1位置的图片索引改为0
				 * 2.将消除标记isRemove赋值为1,表示有格子被消除过
				 * 3.循环上面2个步骤,直到判断条件不成立
				 */
				while(gameMap[i][j]!=0&&gameMap[i][j]==gameMap[i][j+1]){
					gameMap[i][j]++;//合并后数字索引值加1
					gameMap[i][j+1]=0;//右边方格图片消除
					bonus(gameMap[i][j]);
					isRemove=1;
				}
			}
		}
		moveLeft();//入一行内遇到两个消除,从值为0的格子开始左消除完后还要左移
		return isRemove;
	}
	
	//右消除，键盘右方向键触发，左边的图片尝试�?��边合�? 在右消除之前已经调用了右移动，所以，格子都已经右移到右边
	public int removeRight(){
		int isRemove=0;
		for(int i=0;i<4;i++){
			for(int j=3;j>=1;j--){
				while(gameMap[i][j]!=0&&gameMap[i][j]==gameMap[i][j-1]){
					gameMap[i][j]++;
					gameMap[i][j-1]=0;
					bonus(gameMap[i][j]);
					isRemove=1;
				}
			}
		}
		moveRight();
		return isRemove;
	}
	
	//上消除，键盘上方向键触发，下边的图片尝试�?��边合�? 因为在上消除之前已经调用了上移动，所以，格子都已经上移到上边
	public int removeUp(){
		int isRemove=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<4;j++){
				while(gameMap[i][j]!=0&&gameMap[i][j]==gameMap[i+1][j]){
					gameMap[i][j]++;
					gameMap[i+1][j]=0;
					bonus(gameMap[i][j]);
					isRemove=1;
				}
			}
		}
		moveUp();
		return isRemove;
	}
	
	//下消除，键盘下方向键触发，上边的图片尝试�?��边合�? 因为在下消除之前已经调用了下移动，所以，格子都已经下移到下边
	public int removeDown(){
		int isRemove=0;
		for(int i=3;i>0;i--){
			for(int j=0;j<4;j++){
				while(gameMap[i][j]!=0&&gameMap[i][j]==gameMap[i-1][j]){
					gameMap[i][j]++;
					gameMap[i-1][j]=0;
					bonus(gameMap[i][j]);
					isRemove=1;
				}
			}
		}
		moveDown();
		return isRemove;
	}
	
	/**
	 *计算当前分 分数增量为消除后的数字图片面值大小
	 */
	public void bonus(int num){
		int score = data.getScore();
		score += Math.pow(2, num);//计算2的num次方并累加给score
		data.setScore(score);
	}
	/**
	 * 更新最高分
	 */
	public void refreshHightscore(){
		int score = data.getScore();
		if(score>data.getHightScore()){
			data.setHightScore(score);
		}
	}

	//能否移动  ，true为可以  false为不可以
	public boolean canMove(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(i<3){
					if(gameMap[i][j]==gameMap[i+1][j]){
						return true;//可以上消
					}
				}
				if(i>0){
					if(gameMap[i][j]==gameMap[i-1][j]){
						return true;//可以下消
					}
				}
				if(j<3){
					if(gameMap[i][j]==gameMap[i][j+1]){
						return true;//可以左消
					}
				}
				if(j>0){
					if(gameMap[i][j]==gameMap[i][j-1]){
						return true;//可以右消
					}
				}
			}
		}
		return false;
	}
	/*
	 * 判断是否占满所有方格，并且不能移动
	 */
	public boolean isFull(){
		boolean flag = true;  // 临时变量用来存储是否占满了所有方格   占满了为true，未占满为false
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(gameMap[i][j]==0){//索引标记如果为0 则表示格子未占满
					flag = false;
				}
			}
		}
		if(flag&&!canMove()){//占满了，且不能移�?
			return true;
		}
		return false;
	}
	/*
	 * 更新�?��分记�?
	 */
	public void refreshHighScore(){
		int score = data.getScore();
		if(score>data.getHightScore()){
			data.setHightScore(score);
		}
	}
	//重新开始 地图、分数清零,更新最高分数,生产2个方块
	public void restart(){
		gameMap = new int[4][4];
		starttime=System.currentTimeMillis();
		data.setScore(0);
		newBlock();
		newBlock();
	}
	/**
	 * 游戏结束
	 */
	public void isGameOver(){
		if(isFull()){
			Object[] options = {"重新开始","退出游戏"};
			/*
			 * JOptionPane.showOptionDialog
			 * (parentComponent, message, title, optionType, messageType,icon, options, initialValue)
			 * 
			 * JOptionPane:弹出要求用户提供值或向其发出通知的标准对话框
			 * showOptionDialog:询问�?��确认问题，如 yes/no/cancel�?
			 * parentComponent:定义父对话框，设置为null则默认的 Frame 用作父级
			 * message：要置于对话框中的描述消息，类型�?Object[]
			 * title:对话框的标题
			 * optionType:定义在对话框的底部显示的选项按钮的类�? YES_NO_OPTION
			 * messageType：定�?message 的样�?  QUESTION_MESSAGE
			 * icon:要置于对话框中的装饰性图标�?设置为null图标的默认�?�?messageType 参数确定
			 * options：将在对话框底部显示的�?项按钮集合的更详细描述�?参数类型�?Object[] 
			 * initialValue:默认选择    
			 */
			
			long endtime=System.currentTimeMillis();			
			long time=endtime-starttime;
			
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");//初始化Formatter的转换格式。  			  
			
			int choice = JOptionPane.showOptionDialog(
							null,
							"\n  游戏结束!\n  分数为"+data.getScore()+"\n  用时"+formatter.format(time - TimeZone.getDefault().getRawOffset()), 
							"游戏提示", 
							JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE, 
							null, 
							options, 
							options[0]);
			refreshHighScore();//更新最高分
			if(choice==0){//选择重新开始�还是结束游戏，与options数组下标对应
				restart();//重新开始
			}else{
				System.exit(0);//结束游戏
			}
		}else if(is2048()){
			Object[] options = {"重新开始","退出游戏"};
			int choice = JOptionPane.showOptionDialog(
					null,
					"   Y^o^Y恭喜你大神！达成2048 ",
					"游戏提示", 
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					options,
					options[0]);
			
			refreshHighScore();
			if(choice==1){
				restart();
			}else{
				System.exit(0);
			}
		}
	}
	/*
	 * 判断是否达成2048
	 */
	public boolean is2048(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(gameMap[i][j]==11){
					return true;
				}
			}
		}
		return false;
	}
}
