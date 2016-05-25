package com.example.pavel.moneyflow.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavel.moneyflow.R;
import com.example.pavel.moneyflow.util.Prefs;

public class ExpensesAdapter extends RecyclerView.Adapter{

    private Context context;
    private Cursor cursor;

    public ExpensesAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expenses_adapter, parent, false);
        return new InnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        InnerViewHolder innerViewHolder = (InnerViewHolder) holder;
        cursor.moveToPosition(position);
        innerViewHolder.tvName.setText(cursor.getString(cursor.getColumnIndex(Prefs.EXPENCE_NAMES_FIELDS_NAME)));
        innerViewHolder.tvVolume.setText(cursor.getString(cursor.getColumnIndex(Prefs.EXPENSE_FIELD_VOLUME)));
        innerViewHolder.tvDate.setText(cursor.getString(cursor.getColumnIndex(Prefs.EXPENSE_FIELD_DATE)));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    private class InnerViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvVolume;
        public TextView tvDate;

        public InnerViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvNameItemExpenses);
            tvVolume = (TextView) view.findViewById(R.id.tvVolumeItemExpenses);
            tvDate = (TextView) view.findViewById(R.id.tvDateItemExpenses);
        }
    }
}
