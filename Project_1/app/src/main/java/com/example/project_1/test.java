package com.example.project_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;
import org.tensorflow.lite.Interpreter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class test extends AppCompatActivity {

    private RequestQueue requestQueue;
    private Button predict;
    private EditText esex, eage, ecurrent_smoker, ecigs_per_day, eBP_meds, ePre_stroke, ePre_hype, ediabetes, etot_chol, esys_BP, edia_BP, eBMI, eheartrate, eglucose;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String email;
    private Interpreter tflite;
    private TextView result;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        esex = (EditText) findViewById(R.id.esex);
        eage = (EditText) findViewById(R.id.eage);
        ecurrent_smoker = (EditText) findViewById(R.id.ecurrent_smoker);
        ecigs_per_day = (EditText) findViewById(R.id.ecigs_per_day);
        eBP_meds = (EditText) findViewById(R.id.eBP_meds);
        ePre_stroke = (EditText) findViewById(R.id.ePre_stroke);
        ePre_hype = (EditText) findViewById(R.id.ePre_hype);
        ediabetes = (EditText) findViewById(R.id.ediabetes);
        etot_chol = (EditText) findViewById(R.id.etot_chol);
        esys_BP = (EditText) findViewById(R.id.esys_BP);
        edia_BP = (EditText) findViewById(R.id.edia_BP);
        eBMI = (EditText) findViewById(R.id.eBMI);
        eheartrate = (EditText) findViewById(R.id.eheartrate);
        eglucose = (EditText) findViewById(R.id.eglucose);
        result = (TextView) findViewById(R.id.result);


        predict = (Button) findViewById(R.id.predict);


        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sex = esex.getText().toString().trim();
                String age = eage.getText().toString().trim();
                String current_smoker = ecurrent_smoker.getText().toString().trim();
                String cigs_per_day = ecigs_per_day.getText().toString().trim();
                String BP_meds = eBP_meds.getText().toString().trim();
                String Pre_stroke = ePre_stroke.getText().toString().trim();
                String Pre_hype = ePre_hype.getText().toString().trim();
                String diabetes = ediabetes.getText().toString().trim();
                String total_chol = etot_chol.getText().toString().trim();
                String sys_BP = esys_BP.getText().toString().trim();
                String dia_BP = edia_BP.getText().toString().trim();
                String BMI = eBMI.getText().toString().trim();
                String heartrate = eheartrate.getText().toString().trim();
                String glucose = eglucose.getText().toString().trim();

                validateinputs(sex,age,current_smoker,cigs_per_day,BP_meds,Pre_stroke,Pre_hype,diabetes,total_chol,sys_BP,dia_BP,BMI,heartrate,glucose);
                OkHttpClient client = new OkHttpClient();
                url = "https://ensemble-model.herokuapp.com/predict/"+sex+"/"+age+"/"+current_smoker+"/"+cigs_per_day+"/"+BP_meds+"/"+Pre_stroke+"/"+Pre_hype+"/"+diabetes+"/"+total_chol+"/"+sys_BP+"/"+dia_BP+"/"+BMI+"/"+heartrate+"/"+glucose;
                Request request = new Request.Builder().url(url).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.isSuccessful()){
                            String myresponse = response.body().string();
                            test.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    result.setText(myresponse);
                                }
                            });
                        }
                    }
                });
           }
        });
    }



        private void validateinputs(String sex, String age, String current_smoker, String BP_meds, String Pre_stroke, String Pre_hype, String cigs_per_day, String diabetes, String total_chol, String sys_BP, String dia_BP, String BMI, String heartrate, String glucose){
            if(glucose.isEmpty()){
                eglucose.setError("Please provide a valid glucose value");
                eglucose.requestFocus();
                return;
            }
            if(heartrate.isEmpty()){
                eheartrate.setError("Please provide a valid heart rate");
                eheartrate.requestFocus();
                return;
            }
            if(BMI.isEmpty()){
                eBMI.setError("Please provide a valid BMI");
                eBMI.requestFocus();
                return;
            }
            if(dia_BP.isEmpty()){
                edia_BP.setError("Please provide a valid diabolic BP value");
                edia_BP.requestFocus();
                return;
            }
            if(sys_BP.isEmpty()){
                esys_BP.setError("Please provide a valid systolic BP value");
                esys_BP.requestFocus();
                return;
            }
            if(total_chol.isEmpty()){
                etot_chol.setError("Please provide a valid total cholesterol value");
                etot_chol.requestFocus();
                return;
            }
            if(diabetes.isEmpty()){
                ediabetes.setError("Please provide 1 if you have diabetes else 0");
                ediabetes.requestFocus();
                return;
            }
            if(Pre_stroke.isEmpty()){
                ePre_stroke.setError("Please provide 1 if you have prevalent stroke else 0");
                ePre_stroke.requestFocus();
                return;
            }
            if(Pre_hype.isEmpty()){
                ePre_hype.setError("Please provide 1 if you have prevalent hypertension else 0");
                ePre_hype.requestFocus();
                return;
            }
            if(sex.isEmpty()){
                esex.setError("Please provide 1 for male or 0 for female");
                esex.requestFocus();
                return;
            }
            if(age.isEmpty()){
                eage.setError("Please provide a valid age");
                eage.requestFocus();
                return;
            }
            if(current_smoker.isEmpty()){
                ecurrent_smoker.setError("Please provide 1 if you're a current smoker else 0");
                ecurrent_smoker.requestFocus();
                return;
            }
            if(cigs_per_day.isEmpty()){
                ecigs_per_day.setError("Please provide count of smoked cigarettes per day");
                ecigs_per_day.requestFocus();
                return;
            }
            if(BP_meds.isEmpty()){
                eBP_meds.setError("Please provide 1 if you're on BP medication else 0");
                eBP_meds.requestFocus();
                return;
            }
    }

}