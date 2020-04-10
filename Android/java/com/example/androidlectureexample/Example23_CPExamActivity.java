package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
*   Content Provider(내용제공자) App에서 관리하는 데이터(SQLite Database)를
*   다른 App이 접근할 수 있도록 해주는 기능을 해요!
*   CP는 Android 구성요소중의 하나로 안드로이드 시스템에 의해서 관리되요!
*   AndroidManifest.xml파일에 등록해서 사용하게되요!
*   CP가 필요한 이유는 보안규정때문!! 안드로이드 App은 오직 자신의 APP안에서
*   생성한 데이터만 사용할 수 있어요. 다른 App이 가지고 있는 데이터의 접근권한이
*   없어요!!
*   CP를 이용하면 다른 App의 데이터에 접근할 수 있어요!!
*   CP를 이용해서 다른 App의 데이터에 접근하는데 일반적으로 Database에 접근하는
*   방식을 이용합니다.
*   그 이유는 CP가 CRUD를 기반으로 하고 있기 때문에.
*
*   1. 데이터베이스(SQLite)를 이용하기 때문에 SQLiteOpenHelper class를 이용해서
*      DB를 이용할꺼예요!
*
* */

// SQLiteOpenHelper 이용
// PersonDatabaseHelper 객체를 만들면 Database가 생성되고 Table이
// 만들어져요!!
class PersonDatabaseHelper extends SQLiteOpenHelper {

    PersonDatabaseHelper(Context context) {
        super(context, "person.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터베이스가 초기에 만들어지는 시점에
        // Table도 같이 만들꺼예요!
        String sql = "CREATE TABLE IF NOT EXISTS " +
                "person( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, age INTEGER, mobile TEXT)";
        db.execSQL(sql);
        Log.i("DBTest","Table이 생성되었어요!!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 일단 제외!!
    }
}

public class Example23_CPExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example23_cp_exam);

        // 버튼누르면 사람에 대한 데이터 입력처리
        Button _23_empInsertBtn = findViewById(R.id._23_empInsertBtn);
        _23_empInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 원래는 입력된 데이터를 가지고 와서 CP를 이용해서 입력처리!
                // CP를 찾아야해요!!
                String uriString = "content://com.exam.person.provider/person";
                Uri uri = new Uri.Builder().build().parse(uriString);

                // Hashmap형태로 데이터베이스에 입력할 데이터를 저장.
                ContentValues values = new ContentValues();
                values.put("name","홍길동");
                values.put("age",20);
                values.put("mobile","010-1111-5555");

                getContentResolver().insert(uri,values);
                Log.i("DBTest","데이터가 입력되었어요!!");
            }
        });

        final TextView _23_resultTv = findViewById(R.id._23_resultTv);

        Button _23_empSelectBtn = findViewById(R.id._23_empSelectBtn);
        _23_empSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DBTest","Select 클릭!!");
                // 1. DB처리 기능을 제공하는 Content Provider를 찾아야 해요!
                //    Content Provider를 찾기 위한 URI가 있어야 해요
                String uriString = "content://com.exam.person.provider/person";
                Uri uri = new Uri.Builder().build().parse(uriString);
                // 2. Uri를 이용해서 Content Provider를 찾아서 특정 method를 호출
                // column을 표현하기 위한 String[]을 생성해요!
                // "select name, age, mobile from person"
                String[] columns = new String[]{"name", "age", "mobile"};
                //
                Cursor cursor = getContentResolver().query(
                        uri,columns,null,null,
                        "name ASC");
                // 성공하면 Database table에서 결과 record의 집합을 가져와요!!
                while(cursor.moveToNext()) {
                    String name = cursor.getString(0);
                    int age = cursor.getInt(1);
                    String mobile = cursor.getString(2);

                    String result = "record => " + name + ", " + age +
                            ", " + mobile;

                    _23_resultTv.append(result + "\n");

                }


            }
        });
    }
}
