package com.example.androidlectureexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Example15Sub_LifeCycleService extends Service {

    private Thread myThread;

    public Example15Sub_LifeCycleService() {
    }

    // 사용하지 않을꺼예요!!
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Service객체가 생성될 때 호출
        Log.i("ServiceExam","onCreate() 호출");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 실제 서비스 동작을 수행하는 부분
        // onCreate() -> onStartCommand()
        // Service가 하는 일은.. 1초간격으로 1부터 시작해서 10까지 숫자를
        // Log로 출력!!
        myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Thread가 시작되면 1초동안 잤다가 깨서 Log를 이용해서 숫자 출력
                for(int i=0; i<100; i++) {
                    try {
                        Thread.sleep(1000);   // 1초
                        // sleep을 하려고할때 만약 interrupt가 걸려있으면
                        // Exception이 발생!!
                        Log.i("ServiceExam","현재 숫자는 : " + i);
                    } catch(Exception e) {
                        Log.i("ServiceExam",e.toString());
                        break;   // 가장 가까운 LOOP을 벗어나는 키워드.
                    }
                }
            }
        });
        myThread.start();
        // Thread가 가지고 있는 run() method가 호출되요!!
        // 언젠가는 run() method의 실행이 끝나요!!
        // Thread의 상태가 DEAD상태가 되요! => 이 DEAD상태에서 다시
        // 실행을 시킬 수 있는 방법이 없어요!!
        // 유일하게 다시 실행시키는 방법은 Thread를 다시 생성해서 실행

        Log.i("ServiceExam","onStartCommand() 호출");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // stopService()가 호출되면 onDestroy()가 호출되요!
        // 현재 Service에 의해서 동작하고 있는 모든 Thread를 종료!!
        if(myThread != null && myThread.isAlive()) {
            // Thread가 존재하고 현재 Thread가 실행중이면
            // Thread를 중지시켜!!
            myThread.interrupt();
        }
        Log.i("ServiceExam","onDestroy() 호출");
        super.onDestroy();
    }
}
