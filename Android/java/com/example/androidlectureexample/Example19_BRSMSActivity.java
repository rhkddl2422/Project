package com.example.androidlectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/*
* 1. 우리 App이 휴대단말로 온 문자메시지를 처리하려고 해요!
*    문자메시지 처리는 상당히 개인적인 정보예요! => 보안처리
*    - AndroidManifest.xml파일에 기본 보안에 대한 설정이 필요!
*    <uses-permission android:name="android.permission.RECEIVE_SMS" /> 설정!!
*
* 2. Activity가 실행되면 보안설정부터 해야해요!
*    마쉬멜로우 버전이후 부터는 강화된 보안정책을 따라야 해요!!
*    보안처리코드가 나와야 해요! (전형적인 코드로 사용을 해요!! )
*
*
* 3. Broadcast Receiver Component를 하나 생성
*    코드상에서 생성하는게 아니라 AndroidManifest.xml에 등록해서 생성.
*    외부 Component로 따로 생성되기 때문에 AndroidManifest.xml에 자동등록!
*    => 생성된 후 intent-filter를 이용해서 어떤 broadcast signal을 수신할지를
*       설정해야 해요!
*    SMS문자가 오면 이 문자를 Broadcast Receiver가 받아서
*    화면에 표현하기 위해 Activity에게 전달할꺼예요!
*
*    Broadcast Receiver에서 Activity로 데이터가 전달되고..
*    Intent를 통해서 데이터가 전달되요!!
*    => activity는 method를 이용해서 Intent를 받아요!
*    => onNewIntent()
*
* */
public class Example19_BRSMSActivity extends AppCompatActivity {

    private TextView _19_smsSenderTv;
    private TextView _19_smsMessageTv;
    private TextView _19_smsDateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example19_br_sms);

        _19_smsSenderTv = findViewById(R.id._19_smsSenderTv);
        _19_smsMessageTv = findViewById(R.id._19_smsMessageTv);
        _19_smsDateTv = findViewById(R.id._19_smsDateTv);

        // 1. 사용자의 단말기 OS(안드로이드 버전)이 마쉬멜로우 버전 이전이냐
        //    이후인지를 check
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 만약 우리가 사용하는 기기가 M 이상이면
            // 사용자 권한 중 SMS 받기 권한이 설정되어 있는지를 check!!
            int permissionResult = ActivityCompat.checkSelfPermission(
                    getApplicationContext(),
                    Manifest.permission.RECEIVE_SMS);

            if(permissionResult == PackageManager.PERMISSION_DENIED) {
                // 권한이 없으면
                // 1. 앱을 처음 실행해서 아예 물어본적이 없는경우
                // 2. 권한 허용에 대해서 사용자에게 물어는 봤지만 사용자가
                //    거절을 선택한 경우
                // 왜 권한이 없을까??
                if(shouldShowRequestPermissionRationale(
                        Manifest.permission.RECEIVE_SMS)) {
                    // true => 권한을 거부한적이 있는 경우.
                    // 일반적으로 dialog같은걸 이용해서 다시 물어봐요!!
                    AlertDialog.Builder dialog =
                            new AlertDialog.Builder(Example19_BRSMSActivity.this);
                    dialog.setTitle("권한이 필요해요!");
                    dialog.setMessage("SMS 수신기능이 필요합니다.! 수락할까요?");
                    dialog.setPositiveButton("네!!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(
                                    new String[]{Manifest.permission.RECEIVE_SMS},
                                    100);
                        }
                    });
                    dialog.setNegativeButton("아니요!!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 권한설정을 하지 않는다는 의미이므로
                            // 아무런 작업도 할 필요 없어요!
                        }
                    });
                    // 만들어진 dialog를 화면에 띄워요!!
                    dialog.create().show();

                } else {
                    // false => 한번도 물어본적이 없는 경우.
                    // 여러권한을 동시에 사용자에게 요청할 수 있기때문에
                    // 인자가 String 배열로 들어가요!!
                    requestPermissions(
                            new String[]{Manifest.permission.RECEIVE_SMS},
                            100);
                }

            } else {
                // 권한이 있으면
                Log.i("SMSTest","보안설정 통과!!");
            }
        } else {
            // 만약 우리가 사용하는 기기가 M 미만이면
            Log.i("SMSTest","보안설정 통과!!");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 사용자가 권한을 설정하게 되면 이 부분이 마지막으로 호출되게 되요!!
        // 사용자가 권한설정을 하거나 그렇지 않거나 두가지 경우 모두 이 callback
        // 메소드가 호출되요!
        if(requestCode == 100) {
            if(grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 사용자가 권한 허용을 눌렀을 경우.
                Log.i("SMSTest","보안설정 통과!!");
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Broadcast receiver가 보내준 intent를 이놈이 받아요!
        // intent안에 들어있는 정보를 꺼내서 화면에 출력!!

        String sender = intent.getExtras().getString("sender");
        String msg = intent.getExtras().getString("msg");
        String rdDate = intent.getExtras().getString("date");

        _19_smsSenderTv.setText(sender);
        _19_smsMessageTv.setText(msg);
        _19_smsDateTv.setText(rdDate);
    }
}
