package hireapro.himanshu.hireapro;

import java.io.Serializable;

/**
 * Created by root on 26/4/17.
 */
@SuppressWarnings("serial")
public class User implements Serializable{
    private long phone;
    private String email,password,name;

    public String getEmail() {
        return email;
    }

    public long getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
