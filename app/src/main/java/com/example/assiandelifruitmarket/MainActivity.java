package com.example.assiandelifruitmarket;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<FruitItem> fruitList;

    // Member variable for the selected image resource ID
    private int selectedImageResId = R.drawable.apple;
    FruitAdapter fa;
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

        setUpFloationActionButton();
    }

    private void setUpFloationActionButton() {
        FloatingActionButton fab = findViewById(R.id.btnFlowAddItem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog, null);

                EditText nameInput = dialogView.findViewById(R.id.edtFruitName);
                EditText descInput = dialogView.findViewById(R.id.edtFruitDesc);

                handleImageSelection(dialogView);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add new Fruit")
                        .setView(dialogView)
                        .setPositiveButton("Add", (dialog, which) -> {
                            String name = nameInput.getText().toString();
                            String description = descInput.getText().toString();
                            // add a new fruit item to the list
                            int imageResource = selectedImageResId; // Get the image resource id. this affect on added icon to the list
                            Log.d("setUpFloationActionButton::onClick -","fruit.getTag()"+selectedImageResId);
                            fruitList.add(new FruitItem(imageResource, name, description));
                            fa.notifyItemInserted(fruitList.size()-1);
                        })
                        .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                        .setCancelable(false);
                    ;
                builder.show();
            }
        });

    }

    private void handleImageSelection(View dialogView) {
        Spinner spinner = dialogView.findViewById(R.id.spinnerFruitImages);
        ArrayList<SpinnerItem> si = new ArrayList<SpinnerItem>();
        si.add(new SpinnerItem(R.drawable.apple));
        si.add(new SpinnerItem(R.drawable.banana));
        si.add(new SpinnerItem(R.drawable.mango));
        si.add(new SpinnerItem(R.drawable.orange));
        si.add(new SpinnerItem(R.drawable.strawberry));

        spinner.setAdapter(new ImageSpinnerAdapter(MainActivity.this, si));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                selectedImageResId = si.get(position).getImageResId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setupRecyclerView() {
        // set the data!
        // consider the data is received from Database
        // 1 create ArrayList Items
        fruitList = new ArrayList<>();
        fruitList.add(new FruitItem(R.drawable.apple, "appleDesc", "apple"));
        fruitList.add(new FruitItem(R.drawable.banana, "bananaDesc", "banana"));
        fruitList.add(new FruitItem(R.drawable.mango, "mangoDesc", "mango"));
        fruitList.add(new FruitItem(R.drawable.orange, "orangeDesc", "orange"));
        fruitList.add(new FruitItem(R.drawable.strawberry, "strawberryDesc", "strawberry"));

        //2 create adapter
        fa = new FruitAdapter(fruitList);
        //3 set the adapter in the recycler view
        recyclerView.setAdapter(fa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("setupRecyclerView","stam");
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
    }

}