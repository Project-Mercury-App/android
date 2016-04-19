package city.smug.projectmercury.messaging;

import java.util.ArrayList;

public class Group {
    protected long id;
    protected String name;
    protected ArrayList<User> members = new ArrayList<>();
    protected MessageQueue queue;

    public Group(long id, String name, ArrayList<User> members) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.queue = new MessageQueue();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public MessageQueue getQueue() {
        return queue;
    }
}
