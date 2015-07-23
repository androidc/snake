package com.example.snake5;

import java.util.Arrays;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;

public class GameView7 extends SurfaceView implements OnTouchListener {
	String TAG = this.getClass().getSimpleName();
	public int posMeat, GlobDir = 1, scores;
	private GameManager7 gameLoopThread;
	private Canvas mCanvas;
	Snake7 mSnake;
	static int height, width;
	public static int yS; // ���������� ����
	Bitmap bitmap;
    Bitmap bitmapAlpha;
    Paint bPaint, paint, lPaint, iPaint, tPaint;
    Rect bRect, myRect, iRect;
    float[] ptsH, ptsV;
    Path path;
    float xT, yT; // ���������� ������
    
	@Override
	protected void onDraw(Canvas canvas) {	
		canvas.drawRect(iRect, iPaint);
		canvas.drawText("����: ", xT, yT, tPaint);
		canvas.drawRect(myRect, paint);
		canvas.drawRect(bRect, bPaint); // ������
		canvas.drawLines(ptsH, lPaint); // �����
		canvas.drawLines(ptsV, lPaint); // ������������ �����
	}
	
	public GameView7(Context context) {
		super(context);		
		SurfaceHolder holder = getHolder();
		
		gameLoopThread = new GameManager7(this);
		
		setOnTouchListener(this);
		
		holder.addCallback(new SurfaceHolder.Callback() {
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) { }
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				 gameLoopThread.setRunning(true);
				 gameLoopThread.start();				 
			}
	
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				Log.d(TAG, width +";"+ height);
				// 480x690 (� ������)
				int y = 60; // ������ ������ (��������)
				int hS = 11; // ������ ���� ����
				int hB = hS/2; // ������ ������� (0 - ��� �������)
				
				// ������ ������� (�����)
				int ny = height - hB; // ���������/������������� y-����������
				Log.d(TAG, "lastY: " + ny);
				int n = 0; // ���-�� �����
				while (ny > y) {
					n++; ny -= hS; // ����� �� ����� �� ������ ����
				}
				y = ny; // ������ ������ �����
				
				/**************** ����� *************************/
				tPaint = new Paint();
				tPaint.setColor(Color.WHITE);
				tPaint.setTextSize(14);
				tPaint.setAntiAlias(true);
				xT = 14; yT = y/2;
				
				/**************** ����� *************************/
				lPaint = new Paint(); 
				lPaint.setColor(Color.YELLOW);					
				ptsH = new float[n*4];
				int o = 0;		
				for (int i = 1; i < n; i++) {
					int yy = y + (hS * i);
					ptsH[o++] = hS + hB; // x1
					ptsH[o++] = yy; // y1					
					ptsH[o++] = width - hS - hB; // x2
					ptsH[o++] = yy; //y2
				}
				y -= hB; // y-���������� � ������ �������
				
				int x = width/2;
				ptsV = new float[8];
				o = 0;	
				for (int i = 1; i < 2; i++) {
					ptsV[o++] = x * i; // x1
					ptsV[o++] = y + hS + hB; // y1					
					ptsV[o++] = x * i; // x2
					ptsV[o++] = height - hB - hS; //y2
				}
				
				/**************** ���� ���������� ****************/
				iRect = new Rect();
				iRect.set(0, 0, width, y);//������ ����������		
			  	iPaint = new Paint(); // �����		
			  	iPaint.setColor(Color.BLUE);// ���� �����	
			  	iPaint.setStyle(Paint.Style.FILL);// ��� - �������				
				/**************** ���� �����. ********************/ 
				bRect = new Rect();
				bRect.set(hB, y + hB, width - hB, height - hB);//������ ����������		
			  	bPaint = new Paint(); // �����		
			  	bPaint.setColor(Color.GREEN);// ���� �����	
			  	bPaint.setStyle(Paint.Style.FILL);// ��� - �������				
				
				/**************** ���� ������� *******************/
				myRect = new Rect();
				myRect.set(0, y, width, height);//������ ����������		
			  	paint = new Paint(); // �����		
				paint.setColor(Color.RED);// ���� �����	
				paint.setStyle(Paint.Style.FILL);// ��� - �������
				
				yS = y + hB; // y-���������� ����
				
				/**************** ������ ���� ****************/
				mSnake = new Snake7(100, yS);
				mSnake.setApple();
			}
		});	
	}
	
	float x, y;
	public boolean onTouch(View v, MotionEvent event) {
		x = (event.getActionMasked() == MotionEvent.ACTION_DOWN) ? event
				.getX() : x;
		y = (event.getActionMasked() == MotionEvent.ACTION_DOWN) ? event
				.getY() : y;
		setDir((event.getActionMasked() == MotionEvent.ACTION_UP) ? ((Math
				.abs(event.getX() - x) > Math.abs(event.getY() - y) && (event
				.getX() - x) > 0) ? 3
				: (((Math.abs(event.getX() - x) > Math.abs(event.getY() - y)) && (event
						.getX() - x) <= 0) ? 2
						: ((event.getY() - y) > 0 ? 1 : 0)))
				: GlobDir);
//		Log.d(TAG, x + "; " + y);
		return true;
	}
	
	public void setDir(int dir) {
		GlobDir = ((dir + GlobDir == 1) || (dir + GlobDir == 5)) ? GlobDir
				: dir;
	}
	
}
