/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcov.fr;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Ici l'environnement est une arrayList de personne
public class Environnement extends AppCompatActivity {

    Button nextDay; //Boutton pour passer le jour
    GridView myGridView; //Notre gridView
    PersonneGVAdapter adapter; //notre adapter
    ArrayList<Personne> listPersonne;

    PersonneGVAdapter adapterCpy; // la copy
    ArrayList<Personne> listPersonneCpy; //la copy

    /*A détailler */
    private String sAge = "";
    private String sMasque = "";
    private String sPlace = "";
    private String sContact = "";
    private String sDepistage = "";
    private String sIsoler = "";
    private String sNbJ = "";

    /*A détailler */
    private Random rdm = new Random();
    private static final int TEST_NB_COLONNE = 10;
    private static final int TEST_NB_LIGNE = 20;
    private static final int varTMPdieRate = 10;
    private static int quelJour = 0;
    //var possiblement temporaire
    private int nbPersone;

    Formulaire form; //Le formulaire

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environnement);

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
                this.form = new Formulaire(sAge, sMasque, sPlace, sContact, sDepistage, sIsoler, sNbJ);
            }
        }

        myGridView = findViewById(R.id.gv);
        this.nextDay = (Button) findViewById(R.id.btnNextDay);

        /*Tout d'abord on lance la création de X personne dans la fonction adéquat */
        ArrayList<Personne> listPersonne = new ArrayList<Personne>();
        listPersonne = createEnv();
        PersonneGVAdapter adapter = new PersonneGVAdapter(this, listPersonne);
        myGridView.setAdapter(adapter);

        /*Personne cpyPersonne = adapter.getItem(1);
        adapter.remove(adapter.getItem(1));
        cpyPersonne.setImgId(R.drawable.black);
        adapter.insert(cpyPersonne,1);
        //adapter.notifyDataSetChanged();
        //adapter.notifyDataSetChanged();
        */deplacement(11);
        /*this.nextDay.setOnClickListener(
                event ->{
                    //??Log.i( "DEBUG", "Bouton next day clique" );
                    int jEpidemie =  this.getQuelJour();
                    int dureeSimulation = this.getFinEpidemie();
                    if(dureeSimulation > jEpidemie)
                    {
                        this.oneDay();
                    }
                    else
                    {
                        // renvoie vers explication.
                    }
                }
        );*/
    }

    //place les personnes blanches
    public ArrayList<Personne> createEnv()
    {
        ArrayList<Personne> listPersonne = new ArrayList<Personne>();

        //On créé un compteur pour initialiser les id des personnes
        int countId = 0;

        //place X*Y personne blanche
        for(int i = 0; i < this.TEST_NB_LIGNE; i++)
        {
            for (int j = 0; j < this.TEST_NB_COLONNE; j++)
            {
                listPersonne.add(new Personne(R.drawable.white, false, this.varTMPdieRate, i*10+j));
            }
        }

        //les autres sont placé, 1 contaminé et X personne en plus
        int rndPosition = 0, nbPersonne = 0;
        while(nbPersonne < 20)
        {
            rndPosition = rdm.nextInt((this.getNbCase() - 0) + 1) + 0;
            //(int)(Math.random() * (float)(this.getNbCase()));
            if(listPersonne.get(rndPosition).isPersonne() == false)
            {
                if(nbPersonne == 0 )
                    listPersonne.get(rndPosition).setImgId(R.drawable.red); // le premier est contaminé
                else
                    listPersonne.get(rndPosition).setImgId(R.drawable.green); // les autres sont
                nbPersonne++;
            }
        }
        listPersonne.get(11).setImgId(R.drawable.green);
        return listPersonne;
    }

    // fct qui permet de déplacer une seule personne
    private void deplacement(int pos)
    {
        int dx,dy, go;
        if(adapter.getItem(pos).isPersonne()) // Si la case est occupée par une personne
        {
            if(adapter.getItem(pos).getIsDead()) // Si la personne n'est pas morte
            {

                // Détermination du prochain mouvement
                /*do {
                    dx = 1;//rdm.nextInt(1); //random entre 0 et 1
                    dy = 1;//rdm.nextInt(1); //random entre 0 et 1
                    go = getAvailableMove(dx, dy, pos);
                }while(go == -1);*/
                go = 1;
                Personne cpyPersonne1 = adapter.getItem(pos);
                Personne cpyPersonne2 = adapter.getItem((pos+go));
                adapter.remove(adapter.getItem(pos));
                adapter.insert(cpyPersonne2, pos);
                adapter.notifyDataSetChanged();
                adapter.remove(adapter.getItem((pos+go)));
                adapter.insert(cpyPersonne1, (pos+go));
                adapter.notifyDataSetChanged();
                //Sinon on bouge pas

            }
            //Sinon on bouge pas
        }
        //Sinon on bouge pas
    }



    //retourne vrai si la personne peux bouger ou il veut
    public int getAvailableMove(int dx, int dy, int pos)
    {
        int go = -1;
        //premier cas dx = 0 et dy = 0 alors on monte
        if((dx == 0) && (dy == 0))
        {
            if(pos -10 > 0)
                go = pos -10;
        }
        //deuxieme cas dx = 0 et dy = 1 alors a droite
        else if((dx == 0) && (dy == 1))
        {
            if(pos +1 > (this.TEST_NB_LIGNE*10 + TEST_NB_LIGNE))
                go = pos +1;
        }
        //troisieme cas dx = 1 et dy = 0 alors a gauche
        else if((dx == 1) && (dy == 0))
        {
            if(pos -1 > 0)
                go = pos +1;
        }
        //troisieme cas dx = 1 et dy = 1 alors en bas
        else
        {
            if(pos +10 > (this.TEST_NB_LIGNE*10 + TEST_NB_LIGNE))
                go = pos +1;
        }

        //si une personne déja la
        if (go != -1)
            if(adapter.getItem((pos + go)).isPersonne() == true)
                go = -1;
        return go;
    }

    /*
    //une copie de tab a essayer de copie array list et de la mettre dans l'adaptater
    private void majTab()
    {
        for(int i = 0; i < this.TEST_NB_LIGNE; i++)
        {
            for(int j = 0; j < this.TEST_NB_COLONNE; j++)
            {
                if(this.tab[i][j] != null)
                {
                    this.cpy[i][j] = this.tab[i][j]; // Constructeur par copie?
                }
                else
                {
                    this.cpy[i][j] = null;
                }
            }
        }
    }*/



    // Renseigne la liste des personnes rencontrés par une personne malade.
    private void updateListMeet(int i, int j)
    {
        if(adapter.getItem(i*10+j).isPersonne()) //si une personne
        {
            // Si la personne est malade, on va chercher les personnes saines autour d'elle et les ajouter à sa liste de rencontre.
            if(adapter.getItem(i*10+j).getSeek())
            {
                int x, toX, y, toY;
                if(i > 0)
                    x = i - 1;
                else
                    x = i;
                if(i < (this.TEST_NB_LIGNE - 1))
                    toX = i + 1;
                else
                    toX = i;
                if(j > 0)
                    y = j - 1;
                else
                    y = j;
                if(j < (this.TEST_NB_COLONNE - 1))
                    toY = j + 1;
                else
                    toY = j;

                for(int k = x; k <= toX; k++)
                {
                    for(int l = y; l <= toY; l++)
                    {
                        if((k != i) && (l != j))
                        {
                            adapter.getItem((i*10)+j).haveMeet(adapter.getItem((k*10)+l)); //a voir si on prend l'id = int position dans la liste
                        }
                    }
                }
            }
        }
    }


    // Recherche une personne par son identifiant et l'infecte
    private void searchAndInfect(int id)
    {
        for(int i = 0; i < this.TEST_NB_LIGNE; i++)
        {
            for(int j = 0; j < this.TEST_NB_COLONNE; j++)
            {
                if(adapter.getItem(i*10+j).isPersonne()) //si c'est une personne
                {
                    if(adapter.getPosition(adapter.getItem(i*10+j))== id) // a voir idem pour l'id
                        adapter.getItem(i*10+j).setSeek(true);
                }
            }
        }
    }


    // La personne ciblée infecte les personnes de sa liste selon le R0 puis remet sa liste à 0
    // De plus elle met à jour sa maladie
    private void endTurnInfectPeople(int x, int y)
    {
        if(adapter.getItem(x*10+y).isPersonne()) // si c'est une personne
        {
            if(adapter.getItem(x*10+y).getSeek() && (!adapter.getItem(x*10+y).getIsDead())) // si malade et non mort
            {
                int R0 = this.form.getR0();
                int size = adapter.getItem(x*10+y).getNbMeet();
                int newInfection, idNewInfected;
                if(R0 > size)
                    newInfection = size;
                else
                    newInfection = R0;

                while(newInfection != 0)
                {
                    idNewInfected = adapter.getPosition(adapter.getItem(x * 10 + y)); //this.tab[x][y].getIdRencontre(newInfection)
                    searchAndInfect(idNewInfected);
                    newInfection -= 1;
                }

                adapter.getItem(x*10+y).resetMeet();
                adapter.getItem(x*10+y).updateSeek();
            }
        }
    }

    //Fonction effectue les actions d'une journée
    public void oneDay()
    {
        int nbDeplacement = this.form.getContact();
        for(int x = 0; x < nbDeplacement; x++)
        {
            for(int i = 0; i < this.TEST_NB_LIGNE; i++)
            {
                for(int j = 0; j < this.TEST_NB_COLONNE; j++)
                {
                    //deplacement(i, j);
                }
            }
            for(int i = 0; i < this.TEST_NB_LIGNE; i++)
            {
                for(int j = 0; j < this.TEST_NB_COLONNE; j++)
                {
                    updateListMeet(i, j);
                }
            }

            if(x != (nbDeplacement - 1))
            {
                //majTab();
            }
            else
            {
                for(int i = 0; i < this.TEST_NB_LIGNE; i++)
                {
                    for(int j = 0; j < this.TEST_NB_COLONNE; j++)
                    {
                        endTurnInfectPeople(i, j);
                    }
                }
                //majTab();
            }
        }
        this.quelJour += 1;
    }

    //nombre de personne
    public int getNbCase()
    {
        return (this.TEST_NB_LIGNE * 20 + this.TEST_NB_COLONNE);
    }

    // Renvoie à quel jour de l'épidémie on est
    public int getQuelJour()
    {
        return this.getQuelJour();
    }

    //retourne la date de fin de l'épidémie
    public int getFinEpidemie()
    {
        return this.form.getNbJour();
    }

}