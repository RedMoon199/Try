/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;

import javafx.scene.layout.TilePane;

/**
 *
 * @author Mousse
 */
public class Environnement extends TilePane{
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
}
