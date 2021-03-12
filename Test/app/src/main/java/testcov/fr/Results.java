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
    private int age;
    private int masque;
    private int environment;
    private int contact;
    private boolean test;
    private boolean isolation;

    //récupération des informations à la suite de la propagation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environnement);

        Intent intent = getIntent();
        if(intent != null)
        {
            if(intent.hasExtra("nbPersonnes")){
                this.nbPersonnes = intent.getIntExtra("nbPersonnes",0 );
                TextView textNbPersonnes = (TextView) findViewById(R.id.nbPersonnes);
                textNbPersonnes.setText("Sur " + this.nbPersonnes + " Il y a eu :");

            }
            if(intent.hasExtra("nbMort")){
                this.nbMort = intent.getIntExtra("nbMort",0 );
                TextView textNbMort = (TextView) findViewById(R.id.nbMortRes);
                textNbMort.setText("- " +this. nbMort + "Mort");
            }
            if(intent.hasExtra("nbContamine")){
                this.nbContamine = intent.getIntExtra("nbContamine",0 );
                TextView textNbContamine = (TextView) findViewById(R.id.nbContamine);
                textNbContamine.setText("- " + this.nbContamine + " personnes Contiminé(s)");
            }
            if(intent.hasExtra("nbSain")){
                this.nbSain = intent.getIntExtra("nbSain",0 );
                TextView textNbSain = (TextView) findViewById(R.id.nbSain);
                textNbSain.setText("- " + this.nbSain+ " personne(s) encore saine(s)");
            }

            if(intent.hasExtra("age")){
                this.age = intent.getIntExtra("age",0 );
                TextView textNbSain = (TextView) findViewById(R.id.age);
                textNbSain.setText("- La population était agé de " + this.age);
            }
            if(intent.hasExtra("masque")){
                this.masque= intent.getIntExtra("masque",0 );
                TextView textNbSain = (TextView) findViewById(R.id.masque);
                textNbSain.setText("- Le port du masque était : " + this.masque);
            }
            if(intent.hasExtra("environment")){
                this.environment = intent.getIntExtra("environment",0 );
                TextView textNbSain = (TextView) findViewById(R.id.environment);
                textNbSain.setText("- La popuilation se trouvais dans un environement " + this.environment);
            }
            if(intent.hasExtra("contact")){
                this.contact = intent.getIntExtra("contact",0 );
                TextView textNbSain = (TextView) findViewById(R.id.contact);
                textNbSain.setText("- Les contact était : " + this.contact);
            }
            if(intent.hasExtra("test")){
                this.test = intent.getBooleanExtra("test", false);
                TextView textNbSain = (TextView) findViewById(R.id.test);
                textNbSain.setText("- Il y avais des test d'éffectué :" + boolStr(this.test));
            }
            if(intent.hasExtra("isolation")){
                this.isolation = intent.getBooleanExtra("isolation",false );
                TextView textNbSain = (TextView) findViewById(R.id.isolation);
                textNbSain.setText("- Il y a eu un confinement : " + boolStr(this.isolation));
            }
        }
    }

    public String boolStr(boolean b){
        if(b){
            return "Oui";
        }else
            return "Non";
    }

}