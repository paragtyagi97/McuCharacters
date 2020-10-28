package com.parag.mcucharacters.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parag.mcucharacters.database.MyDatabaseHelper;
import com.parag.mcucharacters.R;

public class AddActivity extends AppCompatActivity {

    EditText character_input, desc_input, movies_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        character_input = findViewById(R.id.character_input);
        desc_input = findViewById(R.id.desc_input);
        movies_input = findViewById(R.id.movies_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(com.parag.mcucharacters.ui.AddActivity.this);
                myDB.addCharacter(character_input.getText().toString().trim(),
                        desc_input.getText().toString().trim(),
                        Integer.valueOf(movies_input.getText().toString().trim()));
            }
        });
    }
}
