package com.example.androidlectureexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Example17Sub_KAKAOBookSearchService extends Service {
    public Example17Sub_KAKAOBookSearchService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Service에서 사용하는 3개의 method overriding

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Activity로 부터 전달된 intent를 이용해서 keyword를 얻어내요!
        String keyword = intent.getExtras().getString("KEYWORD");

        // Network연결을 통해 Open API를 호출하는 시간이 걸리는 작업을 수행
        // Thread를 이용해서 처리!!
        KakaoBookSearchRunnable runnable = new KakaoBookSearchRunnable(keyword);
        Thread t = new Thread(runnable);

        t.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // inner class형태
    class KakaoBookSearchRunnable implements Runnable {

        private String keyword;
        KakaoBookSearchRunnable() {

        }
        KakaoBookSearchRunnable(String keyword) {
            this.keyword = keyword;
        }
        @Override
        public void run() {
            // Network 접속을 통해 Open API를 호출!!
            // KAKAO Developer 사이트에서 OPEN API의 호출방식을 알아내요!
            String url = "https://dapi.kakao.com/v3/search/book?target=title";
            url += "&query=" + keyword;

            // 주소를 완성했으면 이 주소를 이용해서 network연결을 해야 해요!
            // Java에서 network연결은 예외상황이 발생할 여지가 있어요!
            // Java는 network처리에 대해서 Exception Handling을 강제하고 있어요!
            try {
                // 1. HTTP접속을 하기 위해 URL객체를 생성
                URL obj = new URL(url);
                // 2. URL Connection을 열어요!
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                // 3. 연결에 대한 설정이 들어가야 해요!
                // 대표적인 설정이 호출방식(GET,POST), 인증에 대한 처리
                con.setRequestMethod("GET");   // API를 보고 결정해야 해요!
                con.setRequestProperty("Authorization",
                        "KakaoAK f2e193773620c8b61535d71216a36736");

                // 일단 접속 성공(정상적으로 처리가 되면)
                // 접속이 성공하면 결과 데이터를 JSON으로 보내주게 되고
                // 해당 데이터를 읽어와야 해요!
                // 데이터 연결통로(Stream)을 열어서 데이터를 읽어와야 해요!
                // 데이터 연결통로는 기본적인 Stream을 먼저 얻고 이를 이용해서
                // 조금 더 사용하기 편하기 통로로 변경해서 사용
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line;
                StringBuffer sb = new StringBuffer();

                // 반복적으로 서버가 보내준 데이터를 읽어요
                while( (line = br.readLine()) != null ) {
                    sb.append(line);
                }
                br.close();

                Log.i("KAKAO",sb.toString());
                // 데이터가 JSON으로 정상적으로 온걸 확인할 수 있어요!
                // documents : [ {책1권} , {책1권}, {책1권}, ...  ]
                // JSON을 처리해서 documents라고 되어있는 key값에 대해
                // Value값을 객체화 해서 가지고 올거예요!!
                // JACKSON library를 이용해서 처리
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> map = mapper.readValue(sb.toString(),
                        new TypeReference<Map<String,Object>>() {});

                Object jsonObject = map.get("documents");
                // jsonObject => [ {책1권} , {책1권}, {책1권}, ...  ]

                // 위에서 얻은 객체를 다시 JSON 문자열로 변환
                String jsonString = mapper.writeValueAsString(jsonObject);
                // jsonString => "[ {책1권} , {책1권}, {책1권}, ...  ]"
                Log.i("KAKAO",jsonString);

                // ArrayList안에 책객체가 들어있는 형태로 만들어야 해요!
                ArrayList<KAKAOBookVO> searchBooks =
                        mapper.readValue(jsonString,
                                new TypeReference<ArrayList<KAKAOBookVO>>() {});

                // 최종결과를 만들어야 해요!
                ArrayList<String> resultData = new ArrayList<String>();

                for(KAKAOBookVO vo : searchBooks) {
                    resultData.add(vo.getTitle());
                }

                // 책 제목만 들어있는 ArrayList를 만들어서 Activity에게
                // 보내주면되요!!
                Intent i = new Intent(getApplicationContext(),
                        Example17_KAKAOBookSearchActivity.class);
                i.putExtra("BOOKRESULT",resultData);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            } catch(Exception e) {
                Log.i("KAKAO",e.toString());
            }
        }
    }

}


// VO를 만들기위해서 class정의
class KAKAOBookVO {

    private ArrayList<String> authors;
    private String contents;
    private String datetime;
    private String isbn;
    private String price;
    private String publisher;
    private String sale_price;
    private String status;
    private String thumbnail;
    private String title;
    private ArrayList<String> translators;
    private String url;

    public KAKAOBookVO() { }     // default 생성자

    // 모든 field를 이용하는 constructor를 만들어야 해요!
    public KAKAOBookVO(ArrayList<String> authors, String contents, String datetime, String isbn, String price, String publisher, String sale_price, String status, String thumbnail, String title, ArrayList<String> translators, String url) {
        this.authors = authors;
        this.contents = contents;
        this.datetime = datetime;
        this.isbn = isbn;
        this.price = price;
        this.publisher = publisher;
        this.sale_price = sale_price;
        this.status = status;
        this.thumbnail = thumbnail;
        this.title = title;
        this.translators = translators;
        this.url = url;
    }

    // getter & setter

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getTranslators() {
        return translators;
    }

    public void setTranslators(ArrayList<String> translators) {
        this.translators = translators;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
