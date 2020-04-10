package com.example.androidlectureexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button _01_linearlayoutBtn =
                (Button)findViewById(R.id._01_linearlayoutBtn);

        _01_linearlayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼이 눌리면 이 부분이 실행되요!!
                // 새로운 activity를 찾아서 실행!!
                // 2가지 방식으로 activity를 찾을 수 있는데..
                // explicit방식과 implicit방식이 있어요! (일단 explicit방식부터)
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example01_LayoutActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _02_widgetBtn = (Button)findViewById(R.id._02_widgetBtn);
        _02_widgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example02_WidgetActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _03_EventBtn = (Button)findViewById(R.id._03_EventBtn);
        _03_EventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example03_EventActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _04_ActivityEventBtn = (Button)findViewById(R.id._04_ActivityEventBtn);
        _04_ActivityEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example04_TouchEventActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _05_SwipeEventBtn = (Button)findViewById(R.id._05_SwipeEventBtn);
        _05_SwipeEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example05_SwipeActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _06_SendMessageBtn = (Button)findViewById(R.id._06_SendMessageBtn);
        _06_SendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // alert창(dialog)를 이용해서 문자열을 입력받고
                // 입력받은 문자열을 다음 activity로 전달

                // 사용자가 문자열을 입력할 수 있는 widget을 일단 하나 생성
                final EditText edittext = new EditText(MainActivity.this);
                // AlertDialog를 하나 생성해야 해요
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Activity 데이터 전달");
                builder.setMessage("다음 Activity에 전달할 내용을 입력하세요");
                builder.setView(edittext);
                builder.setPositiveButton("전달",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 전달을 눌렀을때 수행되는 이벤트 처리작업을 하면되요!
                                Intent i = new Intent();
                                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                                        "com.example.androidlectureexample.Example06_SendMessageActivity");
                                i.setComponent(cname);
                                // 데이터를 전달해서 activity를 시작해야 해요!
                                i.putExtra("sendMSG",edittext.getText().toString());
                                startActivity(i);
                            }
                        });
                builder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 취소버튼이기 때문에 특별한 이벤트처리를
                                // 할 필요가 없어요!!
                            }
                        });
                builder.show();
            }
        });

        Button _07_DataFromBtn = (Button)findViewById(R.id._07_DataFromBtn);
        _07_DataFromBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example07_DataFromActivity");
                i.setComponent(cname);
                // 새로 생성되는 activity로부터 데이터를 받아오기 위한 용도
                // 두번째 activity가 finish되는 순간 데이터를 받아와요!!

                startActivityForResult(i,3000);
            }
        });

        Button _08_ANRBtn = (Button)findViewById(R.id._08_ANRBtn);
        _08_ANRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example08_ANRActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _09_CounterLogBtn = (Button)findViewById(R.id._09_CounterLogBtn);
        _09_CounterLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example09_CounterLogActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _10_CounterLogProgressBtn = (Button)findViewById(R.id._10_CounterLogProgressBtn);
        _10_CounterLogProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example10_CounterLogProgressActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _11_CounterLogHandlerBtn = (Button)findViewById(R.id._11_CounterLogHandlerBtn);
        _11_CounterLogHandlerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example11_CounterLogHandlerActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _12_BookSearchSimpleBtn = (Button)findViewById(R.id._12_BookSearchSimpleBtn);
        _12_BookSearchSimpleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example12_SimpleBookSearchActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _13_BookSearchDetailBtn = (Button)findViewById(R.id._13_BookSearchDetailBtn);
        _13_BookSearchDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example13_DetailBookSearchActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _14_ImplicitIntentBtn = (Button)findViewById(R.id._14_ImplicitIntentBtn);
        _14_ImplicitIntentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Explicit Intent(명시적 인텐트)
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example14_ImplicitIntentActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        /*
        App이 실행되었다고 해서 항상 Activity가 보이는건 아니예요. 대표적인 경우
        카톡, 멜론...
        Service는 화면이 없는 Activity라고 생각하시면 되요!
        Activity는 onCreate() -> onStart() -> onResume() -> onPause -> onStop()
        Service는 onCreate() -> onStartCommand() -> onDestroy()
        눈에 보이지 않기 때문에 background에서 로직처리하는데 많이 이용.
         */

        Button _15_ServiceLifecycleBtn = (Button)findViewById(R.id._15_ServiceLifecycleBtn);
        _15_ServiceLifecycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example15_ServiceLifeCycleActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _16_ActivityServiceDataBtn = (Button)findViewById(R.id._16_ActivityServiceDataBtn);
        _16_ActivityServiceDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example16_ServiceDataTransferActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _17_KAKAOBookSearchBtn = (Button)findViewById(R.id._17_KAKAOBookSearchBtn);
        _17_KAKAOBookSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example17_KAKAOBookSearchActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _18_BRTestBtn = (Button)findViewById(R.id._18_BRTestBtn);
        _18_BRTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example18_BRTestActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _19_BRSMSBtn = (Button)findViewById(R.id._19_BRSMSBtn);
        _19_BRSMSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example19_BRSMSActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _20_BRNotiBtn = (Button)findViewById(R.id._20_BRNotiBtn);
        _20_BRNotiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example20_BRNotiActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });


        Button _21_SQLiteBasicBtn = (Button)findViewById(R.id._21_SQLiteBasicBtn);
        _21_SQLiteBasicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example21_SQLiteBasicActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _22_SQLiteHelperBtn = (Button)findViewById(R.id._22_SQLiteHelperBtn);
        _22_SQLiteHelperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example22_SQLiteHelperActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _23_CPExamBtn = (Button)findViewById(R.id._23_CPExamBtn);
        _23_CPExamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example23_CPExamActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _24_contactBtn = (Button)findViewById(R.id._24_contactBtn);
        _24_contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example24_contactActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });
















    }    // end of onCreate()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 3000 && resultCode == 7000) {
            String msg = (String)data.getExtras().get("ResultValue");
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
