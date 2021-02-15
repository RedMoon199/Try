public class Stat{
	private int nbPersonnes;
	private int nbMort;
	private int nbContamine;
	private int nbSain;

	public final static int DEFAULT_NBMORT = 0;
	public final static int DEFAULT_NBCONTAMINER = 1;

	public Stat(int nbPersonnes){
		this.nbPersonnes = nbPersonnes;
		this.nbMort = DEFAULT_NBMORT;
		this.nbContamine = DEFAULT_NBCONTAMINER;
		this.nbSain = nbPersonnes - DEFAULT_NBCONTAMINER;
	}

	public int getNbPersonnes(){
		return this.nbPersonnes;
	}	

	public int getNbMort(){
		return this.nbMort;
	}
	public int getNbContaminer(){
		return this.nbContamine;
	}
	public int getNbSain(){
		return this.nbSain;
	}

	public void peopleContaminer(){
		this.nbContamine++;
		this.nbSain--;
	}

	public void peopleDie(){
		this.nbContamine --;
		this.nbMort++;
	}

}