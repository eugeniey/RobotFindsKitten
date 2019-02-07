/**
 * Classe qui repr�sente le Kitten
 */
public class Kitten extends Case{

	/* 
	* Pour �viter le prompt qui demande un nom � l'utilisateur
	* (C'est une m�thode alternative)
	*/
	private String nom ="Caramel";
	
	// Symbole ASCII al�atoire
	public Kitten() {
		this.representation = "nki/" + String.valueOf(getRandomNumber(1,82));
	}
	
	// Robot robot peut marcher sur kitten
	@Override
	public boolean interactionPossible(Robot robot) {
		return true;
	}

	public String getNomKitten() {
		return this.nom ;
	}
	
	// Fait rien
	@Override
	public void interagir(Robot robot) {
	}

}
