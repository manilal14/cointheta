package com.example.mani.cointheta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Users> usersList;
    TextView listbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            readFromCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listbtn = findViewById(R.id.list_btn);

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

            user.setUser_id(Integer.parseInt(array[0]));
            user.setName(array[1]);
            user.setContact_no(array[2]);
            user.setAddress(array[3]);
            usersList.add(user);
        }
    }

    
}