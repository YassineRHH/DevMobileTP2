package com.example.rahmouni_yassine_mesure_glycmie;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    private Button btnConsulter;
    private SeekBar sbAge;
    private RadioButton rboui,rbnon;
    private EditText etValeur;
    private TextView textAge;
    private TextView msg;
    private int age = 0;
    private void init() {
        btnConsulter=findViewById((R.id.btnConsulter));
        sbAge=findViewById(R.id.sbAge);
        rboui=findViewById(R.id.rbtoui);
        rbnon=findViewById(R.id.rbtnon);
        etValeur=findViewById(R.id.etValeur);
        msg=findViewById(R.id.msg);
        textAge=findViewById(R.id.textAge) ;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * Notification that the progress level has changed. Clients can use the fromUser parameter
             * to distinguish user-initiated changes from those that occurred programmatically.
             *
             * @param seekBar  The SeekBar whose progress has changed
             * @param progress The current progress level. This will be in the range min..max where min
             *                 and max were set by {@link ProgressBar#setMin(int)} and
             *                 {@link ProgressBar#setMax(int)}, respectively. (The default values for
             *                 min is 0 and max is 100.)
             * @param fromUser True if the progress change was initiated by the user.
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "OnProgressChanged" + progress);
                textAge.setText("Votre age:" + progress + "ans");
            }

            /**
             * Notification that the user has started a touch gesture. Clients may want to use this
             * to disable advancing the seekbar.
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /**
             * Notification that the user has finished a touch gesture. Clients may want to use this
             * to re-enable advancing the seekbar.
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulterGlycemie();
            }
        });}
        private void consulterGlycemie() {
            String valeurString = etValeur.getText().toString().trim();
            if (valeurString.isEmpty()) {
                Toast.makeText(this, "Veuller entrer la valeur mesure ", Toast.LENGTH_SHORT);
                return;
            }
            double valeur = Double.parseDouble(valeurString);
            boolean aJeune = rboui.isChecked();
            String resultat;
            double min, max;
            if (aJeune) {
                if (age > 13) {
                    min = 5.0;
                    max = 7.2;
                } else if (age >= 6) {
                    min = 5.0;
                    max = 10.0;
                } else {
                    min = 5.5;
                    max = 10.0;
                }
            } else {
                min = 0.0;
                max = 10.5;
            }
            if (valeur < min) {
                resultat = "Le niveau de glycemie est trop bas";
            } else if (valeur > max) {
                resultat = "Le niveau de glycemie est trop elevé";
            } else {
                resultat = "Le niveau de glycemie est normal ";
            }
            msg.setText(resultat);

    }
}






