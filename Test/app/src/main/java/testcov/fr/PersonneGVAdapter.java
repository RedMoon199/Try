package testcov.fr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

import testcov.fr.R;

public class PersonneGVAdapter extends ArrayAdapter<Personne> {
    public PersonneGVAdapter(@NonNull Context context, ArrayList<Personne> listPersonne) {
        super(context, 0, listPersonne);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.personne, parent, false);
        }
        Personne personne = getItem(position);
        ImageView personneView = listitemView.findViewById(R.id.personneView);
        personneView.setImageResource(personne.getImgId());
        return listitemView;
    }

}
