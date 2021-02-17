package testcov.fr;

//import de la classe Color
import android.graphics.Color;

import androidx.annotation.ColorInt;

public class Personne {
	private boolean contaminer;
	@ColorInt
	private int color;
	private int nbJour;

	private final int DEFAULT_NBJOUR = 0;

	public Personne(Boolean contaminer, int color){
		this.contaminer = contaminer;
		this.color = color;
		this.nbJour = DEFAULT_NBJOUR;
	}

	public boolean getContaminer(){
		return this.contaminer;
	}
	public void setContaminer(Boolean contaminer){
		this.contaminer = contaminer;
	}

	public int getColor(){
		return this.color;
	}

	public void setColor(int color){
		this.color = color;
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
		setColor(Color.RED); //a voir par rapport a la couleur de l'infection
	}

	
}