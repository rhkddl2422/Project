package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Example07_DataFromActivity extends AppCompatActivity {

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example07_data_from);

        // Spinner안에 표현될 데이터를 만들어요!
        // 우리 예제에서는 Spinner안에 표현될 데이터가 문자열로 가정
        final ArrayList<String> list = new ArrayList<String>();
        list.add("포도");
        list.add("딸기");
        list.add("바나나");
        list.add("사과");

        // Spinner의 reference를 획득
        Spinner spinner = (Spinner)findViewById(R.id.mySpinner);

        // Adapter를 하나 만들어야 해요!(Adapter의 종류가 다양해요!)
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,list);

        // adapter를 spinner에게 부착해요!!
        spinner.setAdapter(adapter);

        // spinner의 event처리
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                result = list.get(position);
                Log.i("SELECTED",result + "가 선택되었어요!!");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button sendBtn = (Button)findViewById(R.id.sendDataBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("ResultValue",result);
                setResult(7000,returnIntent);
                Example07_DataFromActivity.this.finish();

            }
        });



    }
}
