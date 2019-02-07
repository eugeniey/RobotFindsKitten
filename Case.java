/**
 * Classe abstraite dont les objets du jeu vont hériter
 */
public abstract class Case {

	// La représentation ASCII des objets 
	protected String representation= "";
	
	public String getRepresentation() {
		return this.representation;
	}

	/**
	 * Méthode abstraite qui définit si une interaction entre le Robot robot et une case est possible
	 * @param un robot de type Robot 
	 * @return un booléen définissant la possibilité de l'interaction
	 */
	public abstract boolean interactionPossible(Robot robot);

	/**
	 * Méthode abstraite qui définit l'interaction entre une case et le Robot robot
	 * @param un robot de type Robot
	 */
	public abstract void interagir(Robot robot);

	/**
	 * Méthode qui retourne un nombre aléatoire entre 1 et 83 (image des nki)
	 * @return symbole ASCII de type char
	 */
	public static int getRandomNumber(int Min, int Max) {
		
		return Min + (int)(Math.random() * ((Max - Min) + 1)) ;
	}

}
