package com.manojhegde.myplayer;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class CustomVideoList extends AppCompatActivity {

    private File root;
    private ArrayList<File> fileList = new ArrayList<File>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_video_list);

        Bundle bundle = getIntent().getExtras();
        String videoFolder = bundle.getString("videoFolder");

        root = new File(videoFolder);
        fileList = getfile(root);

        MyCustomListAdapter adapter = new MyCustomListAdapter(this, fileList);
        ListView list = (ListView) findViewById(R.id.customList);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
                            || listFile[i].getName().endsWith(".flv")) {
                        fileList.add(listFile[i]);
                    }
                }
            }
        }
        return fileList;
    }
}