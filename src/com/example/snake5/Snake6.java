package com.example.snake5;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;

public class Snake6 {
	String TAG = this.getClass().getSimpleName();
	
    // ��������� �����������
    public static final int DIR_UP = 1;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_DOWN = 3;
    public static final int DIR_LEFT = 4;    
    
    // ���� ���� - ������ ��������� ��������� ������� ��������
    private ArrayList<pos> mSnake = new ArrayList<pos>();
 
    // ������� ����������� �������� ����
    int mDirection = DIR_RIGHT;
    
	private static final Random RNG = new Random(); // 
    
    // ����� ������������ �������
    public class pos {
        int x, y;
        pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }    
	
    Snake6(int oX, int oY) { // ��������� �������
        mSnake.add(new pos(oX, oY));
        mSnake.add(new pos(oX, oY));
        mSnake.add(new pos(oX, oY));
    } 
    
    int appleX, appleY;
    void setApple2() {
    	boolean n = false;
    	int x = getmSnake().get(1).x;
    	int y = getmSnake().get(1).y;    	
    	while (n != true) {
    		appleX = 10 * RNG.nextInt(GameView6.width/10);
    		appleY = 10 * RNG.nextInt(GameView6.height/10);        	
        	if (x != appleX && y != appleY) {
        		n = true;
        	}    		
    	}
    	Log.d(TAG, appleX +"x" + appleY);
    }
    
    /** ��������� �������� ������������� ������� */
    void setTile() { 
        for (int i = mSnake.size(); i > 1 ; i--) {
        	getmSnake().get(i - 1).x = getmSnake().get(i - 2).x;
        	getmSnake().get(i - 1).y = getmSnake().get(i - 2).y;
        }
    }
    
    int cWidth = GameView6.cWidth;
    int width = GameView6.width;
    int height = GameView6.heightReal;
    int top = GameView6.top;
    
    /** ������ �������� */
    public boolean thisMove() {
    	int x = getmSnake().get(0).x; // x-���������� ������
    	int y = getmSnake().get(0).y; // y-���������� ������
    	setTile(); // ��������� �������� ������������� �������   
    	
    	switch (this.mDirection) {
    	case DIR_UP: // �����    				         
            if (y == GameView6.pY + cWidth/2 ) { // �������� �� ������
            	y = height - cWidth * 2;
            	getmSnake().get(0).y = y;
            } else 
            	getmSnake().get(0).y = y - cWidth;
    	break;
    	case DIR_RIGHT: // ������
            if (x == width - cWidth * 2) { // �������� �� ������
            	x = cWidth;
            	getmSnake().get(0).x = x;
            } else 
            	getmSnake().get(0).x = x + cWidth;    		
    	break;
    	case DIR_DOWN: //����
            if (y == height - cWidth*2) { // �������� �� ������
            	y = GameView6.pY + cWidth/2;
            	getmSnake().get(0).y = y;
            } else 
            	getmSnake().get(0).y = y + cWidth;    	
    	break;
    	case DIR_LEFT: //�����
            if (x == cWidth) { // �������� �� ������
            	x = width - cWidth*2;
            	getmSnake().get(0).x = x;
            } else 
            	getmSnake().get(0).x = x - cWidth;     	
    	
    	}    	
		return false;    	
    }
    
    public int getDirection() {
        return mDirection;
    }
 
    public void setDirection(int direction) {
        this.mDirection = direction;
    }
 
    public int getSnakeLength() {
        return mSnake.size();
    }
 
    public ArrayList<pos> getmSnake() {
        return mSnake;
    }
}
