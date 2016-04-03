package com.example.shilpisingh.books;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;


public class Add extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button Submit = (Button) findViewById(R.id.button);
        Submit.setOnClickListener(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
        public void onClick(View v) {
        final SimpleService service = new SimpleService();

        final TextView bookname = (TextView) findViewById(R.id.editText);
        TextView authorname = (TextView) findViewById(R.id.editText2);
        TextView pubname = (TextView) findViewById(R.id.editText3);
        TextView catname = (TextView) findViewById(R.id.editText4);


        switch(v.getId()){
            case R.id.button:
                try {
//                    if(authorname.getText().toString().equals("Type Book Title")||catname.getText().toString().equals("Type Author name")||pubname.getText().toString().equals("Type publisher name") ||bookname.getText().toString().equals("Type Category name") {
//                    //  System.out.println("came inside add");
//                        AlertDialog alertDialog = new AlertDialog.Builder(Add.this).create();
//                        alertDialog.setTitle("Alert");
//                        alertDialog.setMessage("Alert message to be shown");
//                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                        return;
//                                    }
//                                });
//                        alertDialog.show();

//                    }

                    service.post(authorname.getText().toString(),catname.getText().toString(),bookname.getText().toString(),pubname.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
