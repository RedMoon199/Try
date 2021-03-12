/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcov.fr;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Ici l'environnement est une arrayList de personne
public class Environnement extends AppCompatActivity {

    Button nextDay; //Boutton pour passer le jour
    int jEpidemie, dureeSimulation;
    GridView myGridView; //Notre gridView
    PersonneGVAdapter adapter; //notre adapter
    ArrayList<Personne> listPersonne;
    private Personne[][] tab;
    private Personne[][] cpy;

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
        this.tab =  new Personne[this.TEST_NB_LIGNE][this.TEST_NB_COLONNE];
        this.cpy = new Personne[this.TEST_NB_LIGNE][this.TEST_NB_COLONNE];


        /*Tout d'abord on lance la création de X personne dans la fonction adéquat */
        ArrayList<Personne> listPersonne = new ArrayList<Personne>();
        ArrayList<Personne> lisPersonneCpy = new ArrayList<Personne>();
        listPersonne = createEnv();
        PersonneGVAdapter adapter = new PersonneGVAdapter(this, listPersonne);
        myGridView.setAdapter(adapter);

        this.nextDay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                jEpidemie = getQuelJour();
                dureeSimulation = getFinEpidemie();
                if (dureeSimulation > jEpidemie) {
                    oneDay();
                }
                else {
                    //startActivity(resultActivity);
                }
            }
        });
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
                this.tab[i][j] = new Personne(R.drawable.white,false, this.varTMPdieRate, i*10+j);
                this.cpy[i][j] = new Personne(R.drawable.white,false, this.varTMPdieRate, i*10+j);
                listPersonne.add(new Personne(R.drawable.white, false, this.varTMPdieRate, i*10+j));
            }
        }

        //les autres sont placé, 1 contaminé et X personne en plus
        int rndPosition = 0, nbPersonne = 0;
        while(nbPersonne < 20)
        {
            rndPosition = rdm.nextInt((this.getNbCase() - 0) + 1) + 0;
            if(listPersonne.get(rndPosition).isPersonne() == false)
            {
                if(nbPersonne == 0 ){
                    listPersonne.get(rndPosition).setImgId(R.drawable.red); // le premier est contaminé
                    listPersonne.get(rndPosition).setSeek(true);
                    tab[rndPosition/10][rndPosition % 10].setSeek(true);
                    tab[rndPosition/10][rndPosition % 10].setImgId(R.drawable.red);
                    cpy[rndPosition/10][rndPosition % 10].setSeek(true);
                    cpy[rndPosition/10][rndPosition % 10].setImgId(R.drawable.red);
                }

                else {
                    listPersonne.get(rndPosition).setImgId(R.drawable.green); // les autres sont
                    tab[rndPosition/10][rndPosition % 10].setImgId(R.drawable.green);
                    cpy[rndPosition/10][rndPosition % 10].setImgId(R.drawable.green);
                }
                nbPersonne++;
            }
        }
        return listPersonne;
    }

    // fct qui permet de déplacer une seule personne
    private void deplacement(int i, int j)
    {
        if(this.cpy[i][j] != null) // Si la case est occupée par une personne
        {
            if(!this.cpy[i][j].getIsDead()) // Si la personne n'est pas morte
            {
                // Détermination du prochain mouvement
                int dx = this.rdm.nextInt(2);
                int dy = this.rdm.nextInt(2);
                int x, y;
                if((dx == 0) && (j > 0))
                {
                    x = -1;
                }
                else if(j < (this.TEST_NB_LIGNE - 1))
                {
                    x = 1;
                }
                else
                {
                    x = 0;
                }

                if((dy == 0) && (x > 0))
                {
                    y = -1;
                }
                else if(x < ((this.TEST_NB_COLONNE) - 1))
                {
                    y = 1;
                }
                else
                {
                    y = 0;
                }

                // Si la personne n'est pas obligée de rester sur la même case (ex : cas des coins)
                if((x != 0) || (y != 0))
                {
                    if(this.tab[i + x][j + y].isPersonne() == false)
                    {
                        this.tab[i + x][j + y] =/* new Personne(*/this.cpy[i][j]/*)*/; // Est-ce qu'on a besoin d'un constructeur par copie ?
                        this.tab[i][j].setImgId(R.drawable.white);
                    }
                }
                //Sinon on bouge pas

            }
            //Sinon on bouge pas
        }
        //Sinon on bouge pas
    }

    // Renseigne la liste des personnes rencontrés par une personne malade.
    private void updateListMeet(int i, int j)
    {
        if(this.tab[i][j] != null)
        {
            // Si la personne est malade, on va chercher les personnes saines autour d'elle et les ajouter à sa liste de rencontre.
            if(this.tab[i][j].getSeek())
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
                            this.tab[i][j].haveMeet(this.tab[k][l]);
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
                if(this.tab[i][j].isPersonne() == true)
                {
                    if(this.tab[i][j].getId() == id)
                        this.tab[i][j].setSeek(true);
                }
            }
        }
    }

    // La personne ciblée infecte les personnes de sa liste selon le R0 puis remet sa liste à 0
    // De plus elle met à jour sa maladie
    private void endTurnInfectPeople(int x, int y)
    {
        if(this.tab[x][y] != null)
        {
            if(this.tab[x][y].getSeek() && (!this.tab[x][y].getIsDead()))
            {
                int R0 = this.form.getR0();
                int size = this.tab[x][y].getNbMeet();
                int newInfection, idNewInfected;
                if(R0 > size)
                    newInfection = size;
                else
                    newInfection = R0;

                while(newInfection != 0)
                {
                    idNewInfected = this.tab[x][y].getIdRencontre(newInfection);
                    searchAndInfect(idNewInfected);
                    newInfection -= 1;
                }

                this.tab[x][y].resetMeet();
                this.tab[x][y].updateSeek();
            }
        }
    }

    //une copie de tab a essayer de copie array list et de la mettre dans l'adaptater
    private void majTab()
    {
        adapter.clear(); //supprime toute
        for(int i = 0; i < this.TEST_NB_LIGNE; i++)
        {
            for(int j = 0; j < this.TEST_NB_COLONNE; j++)
            {
                adapter.insert(tab[i][j], i*10+j); //remet tous
                this.cpy[i][j] = this.tab[i][j]; //remplace dans la cpy
            }
        }
        adapter.notifyDataSetChanged();
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
                    deplacement(i, j);
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
                majTab(); //on met a jour
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
                majTab();
            }
        }
        this.quelJour += 1;
    }

    //nombre de personne
    public int getNbCase()
    {
        return ((this.TEST_NB_LIGNE-1) * 10 + this.TEST_NB_COLONNE - 1);
    }

    // Renvoie à quel jour de l'épidémie on est
    public int getQuelJour()
    {
        return this.quelJour;
    }

    //retourne la date de fin de l'épidémie
    public int getFinEpidemie()
    {
        return this.form.getNbJour();
    }

}