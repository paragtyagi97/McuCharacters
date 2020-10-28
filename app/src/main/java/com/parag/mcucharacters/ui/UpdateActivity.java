package com.parag.mcucharacters.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parag.mcucharacters.R;
import com.parag.mcucharacters.database.MyDatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    EditText character_input, desc_input, movies_input;
    Button update_button, delete_button;

    String id, character, desc, movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        character_input = findViewById(R.id.character_input2);
        desc_input = findViewById(R.id.desc_input2);
        movies_input = findViewById(R.id.movies_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);


        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(character);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                character = character_input.getText().toString().trim();
                desc = desc_input.getText().toString().trim();
                movies = movies_input.getText().toString().trim();
                myDB.updateData(id, character, desc, movies);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("character") &&
                getIntent().hasExtra("desc") && getIntent().hasExtra("movies")){

            id = getIntent().getStringExtra("id");
            character = getIntent().getStringExtra("character");
            desc = getIntent().getStringExtra("desc");
            movies = getIntent().getStringExtra("movies");


            character_input.setText(character);
            desc_input.setText(desc);
            movies_input.setText(movies);
            Log.d("stev", character+" "+desc+" "+movies);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + character + " ?");
        builder.setMessage("Are you sure you want to delete " + character + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
