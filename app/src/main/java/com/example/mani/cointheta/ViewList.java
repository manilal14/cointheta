package com.example.mani.cointheta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewList extends AppCompatActivity {

    ArrayList<Users> usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        Bundle bundle = getIntent().getExtras();
        usersList = (ArrayList<Users>) bundle.getSerializable("usersList");

        for(int i=0;i<usersList.size();i++) {
            Log.d("MainActivity","created "+ usersList.get(i));
        }

        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<Users> arrayAdapter= new ArrayAdapter<Users>(getBaseContext(),android.R.layout.simple_list_item_1,usersList);
        listView.setAdapter(arrayAdapter);
    }
}
