package com.jarroyo.pokemondata.Views.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jarroyo.pokemondata.Interactor.Model.StatsModel;
import com.jarroyo.pokemondata.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterListStas extends RecyclerView.Adapter<AdapterListStas.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<StatsModel> model;

    private View.OnClickListener listener;

    public AdapterListStas(Context context, ArrayList<StatsModel> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_stats, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String txtVNStats = model.get(position).getStat().getName();
        String txtVBStats = ""+model.get(position).getBase_stat();
        String txtVEStats = ""+model.get(position).getEffort();

        holder.txtVNStats.setText(txtVNStats);
        holder.txtVBStats.setText(txtVBStats);
        holder.txtVEStats.setText(txtVEStats);

    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtVNStats,txtVBStats,txtVEStats;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtVNStats = itemView.findViewById(R.id.txtVNStats);
            txtVBStats = itemView.findViewById(R.id.txtVBStats);
            txtVEStats = itemView.findViewById(R.id.txtVEStats);

        }

    }

}
