package testcov.fr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
        setContentView(R.layout.activity_propagation/*activité_correpsondant au développement du virus*/);
        il faut modifié l'activbité qui va envoyé les infos '

        Intent intent = getIntent();
        if(intent != null)
        {
            if(intent.hasExtra("nbPersonnes")){
                nbPersonnes = intent.getIntExtra("nbPersonnes",0 );
                TextView textNbPersonnes = (TextView) findViewById(R.id.nbPersonnes);
                textNbPersonnes.setText("Sur " + nbPersonnes + " Il y a eu :");

            }
            if(intent.hasExtra("nbMort")){
                nbMort = intent.getIntExtra("nbMort",0 );
                TextView textNbMort = (TextView) findViewById(R.id.nbMortRes);
                textNbMort.setText("- " + nbMort + "Mort");
            }
            if(intent.hasExtra("nbContamine")){
                nbContamine = intent.getIntExtra("nbContamine",0 );
                TextView textNbContamine = (TextView) findViewById(R.id.nbContamine);
                textNbContamine.setText("- " + nbContamine + " personnes Contiminé(s)");
            }
            if(intent.hasExtra("nbSain")){
                nbSain = intent.getIntExtra("nbSain",0 );
                TextView textNbSain = (TextView) findViewById(R.id.nbSain);
                textNbSain.setText("- " + nbSain+ " personne(s) encore saine(s)");
            }
        }
    }


}
