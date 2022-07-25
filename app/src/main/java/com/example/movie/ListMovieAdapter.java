package com.example.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.classes.Search;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.Holder> {
    List<Search> searches;
    Context context;
    onItemClick listener;


    public ListMovieAdapter(List<Search> searches, Context context) {
        this.searches = searches;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listmovie_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.fill(searches.get(position));
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView title, year, imdbID, type;
        ImageView poster;

        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            imdbID = itemView.findViewById(R.id.imdbID);
            type = itemView.findViewById(R.id.type);
            poster = itemView.findViewById(R.id.poster);
itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        listener.setOnItemClickListener(searches.get(getAdapterPosition()));
    }
});
        }

        void fill(Search search) {
            title.setText(search.getTitle());
            year.setText(search.getYear());
            imdbID.setText(search.getImdbID());
            type.setText(search.getType());
            Picasso.get().load(search.getPoster()).into(poster);
        }
    }

    interface onItemClick{
        public void setOnItemClickListener(Search Search);
    }

    public void setOnItemCLickListener(onItemClick listener){
        this.listener = listener;
    }
}
