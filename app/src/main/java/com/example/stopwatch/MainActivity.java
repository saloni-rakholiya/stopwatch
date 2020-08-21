package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    custom custom;
    Button start,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.button);
        reset=findViewById(R.id.button2);
        custom=findViewById(R.id.custom);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(start.getText().equals("START"))
                {
                    custom.start();
                    start.setText("PAUSE");
                }
                else { start.setText("START");
                custom.pause();
                }

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom.reset();
                start.setText("START");
            }
        });



    }
}
