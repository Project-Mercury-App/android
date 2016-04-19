package city.smug.projectmercury;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import city.smug.projectmercury.messaging.Message;
import city.smug.projectmercury.messaging.MessageQueue;

public class MessageAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater layoutInflater;
    protected MessageQueue messages = MessageQueue.getInstance();
    protected DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public MessageAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return messages.getLength();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.message_list_item, null);
        }

        Message message = messages.get(position);
        ((ImageView)convertView.findViewById(R.id.message_avatar)).setImageDrawable(message.getFrom().getAvatar());
        ((TextView)convertView.findViewById(R.id.message_name)).setText(message.getFrom().getName());
        ((TextView)convertView.findViewById(R.id.message_date)).setText(dateFormat.format(message.getWhen()));
        ((TextView)convertView.findViewById(R.id.message_content)).setText(message.getContent());

        return convertView;
    }
}
