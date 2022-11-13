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
        //First we need to check if dades.txt exists
        fileCheck();
        etext = findViewById(R.id.editText); //Obtaining editText
        //Reading the file
        readFile();
        //Adding a ChangeListener to save the text to a file
        etext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFile();
            }
        });

    }

    /*
    * This function make the file if the file not exists
     */
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
    /*
    * This function gets the editText data and writes it to the file
     */
    public void updateFile(){

        String text = String.valueOf(etext.getText());
        try {
            FileOutputStream fos=openFileOutput("dades.txt",MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * This function reads the file dades.txt and append every line to editText
     */
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