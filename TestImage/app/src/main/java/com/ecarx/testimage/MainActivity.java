package com.ecarx.testimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Bitmap bitmap;
    private float dx = 0;
    private int degress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main2);
        //setContentView(R.layout.activity_main3);
        setContentView(R.layout.activity_main4);
        iv = (ImageView) findViewById(R.id.iv);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meinv);
        iv.setImageBitmap(bitmap);
    }



    /**
     * 倒影
     * @param view
     */
    public void click01(View view) {
        Matrix matrix = new Matrix();
        matrix.setScale(1,-1);
        matrix.postTranslate(0,bitmap.getHeight());

        // 1.买一张纸
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth()*2, bitmap.getHeight()*2, bitmap.getConfig());

        // 2.买个画板
        Canvas canvas = new Canvas(newBitmap);

        // 3.临摹绘画
        Paint paint = new Paint();
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);   //消除锯齿

        canvas.drawBitmap(bitmap,matrix,paint);
        iv.setImageBitmap(newBitmap);
    }

    /**
     * 镜面
     * @param view
     */
    public void click02(View view) {
        Matrix matrix = new Matrix();
        matrix.setScale(-1,1);
        matrix.postTranslate(bitmap.getWidth(),0);

        // 1.买一张纸
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth()*2, bitmap.getHeight()*2, bitmap.getConfig());

        // 2.买个画板
        Canvas canvas = new Canvas(newBitmap);

        // 3.临摹绘画
        Paint paint = new Paint();
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);   //消除锯齿

        canvas.drawBitmap(bitmap,matrix,paint);
        iv.setImageBitmap(newBitmap);
    }

    /**
     * 逆时针旋转
     * @param view
     */
    public void turnCircleLeft(View view){
        Matrix matrix = new Matrix();
        matrix.setRotate(--degress,bitmap.getWidth(),bitmap.getHeight());
        matrix.postTranslate(100,100);

        // 1.买一张纸
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth()*2, bitmap.getHeight()*2, bitmap.getConfig());

        // 2.买个画板
        Canvas canvas = new Canvas(newBitmap);

        // 3.临摹绘画
        Paint paint = new Paint();
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);   //消除锯齿

        canvas.drawBitmap(bitmap,matrix,paint);
        iv.setImageBitmap(newBitmap);
    }

    /**
     * 顺时针旋转
     * @param view
     */
    public void turnCircleRight(View view){

        new Thread(){
            @Override
            public void run() {

                while (true) {

                    Matrix matrix = new Matrix();
                    matrix.setRotate(++degress, bitmap.getWidth(), bitmap.getHeight());
                    matrix.postTranslate(30, 30);

                    // 1.买一张纸
                    final Bitmap newBitmap = Bitmap.createBitmap((int)(bitmap.getWidth()*1.5), (int)(bitmap.getHeight()*1.5), bitmap.getConfig());

                    // 2.买个画板
                    Canvas canvas = new Canvas(newBitmap);

                    // 3.临摹绘画
                    Paint paint = new Paint();
                    canvas.drawColor(Color.WHITE);
                    paint.setColor(Color.WHITE);
                    paint.setAntiAlias(true);   //消除锯齿

                    canvas.drawBitmap(bitmap, matrix, paint);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(newBitmap);
                        }
                    });
                    try {
                        Thread.sleep((1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


    }

    /**
     * 左移
     * @param view
     */
    public void turnLeft(View view){
        Matrix matrix = new Matrix();
        dx--;
        matrix.setTranslate(dx,0);

        // 1.买一张纸
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        // 2.买个画板
        Canvas canvas = new Canvas(newBitmap);

        // 3.临摹绘画
        Paint paint = new Paint();
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap,matrix,paint);
        iv.setImageBitmap(newBitmap);
    }

    /**
     * 右移
     * @param view
     */
    public void turnRight(View view){
        Matrix matrix = new Matrix();
        dx++;
        matrix.setTranslate(dx,0);

        // 1.买一张纸
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        // 2.买个画板
        Canvas canvas = new Canvas(newBitmap);

        // 3.临摹绘画
        Paint paint = new Paint();
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap,matrix,paint);
        iv.setImageBitmap(newBitmap);
    }

    /**
     * 放大
     * @param view
     */
    public void turnBig(View view){

        Matrix matrix = new Matrix();
        matrix.setScale(2,2);

        // 1.买一张纸
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth() * 2, bitmap.getHeight() * 2, bitmap.getConfig());

        // 2.买个画板
        Canvas canvas = new Canvas(newBitmap);

        // 3.临摹绘画
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap,matrix,paint);


        iv.setImageBitmap(newBitmap);
    }

    /**
     * 缩小
     * @param view
     */

    public void turnSmall(View view){

        Matrix matrix = new Matrix();
        matrix.setScale(0.5f,0.5f);

        // 1.买一张纸
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getConfig());

        // 2.买个画板
        Canvas canvas = new Canvas(newBitmap);

        // 3.临摹绘画
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap,matrix,paint);


        iv.setImageBitmap(newBitmap);
    }
}
