package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.fragment;

import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

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
 * {@link KontrolFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KontrolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KontrolFragment extends Fragment {

    ApiInterface mApiInterface;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View view;
    EditText edtsuhutujuan1;
    EditText edtsuhutujuan2;
    EditText edtsuhutujuan3;
    Button btnset_suhu1;
    Button btnset_suhu2;
    Button btnset_suhu3;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public KontrolFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KontrolFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KontrolFragment newInstance(String param1, String param2) {
        KontrolFragment fragment = new KontrolFragment();
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
        view = inflater.inflate(R.layout.fragment_kontrol, container, false);
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

                    }
                },3000);
            }
        });


        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Refresh();
        edtsuhutujuan1 = view.findViewById(R.id.edt_suhutujuanink1);
        edtsuhutujuan2 = view.findViewById(R.id.edt_suhutujuanink2);
        edtsuhutujuan3 = view.findViewById(R.id.edt_suhutujuanink3);
        btnset_suhu1 = view.findViewById(R.id.btnsetsuhu_ink1);
        btnset_suhu2 = view.findViewById(R.id.btnsetsuhu_ink2);
        btnset_suhu3 = view.findViewById(R.id.btnsetsuhu_ink3);

        btnset_suhu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String suhusimpan1 = edtsuhutujuan1.getText().toString();
                HashMap<String, String>data=new HashMap<>();
                data.put("suhu", suhusimpan1);
                retrofit2.Call<sensor1> sensor1Call = mApiInterface.postsensorsuhu(data);
                sensor1Call.enqueue(new Callback<sensor1>() {
                    @Override
                    public void onResponse(retrofit2.Call<sensor1> call, Response<sensor1> response) {
                        Toast.makeText(getContext(),"Sensor suhu sudah diupdate",Toast.LENGTH_LONG).show();
                        Refresh();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<sensor1> call, Throwable t) {
                        Toast.makeText(getContext(),"Sensor suhu gagal untuk keupdate",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnset_suhu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String suhusimpan2 = edtsuhutujuan2.getText().toString();
                HashMap<String, String>data= new HashMap<>();
                data.put("suhu", suhusimpan2);
                retrofit2.Call<sensor2> sensor2Call = mApiInterface.postsensorsuhu2(data);
                sensor2Call.enqueue(new Callback<sensor2>() {
                    @Override
                    public void onResponse(retrofit2.Call<sensor2> call, Response<sensor2> response) {
                        Toast.makeText(getContext(),"Sensor suhu sudah diupdate",Toast.LENGTH_LONG).show();
                        Refresh();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<sensor2> call, Throwable t) {
                        Toast.makeText(getContext(),"Sensor suhu gagal untuk keupdate",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        btnset_suhu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String suhusimpan3 = edtsuhutujuan3.getText().toString();
                HashMap<String, String>data= new HashMap<>();
                data.put("suhu", suhusimpan3);
                retrofit2.Call<sensor3> sensor3Call = mApiInterface.postsensorsuhu3(data);
                sensor3Call.enqueue(new Callback<sensor3>() {
                    @Override
                    public void onResponse(retrofit2.Call<sensor3> call, Response<sensor3> response) {
                        Toast.makeText(getContext(),"Sensor suhu sudah diupdate",Toast.LENGTH_LONG).show();
                        Refresh();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<sensor3> call, Throwable t) {
                        Toast.makeText(getContext(),"Sensor suhu gagal untuk keupdate",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


        return view;
    }
        public void Refresh(){
            retrofit2.Call<sensor1> sensor1Call = mApiInterface.getsensor();
            sensor1Call.enqueue(new Callback<sensor1>() {
                @Override
                public void onResponse(retrofit2.Call<sensor1> call, Response<sensor1> response) {
                    String suhu = response.body().getSuhu();
                    TextView  suhukontroltxt = view.findViewById(R.id.nilaisuhukontrol1);
                    suhukontroltxt.setText(suhu+"°C");
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
                    String suhu = response.body().getSuhu();
                    TextView suhukontrol2txt = view.findViewById(R.id.nilaisuhukontrol2);
                    suhukontrol2txt.setText(suhu+"°C");
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
                    String suhu = response.body().getSuhu();
                    TextView suhukontrol3txt = view.findViewById(R.id.nilaisuhukontrol3);
                    suhukontrol3txt.setText(suhu+"°C");
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


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
