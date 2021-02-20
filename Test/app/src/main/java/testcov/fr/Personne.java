package testcov.fr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Personne {

    private boolean seek;
    private boolean isDead;
    private int seekAdvancement;
    // Risque de mort
    private int dieRate;
    //private boolean hasBeenSeek;
    private Random rdm = new Random();
    private int id;
    private List<Integer> rencontre;

    private static final boolean DEFAULT_DEATH = false;
    private static final int DEFAULT_SEEK_ADVANCEMENT = 0;

    // Nombre de jour au bout duquel un malade n'est plus malade.
    private static final int DEFAULT_STOP_SEEK = 15;

    public Personne( boolean seek, int dieRate, int id )
    {
        this.seek = seek;
        this.isDead = this.DEFAULT_DEATH;
        this.seekAdvancement = this.DEFAULT_SEEK_ADVANCEMENT;
        this.dieRate = dieRate;
        this.id = id;
        rencontre = new ArrayList<Integer>();
    }

    //constructeur par copie (est-ce qu'on en a besoin ?)
   /* public Personne(Personne p)
    {
        this.seek = p.seek;
        this.isDead = p.isDead;
        this.seekAdvancement = p.seekAdvancement;
        this.dieRate = p.dieRate;
        this.id = p.id;
        int size = p.rencontre.size();
        for(int i = 0; i < size; i++)
        {
            this.rencontre.add(p.rencontre.get(i); // Est-ce que les indices commencent bien à 0 ?
        }
    }*/

    // Met à jour la maladie de chacun
    public void updateSeek()
    {
        if(this.getSeek())
        {
            if(getSeekAdvancement() == this.DEFAULT_STOP_SEEK)
            {
                this.setSeek(false);
                this.seekAdvancement = 0;
            }
            else
            {
                int mortality = rdm.nextInt(101 );
                if(mortality < this.dieRate)
                {
                    this.setSeek(false);
                    this.isDead = true;
                }
                else
                {
                    this.seekAdvancement += 1;
                }
            }
        }
    }

    // Permet d'ajouter une personne à la liste des personnes rencontrées, si elle n'est pas déjà dans la liste et si elle n'est pas malade
    public void haveMeet(Personne p)
    {
        // Si la personne n'est pas malade et pas morte
        if((!p.getSeek()) && (!p.getIsDead()))
        {
            int id = p.getId();
            int size = this.rencontre.size();
            boolean dejaVu = false;
            for(int i = 0; i < size; i++)
            {
                if(this.rencontre.get(i) == id)
                {
                    dejaVu = true;
                }
            }

            // Si pas déjà Vu, on ajoute à la liste.
            if(!dejaVu)
            {
                this.rencontre.add(id);
            }
        }
    }

    public void resetMeet()
    {
        this.rencontre.clear();
    }

    public boolean getSeek()
    {
        return this.seek;
    }

    public int getSeekAdvancement()
    {
        return this.seekAdvancement;
    }

    public boolean getIsDead()
    {
        return this.isDead;
    }

    public int getId() { return this.id; }

    // Permet de connaître le nombre de rencontre de la personne dans la journée
    public int getNbMeet()
    {
        return this.rencontre.size();
    }

    // Permet de connaître l'id de la personne à l'incice tant dans la liste des personnes rencontrées
    public int getIdRencontre(int indice)
    {
        int realIndice = indice - 1;
        return this.rencontre.get(realIndice);
    }

    public Color getImage()
    {
        if(this.getIsDead())
        {
            return Color.BLACK;
        }
        else
        {
            if(this.getSeek())
            {
                return Color.RED;
            }
            else
            {
                return Color.GREEN;
            }
        }
    }


    public void setSeek( boolean seek )
    {
        this.seek = seek;
    }
}