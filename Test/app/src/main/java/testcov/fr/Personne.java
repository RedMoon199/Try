package testcov.fr;

import java.util.Random;

public class Personne {

    private boolean seek;
    private boolean isDead;
    private int seekAdvancement;
    // Risque de mort
    private int dieRate;
    //private boolean hasBeenSeek;
    private Random rdm = new Random();

    private static final boolean DEFAULT_DEATH = false;
    private static final int DEFAULT_SEEK_ADVANCEMENT = 0;

    // Nombre de jour au bout duquel un malade n'est plus malade.
    private static final int DEFAULT_STOP_SEEK = 15;

    public Personne( boolean seek, int dieRate )
    {
        this.seek = seek;
        this.isDead = this.DEFAULT_DEATH;
        this.seekAdvancement = this.DEFAULT_SEEK_ADVANCEMENT;
        this.dieRate = dieRate;
    }

    //constructeur par copie (est-ce qu'on en a besoin ?)
   /* public Personne(Personne p)
    {
        this.seek = p.seek;
        this.isDead = p.isDead;
        this.seekAdvancement = p.seekAdvancement;
        this.dieRate = p.dieRate;
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

    public void setSeek( boolean seek )
    {
        this.seek = seek;
    }
}