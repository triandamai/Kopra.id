package com.trian.component.utils.ecg;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WaveformView extends SurfaceView implements SurfaceHolder.Callback{
	public final static int METHOD_NORMALSCREEN=1;
	public final static int METHOD_FULLSCREEN=2;
	private int method=1;
	
	private WaveformPlotThread plot_thread;
	
	private  int WIDTH = 320;//320;
	private  int HEIGHT = 320;
	private final static int DATASIZE = 20480;

	private static final String MYTAG = "hkyudong";
	
	
	private int nowX = 0;
	private int Yshrink=1;
	private int Xshrink=10;//skala x
	private int startX=0;
	//private int plusnum=0;
//	private float old_x=0;
//	private float old_y=0;
//	private float new_x=0;
//	private float new_y=0;
	private boolean wait =false;
	private static int[]  data = new int[DATASIZE];

	
	private Paint line_paint = new Paint();
	private Paint grid_paint = new Paint();
	private Paint cross_paint = new Paint();
	private Paint outline_paint = new Paint();
	private Paint nowX_paint = new Paint();
	private Paint coordinate_paint =new Paint();

	public WaveformView(Context context){
		super(context);
		//super(context);
		getHolder().addCallback(this);
		plot_thread = new WaveformPlotThread(getHolder(), this);
		//setFocusable(true);
		line_paint.setColor(Color.YELLOW);
		grid_paint.setColor(Color.rgb(100, 100, 100));
		cross_paint.setColor(Color.rgb(70, 100, 70));
		outline_paint.setColor(Color.GREEN);
		nowX_paint.setColor(Color.RED);
		coordinate_paint.setColor(Color.RED);
		coordinate_paint.setTextSize(20);
	}
	public WaveformView(Context context, AttributeSet attrs) {  

		super(context, attrs);  
		//super(context);
		getHolder().addCallback(this);		
		plot_thread = new WaveformPlotThread(getHolder(), this);
		//setFocusable(true);
		line_paint.setColor(Color.YELLOW);
		line_paint.setStrokeWidth(5f);
		grid_paint.setColor(Color.rgb(100, 100, 100));
		cross_paint.setColor(Color.rgb(70, 100, 70));
		outline_paint.setColor(Color.GREEN);
		nowX_paint.setColor(Color.RED);
		coordinate_paint.setColor(Color.RED);
		coordinate_paint.setTextSize(20);
		
		//my
		
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		plot_thread.setRunning(false);
		WIDTH= WaveformView.this.getWidth();
		HEIGHT = WaveformView.this.getHeight();
		Log.i(MYTAG, "Width"+Integer.toString(WIDTH)+"Height"+Integer.toString(HEIGHT));
		plot_thread.set_conf(WaveformView.this.getHolder(), WaveformView.this);
		plot_thread.setRunning(true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		plot_thread.setRunning(true);
		plot_thread.start();
		WIDTH= WaveformView.this.getWidth();
		HEIGHT = WaveformView.this.getHeight();
		Log.i(MYTAG, "Width"+Integer.toString(WIDTH)+"Height"+Integer.toString(HEIGHT));
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		plot_thread.setRunning(false);
		while (retry){
			try{
				plot_thread.join();
				retry = false;
			}catch(InterruptedException e){
				
			}
		}
		
	}
	
	@Override
	public void onDraw(Canvas canvas){
		PlotPoints(canvas);		
	}
	

	public void clearScreen() {
		for(int i=0; i<DATASIZE; i++){
			data[i] = 0;
			nowX = 0;
		}
	}
	
	public int get_method() {
		return method;
	}
	
	public void set_method(int newmethod) {
		method = newmethod;
	}
	public void set_Yshrink(int shrink) {
		Yshrink = shrink;
	}
	public void set_wait(boolean newwait) {
		wait = newwait;
	}
	private boolean isTuchEdge() {
		int d1=nowX-startX;
		int d2=DATASIZE-startX+nowX;
		Log.i(MYTAG, "d1"+Integer.toString(d1)+"d2"+Integer.toString(d2));
		if (method==WaveformView.METHOD_NORMALSCREEN) {
			
			if ((d1>=WIDTH/Xshrink)) {
				return true;
			}else {
				return false;
			}
		}else {
			if ((d1>=HEIGHT/Xshrink)) {
				return true;
			}else {
				return false;
			}
		}
//		return false;
	}
	private int controlHeight(int tempdata) {//��������ģʽ�µ�����߶�
		return HEIGHT-tempdata-100;
	}
	private int controlWidth(int tempdata) {//����ȫ����ʾģʽ�µ�����߶�
		return tempdata+200;
	}
	public void set_data(int tempdata){
	       
//			plot_thread.setRunning(false);	
			int i = nowX;
			if(i < DATASIZE-1){
				data[nowX] = tempdata;
				//Log.i(MYTAG, "getdata"+Integer.toString(data[nowX]), null);
				nowX++;
//				Log.i(MYTAG, Integer.toString(nowX), null);				
				if ((!wait)&&isTuchEdge()) {
					startX += 1;
					if (startX==DATASIZE) {
						startX=0;			
					}					
				}
			}else {
				data[nowX] = tempdata;
				nowX=0;
			}
//			plot_thread.setRunning(true);
	}
	
	

	public void set_data(int[] tempdata){
//       Log.i(MYTAG, tempdata.toString(), null);
//		plot_thread.setRunning(false);
		int i;
		for(i = 0;i <tempdata.length;i++){
			if(nowX < DATASIZE-1){
				data[nowX] = tempdata[i];
				nowX++;
				if (isTuchEdge()) {
					startX += 1;
//					Log.i(MYTAG, "now startX"+Integer.toString(startX), null);
					if (startX==DATASIZE) {
						startX=0;			
					}					
				}
			}else {
				data[nowX] = tempdata[i];
//				plot_thread.setRunning(true);
				nowX=0;
				}
		}
	}
	public void PlotPoints(Canvas canvas) {
		if (METHOD_NORMALSCREEN == method) {
			PlotPoints_normal(canvas);
		}else {
			PlotPoints_fullscreen(canvas);
		}
	}
	private void PlotPoints_normal(Canvas canvas){
		
		// clear screen
		canvas.drawColor(Color.rgb(20, 20, 20));//black
	//	canvas.drawColor(Color.rgb(255, 255, 255));//white

		// draw grids
	    for(int vertical = 1; vertical<WIDTH/20; vertical++){
	    	if (vertical%5==0) {
	    		canvas.drawLine(vertical*20+1, 1,vertical*20+1, HEIGHT+1,coordinate_paint);
				canvas.drawText(Integer.toString(vertical*20/100)+"s",vertical*20+1,HEIGHT,coordinate_paint);
			}else {
				canvas.drawLine(vertical*20+1, 1,vertical*20+1, HEIGHT+1,grid_paint);
			}
	    	
	    }	    	
	    for(int horizontal = 1; horizontal<HEIGHT/20; horizontal++){
	    	if (horizontal%5==0) {
				canvas.drawLine(1, horizontal*20+1,WIDTH+1, horizontal*20+1,coordinate_paint);
				canvas.drawText(Integer.toString((HEIGHT-horizontal*20)*Yshrink-100),1,horizontal*20,coordinate_paint);
			}else {
				canvas.drawLine(1, horizontal*20+1,WIDTH+1, horizontal*20+1,grid_paint);
			}
	    	
	    }	    	
	    
	    // draw center cross
		//canvas.drawLine(0, (HEIGHT/2)+1, WIDTH+1, (HEIGHT/2)+1, cross_paint);
		//canvas.drawLine((WIDTH/2)+1, 0, (WIDTH/2)+1, HEIGHT+1, cross_paint);
		
		// draw outline
		canvas.drawLine(0, 0, (WIDTH), 0, outline_paint);	// top
		canvas.drawLine((WIDTH-1), 0, (WIDTH-1), (HEIGHT-1), outline_paint); //right
		canvas.drawLine(0, (HEIGHT-1), (WIDTH-1), (HEIGHT-1), outline_paint); // bottom
		canvas.drawLine(0, 0, 0, (HEIGHT-1), outline_paint); //left
		
//		Log.i(MYTAG, Integer.toString(nowX), null);
//		Log.i(MYTAG, "111", null);
		// plot data
//		startX += plusnum;
//		if (startX==DATASIZE) {
//			startX=0;			
//		}
		int nowpaintX=startX;
		for(int x=0; x<(WIDTH-1); x++){		
			if (nowpaintX<DATASIZE-1) {
				nowpaintX++;
//				Log.i(MYTAG, "now data"+Integer.toString(data[nowpaintX]), null);
//				Log.i(MYTAG, "nowpaintX"+Integer.toString(nowpaintX), null);
				canvas.drawLine(Xshrink*(x+1), controlHeight(data[nowpaintX]/Yshrink), Xshrink*(x+2), controlHeight(data[nowpaintX+1]/Yshrink), line_paint);
			}else {
				nowpaintX=0;
				canvas.drawLine(Xshrink*(x+1), controlHeight(data[DATASIZE-1]/Yshrink), Xshrink*(x+2), controlHeight(data[0]/Yshrink), line_paint);
			}
			//canvas.drawLine(x+1, controlHeight(data[nowpaintX]/Yshrink), x+2, controlHeight(data[nowpaintX+1]/Yshrink), line_paint);
//			canvas.drawLine(x+1, ch1_data[x], x+2, ch1_data[x+1], ch1_color);
//			Log.i(MYTAG, Integer.toString(data[x]), null);
		}
		//canvas.drawLine(nowX, 0,nowX, HEIGHT, nowX_paint);//ˢ�·ֽ���
	}
	
	
	private void PlotPoints_fullscreen(Canvas canvas) {
		// TODO Auto-generated method stub
		// clear screen
		canvas.drawColor(Color.rgb(20, 20, 20));//black
		//canvas.drawColor(Color.rgb(255, 255, 255));//white

		// draw grids
	    for(int vertical = 1; vertical<WIDTH/20; vertical++){
	    	if (vertical%5==0) {
	    		canvas.drawLine(vertical*20+1, 1,vertical*20+1, HEIGHT+1,coordinate_paint);
				canvas.drawText(Integer.toString(vertical*20/100-2),vertical*20+1,HEIGHT,coordinate_paint);
			}else {
				canvas.drawLine(vertical*20+1, 1,vertical*20+1, HEIGHT+1,grid_paint);
			}
	    }	    	
	    for(int horizontal = 1; horizontal<HEIGHT/20; horizontal++){
	    	if (horizontal%5==0) {
	    		canvas.drawLine(1,horizontal*20+1, WIDTH+1,horizontal*20+1, coordinate_paint);
				canvas.drawText(Integer.toString(horizontal*20/100)+"s",1,horizontal*20+1,coordinate_paint);
			}else {
				canvas.drawLine(1,horizontal*20+1, WIDTH+1,horizontal*20+1, grid_paint);
				//canvas.drawLine(horizontal*20+1, 1,horizontal*20+1, WIDTH+1,grid_paint);
			}
	    }	    	
	    
	    // draw center cross
		//canvas.drawLine(0, (HEIGHT/2)+1, WIDTH+1, (HEIGHT/2)+1, cross_paint);
		//canvas.drawLine((WIDTH/2)+1, 0, (WIDTH/2)+1, HEIGHT+1, cross_paint);
		
		// draw outline
		canvas.drawLine(0, 0, (WIDTH+1), 0, outline_paint);	// top
		canvas.drawLine((WIDTH), 0, (WIDTH-1), (HEIGHT-1), outline_paint); //right
		canvas.drawLine(0, (HEIGHT-1), (WIDTH-1), (HEIGHT-1), outline_paint); // bottom
		canvas.drawLine(0, 0, 0, (HEIGHT-1), outline_paint); //left

		
//		Log.i(MYTAG, "now width"+Integer.toString(WIDTH), null);
//		Log.i(MYTAG, "111", null);
		// plot data
//		startX += plusnum;
//		if (startX==DATASIZE) {
//			startX=0;			
//		}
		int nowpaintX=startX;
		for(int x=0; x<(HEIGHT-1); x++){
			//canvas.drawLine(0, 0, nowpaintX, nowpaintX, outline_paint);
//			Log.i(MYTAG, Integer.toString(nowpaintX), null);
			if (nowpaintX<DATASIZE-1) {
				nowpaintX++;
				//canvas.drawLine(0, 0, nowpaintX, nowpaintX, outline_paint);
				//Log.i(MYTAG, "now data"+Integer.toString(controlHeight(data[nowpaintX]/Yshrink)), null);
				canvas.drawLine(controlWidth(data[nowpaintX]/Yshrink),Xshrink*(x+1),controlWidth(data[nowpaintX+1]/Yshrink), Xshrink*(x+2), line_paint);
			}else {
				nowpaintX=0;
				canvas.drawLine(controlWidth(data[DATASIZE-1]/Yshrink),Xshrink*(x+1), controlWidth(data[0]/Yshrink),Xshrink*(x+2), line_paint);
			}
		}
	}
//	public boolean onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//		
//		 float x = event.getX();
//         float y = event.getY();
//         switch (event.getAction()) {
//         case MotionEvent.ACTION_DOWN:
//        	 old_x=event.getX();
//        	 old_y=event.getY();
//             break;
//         case MotionEvent.ACTION_MOVE:
//        	 new_x=event.getX();
//        	 new_y=event.getY();
//        	 Xshrink=(int)(new_x-old_x)+Yshrink;
//             break;
//         case MotionEvent.ACTION_UP:
//             break;
//         }
//
//         return super.onTouchEvent(event);
//	}


}
