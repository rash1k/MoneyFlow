package com.example.pavel.moneyflow.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pavel.moneyflow.R;
import com.example.pavel.moneyflow.adapters.ExpensesAdapter;

public class ExpensesActivity extends AppCompatActivity {

    private RecyclerView rvExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvExpenses = (RecyclerView) findViewById(R.id.rvExpenses);
        rvExpenses.setLayoutManager(new LinearLayoutManager(this));

        rvExpenses.setAdapter(new ExpensesAdapter(this, null));
    }
}
