package Accaunts;

/**
 * Created by Владислав on 23.01.2017.
 */
public class UserProfile {
    private String login;
    private String passvord;
    private String email;

    public UserProfile(String login, String passvord, String email) {
        this.login = login;
        this.passvord = passvord;
        this.email = email;
    }
    public UserProfile(String login) {
        this.login = login;
        this.passvord = login;
        this.email = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassvord() {
        return passvord;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return login + '\'' +
                " your pass " + passvord + '\'' +
                " you email " + email + '\'';
    }
}
