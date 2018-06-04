package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Getadmin {

    @SerializedName("status")
    String status;

    @SerializedName("message")
    String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
