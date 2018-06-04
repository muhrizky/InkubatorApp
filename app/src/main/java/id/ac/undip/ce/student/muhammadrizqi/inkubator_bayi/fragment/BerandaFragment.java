package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;

import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.MainActivity;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model.sensor1;
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
    BarChart chart;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> barLabels;
    BarDataSet barDataSet;
    Chart chart1;
    BarData barData;
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

        chart = (BarChart)view.findViewById(R.id.chart);
        if (container == null){
            return null;
        }

        barEntries = new ArrayList<BarEntry>();
        barLabels = new ArrayList<String>();

        barLabels.add("");// index 0 kosongkan saja
        barEntries.add(new BarEntry(1, 28f));
        barLabels.add("Inkubator 1");
        barEntries.add(new BarEntry(2, 25f));
        barLabels.add("Inkubator 2");
        barEntries.add(new BarEntry(3, 20f));
        barLabels.add("Inkubator 3");

        barDataSet = new BarDataSet(barEntries, "Projects");

        barData = new BarData(barDataSet);
        chart.getXAxis().setValueFormatter(
                new IndexAxisValueFormatter(barLabels));

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(barData);

        chart.animateY(3000);


        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Refresh();
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
