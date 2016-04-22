package city.smug.projectmercury.messaging;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class User {
    protected long id;
    protected String name;
    protected String email;
    protected ArrayList<Group> groups;

    // TODO: make this some default image
    protected Drawable avatar = null;

    public User(long id, String email) {
        // TODO: API call to server to fill id/name
        this.id = id;
        name = "nobody";
        this.email = email;
        groups = new ArrayList<>(1);
        groups.add(Group.getTestGroup());
        groups.add(new Group(-2, "Another Group", Group.getTestGroup().members));

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
        // TODO: API request to get user ID (if it exists)
        currentUser = new User(-1, email);

        return true;
    }
}
