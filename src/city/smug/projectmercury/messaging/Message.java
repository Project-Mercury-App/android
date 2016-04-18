package city.smug.projectmercury.messaging;

import java.util.Date;

public class Message {
    protected long id;
    protected User from;
    protected Group to;
    protected Date when;
    protected String content;

    public Message(long id, User from, Group to, Date when, String content) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.when = when;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public Group getTo() {
        return to;
    }

    public Date getWhen() {
        return when;
    }

    public String getContent() {
        return content;
    }
}
