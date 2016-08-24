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
		// TODO �Զ����ɵķ������
		super.onDraw(canvas);
		Log.i("asd", getStatusBarHeight()+"");
		//��canvas��ɫ���л��ƣ���һ��������͸����
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

	//��������ϵ
	private void drawAxis(Canvas canvas){
		int canvasWidth = canvas.getWidth();
		int canvasHeight = canvas.getHeight();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(6 * density);

		//����ɫ��x�ᣬ����ɫ��y��

		//��һ�λ���������
		paint.setColor(0xff00ff00);//��ɫ
		canvas.drawLine(0, 0, canvasWidth, 0, paint);//����x��
		paint.setColor(0xff0000ff);//��ɫ
		canvas.drawLine(0, 0, 0, canvasHeight, paint);//����y��

		//������ϵƽ�ƺ󣬵ڶ��λ���������
		canvas.translate(canvasWidth / 4, canvasWidth /4);//������ϵ�����½�ƽ��
		paint.setColor(0xff00ff00);//��ɫ
		canvas.drawLine(0, 0, canvasWidth, 0, paint);//����x��
		paint.setColor(0xff0000ff);//��ɫ
		canvas.drawLine(0, 0, 0, canvasHeight, paint);//����y��

		//�ٴ�ƽ������ϵ���ڴ˻�������ת����ϵ�������λ���������
		canvas.translate(canvasWidth / 4, canvasWidth / 4);//���ϴ�ƽ�ƵĻ������ٰ�����ϵ�����½�ƽ��
		canvas.rotate(30);//���ڵ�ǰ��ͼ����ϵ��ԭ����ת����ϵ
		paint.setColor(0xff00ff00);//��ɫ
		canvas.drawLine(0, 0, canvasWidth, 0, paint);//����x��
		paint.setColor(0xff0000ff);//��ɫ
		canvas.drawLine(0, 0, 0, canvasHeight, paint);//����y��
	}



	private void drawText(Canvas canvas){
		float translateY =getStatusBarHeight();
		paint.setTextSize(50);

		//���������ı�
		canvas.save();
		canvas.translate(0, translateY);
		canvas.drawText("�����ı�", 0, 0, paint);
		canvas.restore();

		//������ɫ�ı�
		translateY += getStatusBarHeight();
		paint.setColor(0xff00ff00);//��������Ϊ��ɫ
		canvas.save();
		canvas.translate(0, translateY);//�����������ƶ�
		canvas.drawText("������ɫ�ı�", 0, 0, paint);
		paint.setColor(0xff000000);//��������Ϊ��ɫ
		canvas.restore();

		//��������룬��������ԭ�����
		translateY+=getStatusBarHeight();
		paint.setTextAlign(Align.LEFT);
		canvas.save();
		canvas.translate(getWidth()/2, translateY);
		canvas.drawText("������ı�", 0, 0, paint);
		canvas.restore();

		//�����Ҷ���
		translateY+=getStatusBarHeight();
		paint.setTextAlign(Align.RIGHT);
		canvas.save();
		canvas.translate(getWidth()/2, translateY);
		canvas.drawText("�Ҷ����ı�", 0, 0, paint);
		canvas.restore();

		//���þ���
		translateY+=getStatusBarHeight();
		paint.setTextAlign(Align.CENTER);
		canvas.save();
		canvas.translate(getWidth()/2, translateY);
		canvas.drawText("�����ı�", 0, 0, paint);
		canvas.restore();
		paint.setTextAlign(Align.LEFT);

		//�����»���
		translateY+=getStatusBarHeight();
		paint.setUnderlineText(true);//���þ����»���
		canvas.save();
		canvas.translate(0, translateY);
		canvas.drawText("�»����ı�", 0, 0, paint);
		canvas.restore();
		paint.setUnderlineText(false);//��������Ϊû���»���

		//���ƼӴ�����
		translateY+=getStatusBarHeight();
		paint.setFakeBoldText(true);//����������Ϊ����
		canvas.save();
		canvas.translate(0, translateY);
		canvas.drawText("�����ı�", 0, 0, paint);
		canvas.restore();
		paint.setFakeBoldText(false);//���½���������Ϊ�Ǵ���״̬

		//�ı��ƻ������˳ʱ����ת
		translateY+=getStatusBarHeight();
		canvas.save();
		canvas.translate(0, translateY);
		canvas.rotate(20);
		canvas.drawText("�ı��ƻ��������ת20��", 0, 0, paint);
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
		//Ĭ�ϻ��ʵ����ɫ�Ǻ�ɫ
		int left1 = 10;
		int top1 = 10;
		int right1 = canvasWidth / 3;
		int bottom1 = canvasHeight /3;
		canvas.drawRect(left1, top1, right1, bottom1, paint);

		//�޸Ļ�����ɫ
		paint.setColor(0xff8bc5ba);
		int left2 = canvasWidth / 3 * 2;
		int top2 = 10;
		int right2 = canvasWidth - 10;
		int bottom2 = canvasHeight / 3;
		canvas.drawRect(left2, top2, right2, bottom2, paint);
	}

	private void drawCircle(Canvas canvas){
		paint.setColor(0xff8bc5ba);//������ɫ
		paint.setStyle(Paint.Style.FILL);//Ĭ�ϻ�ͼΪ���ģʽ
		int width=getWidth()/2;
		int heigh=getHeight()/6;
		canvas.drawCircle(width,heigh, heigh, paint);
		canvas.translate(0, heigh*2+10);
		//ͨ������Բ����������Բ��
		canvas.drawCircle(width, heigh, heigh, paint);
		paint.setColor(getResources().getColor(android.R.color.white));
		canvas.drawCircle(width,heigh,heigh-heigh/5, paint);

		//ͨ������������Բ��
		canvas.translate(0, heigh*2+10);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth((float) (heigh*0.25));
		paint.setColor(0xff8bc5ba);
		canvas.drawCircle(width, heigh, heigh-40, paint);
	}

	//���ƻ�
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

		paint.setStrokeWidth(2 * density);//�����߿�
		paint.setColor(0xff8bc5ba);//������ɫ
		paint.setStyle(Paint.Style.FILL);//Ĭ�����û���Ϊ���ģʽ

		//������drawArc������������Բ
		canvas.translate(0, ovalHeight / count);
		canvas.drawArc(rectF, 0, 360, true, paint);

		//������Բ���ķ�֮һ,������ӱ��3��λ�ã���3����Ƶ�6���λ��
		canvas.translate(0, (ovalHeight + ovalHeight / count));
		canvas.drawArc(rectF, 0, 90, true, paint);

		//������Բ���ķ�֮һ,��useCenter����Ϊfalse
		canvas.translate(0, (ovalHeight + ovalHeight / count));
		canvas.drawArc(rectF, 0, 90, false, paint);

		//������Բ���ķ�֮һ��ֻ����������
		paint.setStyle(Paint.Style.STROKE);//���û���Ϊ����ģʽ
		canvas.translate(0, (ovalHeight + ovalHeight / count));
		canvas.drawArc(rectF, 0, 90, true, paint);

		//���ƴ��������ߵ���Բ���ķ�֮һ
		//1. �Ȼ�����Բ����䲿��
		paint.setStyle(Paint.Style.FILL);//���û���Ϊ���ģʽ
		canvas.translate(0, (ovalHeight + ovalHeight / count));
		canvas.drawArc(rectF, 0, 90, true, paint);
		//2. �ٻ�����Բ�������߲���
		paint.setStyle(Paint.Style.STROKE);//���û���Ϊ����ģʽ
		paint.setColor(0xff0000ff);//������������Ϊ��ɫ
		canvas.drawArc(rectF, 0, 90, true, paint);
	}

	private void drawPath(Canvas canvas){

		int canvasWidth = canvas.getWidth();
		int deltaX = canvasWidth / 4;
		int deltaY = (int)(deltaX * 0.75);

		paint.setColor(0xff8bc5ba);//���û�����ɫ
		paint.setStrokeWidth(4);//�����߿�

		//��path���ͼ��
		paint.setStyle(Paint.Style.FILL);//���û���Ϊ���ģʽ
		Path path = new Path();
		//��Path�м���Arc
		RectF arcRecF = new RectF(0, 0, deltaX, deltaY);
		path.addArc(arcRecF, 0, 135);
		//��Path�м���Oval
		RectF ovalRecF = new RectF(deltaX, 0, deltaX * 2, deltaY);
		path.addOval(ovalRecF, Path.Direction.CCW);
		//��Path�����Circle
		path.addCircle((float)(deltaX * 2.5), deltaY / 2, deltaY / 2, Path.Direction.CCW);
		//��Path�����Rect
		RectF rectF = new RectF(deltaX * 3, 0, deltaX * 4, deltaY);
		path.addRect(rectF, Path.Direction.CCW);
		canvas.drawPath(path, paint);

		//��path����
		paint.setStyle(Paint.Style.STROKE);//���û���Ϊ����ģʽ
		canvas.translate(0, deltaY * 2);
		Path path2 = path;
		canvas.drawPath(path2, paint);

		//ʹ��lineTo��arcTo��quadTo��cubicTo����
		paint.setStyle(Style.STROKE);
		canvas.translate(0, deltaY*2);
		Path path3=new Path();
		//��ÿ������list�洢
		List<Point> pointlist=new ArrayList<Point>();
		//ֱ��
		path3.moveTo(0, 0);
		path3.lineTo(deltaX/2, 0);
		pointlist.add(new Point(0, 0));
		pointlist.add(new Point(deltaX/2, 0));

		//������Բ���Ͻǵ��ķ�֮һ�Ļ���
		RectF rectF2=new RectF(0,0, deltaX, deltaY);
		path3.arcTo(rectF2, 270, 150);

		//���ﻭ��Բ��˳����˳ʱ���
		//������Բ���½ǵ��ķ�֮һ�Ļ���
		RectF rectF3 = new RectF(deltaX, 0, deltaX * 2, deltaY);
		path3.addArc(rectF3, 90, 130);
		pointlist.add(new Point((int)(deltaX * 1.5), deltaY));

		//���ƶ��ױ���������
		// ���ױ��������ߵ������ǵ�ǰ���ʵ�λ�ã�Ȼ����Ҫ���һ�����Ƶ㣬�Լ�һ���յ�
		path3.quadTo(deltaX * 2, 0, deltaX * 2.5f, deltaY / 2);
		pointlist.add(new Point((int) (deltaX * 2.5f),deltaY / 2));

		//�������ױ��������ߣ����ױ��������ߵ����Ҳ�ǵ�ǰ���ʵ�λ��
		//����Ҫ�������Ƶ㣬���ȶ��ױ��������߶�һ�����Ƶ㣬���Ҳ��Ҫһ���յ�
		//�������Ƶ��õ㴦
		path3.moveTo(deltaX * 2.5f, deltaY / 2);
		path3.cubicTo(deltaX * 3, 0, deltaX * 3.5f, 0, deltaX * 4, deltaY);
		pointlist.add(new Point(deltaX * 4, deltaY));


		canvas.drawPath(path3, paint);



		paint.setStrokeWidth(10);//�����strokeWidthҪ���õıȻ�pathʱҪ��
		paint.setStrokeCap(Paint.Cap.ROUND);//��������ΪԲ��״
		paint.setColor(0xff0000ff);//����Բ��Ϊ��ɫ
		for(Point p : pointlist){
			//����pointList���������ӵ�
			canvas.drawPoint(p.x, p.y, paint);
		}
	}

	private void drawBitmap(Canvas canvas){
		//���bitmap�����ڣ���ô�Ͳ�ִ������Ļ��ƴ���
		
bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		//ֱ����ȫ����Bitmap
		canvas.drawBitmap(bitmap, 0, 0, paint);

		//����Bitmap��һ���֣�����������
		//srcRect������Ҫ����Bitmap����һ����
		Rect srcRect = new Rect();
		srcRect.left = 0;
		srcRect.right = bitmap.getWidth();
		srcRect.top = 0;
		srcRect.bottom = (int)(0.33 * bitmap.getHeight());
		float radio = (float)(srcRect.bottom - srcRect.top)  / bitmap.getWidth();
		//dstRecF������Ҫ�����Ƶ�Bitmap���쵽����
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
