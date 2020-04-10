package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Example09_CounterLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example09_counter_log);

        // 연산시작버튼
        Button startBtn1 = (Button)findViewById(R.id.startBtn1);
        startBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thread를 만들어서 실행!!
                MyRunnable runnable = new MyRunnable();
                Thread t = new Thread(runnable);
                t.start();
            }
        });

        // Toast message를 실행하는 버튼
        Button secondBtn1 = (Button)findViewById(R.id.secondBtn1);
        secondBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Example09_CounterLogActivity.this,
                        "버튼 클릭클릭!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // inner class
    class MyRunnable implements Runnable {
        @Override
        public void run() {
            long sum = 0;
            for(long i=0; i<10000000000L; i++) {
                sum += i;
            }
            Log.i("SumTest","총 합은 : " + sum);
        }
    }

}
