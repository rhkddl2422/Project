package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
* Android에서는 Broadcast라는 signal이 존재
* 이 신호(signal)은 Android system 자체에서 발생할 수도 있고
* 사용자 App에서 임의로 발생시킬 수도 있어요!
*
* 여러개의 일반적으로 정해져있는 Broadcast message를 받고 싶다면
* Broadcast Receiver를 만들어서 등록해야 해요!
*
* 등록하려면 크게 2가지 방식이 있는데..
* 1. AndroidManifest.xml file에 명시
* 2. 코드상에서 Receiver를 만들어서 등록할 수 있어요!
* */
public class Example18_BRTestActivity extends AppCompatActivity {

    private BroadcastReceiver bReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example18_br_test);

        Button _18_br_registerBtn = findViewById(R.id._18_br_registerBtn);
        _18_br_registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Broadcast Receiver 등록버튼 click시 호출!
                // Broadcast Receiver 객체를 만들어서 IntentFilter와 함께
                // 시스템에 등록!
                // 1. IntentFilter부터 생성
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("MY_BROADCAST_SIGNAL");

                // 2. Broadcast Receiver객체를 생성
                bReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        // 이 Receiver가 신호를 받게되면 이부분이 호출
                        // 로직처리를 여기에서 하면되요!
                        if(intent.getAction().equals("MY_BROADCAST_SIGNAL")) {
                            Toast.makeText(getApplicationContext(),
                                    "신호가 왔어요!!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                // 3. Filter와 함께 Broadcast Receiver를 등록!!
                registerReceiver(bReceiver,intentFilter);
            }
        });

        // 등록해제버튼을 눌렀을 때
        Button _18_br_unRegisterBtn = findViewById(R.id._18_br_unRegisterBtn);
        _18_br_unRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver(bReceiver);
            }
        });


        Button _18_sendBroadcastBtn = findViewById(R.id._18_sendBroadcastBtn);
        _18_sendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼을 누르면 Broadcast를 임의로 발생!
                Intent i = new Intent("MY_BROADCAST_SIGNAL");
                sendBroadcast(i);
            }
        });
    }
}
