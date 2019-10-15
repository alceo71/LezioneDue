package com.example.lezionedue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Annotation;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.TextView;

import com.example.lezionedue.common.LogUtil;
import com.example.lezionedue.common.SpanUtility;

import java.util.Locale;

public class StringheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stringhe);

        ////////////////////////////////
        // Uso delle risorse stringhe //
        ////////////////////////////////

        // Recupera la text view
        TextView testo2  = (TextView) findViewById(R.id.testo2);

        // Recupero della risorse
        String etichetta = getString(R.string.altro_testo);

        // Moddfica dell'etichetta
        testo2.setText(etichetta);

        // Modifica direttamente via ID
        testo2.setText(R.string.altro_testo);

        /////////////////////////////////////////
        // Uso di String.Format e place holder //
        /////////////////////////////////////////

        String risorsa = "Nome: %s, Cognome: %s";
        String finale = String.format(risorsa, "Paolo", "Rossi");
        LogUtil.debug("Messaggio: " + finale);

        // Place holder con numerali
        risorsa = "Cognome: %2$s, Nome: %1$s";
        finale = String.format(risorsa, "Paolo", "Rossi");
        LogUtil.debug("Messaggio: " + finale);

        // Utilizziamo una risorsa Android
        risorsa = getString(R.string.nome_cognome);
        finale = String.format(risorsa, "Giovanni", "Zorzetti");
        LogUtil.debug(finale);

        // Senza usare String.format
        finale = getString(R.string.cognome_nome, "Giovanni", "Zorzetti");
        LogUtil.debug(finale );


        // Formattazione di un numero
        String numero = getString(R.string.numero);
        Locale locale = getResources().getConfiguration().locale;
        String finaleNumero = String.format(locale, numero, 23.34);
        LogUtil.debug(finaleNumero);

        // Indichiamo il locale italiano
        finaleNumero = String.format(Locale.ITALIAN, numero, 23.34);
        LogUtil.debug(finaleNumero);


        ////////////////////////////////
        // Uso delle risorse array    //
        ////////////////////////////////

        // Array di stringhe
        String[] planets = getResources().getStringArray(R.array.pianeti_elenco);
        LogUtil.debug("Pianeta numero tre: " + planets[2]);


        ////////////////////////////////
        // Risorse stringa e plurale  //
        ////////////////////////////////
        String etichetta0  = getResources().getQuantityString(R.plurals.risultato_ricerca, 0, 0);
        LogUtil.debug("Plurale: " + etichetta0);
        // Perché in inglese o in italiano non ci sono differenze grammaticali tra 0 e più di uno

        String etichetta1  = getResources().getQuantityString(R.plurals.risultato_ricerca, 1);
        LogUtil.debug("Plurale: " + etichetta1);

        String etichetta7  = getResources().getQuantityString(R.plurals.risultato_ricerca, 7,7);
        LogUtil.debug("Plurale: " + etichetta7);

        //////////////////
        // Stili e HTML //
        //////////////////

        // Recupera la textfield
        TextView textView = (TextView) findViewById(R.id.testoHtmlModificata);
        //String testo = getString(R.string.stringa_html_modificata);
        //textView.setText(testo);

        String testo = getString(R.string.stringa_html_modificata);
        LogUtil.debug("Testo html modificata: " + testo);
        Spanned testoConStili = Html.fromHtml(testo);
        textView.setText(testoConStili);

        // Utilizzo di un oggetto Spannable
        TextView spannableText = (TextView) findViewById(R.id.spannableText);
        CharSequence text = SpanUtility.bold(SpanUtility.italic("Hello "), SpanUtility.color(Color.RED, "world"));
        spannableText.setText(text);

        ////////////////////////////////////////
        // Esempio di uso del tag annotazione //
        ////////////////////////////////////////

        // Recuper ala risorsa stringa usando il metodo getText e cast nell'oggetto SpannedString (oggetto con taf immutabili)
        SpannedString mioTesto = (SpannedString) getText(R.string.testo_con_annotazione);

        // Recupera gli oggetti "span" contenuti che devono essere di tipo Annotation
        Annotation[] annotations = mioTesto.getSpans(0, mioTesto.length(), Annotation.class);

        // Crea un oggetto modificabile
        SpannableString spannableString = new SpannableString(mioTesto);

        // Per ogni annotazione
        for(Annotation annotation:annotations){
            // Se ha l'attributo stile con valore importante
            if(annotation.getKey().equals("stile") && annotation.getValue().equals("importante")){

                // Recupera il colore
                int myColor = ResourcesCompat.getColor(getResources(),R.color.colorPrimary,null);

                // Crea lo span colore
                CharacterStyle spanColore = new ForegroundColorSpan(myColor);

                // Crea lo span bold
                CharacterStyle spanBold = new StyleSpan(Typeface.BOLD);

                spannableString.setSpan(spanColore, mioTesto.getSpanStart(annotation), mioTesto.getSpanEnd(annotation) , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(spanBold, mioTesto.getSpanStart(annotation), mioTesto.getSpanEnd(annotation), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        TextView testoViaAnnotazione = (TextView) findViewById(R.id.testoViaAnnotazione);
        testoViaAnnotazione.setText(spannableString);


    }
}
