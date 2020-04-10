package com.example.androidlectureexample;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/*
*    Android는 Embedded 형태로 개발된 DBMS를 하나 내장하고 있어요
*    경량화된 DBMS로 SQLite라고 불러요!
*    파일로 이루어져 있기 때문에 Database의 복사, 이동같은 처리가 쉽고
*    속도가 빠르고 표준 SQL을 지원해요!
*
*    Database를 생성하고 Table을 만든 후 Data를 insert할 꺼예요!
*    입력된 데이터를 확인해야 하는데.. 외부 tool을 이용해서 확인해보아요!
*    http://sqlitebrowser.org
* */
public class Example21_SQLiteBasicActivity extends AppCompatActivity {

    private EditText _21_dbNameEt;  // database이름 입력 EditText
    private EditText _21_tableNameEt; // table이름 입력 EditText
    private SQLiteDatabase database; // 생성된 database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example21_sqlite_basic);

        _21_dbNameEt = findViewById(R.id._21_dbNameEt);
        _21_tableNameEt = findViewById(R.id._21_tableNameEt);

        Button _21_dbCreateBtn = findViewById(R.id._21_dbCreateBtn);
        _21_dbCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Database 생성!!
                // 파일로 만들어져요! "데이터베이스이름.db" 라는 파일로 생성.
                // /data/data/우리App/ 여기에 생성되요!

                String dbName = _21_dbNameEt.getText().toString();
                // MODE_PRIVATE : 0값이고 일반적인 형태(읽고쓰기가 가능한) DB
                //                생성하거나 open하는 의미.
                database = openOrCreateDatabase(dbName,
                        MODE_PRIVATE,null);
                Log.i("DBTest","Database가 생성되었어요!!");
            }
        });

        Button _21_tableCreateBtn = findViewById(R.id._21_tableCreateBtn);
        _21_tableCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // table이름을 들고와요!!
                String tableName = _21_tableNameEt.getText().toString();

                if(database == null) {
                    Log.i("DBTest","Database부터 생성해주세요!");
                    return;
                }
                // 현재 database에 대한 reference가 존재!
                // SQL을 이용해서 Database안에 Table을 생성하면 되요!!
                String sql = "CREATE TABLE IF NOT EXISTS " +
                        tableName + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, age INTEGER, mobile TEXT)";
                // 완성된 SQL을 어떤 database에서 실행시킨건지를 결정해서 실행~!
                database.execSQL(sql);
                Log.i("DBTest","Table이 생성되었어요!!");
            }
        });

        final EditText _21_empNameEt = findViewById(R.id._21_empNameEt);
        final EditText _21_empAgeEt = findViewById(R.id._21_empAgeEt);
        final EditText _21_empMobileEt = findViewById(R.id._21_empMobileEt);

        Button _21_empInsertBtn = findViewById(R.id._21_empInsertBtn);
        _21_empInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력된 사용자를 table에 insert!!
                String name = _21_empNameEt.getText().toString();
                int age = new Integer(_21_empAgeEt.getText().toString()).intValue();
                String mobile = _21_empMobileEt.getText().toString();

                if(database == null) {
                    Log.i("DBTest","데이터베이스를 먼저 Open해주세요!");
                    return;
                }
                String sql = "INSERT INTO emp(name,age,mobile) VALUES " +
                        "('" + name +"'," + age + ",'" + mobile +"')";

                database.execSQL(sql);
                Log.i("DBTest","데이터가 정상적으로 입력되었어요!!");

                _21_empNameEt.setText("");
                _21_empAgeEt.setText("");
                _21_empMobileEt.setText("");
            }
        });

        final TextView _21_resultTv = findViewById(R.id._21_resultTv);

        // select 처리
        Button _21_empSelectBtn = findViewById(R.id._21_empSelectBtn);
        _21_empSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql = "SELECT _id, name, age, mobile FROM emp";
                // JDBC와 상당히 유사해요!~
                if( database == null ) {
                    Log.i("DBTest","DB를 먼저 오픈해 주세요!!");
                    return;
                }
                // execSQL() : select계열이 아닌 SQL문장을 실행할때 사용.
                // rawQuery() : select계열의 SQL문장을 실행할때 사용.
                Cursor cursor = database.rawQuery(sql,null);

                while(cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    int age = cursor.getInt(2);
                    String mobile = cursor.getString(3);

                    String result = "레코드 : " + id + ", " + name + ", " +
                            age + ", " + mobile;
                    _21_resultTv.append(result + "\n");
                }

            }
        });
    }
}
