package city.smug.projectmercury.messaging;

import java.util.ArrayList;

import city.smug.projectmercury.sms.SmsHandler;

public class Messenger {
    protected ArrayList<Group> groups = new ArrayList<>();

    protected static Messenger instance = new Messenger();
    public static Messenger getInstance() {
        return instance;
    }

    protected Messenger() {}

    public void send(Message message) {
        if (ConnectionStatus.getInstance().isConnected()) {
            // Send over TCP
        }
        else {
            SmsHandler.getInstance().send(message);
        }
    }

    public void receive(Message message) {
        for (Group group : groups) {
            if (group.getId() == message.getTo().getId()) {
                group.getQueue().insert(message);
                break;
            }
        }
    }
}
