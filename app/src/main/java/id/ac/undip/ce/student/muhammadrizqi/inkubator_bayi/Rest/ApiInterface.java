package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Rest;

import java.util.Map;

import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model.sensor1;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model.sensor2;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model.sensor3;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model.suhuchart;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("User_Name") String username,
                                 @Field("Password") String paswrd);
    @GET("sensor1")
    Call<sensor1> getsensor();

    @GET("sensor2")
    Call<sensor2> getsensor2();

    @GET("sensor3")
    Call<sensor3> getsensor3();

    @GET("download")
    @Streaming
    Call<ResponseBody> downloadFile(@Query("date_awal") String dateawal,
                                    @Query("waktu_awal") String waktuawal,
                                    @Query("date_akhir") String dateakhir,
                                    @Query("waktu_akhir") String waktuakhir);
    @GET("suhu")
    Call<suhuchart>getsuhu();

}
