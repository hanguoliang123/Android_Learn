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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main2);
        iv = (ImageView) findViewById(R.id.iv);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meinv);
        iv.setImageBitmap(bitmap);
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
