package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model;

import com.google.gson.annotations.SerializedName;

public class sensor1 {

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    @SerializedName("waktu")
    private String waktu;
    @SerializedName("suhu")
    private String suhu;
    @SerializedName("kelembapan")
    private  String kelembapan;
    @SerializedName("berat_badan")
    private String berat_badan;
    @SerializedName("kadar_oksigen")
    private String kadar_oksigen;

    public sensor1(){}
    public sensor1(String suhu, String kelembapan, String berat_badan, String kadar_oksigen, String waktu){
        this.suhu = suhu;
        this.kelembapan = kelembapan;
        this.berat_badan =berat_badan;
        this.kadar_oksigen = kadar_oksigen;
        this.waktu = waktu;
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
