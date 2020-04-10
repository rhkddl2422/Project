package com.example.androidlectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Example24_contactActivity extends AppCompatActivity {

    private TextView resultTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example24_contact);

        // 결과가 출력될 TextView
        resultTv = findViewById(R.id.resultTv);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionResult = ActivityCompat.checkSelfPermission(
                    getApplicationContext(),
                    Manifest.permission.READ_CONTACTS);

            if(permissionResult == PackageManager.PERMISSION_DENIED) {
                if(shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder dialog =
                            new AlertDialog.Builder(Example24_contactActivity.this);
                    dialog.setTitle("권한이 필요해요!");
                    dialog.setMessage("주소록 읽기 권한이 필요합니다.! 수락할까요?");
                    dialog.setPositiveButton("네!!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(
                                    new String[]{Manifest.permission.READ_CONTACTS},
                                    100);
                        }
                    });
                    dialog.setNegativeButton("아니요!!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.create().show();

                } else {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_CONTACTS},
                            100);
                }

            } else {
                // 권한이 있으면
                Log.i("ContactTest","권한있음.!!");
                processContact();
            }
        } else {
            // 만약 우리가 사용하는 기기가 M 미만이면
            Log.i("ContactTest","OS버전 낮음");
        }

    }

    private void processContact() {
        // 주소록 가져오는 코드를 작성하면 될 거 같아요~!!
        // 1. Content Resolver를 이용해서 데이터를 가져오면 되요!
        //    select계열을 사용해야 해요! => query() method를 이용
        //    첫번째 인자로 URI가 들어가야 해요!! => Content Provider를
        //    찾아야 해요!
        //    어떻게 select처리를 할지 인자를 적어줘야 해요!
        Cursor cursor =
                getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null,   // 모든 컬럼을 다 들고와!!
                null,   // 조건절 : 조건없이 다 들고와!!
                null,  // 조건절에 사용할 값.
                null);    // 정렬방향!!

        while(cursor.moveToNext()) {
            // 각 사람의 이름과 ID를 가져와야 해요!
            // 전화번호는 다른 Table에서 관리해요(전화번호는 여러개가 될 수 있어서)
            // ID를 이용해서 각 사람의 전화번호를 다시 얻어와야 해요!
            String id = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Log.i("ContactTest","얻어온 ID : " + id);
            String name = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.i("ContactTest","얻어온 이름 : " + name);

            String msg = "";

            Cursor mobileCursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, // 모든 컬럼 다 가져와!!
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +
                            " = " + id,
                    null,
                    null);

            while(mobileCursor.moveToNext()) {
                String mobile = mobileCursor.getString(
                        mobileCursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                msg = "이름 : " + name + ", " + "전화번호 : " + mobile;
            }
            resultTv.append(msg + "\n");

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
                Log.i("ContactTest","onRequestPermissionsResult() 호출");
                processContact(); // 주소록 가져오는 method를 호출!!
            }
        }

    }
}
