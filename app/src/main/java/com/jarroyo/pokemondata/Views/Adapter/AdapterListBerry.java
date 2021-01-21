package com.jarroyo.pokemondata.Views.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jarroyo.pokemondata.Interactor.Model.BerryModel;
import com.jarroyo.pokemondata.Interactor.Model.PokemonModel;
import com.jarroyo.pokemondata.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterListBerry extends RecyclerView.Adapter<AdapterListBerry.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<BerryModel> model;

    private View.OnClickListener listener;

    public AdapterListBerry(Context context, ArrayList<BerryModel> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_berry, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtVNomBerry;
        ImageView imgFavorite;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtVNomBerry = itemView.findViewById(R.id.txtVNomBerry);
            imgFavorite = itemView.findViewById(R.id.imgFavorite);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nomBerry = model.get(position).getName();
        Integer imgFavorite = model.get(position).getFavorite();

        holder.txtVNomBerry.setText(nomBerry);

        if (imgFavorite==1)
            holder.imgFavorite.setImageResource(R.drawable.favorite_on);
            //holder.imgFavorite.setVisibility(View.VISIBLE);
        else
            holder.imgFavorite.setImageResource(R.drawable.favorite_off);
            //holder.imgFavorite.setVisibility(View.INVISIBLE);

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


}
