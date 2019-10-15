package com.example.lezionedue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class Stringhe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stringhe);

        TextView testo2  = (TextView) findViewById(R.id.testo2);

        // Recupero della risorse
        String etichetta = getString(R.string.altro_testo);
        testo2.setText(etichetta);

        // testo2.setText(R.string.altro_testo);

        // Uso di String.Format e place holder
        String risorsa = "Nome: %s, Cognome: %s";
        String finale = String.format(risorsa, "Paolo", "Rossi");
        Log.d("MIO", "Messaggio: " + finale);

        // Place holder con numerali
        risorsa = "Cognome: %2$s, Nome: %1$s";
        finale = String.format(risorsa, "Paolo", "Rossi");
        Log.d("MIO", "Messaggio: " + finale);

        // Utilizziamo una risorsa Android
        risorsa = getString(R.string.nome_cognome);
        finale = String.format(risorsa, "Giovanni", "Zorzetti");
        Log.d("MIO", finale);

        // Senza usare String.format
        finale = getString(R.string.cognome_nome, "Giovanni", "Zorzetti");
        Log.d("MIO", finale );

        // Formattazione di un numero
        String numero = getString(R.string.numero);
        Locale locale = getResources().getConfiguration().locale;
        String finaleNumero = String.format(locale, numero, 23.34);
        Log.d("MIO", finaleNumero);

        // Indichiamo il locale italiano
        finaleNumero = String.format(Locale.ITALIAN, numero, 23.34);
        Log.d("MIO", finaleNumero);


    }
}
