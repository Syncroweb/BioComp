package it.syncroweb.android.bio;


class User {

    private String name;
    private String birthdate;
    private int photo;

    User(){};

    public User(String name, String birthdate, int photo) {
        this.name = name;
        this.birthdate = birthdate;
        this.photo = photo;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
