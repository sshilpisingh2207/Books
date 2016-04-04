package com.example.shilpisingh.books;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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


        switch (v.getId()) {
            case R.id.button:
                try {
                    if (authorname.getText().toString().equals("Type Author name") || authorname.getText().toString().equals(" ") ||
                            catname.getText().toString().equals("Type Book Title") || catname.getText().toString().equals(" ") ||
                            pubname.getText().toString().equals("Type publisher name") || pubname.getText().toString().equals(" ") ||
                            bookname.getText().toString().equals("Type Category name") || bookname.getText().toString().equals(" ")) {
                        AlertDialog alertDialog = new AlertDialog.Builder(Add.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Please input a valid value in all the sections");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        return;
                    }

                    service.post(authorname.getText().toString(), catname.getText().toString(), bookname.getText().toString(), pubname.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


        }
    }

    @Override
    public void onBackPressed() {
        final TextView bookname = (TextView) findViewById(R.id.editText);
        TextView authorname = (TextView) findViewById(R.id.editText2);
        TextView pubname = (TextView) findViewById(R.id.editText3);
        TextView catname = (TextView) findViewById(R.id.editText4);


        if (authorname.getText().toString().equalsIgnoreCase("Type Author name") &&
                catname.getText().toString().equalsIgnoreCase("Type Category name")  &&
                pubname.getText().toString().equalsIgnoreCase("Type publisher name") &&
                bookname.getText().toString().equalsIgnoreCase("Type Book Title") ) {
            finish();
        }
        else if(authorname.getText().toString().equals("") &&
                catname.getText().toString().equals("")
                && pubname.getText().toString().equals("")
                && bookname.getText().toString().equals("")) {
            finish();
        }

        else {

            doExit();
        }
    }

    private void doExit() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Add.this);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.setNegativeButton("No", null);
        alertDialog.setMessage("Do you want to exit with unsaved changes?");
        alertDialog.setTitle("exit");
        alertDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        final TextView bookname = (TextView) findViewById(R.id.editText);
        TextView authorname = (TextView) findViewById(R.id.editText2);
        TextView pubname = (TextView) findViewById(R.id.editText3);
        TextView catname = (TextView) findViewById(R.id.editText4);


        switch (id) {
            case R.id.action_done:
                if (authorname.getText().toString().equalsIgnoreCase("Type Author name") &&
                        catname.getText().toString().equalsIgnoreCase("Type Category name")  &&
                        pubname.getText().toString().equalsIgnoreCase("Type publisher name") &&
                        bookname.getText().toString().equalsIgnoreCase("Type Book Title") ) {
                    finish();
                }
                else if(authorname.getText().toString().equals("") &&
                        catname.getText().toString().equals("")
                        && pubname.getText().toString().equals("")
                        && bookname.getText().toString().equals("")) {
                    finish();
                }

                else {
                    doExit();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
