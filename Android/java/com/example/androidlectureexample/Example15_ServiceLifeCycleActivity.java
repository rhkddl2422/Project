package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Example15_ServiceLifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example15_service_life_cycle);

        Button StartServiceBtn = (Button)findViewById(R.id.StartServiceBtn);
        StartServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Service Component를 시작!!
                Intent i = new Intent(getApplicationContext(),
                        Example15Sub_LifeCycleService.class);
                i.putExtra("MSG","소리없는 아우성!!");
                startService(i);
                // 만약 service객체가 없으면 생성하고 수행
                // onCreate() -> onStartcommand() 호출
                // 만약 service객체가 존재하고 있으면
                // onStartCommand() 호출
            }
        });

        Button StopServiceBtn = (Button)findViewById(R.id.StopServiceBtn);
        StopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        Example15Sub_LifeCycleService.class);
                stopService(i);
            }
        });

    }
}
