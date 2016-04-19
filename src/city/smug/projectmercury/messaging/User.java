package city.smug.projectmercury.messaging;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.InputStream;
import java.net.URL;

public class User {
    protected long id;
    protected String name;
    protected String email;

    // TODO: make this some default image
    protected Drawable avatar = null;

    public User(String email, String password) {
        // TODO: API call to server to fill id/name
        id = -1;
        name = "nobody";
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

    protected static User currentUser = new User("rjk363@nyu.edu", "password");
    public static User getCurrentUser() {
        return currentUser;
    }
}
