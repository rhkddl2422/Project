package com.example.androidlectureexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

// Service가 실행되면
// 만약 Service가 존재하지 않는다면 일단 Service를 생성해야 해요!
// Service를 생성하기 위해서 생성자가 호출
// onCreate()가 호출되고 그 다음에 onStartCommand()호출되서 로직처리를 시작
// 만약 Service 객체가 이미 존재하고 있으면
// onStartCommand()만 호출되서 로직처리를 시작

public class Example16Sub_Service extends Service {
    public Example16Sub_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // 기본 3개의 method를 overriding

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 로직처리를 하는 부분!!
        // Activity로 부터 전달된 intent가 이 method의 인자로 전달.
        String data = intent.getExtras().getString("DATA");

        // 전달받은 데이터를 이용해서 일반적인 로직처리를 진행해요!!
        // 만약 로직처리가 길어지면 activity가 block되요!
        // 이런 경우를 방지하기 위해서 일반적으로 Thread를 활용해서 로직처리를해요!

        // 결과데이터를 Service에서 생성했어요!!
        String resultData = data + " 를 받았어요!!";
        // 이 결과데이터를 Activity에게 전달하고 Activity는 화면에
        // 결과데이터를 TextView로 출력!!
        Intent resultIntent = new Intent(getApplicationContext(),
                Example16_ServiceDataTransferActivity.class);

        // 결과값을 intent에 부착!!
        resultIntent.putExtra("RESULT",resultData);

        // Service에서 Activity를 호출하려고 해요!
        // 화면이 없는 Service에서 화면을 가지고 있는 Activity를 호출
        // TASK라는게 필요!
        // Activity를 새로 생성하는게 아니라 메모리에 존재하는
        // 이전 Activity를 찾아서 실행 => 플래그 2개를 추가
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(resultIntent);


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
