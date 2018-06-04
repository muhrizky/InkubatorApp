package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model;

import com.google.gson.annotations.SerializedName;

public class admin {
    @SerializedName("User_Name")
    private String username;

    public admin(){}

    public admin(String username, String pasword){
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
