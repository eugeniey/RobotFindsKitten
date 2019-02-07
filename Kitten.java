/**
 * Classe qui représente le Kitten
 */
public class Kitten extends Case{

	/* 
	* Pour éviter le prompt qui demande un nom à l'utilisateur
	* (C'est une méthode alternative)
	*/
	private String nom ="Caramel";
	
	// Symbole ASCII aléatoire
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
