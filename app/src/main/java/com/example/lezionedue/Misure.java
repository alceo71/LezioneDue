package com.example.lezionedue;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.content.res.Resources;
        import android.os.Bundle;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;

public class Misure extends AppCompatActivity implements View.OnClickListener{

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misure);

        // Cambiamo la grandezza del bottone
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);




    }


    @Override
    public void onClick(View view) {

        ViewGroup.LayoutParams params = button.getLayoutParams();

        Log.d("Mio", "Misura larghezza " + params.width);

        // params.width = 100;
        // params.width = convertiDpInPixel(100,this);
        // params.width = ViewGroup.LayoutParams.WRAP_CONTENT;

        int larghezzaDim = (int) getResources().getDimension(R.dimen.larghezza_bottone);

        Log.d("Mio", "larghezzaDim " + larghezzaDim);

        params.width = larghezzaDim;

        button.setLayoutParams(params);
    }

    public static int convertiDpInPixel(int dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        Log.d("Mio", "metrics.densityDpi " + metrics.densityDpi);
        Log.d("Mio", "DisplayMetrics.DENSITY_DEFAULT " + DisplayMetrics.DENSITY_DEFAULT);

        int pixels = Math.round (dp * ((float) metrics.densityDpi / (float) DisplayMetrics.DENSITY_DEFAULT));
        return pixels;
    }

}
