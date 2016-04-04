package com.example.shilpisingh.books;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.shilpisingh.books.SimpleService.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("msg", "The onStart() event");


        final ArrayList<Integer> Lid = new ArrayList<>();

        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Detail.class);
                int sed = Lid.get(position);
                System.out.println("sed" + sed);
                intent.putExtra("id", sed);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id1) {
                int position = Lid.get(pos);
                AlertDialog dialbox = AskOption(position);
                dialbox.show();


                return true;
            }
        });

        Callback callback = new Callback<List<Book>>() {

            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {

                System.out.println("inside response");
                final ListView listView = (ListView) findViewById(R.id.listView);
                final ArrayList<String> lv = new ArrayList<>();

                for(int i=0;i<response.body().size();i++) {
                    System.out.println("response" + response.body().get(i).title);
                    lv.add(response.body().get(i).title);
                    Lid.add(response.body().get(i).id);

                }

                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, lv);
                listView.setAdapter(itemsAdapter);
                itemsAdapter.notifyDataSetChanged();
                System.out.println("came inside main callback");
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                System.out.print("main callback failed response");
            }
        };


        try {
            SimpleService.getBooks(callback);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    protected void onResume() {
        super.onResume();
        Log.d("msg", "The onResume() event");
    }

    protected void onPause() {
        super.onPause();
        Log.d("msg", "The onpause() event");

    }

    protected void onStop() {
        super.onStop();
        Log.d("msg", "The onStop() event");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d("msg", "The onDestroy() event");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        switch (id) {

            case R.id.action_add:
                Intent intent = new Intent(this, Add.class);
                startActivity(intent);
                return true;

            case R.id.action_clean:
                try {
                    SimpleService.clean();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                onStart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private AlertDialog AskOption(final int pos)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            SimpleService.delete(pos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();
                        onStart();

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        return myQuittingDialogBox;

    }

}









    






