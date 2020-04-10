package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Example06_SendMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example06_send_message);

        // XML에 정의되어 있는 component의 reference를 획득
        TextView tv = (TextView)findViewById(R.id.msgTv);
        Button closeBtn = (Button)findViewById(R.id.closeBtn);
        // 이 Activity에게 전달된 Intent를 획득
        Intent i = getIntent();
        String msg = (String)i.getExtras().get("sendMSG");
        tv.setText(msg);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Example06_SendMessageActivity.this.finish();
            }
        });


    }
}
