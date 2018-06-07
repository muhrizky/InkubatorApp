package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model;

import com.google.gson.annotations.SerializedName;

public class kadar_oksigen_chart {
    @SerializedName("chartkadar_oksigen")
    sensor1[] sensor1s;
    public kadar_oksigen_chart(){

    }
    public kadar_oksigen_chart(sensor1[] sensor1s){
        this.sensor1s =sensor1s;
    }

    public sensor1[] getSensor1s() {
        return sensor1s;
    }

    public void setSensor1s(sensor1[] sensor1s) {
        this.sensor1s = sensor1s;
    }
}
