package com.example.androidlectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Example11_CounterLogHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example11_counter_log_handler);

        // TextView에 대한 reference를 가져와서 final로 처리
        final TextView tv = (TextView)findViewById(R.id.sumTv3);
        // ProgressBar에 대한 reference를 가져와서 final로 처리
        final ProgressBar pb = (ProgressBar)findViewById(R.id.counterProgress1);

        // 데이터를 주고받는 역할을 수행하는 Handler객체를 하나 생성
        // Handler 객체는 메시지를 전달하는 method와
        // 메시지를 전달받아서 로직처리하는 method 2개를 주로 사용.
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                // 받은 메시지를 이용해서 화면처리!!
                Bundle bundle = msg.getData();
                String count = bundle.getString("count");
                pb.setProgress(Integer.parseInt(count));
            }
        };

        // 연산시작이라는 버튼을 클릭했을 때 로직처리하는 Thread를 생성해서
        // 실행!!
        Button startBtn = (Button)findViewById(R.id.startBtn3);
        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Thread를 생성해야 되요!
                // Thread에게 activity와 데이터 통신을 할 수 있는 handler객체를
                // 전달해야 해요!
                MySumThread runnable = new MySumThread(handler);
                Thread t = new Thread(runnable);
                t.start();
            }
        });
    }
}

// 연산을 담당하는 Thread를 위한 Runnable interface구현한 class
class MySumThread implements Runnable {

    private Handler handler;

    MySumThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        // 숫자를 더해가면서 progressbar를 진행시켜요!
        long sum = 0;
        for(long i=0; i<10000000000L; i++) {
            sum += i;
            if(i % 100000000 == 0) {
                long loop = i / 100000000;
                // Message를 만들어서 handler를 이용해 Activity에게 메시지를 전달
                //pb.setProgress((int)loop);
                // Bundle 객체를 하나 만들어요!
                Bundle bundle = new Bundle();
                bundle.putString("count",String.valueOf(loop));
                // 이 Bundle을 가질 수 있는 Message객체를 생성
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }
    }
}
