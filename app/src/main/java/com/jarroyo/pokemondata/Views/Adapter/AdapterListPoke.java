package com.jarroyo.pokemondata.Views.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jarroyo.pokemondata.Interactor.PokemonModel;
import com.jarroyo.pokemondata.R;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterListPoke extends RecyclerView.Adapter<AdapterListPoke.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<PokemonModel> model;

    private View.OnClickListener listener;

    public AdapterListPoke(Context context, ArrayList<PokemonModel> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_pokemon, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre = model.get(position).getName();
        holder.nombre.setText(nombre);
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
        TextView nombre;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txtVNombre);

        }

    }

}
