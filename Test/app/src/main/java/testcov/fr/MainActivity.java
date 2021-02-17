package testcov.fr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit;

    private RadioGroup rgAge;
    private RadioGroup rgMasque;
    private RadioGroup rgPlace;
    private RadioGroup rgContact;
    private RadioGroup rgDepistage;
    private RadioGroup rgIsoler;

    private String sAge;
    private String sMasque;
    private String sPlace;
    private String sContact;
    private String sDepistage;
    private String sIsoler;

    private View.OnClickListener btnCompareListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Log.i( "DEBUG", "Bouton clique" );
            int idBtnAge = rgAge.getCheckedRadioButtonId();
            int idBtnMasque = rgMasque.getCheckedRadioButtonId();
            int idBtnPlace = rgPlace.getCheckedRadioButtonId();
            int idBtnContact = rgContact.getCheckedRadioButtonId();
            int idBtnDepistage = rgDepistage.getCheckedRadioButtonId();
            int idBtnIsoler = rgIsoler.getCheckedRadioButtonId();

            RadioButton rbAge = findViewById(idBtnAge);
            RadioButton rbMasque = findViewById(idBtnMasque);
            RadioButton rbPlace = findViewById(idBtnPlace);
            RadioButton rbContact = findViewById(idBtnContact);
            RadioButton rbDepistage = findViewById(idBtnDepistage);
            RadioButton rbIsoler = findViewById(idBtnIsoler);

            sAge = (String) rbAge.getText();
            sMasque = (String) rbMasque.getText();
            sPlace = (String) rbPlace.getText();
            sContact = (String) rbContact.getText();
            sDepistage = (String) rbDepistage.getText();
            sIsoler = (String) rbIsoler.getText();

            // Création de la nouvelle activité
            Intent propagationActivity = new Intent(getApplicationContext(), Propagation.class);

            // On passe les infos récupéré des boutons à la nouvelle activité.
            propagationActivity.putExtra("Age", sAge);
            propagationActivity.putExtra("Masque", sMasque);
            propagationActivity.putExtra("Place", sPlace);
            propagationActivity.putExtra("Contact", sContact);
            propagationActivity.putExtra("Depistage", sDepistage);
            propagationActivity.putExtra("Isoler", sIsoler);

            // On démare la nouvelle activité
            startActivity(propagationActivity);

            // On met fin à cette activité sinon on a une erreur.
            finish();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgAge = (RadioGroup) findViewById(R.id.rgAge);
        rgMasque = (RadioGroup) findViewById(R.id.rgMasque);
        rgPlace = (RadioGroup) findViewById(R.id.rgPlace);
        rgContact = (RadioGroup) findViewById(R.id.rgContact);
        rgDepistage = (RadioGroup) findViewById(R.id.rgDepistage);
        rgIsoler = (RadioGroup) findViewById(R.id.rgIsoler);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(btnCompareListener);
    }
}