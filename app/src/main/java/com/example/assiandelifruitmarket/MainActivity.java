package com.example.assiandelifruitmarket;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initUI();
        setupRecyclerView();

    }

    private void setupRecyclerView() {
        // set the data!
        // consider the data is received from Database
        // 1 create ArrayList Items
        ArrayList<FruitItem> fruitList = new ArrayList<>();
        fruitList.add(new FruitItem(R.drawable.apple, "appleDesc", "apple"));
        fruitList.add(new FruitItem(R.drawable.banana, "bananaDesc", "banana"));
        fruitList.add(new FruitItem(R.drawable.mango, "mangoDesc", "mango"));
        fruitList.add(new FruitItem(R.drawable.orange, "orangeDesc", "orange"));
        fruitList.add(new FruitItem(R.drawable.strawberry, "strawberryDesc", "strawberry"));

        //2 create adapter
        FruitAdapter fa = new FruitAdapter(fruitList);
        //3 set the adapter in the recycler view
        recyclerView.setAdapter(fa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("setupRecyclerView","stam");
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
    }

}