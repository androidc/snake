package com.example.snake5;

import java.util.ArrayList;
import java.util.Random;

import com.example.snake5.Snake6.pos;

import android.util.Log;

public class Snake7 {
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
	
    Snake7(int oX, int oY) { // ��������� �������
        mSnake.add(new pos(oX, oY));
        mSnake.add(new pos(oX, oY));
        mSnake.add(new pos(oX, oY));
    } 
    
    float yS = GameView7.yS;
    int appleX, appleY;
    void setApple() {
    	
    }
    
    /** ��������� �������� ������������� ������� */
    void setTile() { 

    }
   
    
//    int cWidth = GameView7.cWidth;
//    int width = GameView6.width;
//    int height = GameView6.heightReal;
//    int top = GameView6.top;
    
    /** ������ �������� */
    public boolean thisMove() {
    	
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
