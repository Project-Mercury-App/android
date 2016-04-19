package city.smug.projectmercury.messaging;

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

        try {
            String url = new StringBuilder(63)
                    .append("http://www.gravatar.com/avatar/")
                    .append(Hex.encodeHex(DigestUtils.md5(email)))
                    .toString();
            InputStream stream = (InputStream)(new URL(url).getContent());
            avatar = Drawable.createFromStream(stream, null);
        }
        catch (Exception ignored) {}
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
}
