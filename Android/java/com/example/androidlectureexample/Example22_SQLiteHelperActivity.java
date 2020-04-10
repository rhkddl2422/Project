package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// DatabaseOpenHelper를 이용하려면 별로의 class를 define
class MyDatabaseHelper extends SQLiteOpenHelper {

    // 인자도 없고 하는일도 없는 생성자 => default constructor
    // 만약 직접 코드에 명시하지 않으면 javac compiler가 삽입.
    MyDatabaseHelper(Context context, String dbName, int version) {
        //super();   // 상위 class의 생성자를 호출
                   // SQLiteOpenHelper은 생성자 중에 default 생성자가 없어요!
        super(context, dbName, null, version);
        // 만약 dbName으로 만든 데이터베이스가 없으면 해당 데이터베이스를
        // 생성할 때 version정보를 같이 명시.
        // onCreate()를 callback -> onOpen() 호출
        // 만약 dbName으로 만든 데이터베이스가 이미 존재하면
        // onOpen() 호출
        // 만약 dbName으로 만든 데이터베이스가 이미 존재하는데 만약 version값이
        // 기본 만들었을때의 version값과 다르면
        // => onUpgrade() 호출
        //    기존 데이터베이스의 스키마를 변경하는 작업을 진행.
        //    초창기에 앱을 만들어서 배포할때 DB 스키마를 생성.
        //    앱을 업그레이드해서 다시 배포할때 DB 스키마를 대시 생성하기 위해서
        //    사용이 되요!!
        //    이전 DB를 drop시키고 새로운 DB를 만드는 작업을 진행
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // 데이터베이스가 Open될때 자동으로 호출!!
        Log.i("DBTest","onOpen() 호출!!");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터베이스가 존재하지 않아서 생성할때 호출!!
        Log.i("DBTest","onCreate() 호출!!");
        // 데이터베이스 생성(테이블 생성)하는 코드가 나와요!!
        String sql = "CREATE TABLE IF NOT EXISTS " +
                "person( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, age INTEGER, mobile TEXT)";
        // 완성된 SQL을 어떤 database에서 실행시킨건지를 결정해서 실행~!
        db.execSQL(sql);
        Log.i("DBTest","Table이 생성되었어요!!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 데이터베이스 버전이 바뀌어서 데이터베이스를 수정할때 호출!!
        Log.i("DBTest","onUpgrade() 호출!!");
    }
}

public class Example22_SQLiteHelperActivity extends AppCompatActivity {

    private SQLiteDatabase database;   // 사용할 Database에 대한 reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example22_sqlite_helper);

        Button _22_dbCreateBtn = findViewById(R.id._22_dbCreateBtn);
        _22_dbCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et = findViewById(R.id._22_dbNameEt);
                // 사용자가 입력한 Database 이름을 가져와요!
                String dbName = et.getText().toString();
                // Database를 생성해보아요. 생성한 후 Open 수행.
                // 만약 Database가 존재하면 Open만 실행

                // Helper class를 이용해서 database를 생성 및 open을 수행
                MyDatabaseHelper helper =
                        new MyDatabaseHelper(Example22_SQLiteHelperActivity.this,
                                dbName,2);
                // helper를 통해서 database reference를 획득
                database = helper.getWritableDatabase();
                // 1. 새로운 데이터베이스를 만들어 보아요!!
                // helper가 생성되면서 데이터베이스가 만들어지고
                // onCreate() -> onOpen()
            }
        });
    }
}
