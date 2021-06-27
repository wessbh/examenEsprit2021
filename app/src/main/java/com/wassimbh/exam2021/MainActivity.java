package com.wassimbh.exam2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wassimbh.exam2021.utilities.SharedPreferenceHelper;
import com.wassimbh.exam2021.utilities.Utilities;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView userName = findViewById(R.id.username_text);
        Button jouerBtn = findViewById(R.id.joueur_btn);
        Button disconnectBtn = findViewById(R.id.disconnect_btn);
        Button joueursBtn = findViewById(R.id.joueurs_btn);
        Button amisBtn = findViewById(R.id.amis_btn);
        String userNameStr = SharedPreferenceHelper.getString("username", "", getApplicationContext());
        userName.setText(userNameStr);

        disconnectBtn.setOnClickListener(v -> {
            SharedPreferenceHelper.clearSharedPrefs(getApplicationContext());
            finish();
        });


        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        PlayersAdapter adapter = new PlayersAdapter(Utilities.getPlayerList());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}