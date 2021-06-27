package com.wassimbh.exam2021;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wassimbh.exam2021.entities.Player;
import com.wassimbh.exam2021.utilities.OnRecycleItemClicked;

import java.util.ArrayList;
import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.RecycleViewHolder> {
    private Context context;
    private List<Player> players;
    private boolean fromMainActivity;
    private OnRecycleItemClicked<Player> listener;


    public PlayersAdapter(List<Player> p, OnRecycleItemClicked<Player> listener, boolean fromWhere) {
        players = p;
        this.listener = listener;
        fromMainActivity = fromWhere;
    }


    @NonNull
    @Override
    public PlayersAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);

        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersAdapter.RecycleViewHolder holder, int position) {
        Player player = players.get(position);
        holder.username.setText(player.getUserName());
        holder.profileIcon.setImageResource(player.getDrawableID());
        holder.addIcon.setOnClickListener(v -> {
            listener.onRecyclerItemClicked(position, player);
        });
        holder.removeIcon.setOnClickListener(v -> {
            listener.onRemoveClicked(position, player);
        });
        if(fromMainActivity){
            holder.addIcon.setVisibility(View.VISIBLE);
            holder.removeIcon.setVisibility(View.GONE);
        }else{
            holder.addIcon.setVisibility(View.GONE);
            holder.removeIcon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return players.size();
    }


    class RecycleViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        ImageView profileIcon, addIcon, removeIcon;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            profileIcon = itemView.findViewById(R.id.profile_icon);
            addIcon = itemView.findViewById(R.id.add);
            removeIcon = itemView.findViewById(R.id.remove);
        }
    }
    public void removePlayer(Player player){
        players.remove(player);
        notifyDataSetChanged();
    }

}