package city.smug.projectmercury.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SmsHandler extends BroadcastReceiver {
    private final SmsManager smsManager = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

        // TODO
    }

    public void send(String destination, String message) {
        smsManager.sendTextMessage(destination, null, message, null, null);
    }
}
