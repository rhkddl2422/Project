package com.example.androidlectureexample;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Example14_ImplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example14_implicit_intent);

        Button implicitIntentBtn = (Button)findViewById(R.id.implicitIntentBtn);
        implicitIntentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼을 클릭했을 때 새로운 Activity를 실행(묵시적 Intent 이용)
                // implicit Intent(묵시적 인텐트)
                // 방금 생성한 Activity를 호출하려 해요!
                Intent i = new Intent();
                i.setAction("MY_ACTION");
                i.addCategory("INTENT_TEST");
                i.putExtra("SEND DATA","안녕하세요!!");
                startActivity(i);
            }
        });

        Button dialBtn = (Button)findViewById(R.id.dialBtn);
        dialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 전화걸기 Activity를 호출하려면 2가지 중 1가지를 이용
                // 1. 클래스명을 알면 호출할 수 있어요!(Explicit intent이용)
                // 2. 묵시적 Intent를 이용해서 알려있는지 Action을 통해서 이용
                Intent i = new Intent();
                i.setAction(Intent.ACTION_DIAL);
                // Intent.ACTION_DIAL => 전화가 실제로 걸리는건 아니예요.
                i.setData(Uri.parse("tel:01037659900"));
                startActivity(i);
            }
        });

        Button browserBtn = (Button)findViewById(R.id.browserBtn);
        browserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 특정 URL을 이용해서 browser를 실행
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.naver.com"));
                startActivity(i);
            }
        });

        /*
        먼저 전화걸기 기능을 사용하려면 AndroidManifest.xml파일에
        권한설정을 해야해요!!
        안드로이드 6.0(마쉬멜로우) 이상에서는 manifest파일에 기술하는거 이외에
        사용자에게 권한을 따로 요청해요!!
        권한자체가 크게 2가지로 분류되서 관리되요!
        - 일반 권한(normal permission)
        - 위험 권한(dangerous permission) : 위치, 전화걸기기능,카메라,
                                           마이크,문자,일정,주소록,센서
        특정 앱을 사용할 때 앱이 사용자에게 권한을 요구하고
        사용자가 권한을 허용하면 그 기능을 이용할 수 있어요!
        설정 > 어플리케이션 > 앱 > 권한 부분을 이용하면 이미 허용한 권한을
        취소할 수 있어요!
         */
        Button callBtn = (Button)findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 사용하는 안드로이드 버전이 "M" 버전 이상인지 확인
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // 사용자의 Android version이 6이상인 경우
                    // 사용자 권한 중 전화걸기 권한이 설정되어 있는지 확인!
                    int permissionResult =
                            ActivityCompat.checkSelfPermission(getApplicationContext(),
                                    Manifest.permission.CALL_PHONE);
                    // 권한을 이미 허용했는지 그렇지 않은지를 비교해서 다르게 처리
                    if(permissionResult == PackageManager.PERMISSION_DENIED) {
                        // 권한이 없는 경우

                        // 권한 설정을 거부한 적이 있는지 그렇지 않은지를 확인
                        if(shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                            // 임의로 app의 권한을 끄거나 혹은
                            // 권한 요청 화면에서 거절을 클릭했을 경우
                            AlertDialog.Builder dialog =
                                    new AlertDialog.Builder(Example14_ImplicitIntentActivity.this);
                            dialog.setTitle("권한요청에 대한 Dialog");
                            dialog.setMessage("전화걸기 기능이 필요해요. 수락할까요?");
                            dialog.setPositiveButton("네",
                                    new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                            1000);
                                }
                            });
                            dialog.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 할일이 없어요!!
                                }
                            });
                            dialog.show();
                        } else {
                            // 권한을 거부한 적이 없음.
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                    1000);
                        }
                    } else {
                        // 권한이 있는 경우
                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:01012345678"));
                        startActivity(i);
                    }
                } else {
                    // 사용자의 Android version이 6미안인 경우
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:01012345678"));
                    startActivity(i);
                }
            }
        });

    }  // end of onCreate()

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 위에서 수행한 권한 요청에 대한 응답인지를 확인!
        if(requestCode == 1000) {
            // 권한 요청의 응답개수가 1개 이상이고
            // 지금 상황에서 전화걸기 기능 1개만 물어봤으니까 첫번째 배열에
            // 그 결과가 담겨도착!
            if(grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한 허용을 눌렀으면..
                Intent i = new Intent();
                i.setAction(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:01012345678"));
                startActivity(i);
            }

        }
    }
}
