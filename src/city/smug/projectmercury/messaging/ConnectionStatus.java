package city.smug.projectmercury.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionStatus extends BroadcastReceiver {
    protected static ConnectionStatus instance = null;
    protected boolean connected = false;
    protected Context context;

    protected ConnectionStatus(Context context) {
        this.context = context;
        update();
    }

    public static void createInstance(Context context) {
        if (instance == null)
            instance = new ConnectionStatus(context);
    }

    public static ConnectionStatus getInstance(Context context) {
        return instance;
    }

    public boolean isConnected() {
        return connected;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        update();
    }

    protected void update() {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}