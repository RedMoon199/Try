//import de la classe Color
import java.awt.Color;

public class Personne {
	private boolean contaminer;
	private Color infected;
	private int nbJour;

	private final int DEFAULT_NBJOUR = 0;

	public Personne(Boolean contaminer, Color infected){
		this.contaminer = contaminer;
		this.infected = infected;
		this.nbJour = DEFAULT_NBJOUR;
	}

	public boolean getContaminer(){
		return this.contaminer;
	}
	public void setContaminer(Boolean contaminer){
		this.contaminer = contaminer;
	}

	public Color getInfected(){
		return this.infected;
	}

	public void setInfected(Color color){
		this.infected = color;
	}

	public int getNbJour(){
		return this.nbJour;
	}

	public void setNbJour(int x)
	{
		this.nbJour = x;
	} 
	public void beInfected(){
		setContaminer(true);
		setInfected(Color.RED); //a voir par rapport a la couleur de l'infection 
	}

	
}