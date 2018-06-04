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

public class ViewFoldersActivity extends AppCompatActivity {

    private File rootFile;
    private ArrayList<File> folderList = new ArrayList<File>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_folders);

        rootFile = new File(Environment.getExternalStorageDirectory().getParent());
        folderList=getfolder(rootFile);

        MyCustomListAdapter adapter = new MyCustomListAdapter(this, folderList);
        ListView list = (ListView) findViewById(R.id.folderListView);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String videoFolderPath= folderList.get(position).getPath();
                Intent myIntent = new Intent(view.getContext(), CustomVideoList.class);
                myIntent.putExtra("videoFolder",videoFolderPath);
                startActivityForResult(myIntent, 0);
            }

        });


    }

    public ArrayList<File> getfolder(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    getfolder(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(".mp4")
                            || listFile[i].getName().endsWith(".flv")) {
                        folderList.add(dir);
                        break;
                    }
                }
            }
        }
        return folderList;
    }


}
