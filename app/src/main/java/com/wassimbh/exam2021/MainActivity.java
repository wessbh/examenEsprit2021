package com.wassimbh.exam2021;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wassimbh.exam2021.entities.Player;
import com.wassimbh.exam2021.room.AppDatabase;
import com.wassimbh.exam2021.room.PlayerDao;
import com.wassimbh.exam2021.utilities.OnRecycleItemClicked;
import com.wassimbh.exam2021.utilities.SharedPreferenceHelper;
import com.wassimbh.exam2021.utilities.Utilities;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRecycleItemClicked<Player> {

    AppDatabase db;
    PlayerDao playerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "examen")
                .allowMainThreadQueries()
                .build();

        playerDao = db.player_Dao();


        ConstraintLayout parentLayout = findViewById(R.id.parent_layout);

        TextView userName = findViewById(R.id.username_text);
        Button jouerBtn = findViewById(R.id.joueur_btn);
        Button disconnectBtn = findViewById(R.id.disconnect_btn);
        Button joueursBtn = findViewById(R.id.joueurs_btn);
        Button amisBtn = findViewById(R.id.amis_btn);
        List<Player> players = Utilities.getPlayerList();
        String userNameStr = SharedPreferenceHelper.getString("username", "", getApplicationContext());
        userName.setText(userNameStr);


        disconnectBtn.setOnClickListener(v -> {
            SharedPreferenceHelper.clearSharedPrefs(getApplicationContext());
            finish();
        });

        amisBtn.setOnClickListener(v->{
            changeFragment(new AmisFragment());
        });

        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        PlayersAdapter adapter = new PlayersAdapter(players, this, true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onRecyclerItemClicked(int pos, Player entity) {
        verifyPlayer(entity);
    }

    @Override
    public void onRemoveClicked(int pos, Player entity) {

    }

    private void verifyPlayer (Player player){
        List<Player> list = playerDao.getAllPlayers();
        if(list.contains(player)){
            showToast("Ce joueur est déjà dans votre liste d’amis");
        }
        else{
            showToast("Nom du joueur choisi " + player.getUserName());
            playerDao.addPlayer(player);
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.recycler_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}