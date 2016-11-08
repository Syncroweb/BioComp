package it.syncroweb.android.bio;

import java.util.ArrayList;
import java.util.List;

class ListProvider {

    static List<User> userList = new ArrayList<>();

/*
    static {
        addUser(Name", "Birthdate", String.valueOf(R.drawable.user));

    }
*/



    private static void addUser(String id, String name, String birthdate, String imgUser) {
        User user = new User(id, name, birthdate, imgUser);

        //Add to List
        userList.add(user);
    }

    public static List<String> getProductNames() {
        List<String> list = new ArrayList<>();
        for (User user : userList) {
            list.add(user.getId() + user.getName() + user.getBirthdate() + user.getPhoto());
        }
        return list;
    }
}
