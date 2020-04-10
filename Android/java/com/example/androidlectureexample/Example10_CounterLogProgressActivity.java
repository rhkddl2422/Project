package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Example10_CounterLogProgressActivity extends AppCompatActivity {

    private TextView tv;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example10_counter_log_progress);

        tv = (TextView)findViewById(R.id.sumTv2);
        pb = (ProgressBar)findViewById(R.id.counterProgress);

        Button startBtn2 = (Button)findViewById(R.id.startBtn2);
        startBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thread를 만들어서 처리하도록 해야 해요!
                CounterRunnable runnable = new CounterRunnable();
                Thread t = new Thread(runnable);
                t.start();
            }
        });
    }

    // Android UI component(Widget)는 thread safe하지 않아요!!
    // Android UI component는 오직 UI Thread에 의해서만 제어되어야 해요!
    // 화면제어(UI component-widget)는 오직 UI Thread에 의해서만(Activity)
    // 제어될 수 있어요!
    // 아래의 코드는 실행되긴 하지만 올바르지 않은 코드예요!
    // 외부 Thread(UI Thread가 아닌)에서 UI component를 직접제어할 수 없어요!
    // Handler를 이용해서 이 문제를 해결해야 해요!!

    class CounterRunnable implements Runnable {
        @Override
        public void run() {
            // 숫자를 더해가면서 progressbar를 진행시켜요!
            long sum = 0;
            for(long i=0; i<10000000000L; i++) {
                sum += i;
                if(i % 100000000 == 0) {
                    long loop = i / 100000000;
                    pb.setProgress((int)loop);
                }
            }
            tv.setText("합계는 : " + sum);
        }
    }
}
