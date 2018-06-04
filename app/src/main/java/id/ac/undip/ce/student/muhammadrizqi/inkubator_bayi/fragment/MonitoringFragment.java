package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.MainActivity;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model.sensor1;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model.sensor2;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Model.sensor3;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.R;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Rest.ApiClient;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Rest.ApiInterface;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MonitoringFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MonitoringFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonitoringFragment extends Fragment {

    View view;
    ApiInterface mApiInterface;
    SwipeRefreshLayout mSwipeResfreshLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MonitoringFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonitoringFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonitoringFragment newInstance(String param1, String param2) {
        MonitoringFragment fragment = new MonitoringFragment();
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_monitoring, container, false);
        mSwipeResfreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);
        mSwipeResfreshLayout.setColorSchemeResources(R.color.primaryDarkColor, R.color.primaryColor);
        mSwipeResfreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeResfreshLayout.setRefreshing(false);
                        Refresh();

                    }
                }, 3000);
            }
        });



        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Refresh();
        return view;
    }



    public void Refresh(){
        retrofit2.Call<sensor1> sensor1Call =  mApiInterface.getsensor();

        sensor1Call.enqueue(new Callback<sensor1>() {


            @Override
            public void onResponse(retrofit2.Call<sensor1> call, Response<sensor1> response) {
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
            public void onFailure(retrofit2.Call<sensor1> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });

        retrofit2.Call<sensor2> sensor2Call = mApiInterface.getsensor2();
        sensor2Call.enqueue(new Callback<sensor2>() {
            @Override
            public void onResponse(retrofit2.Call<sensor2> call, Response<sensor2> response) {
                String suhu2 = response.body().getSuhu();
                String kelembapan2 = response.body().getKelembapan();
                String berat_badan2 = response.body().getBerat_badan();
                String kadar_oksigen2 = response.body().getKadar_oksigen();

                TextView suhutxt2 = view.findViewById(R.id.nialaikadar_oksigen2);
                TextView kelembapantxt2 = view.findViewById(R.id.nilaikelembapan2);
                TextView berat_badantxt2 = view.findViewById(R.id.nilaiberat2);
                TextView kadar_oksigentxt2 = view.findViewById(R.id.nialaikadar_oksigen2);
                suhutxt2.setText(suhu2+" °C");
                kelembapantxt2.setText(kelembapan2+" %");
                berat_badantxt2.setText(berat_badan2+" Kg");
                kadar_oksigentxt2.setText(kadar_oksigen2+ " mg/l");

            }

            @Override
            public void onFailure(retrofit2.Call<sensor2> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });

        retrofit2.Call<sensor3> sensor3Call = mApiInterface.getsensor3();
        sensor3Call.enqueue(new Callback<sensor3>() {
            @Override
            public void onResponse(retrofit2.Call<sensor3> call, Response<sensor3> response) {
                String suhu3 = response.body().getSuhu();
                String kelembapan3 = response.body().getKelembapan();
                String berat_badan3 = response.body().getBerat_badan();
                String kadar_oksigen3 = response.body().getKadar_oksigen();

                TextView suhutxt3 = view.findViewById(R.id.nilaisuhu3);
                TextView kelembapantxt3 = view.findViewById(R.id.nilaikelembapan3);
                TextView berat_badantxt3 = view.findViewById(R.id.nilaiberat3);
                TextView kadar_oksigentxt3 = view.findViewById(R.id.nialaikadar_oksigen3);
                suhutxt3.setText(suhu3+" °C");
                kelembapantxt3.setText(kelembapan3+" %");
                berat_badantxt3.setText(berat_badan3+" Kg");
                kadar_oksigentxt3.setText(kadar_oksigen3+ " mg/l");
            }

            @Override
            public void onFailure(retrofit2.Call<sensor3> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
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
