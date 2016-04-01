package com.example.shilpisingh.books;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shilpisingh.books.SimpleService.BookAPI;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class Add extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add1);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button Submit = (Button) findViewById(R.id.button);
        Submit.setOnClickListener(this);


        final TextView bookname = (TextView) findViewById(R.id.editText);
        TextView authorname = (TextView) findViewById(R.id.editText2);
        TextView pubname = (TextView) findViewById(R.id.editText3);
        TextView catname = (TextView) findViewById(R.id.editText4);

        final Button Clean = (Button) findViewById(R.id.button3);
        Clean.setOnClickListener(this);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


//        Submit.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                final SimpleService service = new SimpleService();
////
////                Thread thread = new Thread(new Runnable(){
////                    @Override
////                    public void run() {
////                        try {
////                            service.post();
////                        } catch (Exception e) {
////                            // blah
////                        }
////                    }
////                });
////
////                thread.start();
//
//                try {
//                    service.clean();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });


    }

    @Override
    public void onClick(View v) {
        final SimpleService service = new SimpleService();

        switch(v.getId()){
            case R.id.button3:
                try {
                    System.out.print("came inside clean");
                    service.clean();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.button:
                try {
                    System.out.println("came inside add");
                    service.post();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

        }

    }
}
