package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Example16_ServiceDataTransferActivity extends AppCompatActivity {

    TextView dataFromServiceTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example16_service_data_transfer);

        // 사용할 Component의 Reference를 획득해요!
        dataFromServiceTv =
                (TextView)findViewById(R.id.dataFromServiceTv);
        final EditText dataToServiceEt =
                (EditText)findViewById(R.id.dataToServiceEt);
        Button dataToServiceBtn =
                (Button)findViewById(R.id.dataToServiceBtn);

        // Button에 대한 click event 처리를 하면되요!
        // (anonymous inner class를 이용해서 event 처리)
        dataToServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText안에 사용자가 입력한 데이터를 가지고
                // Service를 시작
                Intent intent = new Intent(getApplicationContext(),
                        Example16Sub_Service.class);
                // Service에게 데이터를 전달하려면
                // intent를 이용해서 데이터를 전달해야해요!!
                // key,value의 형식으로 데이터를 intent에 붙여요!
                intent.putExtra("DATA",
                        dataToServiceEt.getText().toString());
                startService(intent);
            }
        });

    }

    // Service로부터 intent가 도착하면 method가 호출
    @Override
    protected void onNewIntent(Intent intent) {
        // 여기서 Service가 보내준 결과데이터를 받아서 화면처리!!
        String result = intent.getExtras().getString("RESULT");
        // 추출한 결과를 TextView에 세팅!!
        dataFromServiceTv.setText(result);
        super.onNewIntent(intent);
    }
}
