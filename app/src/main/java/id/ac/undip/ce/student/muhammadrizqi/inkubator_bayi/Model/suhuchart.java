package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model;

import com.google.gson.annotations.SerializedName;

public class suhuchart {
    @SerializedName("chartsuhu")
    sensor1[] sensor1s;

    public suhuchart() {
    }

    public suhuchart(sensor1[] sensor1s) {
        this.sensor1s = sensor1s;
    }

    public sensor1[] getSensor1s() {
        return sensor1s;
    }

    public void setSensor1s(sensor1[] sensor1s) {
        this.sensor1s = sensor1s;
    }
}
