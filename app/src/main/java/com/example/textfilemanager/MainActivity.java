package com.example.textfilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText etext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileCheck();
        etext = findViewById(R.id.editText);
        readFile();


    }

    public void fileCheck(){
        String data = Arrays.toString(this.getFilesDir().list());
        if (!data.contains("dades.txt")) {
            try {
                FileOutputStream fos=openFileOutput("dades.txt",MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void readFile(){
        try {
            FileInputStream fis=openFileInput("dades.txt");
            InputStreamReader fir = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(fir);
            String line = br.readLine();
            while (line != null){
                etext.append(line+"\n");
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}