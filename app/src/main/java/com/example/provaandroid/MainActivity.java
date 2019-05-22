package com.example.provaandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SimpleCursorAdapter adapter;
    private ListView listView;
    private ArrayAdapter<String> patientArrayAdapter;
    private Cursor cursor;
    private ArrayList<String> pacientes = new ArrayList<String>();
    private Runnable run;


        @SuppressLint("WrongConstant")
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            run = new Runnable() {
                public void run() {
                    //reload content
                    carregaPacientes();
                    patientArrayAdapter.notifyDataSetChanged();
                    listView.invalidateViews();
                    listView.refreshDrawableState();
                }
            };

            Button btnAdd = findViewById(R.id.btAdd);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                    startActivity(intent);
                }
            });

            listView = findViewById(R.id.mlistView);
            patientArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pacientes);
            listView.setAdapter(patientArrayAdapter);

            if (temPacientes()) {
                carregaPacientes();
            } else {
                Toast.makeText(this, "Sem pacientes no banco de dados", 4).show();
            }
        }

        private boolean temPacientes () {
            DAL dal = new DAL(this);
            cursor = dal.loadAll();
            if (cursor == null) {
                return false;
            } else if (cursor.getCount() == 0) {
                return false;
            }

            return true;
        }

        private void carregaPacientes () {
            DAL dal = new DAL(this);
            cursor = dal.loadAll();

            if (cursor == null) {
                return;
            }

            pacientes.clear();

            while (!cursor.isAfterLast()) {
                String nome = cursor.getString(cursor.getColumnIndex(CreateDatabase.NOME));
                int idade = cursor.getInt(cursor.getColumnIndex(CreateDatabase.IDADE));
                double mortalidade = cursor.getDouble(cursor.getColumnIndex(CreateDatabase.MORTALIDADE));
                pacientes.add("Nome: " + nome + "\nIdade: " + idade + "\nMortalidade: " + mortalidade + "%");
                cursor.moveToNext();
            }

            Log.i(TAG, "Número de registros: " + cursor.getCount());
        }

        @Override
        public void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            runOnUiThread(run);
            Log.i(TAG, "New patients add.");
        }

    }




    /*  DAL dal = new DAL(this);
        Cursor cursor = dal.loadAll();

        String[] fields = new String[] {CreateDatabase.ID, CreateDatabase.NOME};
        int[] ids = {R.id.tvId, R.id.tvNome};

        Log.d(TAG, "onCreate: " + cursor.getCount());
        adapter = new SimpleCursorAdapter(MainActivity.this,
                R.layout.content_main, cursor, fields, ids, 0);
        lvPaciente.setAdapter(adapter);


        lvPaciente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                intent.putExtra(CreateDatabase.NOME, Integer.valueOf(((TextView)view.findViewById(R.id.tvId)).getText().toString()));
                startActivity(intent);
            }
        });


        Button btnAdd = findViewById(R.id.btAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}*/
