package com.wassimbh.exam2021;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.wassimbh.exam2021.entities.Player;
import com.wassimbh.exam2021.utilities.FromWhere;
import com.wassimbh.exam2021.utilities.OnRecycleItemClicked;
import com.wassimbh.exam2021.utilities.SharedPreferenceHelper;
import com.wassimbh.exam2021.utilities.Utilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements OnRecycleItemClicked<Player> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        List<Player> players = Utilities.getPlayerList();
        Collections.shuffle(players);

        List<Player> finalList = new ArrayList<>();
        for(int i =0; i <= 6; i++){
            finalList.add(players.get(i));
        }
        String myUsername = SharedPreferenceHelper.getString("username", "", getApplicationContext());
        Player myPlayer = new Player(0, myUsername, R.drawable.ic_profile);
        finalList.add(0, myPlayer);
        Random random  = new Random();
        int randomNumber = random.nextInt(6);

        finalList.get(randomNumber).setDifferent(true);

        RecyclerView recyclerView = findViewById(R.id.different_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        PlayersAdapter adapter = new PlayersAdapter(finalList, this, FromWhere.GameActivity);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRecyclerItemClicked(int pos, Player entity) {

    }

    @Override
    public void onRemoveClicked(int pos, Player entity) {

    }
}