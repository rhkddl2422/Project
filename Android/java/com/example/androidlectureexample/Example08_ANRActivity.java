package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Example08_ANRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example08_anr);

        // TextView에 대한 reference를 획득
        final TextView sumTv = (TextView)findViewById(R.id.sumTv);

        // 첫번째 버튼에 대한 reference획득 & 이벤트 처리
        // 버튼을 누르면 상당히 오랜시간 연산이 수행
        // 결과적으로 사용자와의 interaction이 중지
        // Activity가 block된 것처럼 보여요!(ANR : Application Not Responding)
        // 당연히 ANR현상은 피해줘야 해요!
        // Activity의 최우선작업은 사용자와의 interaction
        // Activity에서는 시간이 오래 걸리는 작업은 하면 안되요!!
        // Activity는 Thread로 동작해요!!(UI Thread)
        // 로직처리는 background Thread를 이용해서 처리해야 해요!
        Button startBtn = (Button)findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long sum = 0;
                for(long i=0; i < 10000000000L; i++) {
                    sum += i;
                }
                Log.i("SumTest","연산된 결과는" + sum);
            }
        });

        // 두번째 버튼에 대한 reference획득 & 이벤트 처리
        // Toast message를 출력!
        Button secondBtn = (Button)findViewById(R.id.secondBtn);
        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Example08_ANRActivity.this,
                        "버튼이 클릭되었어요!",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}
