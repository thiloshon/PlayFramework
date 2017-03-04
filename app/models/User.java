package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Thiloshon on 03-Mar-17.
 */


public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public String name;
    public String mailID;
    public String password;

    public User(String name, String mailID, String password) {
        this.name = name;
        this.mailID = mailID;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailID() {
        return mailID;
    }

    public String getPassword() {
        return password;
    }

}
