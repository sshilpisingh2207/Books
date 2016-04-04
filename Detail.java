package com.example.shilpisingh.books;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Detail extends AppCompatActivity {
    ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView bookname = (TextView) findViewById(R.id.editText5);
        final TextView authorname = (TextView) findViewById(R.id.editText6);
        final TextView pubname = (TextView) findViewById(R.id.editText7);
        final TextView catname = (TextView) findViewById(R.id.editText8);
        final TextView lastcheckedby = (TextView) findViewById(R.id.editText9);
        final TextView lastcheckeddate = (TextView) findViewById(R.id.editText10);
        final Button Checkout = (Button) findViewById(R.id.button4);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final Context context = this;
        Bundle s = getIntent().getExtras();
        final int i = s.getInt("id");
        SimpleService.get(i, new Callback<SimpleService.Book>() {

            @Override
            public void onResponse(Call<SimpleService.Book> call, Response<SimpleService.Book> response) {
                bookname.setText(response.body().title);
                authorname.setText(response.body().author);
                pubname.setText(response.body().publisher);
                catname.setText(response.body().categories);
                lastcheckedby.setText(response.body().lastCheckedOutBy);
                lastcheckeddate.setText(response.body().lastCheckedOut);
            }

            @Override
            public void onFailure(Call<SimpleService.Book> call, Throwable t) {

            }
        });


        Checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pattern = "yyyy-MM-dd HH:mm:ss zzz";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                final String date = simpleDateFormat.format(new Date());
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                lastcheckedby.setText(userInput.getText());
                                lastcheckeddate.setText(date.toString());
                                SimpleService.put(i, String.valueOf(userInput.getText()), date.toString());
                            }
                        }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://google.com/");
        mShareActionProvider.setShareIntent(shareIntent);
        return true;
    }
}


