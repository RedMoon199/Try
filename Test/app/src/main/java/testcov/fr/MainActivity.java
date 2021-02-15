package testcov.fr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*RadioGroup rgAge;
    RadioButton rbAge;
    TextView txtAge;*/

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

    private Formulaire form;

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

            init(sAge, sMasque, sPlace, sContact, sDepistage, sIsoler);

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

        btnSubmit.setOnClickListener(btnCompareListener);
    }

    private void init(String age, String masque, String place, String contact, String depistage, String isoler)
    {
        form = new Formulaire(age, masque, place, contact, depistage, isoler);
    }
}