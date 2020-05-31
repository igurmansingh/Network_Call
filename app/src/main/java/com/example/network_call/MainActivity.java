package com.example.network_call;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn=findViewById(R.id.Btn_Get);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network_call();
            }
        });
    }

    private void network_call() {

        networkTask tk=new networkTask();
        tk.execute("https://api.github.com/search/users?q=harshit");

    }
    class networkTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
         String stringurl= strings[0];

             try {
                URL url=new URL(stringurl);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                InputStream ip= con.getInputStream();
                Scanner sc=new Scanner(ip);
                sc.useDelimiter("\\A");
                if(sc.hasNext()){
                    String s=sc.next();
                    return s;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
             return "null";
        }
         @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView txt=findViewById(R.id.txt);
            txt.setText(s);
        }

    }
}
