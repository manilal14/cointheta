package com.example.mani.cointheta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Users> usersList;
    TextView listbtn;
    TextView uploadbtn;
    public static final String UPLOAD_URL = "http://192.168.43.154/cointhetaApp/upload_data_to_database.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            readFromCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listbtn  = findViewById(R.id.list_btn);
        uploadbtn = findViewById(R.id.upload_btn);


        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,ViewList.class);

                // To send userList to new Activity
                Bundle bundle = new Bundle();
                bundle.putSerializable("usersList", (Serializable) usersList);
                i.putExtras(bundle);

                startActivity(i);

            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToDatabase();
            }
        });




    }

    //Funtion to read from csv file
    private void readFromCsv() throws IOException {

        usersList = new ArrayList<Users>();

        InputStream inputStream = getResources().openRawResource(R.raw.customer1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

        // columns name will not be added
        String line = reader.readLine();

        while((line = reader.readLine()) != null){

            String[] array = line.split(",");

            Users user = new Users();

            user.setUser_id(array[0]);
            user.setName(array[1]);
            user.setContact_no(array[2]);
            user.setAddress(array[3]);
            usersList.add(user);
        }
    }

    // Function to upload to Database

    private void uploadToDatabase()
    {
        final String id = usersList.get(0).getUser_id();
        final String name = usersList.get(0).getName();
        final String contact_no = usersList.get(0).getContact_no();
        final String address = usersList.get(0).getAddress();

        //og.d("MainActivity","created "+ id +" "+name+" "+contact_no+" "+address);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(MainActivity.this, Response, Toast.LENGTH_LONG).show();
                         } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT);
                    }

                }) {

            @Override
            protected Map<String,String> getParams() {

                Map<String,String> params = new HashMap<>();

                params.put("user_id",id);
                params.put("name",name);
                params.put("contact_no",contact_no);
                params.put("address",address);

                return params;
            }
        };

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);

    }

    
}