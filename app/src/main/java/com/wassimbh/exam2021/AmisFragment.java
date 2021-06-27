package com.wassimbh.exam2021;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wassimbh.exam2021.entities.Player;
import com.wassimbh.exam2021.room.AppDatabase;
import com.wassimbh.exam2021.room.PlayerDao;
import com.wassimbh.exam2021.utilities.OnRecycleItemClicked;


public class AmisFragment extends Fragment implements OnRecycleItemClicked<Player> {

    AppDatabase db;
    PlayerDao playerDao;
    PlayersAdapter adapter;

    public AmisFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(requireContext(), AppDatabase.class, "examen")
                .allowMainThreadQueries()
                .build();

        playerDao = db.player_Dao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_amis, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.amis_recycler);
        adapter = new PlayersAdapter(playerDao.getAllPlayers(),this, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }



    @Override
    public void onRecyclerItemClicked(int pos, Player entity) {

    }

    @Override
    public void onRemoveClicked(int pos, Player entity) {
        playerDao.deletePlayer(entity);
        adapter.removePlayer(entity);
    }
}