package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

public class result extends AppCompatActivity {

    private TextView result;
    private RequestQueue requestQueue;
    private EditText sex, age, current_smoker, cigs_per_day, BP_meds, Pre_stroke, Pre_hype, diabetes, tot_chol, sys_BP, dia_BP, BMI, heartrate, glucose;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }
}

       /* RequestQueue requestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();

        data = "{"+
                "\"sex\":" + "\"" + sex.getText().toString() + "\","+
                "\"age\":" + "\"" + age.getText().toString() + "\""+
                "\"currentSmoker\":" + "\"" + current_smoker.getText().toString() + "\","+
                "\"cigsPerDay\":" + "\"" + cigs_per_day.getText().toString() + "\","+
                "\"BPmeds\":" + "\"" + BP_meds.getText().toString() + "\","+
                "\"prevalentStroke\":" + "\"" + Pre_stroke.getText().toString() + "\","+
                "\"prevalentHyp\":" + "\"" + Pre_hype.getText().toString() + "\","+
                "\"diabetes\":" + "\"" + diabetes.getText().toString() + "\","+
                "\"totchol\":" + "\"" + tot_chol.getText().toString() + "\","+
                "\"sysBP\":" + "\"" + sys_BP.getText().toString() + "\","+
                "\"diaBP\":" + "\"" + dia_BP.getText().toString() + "\","+
                "\"BMI\":" + "\"" + BMI.getText().toString() + "\","+
                "\"heartRate\":" + "\"" + heartrate.getText().toString() + "\","+
                "\"glucose\":" + "\"" + glucose.getText().toString() + "\","+
                "}";

        String url ="https://ussouthcentral.services.azureml.net/workspaces/ab8e1cecc5f94c0bba414b2d9ea77445/services/199028c672844aacaa2faa5144e86f85/execute?api-version=2.0&details=true";

// Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        result.setText("Response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_LONG).show();
                    }
                });

// Add the request to the RequestQueue.
        requestQueue.add(stringRequest);

// ...


    }
}
*/