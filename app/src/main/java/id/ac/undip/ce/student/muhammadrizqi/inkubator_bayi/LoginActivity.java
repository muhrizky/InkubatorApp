package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Rest.ApiClient;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Rest.ApiInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtusername, edtpasswd;
    ApiInterface mApiInterface;
    ProgressDialog loading;
    String username;
    String passwrd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtusername = (EditText)findViewById(R.id.user2);
        edtpasswd = (EditText)findViewById(R.id.password);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Button loginberhasil = (Button)findViewById(R.id.btnlogin);
        loginberhasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 username= edtusername.getText().toString();
                 passwrd = edtpasswd.getText().toString();
                if (!username.isEmpty()&& !passwrd.isEmpty()){
                    loading = new ProgressDialog(LoginActivity.this);
                    loading.setMessage("Loginging");
                    loading.setCancelable(false);
                    loading.show();
                                 requestLogin();
                }else {
                    Toast.makeText(getApplicationContext(),"Harap isi dengan lengkap", Toast.LENGTH_LONG).show();
                }}

        });


        Button forgetpasswd = (Button) findViewById(R.id.btnLinkToForgotPsswrd);
        forgetpasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

private void requestLogin(){
        mApiInterface.loginRequest(username,passwrd).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    try{
                        JSONObject JsonRESULT = new JSONObject(response.body().string());
                        if (JsonRESULT.getInt("success")==1){
                            Toast.makeText(getApplicationContext(),"Berhasil Login", Toast.LENGTH_SHORT).show();
                            String Username = JsonRESULT.getString("Username");
                            String jabatan = JsonRESULT.getString("jabatan");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("result_username", Username);
                            intent.putExtra("result_jabatan", jabatan);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "User atau password tidak sesuai, coba perikasa lagi", Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else{
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure:Error>" + t.toString());
                loading.dismiss();

            }
        });
}

}
