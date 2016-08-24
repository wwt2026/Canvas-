package com.wwt.canvas;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {
	
	private TextPaint paint;
	private float density = getResources().getDisplayMetrics().density;
	Activity cActivity;
	private Bitmap bitmap;

	public MyView(Context context){
		super(context);
		paint=new TextPaint();

	}
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint=new TextPaint();
		this.cActivity=(Activity) context;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO 自动生成的方法存根
		super.onDraw(canvas);
		Log.i("asd", getStatusBarHeight()+"");
		//对canvas颜色进行绘制，第一个参数是透明度
		//canvas.drawARGB(180, 139, 197, 186);
		//	drawAxis(canvas);
		//	drawText(canvas);
		//drawPointAndLine(canvas);
		//drawRect(canvas);
		//drawCircle(canvas);
		//drawArc(canvas);
		//drawPath(canvas);
		drawBitmap(canvas);
	}

	//绘制坐标系
	private void drawAxis(Canvas canvas){
		int canvasWidth = canvas.getWidth();
		int canvasHeight = canvas.getHeight();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(6 * density);

		//用绿色画x轴，用蓝色画y轴

		//第一次绘制坐标轴
		paint.setColor(0xff00ff00);//绿色
		canvas.drawLine(0, 0, canvasWidth, 0, paint);//绘制x轴
		paint.setColor(0xff0000ff);//蓝色
		canvas.drawLine(0, 0, 0, canvasHeight, paint);//绘制y轴

		//对坐标系平移后，第二次绘制坐标轴
		canvas.translate(canvasWidth / 4, canvasWidth /4);//把坐标系向右下角平移
		paint.setColor(0xff00ff00);//绿色
		canvas.drawLine(0, 0, canvasWidth, 0, paint);//绘制x轴
		paint.setColor(0xff0000ff);//蓝色
		canvas.drawLine(0, 0, 0, canvasHeight, paint);//绘制y轴

		//再次平移坐标系并在此基础上旋转坐标系，第三次绘制坐标轴
		canvas.translate(canvasWidth / 4, canvasWidth / 4);//在上次平移的基础上再把坐标系向右下角平移
		canvas.rotate(30);//基于当前绘图坐标系的原点旋转坐标系
		paint.setColor(0xff00ff00);//绿色
		canvas.drawLine(0, 0, canvasWidth, 0, paint);//绘制x轴
		paint.setColor(0xff0000ff);//蓝色
		canvas.drawLine(0, 0, 0, canvasHeight, paint);//绘制y轴
	}



	private void drawText(Canvas canvas){
		float translateY =getStatusBarHeight();
		paint.setTextSize(50);

		//绘制正常文本
		canvas.save();
		canvas.translate(0, translateY);
		canvas.drawText("正常文本", 0, 0, paint);
		canvas.restore();

		//绘制绿色文本
		translateY += getStatusBarHeight();
		paint.setColor(0xff00ff00);//设置字体为绿色
		canvas.save();
		canvas.translate(0, translateY);//将画笔向下移动
		canvas.drawText("绘制绿色文本", 0, 0, paint);
		paint.setColor(0xff000000);//重新设置为黑色
		canvas.restore();

		//设置左对齐，即文字在原点左边
		translateY+=getStatusBarHeight();
		paint.setTextAlign(Align.LEFT);
		canvas.save();
		canvas.translate(getWidth()/2, translateY);
		canvas.drawText("左对齐文本", 0, 0, paint);
		canvas.restore();

		//设置右对齐
		translateY+=getStatusBarHeight();
		paint.setTextAlign(Align.RIGHT);
		canvas.save();
		canvas.translate(getWidth()/2, translateY);
		canvas.drawText("右对齐文本", 0, 0, paint);
		canvas.restore();

		//设置居中
		translateY+=getStatusBarHeight();
		paint.setTextAlign(Align.CENTER);
		canvas.save();
		canvas.translate(getWidth()/2, translateY);
		canvas.drawText("居中文本", 0, 0, paint);
		canvas.restore();
		paint.setTextAlign(Align.LEFT);

		//设置下划线
		translateY+=getStatusBarHeight();
		paint.setUnderlineText(true);//设置具有下划线
		canvas.save();
		canvas.translate(0, translateY);
		canvas.drawText("下划线文本", 0, 0, paint);
		canvas.restore();
		paint.setUnderlineText(false);//重新设置为没有下划线

		//绘制加粗文字
		translateY+=getStatusBarHeight();
		paint.setFakeBoldText(true);//将画笔设置为粗体
		canvas.save();
		canvas.translate(0, translateY);
		canvas.drawText("粗体文本", 0, 0, paint);
		canvas.restore();
		paint.setFakeBoldText(false);//重新将画笔设置为非粗体状态

		//文本绕绘制起点顺时针旋转
		translateY+=getStatusBarHeight();
		canvas.save();
		canvas.translate(0, translateY);
		canvas.rotate(20);
		canvas.drawText("文本绕绘制起点旋转20度", 0, 0, paint);
		canvas.restore();
	}

	private void drawPointAndLine(Canvas canvas){
		float[] pts={0,0,100,100,100,100,550,320};

		paint.setStrokeWidth(20*density);
		canvas.drawPoint(100, 100, paint);	
		canvas.translate(0, 120);
		paint.setStrokeWidth(density*2);
		canvas.drawLines(pts, paint);
	}	
	private void drawRect(Canvas canvas){
		int canvasWidth = canvas.getWidth();
		int canvasHeight = canvas.getHeight();
		paint.setColor(0xff000000);
		//默认画笔的填充色是黑色
		int left1 = 10;
		int top1 = 10;
		int right1 = canvasWidth / 3;
		int bottom1 = canvasHeight /3;
		canvas.drawRect(left1, top1, right1, bottom1, paint);

		//修改画笔颜色
		paint.setColor(0xff8bc5ba);
		int left2 = canvasWidth / 3 * 2;
		int top2 = 10;
		int right2 = canvasWidth - 10;
		int bottom2 = canvasHeight / 3;
		canvas.drawRect(left2, top2, right2, bottom2, paint);
	}

	private void drawCircle(Canvas canvas){
		paint.setColor(0xff8bc5ba);//设置颜色
		paint.setStyle(Paint.Style.FILL);//默认绘图为填充模式
		int width=getWidth()/2;
		int heigh=getHeight()/6;
		canvas.drawCircle(width,heigh, heigh, paint);
		canvas.translate(0, heigh*2+10);
		//通过两个圆叠加来绘制圆环
		canvas.drawCircle(width, heigh, heigh, paint);
		paint.setColor(getResources().getColor(android.R.color.white));
		canvas.drawCircle(width,heigh,heigh-heigh/5, paint);

		//通过线条来绘制圆环
		canvas.translate(0, heigh*2+10);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth((float) (heigh*0.25));
		paint.setColor(0xff8bc5ba);
		canvas.drawCircle(width, heigh, heigh-40, paint);
	}

	//绘制弧
	private void drawArc(Canvas canvas){
		int canvasWidth = canvas.getWidth();
		int canvasHeight = canvas.getHeight();
		int count = 5;
		float ovalHeight = canvasHeight / (count + 1);
		float left = 10 * density;
		float top = 0;
		float right = canvasWidth - left;
		float bottom= ovalHeight;
		RectF rectF = new RectF(left, top, right, bottom);

		paint.setStrokeWidth(2 * density);//设置线宽
		paint.setColor(0xff8bc5ba);//设置颜色
		paint.setStyle(Paint.Style.FILL);//默认设置画笔为填充模式

		//绘制用drawArc绘制完整的椭圆
		canvas.translate(0, ovalHeight / count);
		canvas.drawArc(rectF, 0, 360, true, paint);

		//绘制椭圆的四分之一,起点是钟表的3点位置，从3点绘制到6点的位置
		canvas.translate(0, (ovalHeight + ovalHeight / count));
		canvas.drawArc(rectF, 0, 90, true, paint);

		//绘制椭圆的四分之一,将useCenter设置为false
		canvas.translate(0, (ovalHeight + ovalHeight / count));
		canvas.drawArc(rectF, 0, 90, false, paint);

		//绘制椭圆的四分之一，只绘制轮廓线
		paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
		canvas.translate(0, (ovalHeight + ovalHeight / count));
		canvas.drawArc(rectF, 0, 90, true, paint);

		//绘制带有轮廓线的椭圆的四分之一
		//1. 先绘制椭圆的填充部分
		paint.setStyle(Paint.Style.FILL);//设置画笔为填充模式
		canvas.translate(0, (ovalHeight + ovalHeight / count));
		canvas.drawArc(rectF, 0, 90, true, paint);
		//2. 再绘制椭圆的轮廓线部分
		paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
		paint.setColor(0xff0000ff);//设置轮廓线条为蓝色
		canvas.drawArc(rectF, 0, 90, true, paint);
	}

	private void drawPath(Canvas canvas){

		int canvasWidth = canvas.getWidth();
		int deltaX = canvasWidth / 4;
		int deltaY = (int)(deltaX * 0.75);

		paint.setColor(0xff8bc5ba);//设置画笔颜色
		paint.setStrokeWidth(4);//设置线宽

		//用path填充图形
		paint.setStyle(Paint.Style.FILL);//设置画笔为填充模式
		Path path = new Path();
		//向Path中加入Arc
		RectF arcRecF = new RectF(0, 0, deltaX, deltaY);
		path.addArc(arcRecF, 0, 135);
		//向Path中加入Oval
		RectF ovalRecF = new RectF(deltaX, 0, deltaX * 2, deltaY);
		path.addOval(ovalRecF, Path.Direction.CCW);
		//向Path中添加Circle
		path.addCircle((float)(deltaX * 2.5), deltaY / 2, deltaY / 2, Path.Direction.CCW);
		//向Path中添加Rect
		RectF rectF = new RectF(deltaX * 3, 0, deltaX * 4, deltaY);
		path.addRect(rectF, Path.Direction.CCW);
		canvas.drawPath(path, paint);

		//用path画线
		paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
		canvas.translate(0, deltaY * 2);
		Path path2 = path;
		canvas.drawPath(path2, paint);

		//使用lineTo、arcTo、quadTo、cubicTo画线
		paint.setStyle(Style.STROKE);
		canvas.translate(0, deltaY*2);
		Path path3=new Path();
		//将每个点用list存储
		List<Point> pointlist=new ArrayList<Point>();
		//直线
		path3.moveTo(0, 0);
		path3.lineTo(deltaX/2, 0);
		pointlist.add(new Point(0, 0));
		pointlist.add(new Point(deltaX/2, 0));

		//绘制椭圆右上角的四分之一的弧线
		RectF rectF2=new RectF(0,0, deltaX, deltaY);
		path3.arcTo(rectF2, 270, 150);

		//这里画椭圆的顺序是顺时针的
		//绘制椭圆左下角的四分之一的弧线
		RectF rectF3 = new RectF(deltaX, 0, deltaX * 2, deltaY);
		path3.addArc(rectF3, 90, 130);
		pointlist.add(new Point((int)(deltaX * 1.5), deltaY));

		//绘制二阶贝塞尔曲线
		// 二阶贝塞尔曲线的起点就是当前画笔的位置，然后需要添加一个控制点，以及一个终点
		path3.quadTo(deltaX * 2, 0, deltaX * 2.5f, deltaY / 2);
		pointlist.add(new Point((int) (deltaX * 2.5f),deltaY / 2));

		//绘制三阶贝塞尔曲线，三阶贝塞尔曲线的起点也是当前画笔的位置
		//其需要两个控制点，即比二阶贝赛尔曲线多一个控制点，最后也需要一个终点
		//将画笔移到该点处
		path3.moveTo(deltaX * 2.5f, deltaY / 2);
		path3.cubicTo(deltaX * 3, 0, deltaX * 3.5f, 0, deltaX * 4, deltaY);
		pointlist.add(new Point(deltaX * 4, deltaY));


		canvas.drawPath(path3, paint);



		paint.setStrokeWidth(10);//将点的strokeWidth要设置的比画path时要大
		paint.setStrokeCap(Paint.Cap.ROUND);//将点设置为圆点状
		paint.setColor(0xff0000ff);//设置圆点为蓝色
		for(Point p : pointlist){
			//遍历pointList，绘制连接点
			canvas.drawPoint(p.x, p.y, paint);
		}
	}

	private void drawBitmap(Canvas canvas){
		//如果bitmap不存在，那么就不执行下面的绘制代码
		
bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		//直接完全绘制Bitmap
		canvas.drawBitmap(bitmap, 0, 0, paint);

		//绘制Bitmap的一部分，并对其拉伸
		//srcRect定义了要绘制Bitmap的哪一部分
		Rect srcRect = new Rect();
		srcRect.left = 0;
		srcRect.right = bitmap.getWidth();
		srcRect.top = 0;
		srcRect.bottom = (int)(0.33 * bitmap.getHeight());
		float radio = (float)(srcRect.bottom - srcRect.top)  / bitmap.getWidth();
		//dstRecF定义了要将绘制的Bitmap拉伸到哪里
		RectF dstRecF = new RectF();
		dstRecF.left = 0;
		dstRecF.right = canvas.getWidth();
		dstRecF.top = bitmap.getHeight();
		float dstHeight = (dstRecF.right - dstRecF.left) * radio;
		dstRecF.bottom = dstRecF.top + dstHeight;
		canvas.drawBitmap(bitmap, srcRect, dstRecF, paint);
	}



	private int getStatusBarHeight() {  
		Rect rect = new Rect();  
		cActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);  
		return rect.top;
	} 
	
	
}
