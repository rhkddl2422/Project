package com.example.androidlectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Example12_SimpleBookSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example12_simple_book_search);

        // 검색버튼에 대한 reference 획득
        Button searchBtn = (Button)findViewById(R.id.searchBtn);
        // 검색입력상자에 대한 reference 획득
        final EditText searchTitle = (EditText)findViewById(R.id.searchTitle);
        // 결과 listview에 대한 reference 획득
        final ListView searchList = (ListView)findViewById(R.id.searchList);

        // Network연결(Web Application 호출)을 해야 하기 때문에
        // UI Thread(Activity)에서 이 작업을 하면 안되요!!
        // => Thread로 해결해야 해요
        // => Thread와 데이터를 주고받기 위해서 Handler를 이용

        // Handler객체를 생성!
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            // Thread에 의해서 추후에 sendMessage가 호출되는데
            // sendMessage에 의해서 전달된 데이터를 처리하기 위해서
            // handleMessage를 overriding하면서 instance를 생성.
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                // Thread가 보내준 데이터로 ListView를 채우는 코드
                // Thread가 보내준 Message에서 Bundle을 꺼내요!
                Bundle bundle = msg.getData();
                // Bundle에서 key값을 이용해 데이터를 추출해요!!
                String[] bookList = (String[])bundle.get("BOOKLIST");

                // ListView 사용은 Spinner사용과 비슷.
                // ListView에 데이터를 설정하려면 Adapter가 있어야 해요!
                // Adapter에 데이터를 설정해서 이 Adapter를 ListView에 붙여요!
                ArrayAdapter adapter =
                        new ArrayAdapter(getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                bookList);
                // ListView에 위에서 생성한 adapter를 붙여요!
                searchList.setAdapter(adapter);
            }
        };

        // Button에 대한 event처리
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thread를 생성
                BookSearchRunnable runnable =
                        new BookSearchRunnable(handler, searchTitle.getText().toString());
                Thread t = new Thread(runnable);
                // Thread를 시작하려면 start()를 호출
                t.start();
            }
        });
    }
}

// Thread를 생성하기 위한 Runnable interface를 구현한 class를 정의
class BookSearchRunnable implements Runnable {

    private Handler handler;
    private String keyword;

    // 모든 class는 기본생성자를 일반적으로 가지고 있어요!
    BookSearchRunnable() {}   // default constructor(기본 생성자)

    BookSearchRunnable(Handler handler, String keyword) {
        this.handler = handler;
        this.keyword = keyword;
    }
    @Override
    public void run() {
        // Web Application을 호출!!
        // 결과를 받아와서 그 결과를 예쁘게 만든 후 handler를 통해서
        // activity에게 전달.
        // 1. 접속할 URL을 준비해요!
        String url = "http://70.12.60.90:8080/bookSearch/searchTitlebyKeyword?keyword=" + keyword;
        // 2. Java Network 기능은 Exception 처리를 기본으로 해야해요!
        try {
            // 3. URL 객체를 생성해야 해요!! ( Java의 URL class를 이용해서 )
            URL obj = new URL(url);
            // 4. URL 객체를 이용해서 접속을 시도해요!!
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            // 5. Web Application의 호출방식을 설정해요!! (GET, POST)
            con.setRequestMethod("GET");
            // 6. 정상적으로 접속이 되었는지를 확인!!
            //    HTTP protocol로 접속할 때 접속 결과에 대한 상태값.
            //    200: 접속성공~ , 404 : not found, 500 : internal server error
            //    403: forbidden, ...

            int responseCode = con.getResponseCode();
            Log.i("BookSearch","서버로부터 전달된 code : " + responseCode);
            // 7. 네트워크 접속을 위해 보안설정이 필요해요!!
            //    android 9(pie)버전부터는 보안이 강화되었어요!
            //    웹 프로토콜에 대한 기본 protocol이 HTTP에서 HTTPS로 변경
            //    따라서 http protocol을 이용하는 경우 특수한 설정이
            //    AndroidManifest.xml파일에 있어야 해요!

            // 8. 서버와의 연결객체를 이용해서
            // 서버와의 데이터 통로를 하나 열어야 해요!(Java Stream)
            // 이 연결통로를 이용해서 데이터를 읽어들일 수 있어요!
            // 이 통로를 하나 생성해야 해요!!
            // 기본적인 연결통로를 이용해서 조금 더 효율적인 연결통로로
            // 다시 만들어서 사용.
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            // 서버가 보내주는 데이터를 읽어서 하나의 문자열로 만들어요!
            String readLine = "";
            StringBuffer responseTxt = new StringBuffer();
            while((readLine = br.readLine()) != null) {
                responseTxt.append(readLine);
            }
            br.close();   // 통로사용이 끝났으니 해당 resource자원을 해제.

            // 서버로부터 얻어온 문자열을 Log로 출력해보아요!
            Log.i("BookSearch","얻어온 내용은 : " + responseTxt.toString());
            // ["Head First Java: ...","뇌를 자극하는..."]
            // 가져온 데이터(문자열)를 자료구조화 시켜서 안드로이드 앱에 적용하는
            // 방법을 생각해 보아요!!

            // 일반적으로 서버쪽 웹 프로그램은 XML이나 혹은 JSON으로 결과 데이터를
            // 제공해줘요!!
            // 우리가 받은 데이터도 JSON형식이예요!!
            // 받은 JSON을 Java의 자료구조로 변경해야 해요!!
            // JSON parsing library를 가져다가 좀 편하고 쉽게 JSON을 handling
            // 가장 대표적인 JSON처리 library중 하나인 JACKSON library를 이용

            // JSON library가 있어야 사용할 수 있으니 라이브러리를 설치해야 해요!

            // JSCKSON library 사용
            ObjectMapper mapper = new ObjectMapper();
            String[] resultArr =
                    mapper.readValue(responseTxt.toString(),String[].class);
            // 얻어온 JSON문자열 데이터를 Java의 String array로 변환했어요!

            // 최종 결과 데이터를 Activity에게 전달해야 해요!!(UI Thread에게 전달해서
            // 화면제어를 해야 해요!!)

            // 데이터를 Activity에게 전달하기 위해서
            // 1. Bundle에 전달할 데이터를 붙여요!!
            Bundle bundle = new Bundle();
            bundle.putStringArray("BOOKLIST",resultArr);
            // 2. Message를 만들어서 Bundle을 Message에 부착
            Message msg = new Message();
            msg.setData(bundle);
            // 3. Handler를 이용해서 Message를 Activity에게 전달.
            handler.sendMessage(msg);

        } catch(Exception e) {
            Log.i("BookSearch",e.toString());
        }


    }
}
