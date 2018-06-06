package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.fragment;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.MimeTypeFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ikovac.timepickerwithseconds.MyTimePickerDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.MainActivity;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.R;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Rest.ApiClient;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.Rest.ApiInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.text.InputType.TYPE_NULL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment {
    DatePickerDialog picker;
    TimePickerDialog timePicker;
    MyTimePickerDialog myTimePickerDialog;
    EditText eText1;
    EditText eText2;
    EditText eText3;
    EditText eText4;
    EditText dateAwal;
    EditText dateAkhir;
    Button  btnunduh;
    String dateawal;

    String dateakhir;
    Calendar dateawalcalendar;
    Calendar dateakhircalendar;
    ApiInterface mApiInterface;

    NotificationManager notificationManager;
    Notification.Builder builder;
    int id = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();
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
        View view= inflater.inflate(R.layout.fragment_data, container, false);

        dateawalcalendar = Calendar.getInstance();
        dateakhircalendar = Calendar.getInstance();
        dateAwal = view.findViewById(R.id.date_awal);
        dateAwal.setInputType(TYPE_NULL);
        dateAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                picker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int mount, int day) {
                        dateawalcalendar.set(year, mount, day);
                        myTimePickerDialog = new MyTimePickerDialog(getContext(), new MyTimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
                                dateawalcalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                dateawalcalendar.set(Calendar.MINUTE, minute);
                                dateawalcalendar.set(Calendar.SECOND, seconds);
                                SimpleDateFormat format = new SimpleDateFormat("d/MMMM/yyyy HH:mm:ss");
                                dateAwal.setText(format.format(dateawalcalendar.getTime()));
                            }
                        }
                                , 0, 0, 0, true);
                        myTimePickerDialog.show();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                picker.show();

            }

        });

        dateAkhir = view.findViewById(R.id.date_akhir);
        dateAkhir.setInputType(TYPE_NULL);
        dateAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                picker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int mount, int day) {
                        dateakhircalendar.set(year, mount, day);
                        myTimePickerDialog = new MyTimePickerDialog(getContext(), new MyTimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
                                dateakhircalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                dateakhircalendar.set(Calendar.MINUTE, minute);
                                dateakhircalendar.set(Calendar.SECOND, seconds);
                                SimpleDateFormat format = new SimpleDateFormat("d/MMMM/yyyy HH:mm:ss");
                                dateAkhir.setText(format.format(dateakhircalendar.getTime()));
                            }
                        }
                                , 0, 0, 0, true);
                        myTimePickerDialog.show();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                picker.show();

            }

        });
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        final CheckBox inkubatorcheckbox1 = (CheckBox) view.findViewById(R.id.data_ink1);
        btnunduh = view.findViewById(R.id.unduh_data);
        btnunduh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateawal = dateAwal.getText().toString();
                dateakhir = dateAkhir.getText().toString();

                if(!dateawal.isEmpty()&&!dateakhir.isEmpty()&&inkubatorcheckbox1.isChecked()){

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");


                    Call<ResponseBody> call = mApiInterface.downloadFile(
                            dateFormat.format(dateawalcalendar.getTime()),
                            timeFormat.format(dateawalcalendar.getTime()),
                            dateFormat.format(dateakhircalendar.getTime()),
                            timeFormat.format(dateakhircalendar.getTime()));

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
//                            boolean success = writeResponseBodyToDisk(response.body());
                                Log.e("Download",call.request().url().toString());
                                new WriteToDisk(getContext(), response.body()).execute();

                            }
                            else {
                                Log.e("Download","Download Failed");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                } else {
                    Toast.makeText(getActivity().getApplicationContext(),"Harap isi dengan lengkap", Toast.LENGTH_SHORT).show();

                }

            }
        });



        return view;
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

    static class WriteToDisk extends AsyncTask<Void, Integer, Boolean>{

        ResponseBody body;
        Context context;
        NotificationManager notificationManager;
        NotificationCompat.Builder builder;
        File file;

        static final int NOTIFICATION_ID = 1;
        WriteToDisk(Context context, ResponseBody body){
            this.context = context;
            this.body = body;

        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(context, "Downloading File", Toast.LENGTH_LONG).show();
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            builder = new NotificationCompat.Builder(context, "Download");
            builder.setContentTitle("Download File")
                    .setContentText("Download in progress")
                    .setSmallIcon(R.drawable.ic_download)
                    .setPriority(NotificationCompat.PRIORITY_LOW);
            builder.setProgress(100, 0, false);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(values[0] % 5 == 0){
                builder.setProgress(100, values[0], false);
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(success){
                Toast.makeText(context, "Download Success", Toast.LENGTH_LONG).show();
                builder.setContentText("Download Complete")
                        .setProgress(0,0,false);

                Uri uri = Uri.fromFile(file).normalizeScheme();
                String ext = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
                String mime = null;
                if(ext != null){
                    mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
                }
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, mime);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                builder.setContentIntent(pendingIntent);

                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }
            else {
                Toast.makeText(context, "Download Failed", Toast.LENGTH_LONG).show();
                builder.setContentText("Download Failed")
                        .setProgress(0,0,false);
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }
            super.onPostExecute(success);
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                File path = Environment.getExternalStorageDirectory();
                file = new File(path, "laporan_Monitoring_inkubator_bayi.pdf");
                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    byte[] fileReader = new byte[4096];

                    long fileSize = body.contentLength();
                    long fileSizeDownloaded = 0;

                    inputStream = body.byteStream();
                    outputStream = new FileOutputStream(file);

                    while (true) {
                        int read = inputStream.read(fileReader);

                        if (read == -1) {
                            break;
                        }

                        outputStream.write(fileReader, 0, read);

                        fileSizeDownloaded += read;

                        Log.d("Download", "file download: " + fileSizeDownloaded + " of " + fileSize);
                    }

                    outputStream.flush();

                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
