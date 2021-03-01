package testcov.fr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;

public class Propagation extends AppCompatActivity {

    GridView gv;
    // A voir TableLayout

    Integer[] image;

    Button nextDay;

    private Environnement env;

    private String sAge = "";
    private String sMasque = "";
    private String sPlace = "";
    private String sContact = "";
    private String sDepistage = "";
    private String sIsoler = "";
    private String sNbJ = "";

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
            if(intent.hasExtra("nbJour"))
            {
                sNbJ = intent.getStringExtra("NbJour");
            }

            // Si on a bien toutes les infos, on créé le formulaire.
            if(sAge != "" && sMasque != "" && sPlace != "" && sContact != "" && sDepistage != "" && sIsoler != "" && sNbJ != null)
            {
                env = new Environnement(sAge, sMasque, sPlace, sContact, sDepistage, sIsoler, sNbJ);
            }

            this.image = new Integer[this.env.getNbCase()];
            this.replace(this.env.getTab());

            this.gv = (GridView) findViewById(R.id.gv);
            this.gv.setAdapter(new ImageAdapterGridView(this));

            this.nextDay = (Button) findViewById(R.id.btnNextDay);
            this.nextDay.setOnClickListener(
                    event -> {
                        Log.i( "DEBUG", "Bouton next day clique" );
                        int jEpidemie = this.env.getQuelJour();
                        int dureeSimulation = this.env.getFinEpidemie();
                        if(dureeSimulation > jEpidemie)
                        {
                            this.env.oneDay();
                            this.replace(this.env.getTab());
                            this.gv.removeAllViewsInLayout();
                            this.gv.setAdapter(new ImageAdapterGridView(this));
                        }
                        else
                        {
                            // renvoie vers explication.
                        }
                    }
            );
        }
    }

    private class ImageAdapterGridView extends BaseAdapter{

        private Context mContext;

        public ImageAdapterGridView(Context context)
        {
            mContext = context;
        }

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            if(convertView == null)
            {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(250, 250));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(16, 16, 16, 16);
            }
            else
            {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(image[position]);
            return imageView;
        }
    }

    private void replace(Integer image[])
    {
        if(image.length != this.image.length)
        {
            finish();
        }
        else
        {
            int length = this.image.length;
            for(int i = 0; i < length; i++)
            {
                this.image[i] = image[i];
            }
        }
    }
}