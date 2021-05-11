package br.com.local.listapadrinhosmagicos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    List<Temporadas> listTemporadas;

    public Adapter(Context context, List<Temporadas> listTemporadas) {
        this.context = context;
        this.listTemporadas = listTemporadas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout, parent, false);

        return new MyViewHolder( itemLista );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Temporadas temporadas = listTemporadas.get( position );
        holder.textTemporada.setText(temporadas.getTemporada());
        holder.textEpisodios.setText(temporadas.getEpisodios());
        holder.textData.setText(temporadas.getDataLancamento());

    }

    @Override
    public int getItemCount() {
        return listTemporadas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textTemporada, textEpisodios, textData;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textTemporada = itemView.findViewById(R.id.textTemporada);
            textEpisodios = itemView.findViewById(R.id.textEpisodios);
            textData      = itemView.findViewById(R.id.textData);

        }
    }
}
