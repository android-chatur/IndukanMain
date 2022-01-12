package com.geogreenapps.apps.indukaan.push_notification_firebase;

import android.util.Log;

import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by idriss on 06/10/2016.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    public static final String TAG = "FirebaseMessaging";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (AppConfig.APP_DEBUG) {
            Log.d(TAG, "From " + remoteMessage.toString());
            Log.d(TAG, "From: " + remoteMessage.getFrom());
        }

        Map<String, String> messageFromOwnServer = remoteMessage.getData();
        try {
            showNotification(messageFromOwnServer);
        } catch (Exception e) {
            try {
                //showNotification(remoteMessage.getNotification().getBody());
            } catch (Exception e1) {
                if (AppConfig.APP_DEBUG)
                    e1.printStackTrace();
            }

            if (AppConfig.APP_DEBUG)
                e.printStackTrace();
        }


    }


    private void showNotification(Map<String, String> message) {


        if (AppConfig.APP_DEBUG) {
            Log.e(TAG, "InCommingData " + message.toString());
        }

        DTNotificationManager mCampaignNotifManager = new DTNotificationManager(this, message);
        mCampaignNotifManager.push();

    }
}
