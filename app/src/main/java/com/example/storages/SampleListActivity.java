package com.example.storages;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleListActivity extends AppCompatActivity {
    private SampleAdapter adapter;
    private static final String FILE_NAME = "sample.txt";
    private String BASE_WORLD="base_world";
    private Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);

        ListView listView =findViewById(R.id.listView);
        adapter = new SampleAdapter();
        listView.setAdapter(adapter);

        List<Sample> savedData = loadSamplesFromFile();
        if (!savedData.isEmpty()){
            adapter.setData(savedData);
        }

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String generatedStr = createNewWord();
                saveDataToFile(generatedStr);
                Sample sample = new Sample(generatedStr);
                adapter.addData(sample);
            }


        });
    }
    private void rewriteFile(List<Sample> samples){
        for (Sample sample : samples)
        File file = new File(getExternalFilesDir(null),FILE_NAME);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(file,false)));
            writer.write(sample.getTe+";");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private List<Sample> loadSamplesFromFile() {
        List<Sample> result = new ArrayList<>();
        File file = new File(getExternalFilesDir(null), FILE_NAME);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String allLines = reader.readLine();
            if(allLines==null){
                return result;
            } else {
                String[] lines = allLines.split(";");
                for (String line: lines){
                    result.add(new Sample(line));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    private void saveDataToFile(String generatedStr) {
        File file = new File(getExternalFilesDir(null),FILE_NAME);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(file,true)));
            writer.write(generatedStr+";");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String createNewWord() {
        return BASE_WORLD+random.nextInt();
    }
}
