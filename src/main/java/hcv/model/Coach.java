package hcv.model;

/**
 * Created by Tomo.
 */
public class Coach {

    private String username;
    private String password;
    private RightsLevel rightsLevel;

    public Coach() {
    }

    public Coach(String username, String password, RightsLevel rightsLevel) {
        this.username = username;
        this.password = password;
        this.rightsLevel = rightsLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RightsLevel getRightsLevel() {
        return rightsLevel;
    }

    public void setRightsLevel(RightsLevel rightsLevel) {
        this.rightsLevel = rightsLevel;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rightsLevel=" + rightsLevel +
                '}';
    }
}
