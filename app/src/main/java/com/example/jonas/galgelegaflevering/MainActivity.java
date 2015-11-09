package com.example.jonas.galgelegaflevering;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    Button gameButton;
    Button rulesButton;
    static Galgelogik galgeLogik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        gameButton = (Button) findViewById(R.id.gameButton);
        rulesButton = (Button) findViewById(R.id.rulesButton);

        gameButton.setOnClickListener(this);
        rulesButton.setOnClickListener(this);
        findViewById(R.id.wordButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WordList.class));
            }
        });

        if (galgeLogik == null){
            galgeLogik = new Galgelogik();

            new AsyncTask() {
                protected Object doInBackground(Object... arg0) {
                    try {
                        MainActivity.galgeLogik.hentOrdFraDr();
                        return "Ordene blev korrekt hentet fra DR's server";
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "Ordene blev ikke hentet korrekt: "+e;
                    }
                }

                @Override
                protected void onPostExecute(Object resultat) {

                    // info.setText("resultat: \n" + resultat);
                }
            }.execute();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == gameButton){
            Intent i = new Intent(this, GameActivity.class);
            startActivity(i);
        }
        else if(v == rulesButton){
            Intent i = new Intent(this, RulesActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "Denne knap er ikke implementeret endnu", Toast.LENGTH_LONG).show();
        }
    }
}
