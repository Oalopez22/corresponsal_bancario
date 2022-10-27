package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.adaptadores.HistoryCopTransactionsAdapter;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class Corresponsal_History extends AppCompatActivity implements  SearchView.OnQueryTextListener{
    ImageView imgArrowBack;
    RecyclerView rviewHistory;
    HistoryCopTransactionsAdapter adapter;
    SearchView svHistory;
    DbLogin db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_history);
        imgArrowBack = findViewById(R.id.imgArrowbackRet);
        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rviewHistory = findViewById(R.id.rviewHistory);
        db = new DbLogin(Corresponsal_History.this);
        sp = new SharedPreferences(this);
        rviewHistory.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryCopTransactionsAdapter(db.historial(sp));
        rviewHistory.setAdapter(adapter);
        svHistory = findViewById(R.id.svHistoryCop);
        svHistory.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrado(newText);
        return false;
    }
}