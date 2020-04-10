package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Example17_KAKAOBookSearchActivity extends AppCompatActivity {

    ListView kakaoBookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example17_kakao_book_search);

        // Widget에 대한 reference부터 얻어와요!
        final EditText kakaoEt = (EditText)findViewById(R.id.kakaoEt);
        Button kakaoSearchBtn = (Button)findViewById(R.id.kakaoSearchBtn);
        kakaoBookList = (ListView)findViewById(R.id.kakaoBookList);

        // Button에 대한 이벤트 처리
        kakaoSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        Example17Sub_KAKAOBookSearchService.class);
                i.putExtra("KEYWORD",kakaoEt.getText().toString());
                startService(i);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ArrayList<String> booksTitle =
                (ArrayList<String>)intent.getExtras().get("BOOKRESULT");

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                booksTitle);

        kakaoBookList.setAdapter(adapter);
    }
}
