package testcov.fr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Propagation extends AppCompatActivity {

    private Formulaire form;

    private String sAge = "";
    private String sMasque = "";
    private String sPlace = "";
    private String sContact = "";
    private String sDepistage = "";
    private String sIsoler = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propagation);

        // On récupère les infos du formulaire.
        Intent intent = getIntent();
        if(intent != null)
        {
            if(intent.hasExtra("Age"))
            {
                sAge = intent.getStringExtra("Age");
            }
            if(intent.hasExtra("Masque"))
            {
                sMasque = intent.getStringExtra("Masque");
            }
            if(intent.hasExtra("Place"))
            {
                sPlace = intent.getStringExtra("Place");
            }
            if(intent.hasExtra("Contact"))
            {
                sContact = intent.getStringExtra("Contact");
            }
            if(intent.hasExtra("Depistage"))
            {
                sDepistage = intent.getStringExtra("Depistage");
            }
            if(intent.hasExtra("Isoler"))
            {
                sIsoler = intent.getStringExtra("Isoler");
            }

            // Si on a bien toutes les infos, on créé le formulaire.
            if(sAge != "" && sMasque != "" && sPlace != "" && sContact != "" && sDepistage != "" && sIsoler != "")
            {
                form = new Formulaire(sAge, sMasque, sPlace, sContact, sDepistage, sIsoler);
            }
        }

    }
}