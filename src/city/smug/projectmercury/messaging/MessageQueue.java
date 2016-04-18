package city.smug.projectmercury.messaging;

import android.support.v7.util.SortedList;

public class MessageQueue {
    protected SortedList<Message> messages = new SortedList<>(Message.class, new SortedList.Callback<Message>() {
        @Override
        public int compare(Message o1, Message o2) {
            return o1.getWhen().compareTo(o2.getWhen());
        }

        @Override
        public void onInserted(int position, int count) {}

        @Override
        public void onRemoved(int position, int count) {}

        @Override
        public void onMoved(int fromPosition, int toPosition) {}

        @Override
        public void onChanged(int position, int count) {}

        @Override
        public boolean areContentsTheSame(Message oldItem, Message newItem) {
            return false; // TODO
        }

        @Override
        public boolean areItemsTheSame(Message item1, Message item2) {
            return item1.getId() == item2.getId();
        }
    });

    public void insert(Message message) {
        messages.add(message);
    }
}