package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model;

import com.google.gson.annotations.SerializedName;

public class sensor3 {
    @SerializedName("suhu")
    private String suhu;
    @SerializedName("kelembapan")
    private String kelembapan;
    @SerializedName("berat_badan")
    private String berat_badan;
    @SerializedName("kadar_oksigen")
    private String kadar_oksigen;

    public sensor3() {
    }

    public sensor3(String suhu, String kelembapan, String berat_badan, String kadar_oksigen) {
        this.suhu = suhu;
        this.kelembapan = kelembapan;
        this.berat_badan = berat_badan;
        this.kadar_oksigen = kadar_oksigen;

    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getKelembapan() {
        return kelembapan;
    }

    public void setKelembapan(String kelembapan) {
        this.kelembapan = kelembapan;
    }

    public String getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(String berat_badan) {
        this.berat_badan = berat_badan;
    }

    public String getKadar_oksigen() {
        return kadar_oksigen;
    }

    public void setKadar_oksigen(String kadar_oksigen) {
        this.kadar_oksigen = kadar_oksigen;
    }
}

