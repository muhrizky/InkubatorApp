package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button backtologin = (Button)findViewById(R.id.bcktologin);
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout kirimemail = (LinearLayout)findViewById(R.id.kirim_email);
        kirimemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Permintaan Bantuan Masuk Aplikasi Sistem Pengendali Inkubator Bayi");
                intent.putExtra(Intent.EXTRA_TEXT, "Harap Berikan informasi username yang digunakan dan masalah yang dihadapi apa");
                intent.putExtra(Intent.EXTRA_EMAIL, new  String[]{"admin@inkubatorbayi.com"});
                startActivity(intent);
            }
        });
    }
}
