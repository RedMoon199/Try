/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcov.fr;

import java.util.Random;

public class Environnement {

    private Formulaire form;
    private Personne[][] tab;
    private Personne[][] cpy;
    private Random rdm = new Random();
    private static final int TEST_NB_COLONNE = 10;
    private static final int TEST_NB_LIGNE = 20;
    private static final int varTMPdieRate = 10;
    //var possiblement temporaire
    private int nbPersone;

    public Environnement(String sAge, String sMasque, String sPlace, String sContact, String sDepistage, String sIsoler)
    {
        this.form = new Formulaire(sAge, sMasque, sPlace, sContact, sDepistage, sIsoler);
        this.tab =  new Personne[this.TEST_NB_LIGNE][this.TEST_NB_COLONNE];
        this.cpy = new Personne[this.TEST_NB_LIGNE][this.TEST_NB_COLONNE];

        //On créé un compteur pour initialiser les id des personnes
        int countId = 1;

        for(int i = 0; i < this.TEST_NB_LIGNE; i++)
        {
            for(int j = 0; j < this.TEST_NB_COLONNE; j++)
            {
                if((i % 2 == 1) && (j % 2 == 1))
                {
                    this.tab[i][j] = new Personne(false, this.varTMPdieRate, countId);
                    this.cpy[i][j] = new Personne(false, this.varTMPdieRate, countId);
                    countId += 1;
                }
                else
                {
                    this.tab[i][j] = null;
                    this.cpy[i][j] = null;
                }
            }
        }

        this.nbPersone = this.getNbPersonne();

        // Ici, on va prendre une personne aléatoire et lui donner le virus.
        int pInfected = rdm.nextInt(this.nbPersone) + 1;
        int personnePasse = 0;
        for(int i = 0; i < this.TEST_NB_LIGNE; i++)
        {
            for(int j = 0; j < this.TEST_NB_COLONNE; j++)
            {
                if(this.tab[i][j] != null)
                {
                    personnePasse += 1;
                    if(personnePasse == pInfected)
                    {
                        tab[i][j].setSeek(true);
                        cpy[i][j].setSeek(true);
                    }
                }
            }
        }

    }

    //fct possiblement temporaire
    private int getNbPersonne()
    {
        int count = 0;
        for(int i = 0; i < TEST_NB_LIGNE; i++)
        {
            for(int j = 0; j < TEST_NB_COLONNE; j++)
            {
                if(this.tab[i][j] != null)
                {
                    count += 1;
                }
            }
        }

        return count;
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
                    if(this.tab[i + x][j + y] == null)
                    {
                        this.tab[i + x][j + y] =/* new Personne(*/this.cpy[i][j]/*)*/; // Est-ce qu'on a besoin d'un constructeur par copie ?
                        this.tab[i][j] = null;
                    }
                }
                //Sinon on bouge pas

            }
            //Sinon on bouge pas
        }
        //Sinon on bouge pas
    }

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
                if(this.tab[i][j] != null)
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

}

/*public class Environnement extends TilePane{
    Personne[][] tab;
    Personne[][] copie;
    Stat data;
    float tauxContamination;
    
    public Environnement(Formulaire f)
    {
        Stat data = new Stat(f.nbPersonne);
		
    }
    
	//effectué l'ensemble des actions d'un jour
    public void tour(int jour)
    {
		majTab();
		contamination();
		majStat();
    }
    
    //deplacement de 1 personne
    public void deplacement(int x, int y)
    {
		bool cango = false;
		int rnd;
		
		while(canGo == false)
		{
			rnd = Math.random() * 3; //un chiffre entre 1 et 3
			if(rnd == 0 && (y - 1) < tab.tailleMinY) //gauche
			{
				copie[i][j-1] = tab[x][y];
				cango = true;
			}
			else if(rnd == 1 && (x - 1) > tab.tailleMinX) //haut
			{
				copie[i-1][j] = tab[x][y];
				cango = true;
			}
			else if(rnd == 2 && (y + 1) < tab.tailleMaxY) //droite
			{
				copie[i][j+1] = tab[x][y];
				cango = true;
			}
			else if(rnd == 3 && (x + 1) < tab.tailleMaxX)//bas
			{
				copie[i+1][j] = tab[x][y];
				cango = true;
			}
		}
	}
    
    //copie du tableau de base qui effectué les déplacements
    public void majTab()
    {
		for (int i = 0 ; i < tailleMaxX; i++)
		{
			for (int j = 0 ; i < tailleMaxY; i++)
			{
				if(tab[i][j].elem.color = COLOR.RED || tab[i][j].elem.color = COLOR.BLUE)
				{
					deplacement(i,j);
				}
			}
		}
		tab = copie;
    }
    
    // Contamine avec le nouveau tableau
    public int contamination()
    {
		float rnd;
        for (int i = 0 ; i < tailleMaxX; i++)
		{
			for (int j = 0 ; i < tailleMaxY; i++)
			{
				if(tab[i][j].elem.color = COLOR.RED)
				{
					
					//parcours 9 cases
					for(i2 = i-1; i2< i+1; i2++)
					{
						for(j2 = j-1; j2< j+1; j2++)
						{
							//les 8 cases autour + appel de taux de contamination si on trouve une personne saine
							if(tab[i2][j2].elem.color = COLOR.BLUE)
							{
								rnd = Math.random() * 10,0;
								if(rnd > tauxContamination)
									tab[i2][j2].elem.color = COLOR.RED//on contamine
							}
						}
					}
				}
			}
		}
    }
    
    //met a jour les stats
    public void majStat()
    {
		int nbContamine;
		int nbSain;
		
        for (int i = 0 ; i < tailleMaxX; i++)
		{
			for (int j = 0 ; i < tailleMaxY; i++)
			{
				if(tab[i][j].elem.color = COLOR.RED)
					nbContamine++;
				else if(tab[i][j].elem.color = COLOR.BLUE)
					nbSain++;
			}
		}
		data.nbSain = nbSain;
		data.nbContamine = nbContamine;
		data.nbMort = data.nbPersonne - nbSain - nbContamine;
    }
    
    //retourne les stats
    public data getStat()
    {
        return data;
    }
    
    //détermine le taux de contamination
    public void determineTaux(bool portMasque, int txConfinement, int nbJourConta;)
    {
        bool portMasque;
		int txConfinement;
		float R0 = 2,5;
		
		//On part du principe que c'est un masque en tissu, donc public alors taux diminue de 70%
		//le taux de confinement est a déterminé, tout dépend du type de confinement
		if(portMasque = true)
			R0 = R0 - (R0* (70/100) + R0*(txConfinement/100));
		else
			R0 = R0 - (R0* (70/100))
    }
}*/
