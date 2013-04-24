package com.example.checker;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint paint, mBitmapPaint;
    private float canvasSize;
    private final int size;// size;
    private final game Game;
    int startx;
    int starty;
    int endx;
    int endy;
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //размер игрового поля
        size =8;
        startx=-1;
        starty=-1;
        endx=-1;
        endy=-1;
       // size =8;
        Game = new game();
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        canvasSize=300 * (metrics.densityDpi/160f);
        mBitmap = Bitmap.createBitmap((int) canvasSize, (int) canvasSize, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        
    }
    public void drawField() {
        paint =new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
      
        float X= canvasSize/size;
        float Y = canvasSize/size;
        System.out.println(X);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);   
        
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                if ((i%2==1 && j%2==1) || (i%2==0 && j%2==0))
                {
                	paint.setColor(Color.rgb(205,127,50));
                    mCanvas.drawRect(X*j, Y*i,X*j+X,Y*i+Y,paint);
                 
                }
                else  {
                	paint.setColor(Color.GRAY);
                	mCanvas.drawRect(j*X, i*Y,X*j+X,Y*i+Y,paint);
                }
                
            }
          

        }

//        for (int i=0; i<3; i++) {
//        	for (int j=0; j<3; j++) {
//        		if (Game.gameField[i][j]==cellState.O ){
//        			System.out.println("FUCKYEAH");
//        		mCanvas.drawLine ((float) canvasSize*i / size, (float)j* canvasSize / size,
//        				(float) canvasSize*(i+1) / size, (float)(j+1)* canvasSize / size, paint);
//        		mCanvas.drawLine((float) canvasSize*(i+1) / size, (float)j* canvasSize / size,
//        				(float) canvasSize*i / size, (float)(j+1)* canvasSize / size, paint);
//        		}
//        	}
//        }
        
        	
    }
    public void drawCheckers() {
    	
    	invalidate();
    	for (int i=0; i<size; i++) {
        	for (int j=0; j<size; j++) {
        		
        		if (Game.board[i][j]==cell.black ){
        			paint.setColor(Color.BLACK);
        			mCanvas.drawCircle((float) (canvasSize*i / size + canvasSize/(2*size)),
        					canvasSize*j / size + canvasSize/(2*size), canvasSize/(2*size), paint);
        		}
        		if (Game.board[i][j]==cell.white ){
        			paint.setColor(Color.WHITE);
        			mCanvas.drawCircle((float) (canvasSize*i / size + canvasSize/(2*size)),
        					canvasSize*j / size + canvasSize/(2*size), canvasSize/(2*size), paint);
        		}
        	}
        }
    	 //float X= canvasSize/size;
         //float Y = canvasSize/size;
    	//mCanvas.drawCircle(X, Y, X/2, paint);
    	//invalidate();
    	
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

    	if(event.getAction() == MotionEvent.ACTION_DOWN)
    	{ 
    		float touchX = event.getX();
    		float touchY = event.getY();
    		
    		int X = (int) (touchX / (canvasSize/ size));
    		int Y = (int) (touchY / (canvasSize/ size));
    		//System.out.println(X);
    		//System.out.println(Y);
    		
    		//Game.makeTurn(X, Y);
    		//cell win = Game.checkWinner();
    		//Game.checkWinner();
    		invalidate();
    		if (Game.currentPlayer && (Game.board[X][Y]==cell.black))
    	    {
    	        startx=X;
    	        starty=Y;
    	    }
    	    else if (!Game.currentPlayer && (Game.board[X][Y]==cell.white))
    	    {
    	        startx=X;
    	        starty=Y;
    	    }

    	    if (Game.board[X][Y]==cell.empty && startx!=-1) //второе условие является проверкой на повторый
    	                                               //клик по своей шашке, проверка y не нужна тк y и x
    	                                               //меняются вместе
    	    {
    	        endx=X;
    	        endy=Y;
    	        Game.makeMove(startx, starty,endx,endy);
    	        Game.currentPlayer=!Game.currentPlayer;
    	        invalidate();
    	    }

    		//if (win==cell.O) System.out.println("WINNER O");
    		//if (win==cell.X) System.out.println("WINNER X"); 
    		
    	}
    	return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
    	
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        drawField();
        drawCheckers();
        
    }

    
    public float convertDpToPixel(float dp,Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi/160f);
    }
}