package city.smug.projectmercury.messaging;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

import city.smug.projectmercury.api.Rest;
import city.smug.projectmercury.sms.SmsHandler;

public class Messenger {
    protected ArrayList<Group> groups = new ArrayList<>();
    protected NetworkRunnable netRunner = new NetworkRunnable();

    protected static Messenger instance = new Messenger();
    public static Messenger getInstance() {
        return instance;
    }

    protected class NetworkRunnable implements Runnable {
        Socket socket = null;

        public void send(Message message) {
            if (socket == null)
                return;

            try {
                DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
                stream.writeLong(-1L);
                stream.writeLong(message.getFrom().getId());
                stream.writeLong(message.getTo().getId());
                stream.writeUTF(message.getContent());
                stream.flush();
            }
            catch (Exception ignored) {}
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket("pdc-amd01.poly.edu", 8080);
                DataInputStream stream = new DataInputStream(socket.getInputStream());

                while (true) {
                    receive(new Message(stream.readLong(), Rest.getUser(stream.readLong()), Rest.getGroup(stream.readLong()),
                            Calendar.getInstance().getTime(), stream.readUTF()));
                }
            }
            catch (Exception ignored) {}
        }
    }

    protected Messenger() {
        new Thread(netRunner).start();
    }

    public void send(Message message) {
        if (ConnectionStatus.getInstance().isConnected()) {
            try {
                netRunner.send(message);
            }
            catch (Exception ignored) {
                SmsHandler.getInstance().send(message);
            }
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
