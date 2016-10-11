package it.syncroweb.android.bio;

import java.util.ArrayList;
import java.util.List;

class ListProvider {

    static List<User> userList = new ArrayList<>();


    static {
        addUser("Nome", "Data di Nascita", String.valueOf(R.drawable.user));
        addUser("Nome", "Data di Nascita", String.valueOf(R.drawable.user));
        addUser("Nome", "Data di Nascita", String.valueOf(R.drawable.user));
        addUser("Nome", "Data di Nascita", String.valueOf(R.drawable.user));
        addUser("Nome", "Data di Nascita", String.valueOf(R.drawable.user));
        addUser("Nome", "Data di Nascita", String.valueOf(R.drawable.user));
        addUser("Nome", "Data di Nascita", String.valueOf(R.drawable.user));
    }


    private static void addUser(String name, String birthdate, String imgUser) {
        User user = new User(name, birthdate, imgUser);

        //Add to List
        userList.add(user);
    }

    public static List<String> getProductNames() {
        List<String> list = new ArrayList<>();
        for (User user : userList) {
            list.add(user.getName() + user.getBirthdate() + user.getPhoto());
        }
        return list;
    }
}
