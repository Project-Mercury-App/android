package city.smug.projectmercury.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.util.Date;

import city.smug.projectmercury.api.Rest;
import city.smug.projectmercury.messaging.Group;
import city.smug.projectmercury.messaging.Message;
import city.smug.projectmercury.messaging.Messenger;
import city.smug.projectmercury.messaging.User;

public class SmsHandler {
    private final SmsManager smsManager = SmsManager.getDefault();
    private final String serverNumber = "7572315125";
    private final char us = '\31';

    private SmsHandler() {}

    private static SmsHandler instance = new SmsHandler();
    public static SmsHandler getInstance() {
        return instance;
    }

    public class SmsReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

            for (SmsMessage message : messages) {
                try {
                    // TODO: filter messages to server's number
                    String[] tokens = message.getMessageBody().split(Character.toString(us));
                    if (tokens.length != 6)
                        continue;

                    long id = Long.parseLong(tokens[0]);
                    User from = Rest.getUser(Long.parseLong(tokens[1]));
                    Group to = Rest.getGroup(Long.parseLong(tokens[2]));
                    // TODO: index and count
                    String content = tokens[5];

                    Messenger.getInstance().receive(new Message(id, from, to, new Date(message.getTimestampMillis()), content));
                }
                catch (Exception ignored) {}
            }
        }
    }

    public void send(Message message) {
        // Message ID <US> User ID <US> Group ID <US> Message Index <US> Total Messages <US> Message content
        String text = new StringBuilder()
                .append(-1)
                .append(us)
                .append(message.getFrom().getId())
                .append(us)
                .append(message.getTo().getId())
                .append(us)
                .append(0) // TODO
                .append(us)
                .append(1) // TODO
                .append(us)
                .append(message.getContent())
                .toString();

        smsManager.sendTextMessage(serverNumber, null, text, null, null);
    }
}
