package com.example.androidlectureexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

/*
*  보안설정이 잘 되어 있으면
*  특정 signal(Broadcast가 전파되면)이 발생하면
*  해당 Broadcast를 받을 수 있어요!!
*
* */
public class Example19Sub_SMSBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Broadcast를 받으면 이 method가 호출되요!!
        // SMS가 도착하면 해당 method가 호출되요!!
        Log.i("SMSTest","SMS가 도착했어요!!!");
        // 만약 SMS signal을 받을 수 있으면
        // 전화번호, 문자내용을 뽑아서 Activity에게 전달하면 되요!!
        // 전달받은 Intent안에 보낸사람의 전화번호, 메시지 내용, 날짜..
        // 정보가 들어있어요!

        // Intent안에 포함되어 있는 정보를 추출해요!!
        Bundle bundle = intent.getExtras();

        // Bundle안에 key,value형태로 데이터가 여러개 저장되어 있는데
        // SMS의 정보는 "pdus"라는 키값으로 저장되어 있어요!
        // 거의 시간상 동시에 여러개의 SMS가 도착할 수 있어요!!
        // 객체 1개가 SMS 메시지 1개를 의미.
        Object[] obj = (Object[])bundle.get("pdus");

        SmsMessage[] message = new SmsMessage[obj.length];

        // 우리 예제에서는 1개의 SMS만 전달된다고 가정하고 진행.
        // Object 객체 형태를 SmsMessage 객체 형태로 converting.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String format = bundle.getString("format");
            message[0] = SmsMessage.createFromPdu((byte[])obj[0],format);
        } else {
            message[0] = SmsMessage.createFromPdu((byte[])obj[0]);
        }

        // 보낸사람 전화번호를 SmsMessage객체에서 추출
        String sender = message[0].getOriginatingAddress();
        // SMS 문자내용을 추출
        String msg = message[0].getMessageBody();
        // SMS 받은 시간을 추출
        String reDate = new Date(message[0].getTimestampMillis()).toString();

        Log.i("SMSTest","전화번호 : " + sender);
        Log.i("SMSTest","내용 : " + msg);
        Log.i("SMSTest","시간 : " + reDate);

        // 데이터를 잘 받아왔으면 해당 Data를 Activity에게 전달해요!
        Intent i = new Intent(context,
                Example19_BRSMSActivity.class);
        // 기존에 이미 생성되어 있는 activity에게 전달해야 하므로 flag를 설정
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Intent에 데이터를 저장해서 보내요!
        i.putExtra("sender",sender);
        i.putExtra("msg",msg);
        i.putExtra("date",reDate);

        context.startActivity(i);
    }
}
