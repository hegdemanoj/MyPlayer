package com.manojhegde.myplayer;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    AudioManager audioManager;
    private File root;
    private ArrayList<File> fileList = new ArrayList<File>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medialistlayout);

        final ListView myListView=(ListView)findViewById(R.id.listView);
        ArrayList<String>myStringFileList=new ArrayList<String>();

        root = new File(Environment.getExternalStorageDirectory().getPath());

        fileList=getfile(root);
        for (int i = 0; i < fileList.size(); i++) {
            myStringFileList.add(fileList.get(i).getName());
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myStringFileList);
        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String videoFileToPlay= fileList.get(position).getAbsolutePath();
                Intent myIntent = new Intent(view.getContext(), VideoPlayActivity.class);
                myIntent.putExtra("videofile",videoFileToPlay);
                startActivityForResult(myIntent, 0);
            }
        });

    }

    public ArrayList<File> getfile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    getfile(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(".mp4")
                            ||listFile[i].getName().endsWith(".flv"))
                    {
                        fileList.add(listFile[i]);
                    }
                }
            }
        }
        return fileList;
    }


}
