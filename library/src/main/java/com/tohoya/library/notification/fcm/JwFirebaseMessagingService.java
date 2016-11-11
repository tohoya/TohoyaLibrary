package com.tohoya.library.notification.fcm;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.tohoya.library.R;

import java.util.Map;

/**
 * Created by jinyoungho on 2016. 11. 11..
 */

public class JwFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "JFMS";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

//        Map<String, String> bundle = remoteMessage.getData();
//        System.out.println(bundle.toString());
//        String title = bundle.get("title"); //노드 서버에서 message에서 title 받아오기
//        String message = bundle.get("message");
//        String location = bundle.get("location");
//        Double lat = Double.valueOf(bundle.get("lat"));//노드 서버에서 message에서 message 받아오기
//        Double lot = Double.valueOf(bundle.get("lot"));//노드 서버에서 message에서 message 받아오기
//
//
//        Intent intent = new Intent(this, MainActivity.class);//푸시알람 클릭시 띄울 페이지
//        intent.putExtra("location",location);
//        intent.putExtra("lat", lat); // 인텐트에 데이터 위도 담아주기
//        intent.putExtra("lot", lot); // 인텐트에 데이터 경도 담아주기
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //MainActiviy 위쪽의 스택을 모두 제거
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);  //PendingIntent를 FLAG_ONE_SHOT(일회용)
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(android.R.drawable.ic_dialog_email) //푸시 아이콘 설정
//                .setContentTitle(title) //푸시 타이틀 이름
//                .setContentText(message)//푸시 서브 타이틀
//                .setAutoCancel(true)//선택시 자동제거?
//                .setSound(defaultSoundUri) // 도착시 알람
//                .setContentIntent(pendingIntent); // PendingIntent 정의
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build()); //푸시 빌더를 매니저로 넘겨주기 (푸시 실행)

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        sendNotification(remoteMessage.getData());
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(Map<String, String> messageBody) {
        Intent intent = new Intent(this, this.getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle(messageBody.get("title"))
                .setContentText(messageBody.get("message"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
