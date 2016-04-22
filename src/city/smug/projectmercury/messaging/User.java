package city.smug.projectmercury.messaging;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import city.smug.projectmercury.api.Rest;

public class User {
    protected long id;
    protected String name;
    protected String email;
    protected ArrayList<Group> groups = new ArrayList<>();

    // TODO: make this some default image
    protected Drawable avatar = null;

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

        final String url = new StringBuilder(81)
                .append("http://www.gravatar.com/avatar/")
                .append(Hex.encodeHex(DigestUtils.md5(email)))
                .append("?d=identicon&s=256")
                .toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try (InputStream stream = (InputStream) (new URL(url).getContent())) {
                    avatar = BitmapDrawable.createFromStream(stream, null);
                }
                catch (Exception ignored) {}
            }
        }).start();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    protected static User currentUser = null;
    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean logIn(String email) {
        User user = Rest.loginUser(email);
        if (user == null)
            return false;

        currentUser = user;
        return true;
    }
}
