package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.fragment;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.MainActivity;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model.sensor1;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model.suhuchart;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.R;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Rest.ApiClient;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BerandaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BerandaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BerandaFragment extends Fragment  {
    View view;
    LineChart chartsuhu;
    ApiInterface mApiInterface;
    SwipeRefreshLayout mSwipeRefreshLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BerandaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BerandaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BerandaFragment newInstance(String param1, String param2) {
        BerandaFragment fragment = new BerandaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);




        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_beranda, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primaryDarkColor, R.color.primaryColor);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Refresh();
                        Refresh2();
                        setupChart();
                        updateChart();

                    }
                }, 3000);
            }
        });

        LinearLayout masuksimulasi = (LinearLayout) view.findViewById(R.id.masuksimulasi);
        masuksimulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Intent intent = new Intent(getActivity(), SimulasiFragment.class);
             //   startActivity(intent);
                MainActivity.navItemIndex = 2;
                MainActivity.CURRENT_TAG = MainActivity.TAG_SIMULASI;
                ((MainActivity)getActivity()).loadBerandaFragment();
            }
        });

        TextView username_admin = (TextView)view.findViewById(R.id.username_admin);
        String User_name = getArguments().getString("result_username");
        Log.i("jho", User_name);
        username_admin.setText(User_name);

        TextView jabatan = (TextView)view.findViewById(R.id.jabatan);
        String Jabatan = getArguments().getString("result_jabatan");

        jabatan.setText(Jabatan);




        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Refresh();
        setupChart();
        updateChart();
        Refresh2();
        return view;

    }

    public void Refresh(){
        Call<sensor1> sensor1Call =  mApiInterface.getsensor();
        sensor1Call.enqueue(new Callback<sensor1>() {
            @Override
            public void onResponse(Call<sensor1> call, Response<sensor1> response) {
                String suhu = response.body().getSuhu();
                String kelembapan = response.body().getKelembapan();
                String berat_badan = response.body().getBerat_badan();
                String kadar_oksigen = response.body().getKadar_oksigen();

                TextView suhutxt = view.findViewById(R.id.nilaisuhu);
                TextView kelembapantxt = view.findViewById(R.id.nilaikelembapan);
                TextView berat_badantxt = view.findViewById(R.id.nilaiberat);
                TextView kadar_oksigentxt = view.findViewById(R.id.nialaikadar_oksigen);
                suhutxt.setText(suhu+" °C");
                kelembapantxt.setText(kelembapan+" %");
                berat_badantxt.setText(berat_badan+" Kg");
                kadar_oksigentxt.setText(kadar_oksigen+ " mg/l");


            }

            @Override
            public void onFailure(Call<sensor1> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
    public void Refresh2(){
        Call<sensor1> sensor1Call = mApiInterface.getsensor();
        sensor1Call.enqueue(new Callback<sensor1>() {
            @Override
            public void onResponse(Call<sensor1> call, Response<sensor1> response) {
                String suhu = response.body().getSuhu();
                TextView suhukontroltxt = view.findViewById(R.id.nilaisuhukontrol1);
                suhukontroltxt.setText(suhu+ "°C");

            }

            @Override
            public void onFailure(Call<sensor1> call, Throwable t) {
                Log.e("Retrofit get", t.toString());

            }
        });
    }
    private void setupChart(){
        chartsuhu =(LineChart)view.findViewById(R.id.chartsuhuink1);
        chartsuhu.setDescription("");

        updateChart();
    }
    private void updateChart(){
        Call<suhuchart> sensor1Call = mApiInterface.getsuhu();
        sensor1Call.enqueue(new Callback<suhuchart>() {
            @Override
            public void onResponse(Call<suhuchart> call, Response<suhuchart> response) {
                Log.e("error", call.request().url().toString());
                sensor1[] sensor1s = response.body().getSensor1s();
                ArrayList<Entry> entrysuhu = new ArrayList<>();
                ArrayList<String> labelsuhu = new ArrayList<>();

                entrysuhu.clear();
                labelsuhu.clear();

                //date formater
                SimpleDateFormat sourceformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date parsed = new Date();SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                dateFormat.setTimeZone(TimeZone.getDefault());

                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                timeFormat.setTimeZone(TimeZone.getDefault());
                //end date formater

                int x = 0;
                for (sensor1 suhu : sensor1s){
                    try {
                        parsed =sourceformat.parse(suhu.getWaktu());
                    }catch (Exception e){
                        Log.e("setsuhuloadedlistener" ,"error parsing date");
                        e.printStackTrace();
                    }
                    entrysuhu.add(new Entry(Float.parseFloat(suhu.getSuhu()),x));
                    labelsuhu.add(timeFormat.format(parsed));
                    x++;
                }

                LineDataSet lineDataSet = new LineDataSet(entrysuhu, "Derajat Celcius");
                lineDataSet.setColor(Color.parseColor("#009688"));
                lineDataSet.setCircleColor(Color.parseColor("#ffcdd2"));
                lineDataSet.setCircleColorHole(Color.parseColor("#f44336"));
                //error disini
                //    LineData datasuhu = new LineData();
                //  datasuhu.addDataSet(lineDataSet);
                LineData datasuhu = new LineData(labelsuhu, lineDataSet);
                chartsuhu.setData(datasuhu);
                chartsuhu.notifyDataSetChanged();
                chartsuhu.animateY(1000);

            }

            @Override
            public void onFailure(Call<suhuchart> call, Throwable t) {
                Log.e("erorr", call.request().url().toString());
                try{
                    Snackbar.make(getView(), "Rewfres data gagal, coba lagi nanti", Snackbar.LENGTH_LONG).show();

                }catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
