package city.smug.projectmercury.api;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import city.smug.projectmercury.messaging.Group;
import city.smug.projectmercury.messaging.User;

/**
 * Created by rob on 4/22/16.
 */
public final class Rest {
    private Rest() {}

    private static User testUser; // TODO: not this

    private static HashMap<Long, User> userLookup = new HashMap<>();
    private static HashMap<Long, Group> groupLookup = new HashMap<>();

    public static User loginUser(String email) {
        userLookup.clear();
        testUser = new User(-1, "nobody", email);
        testUser.getGroups().addAll(getGroupsForUser(testUser.getId())); // TODO
        userLookup.put(-1L, testUser); // TODO
        return testUser;
    }

    public static User getUser(long userId) {
        if (userLookup.containsKey(userId))
            return userLookup.get(userId);

        // TODO: userLookup.put
        return null;
    }

    public static Group getGroup(long groupId) {
        if (groupLookup.containsKey(groupId))
            return groupLookup.get(groupId);

        // TODO: groupLookup.put
        return null;
    }

    public static ArrayList<Group> getGroupsForUser(long userId) {
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group(-1, "Test Group 1", getMembersOfGroup(-1)));
        groups.add(new Group(-2, "Test Group 2", getMembersOfGroup(-2)));
        return groups;
    }

    public static ArrayList<User> getMembersOfGroup(long groupId) {
        ArrayList<User> members = new ArrayList<>();
        members.add(testUser); // TODO
        return members;
    }
}
