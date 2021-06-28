package com.wassimbh.exam2021;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wassimbh.exam2021.entities.Player;
import com.wassimbh.exam2021.utilities.FromWhere;
import com.wassimbh.exam2021.utilities.OnRecycleItemClicked;

import java.util.ArrayList;
import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Player> players;
    private FromWhere fromWhere;
    private OnRecycleItemClicked<Player> listener;


    public PlayersAdapter(List<Player> p, OnRecycleItemClicked<Player> listener, FromWhere fromWhere) {
        players = p;
        this.listener = listener;
        this.fromWhere = fromWhere;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        if (fromWhere == FromWhere.GameActivity) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.different_layout, parent, false);
            return new DifferentViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.player_item, parent, false);
            return new RecycleViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Player player = players.get(position);
        if(fromWhere == FromWhere.GameActivity){
            DifferentViewHolder mHolder = (DifferentViewHolder) holder;
            mHolder.username.setText(player.getUserName());
            if(player.isDifferent()){
                mHolder.username.setTextColor(context.getResources().getColor(R.color.red));
                mHolder.username.setTypeface(Typeface.DEFAULT_BOLD);
            }
        }
        else{
            RecycleViewHolder mHolder = (RecycleViewHolder) holder;
            mHolder.username.setText(player.getUserName());
            mHolder.profileIcon.setImageResource(player.getDrawableID());
            mHolder.addIcon.setOnClickListener(v -> {
                listener.onRecyclerItemClicked(position, player);
            });
            mHolder.removeIcon.setOnClickListener(v -> {
                listener.onRemoveClicked(position, player);
            });
            if (fromWhere == FromWhere.MainActivity) {
                mHolder.addIcon.setVisibility(View.VISIBLE);
                mHolder.removeIcon.setVisibility(View.GONE);
            } else {
                mHolder.addIcon.setVisibility(View.GONE);
                mHolder.removeIcon.setVisibility(View.VISIBLE);
            }
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

    public void removePlayer(Player player) {
        players.remove(player);
        notifyDataSetChanged();
    }

    class DifferentViewHolder extends RecyclerView.ViewHolder {
        TextView username;

        public DifferentViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
        }
    }

}