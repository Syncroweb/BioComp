package it.syncroweb.android.bio;


class User {

    private String id;
    private String name;
    private String birthdate;
    private String photo;

    User(String id, String name, String photo, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.photo = photo;
    }

    public String getId() { return id;  }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
