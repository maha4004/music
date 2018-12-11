package com.example.softw.music;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    ListView listView;

    List<String> list;

    ListAdapter listAdapter;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        list = new ArrayList<>();

        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);

        listView.setAdapter(listAdapter);

        Field[] fields = R.raw.class.getFields();
        for(int i=0;i<fields.length;i++){
            list.add(fields[i].getName());
        }
       list.remove(0);
      list.remove(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                if (mediaPlayer != null) {
                    mediaPlayer.release();
               }

                int resID = getResources().getIdentifier(list.get(i), "raw", getPackageName());

                mediaPlayer = MediaPlayer.create(MainActivity.this, resID);
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                else {
                mediaPlayer.start();}

            }  });
    }
}
