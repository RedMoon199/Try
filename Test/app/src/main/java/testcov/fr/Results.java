package testcov.fr;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Results extends AppCompatActivity {

    private int nbPersonnes;
    private int nbMort;
    private int nbContamine;
    private int nbSain;

    //récupération des informations à la suite de la propagation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout./*activité_correpsondant au développement du virus*/);

        Intent intent = getIntent();
        if(intent != null)
        {
            if(intent.hasExtra("nbPersonnes")){
                nbPersonnes = intent.getIntExtra("nbPersonnes",0 );
            }
            if(intent.hasExtra("nbMort")){
                nbMort = intent.getIntExtra("nbMort",0 );
            }
            if(intent.hasExtra("nbContamine")){
                nbContamine = intent.getIntExtra("nbContamine",0 );
            }
            if(intent.hasExtra("nbSain")){
                nbSain = intent.getIntExtra("nbSain",0 );
            }
        }
    }
}
