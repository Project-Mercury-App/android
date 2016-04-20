package city.smug.projectmercury;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import city.smug.projectmercury.messaging.Group;
import city.smug.projectmercury.messaging.User;

public class GroupAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater layoutInflater;

    public GroupAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return User.getCurrentUser().getGroups().size();
    }

    @Override
    public Object getItem(int position) {
        return User.getCurrentUser().getGroups().get(position);
    }

    @Override
    public long getItemId(int position) {
        return User.getCurrentUser().getGroups().get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.group_list_item, null);
        }

        Group group = User.getCurrentUser().getGroups().get(position);
        ((TextView)convertView.findViewById(R.id.group_list_name)).setText(group.getName());
        ((TextView)convertView.findViewById(R.id.group_list_members)).setText(Integer.toString(group.getMembers().size()));

        return convertView;
    }
}
