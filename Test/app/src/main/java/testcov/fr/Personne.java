//import de la classe Color
import java.awt.Color;

public class Personne {
	private boolean contaminer;
	private Color infected;

	public Personne(Boolean contaminer, Color infected){
		this.contaminer = contaminer;
		this.infected = infected;
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

	public void beInfected(){
		setContaminer(true);
		setInfected(Color.RED); //a voir par rapport a la couleur de l'infection 
	}

	
}