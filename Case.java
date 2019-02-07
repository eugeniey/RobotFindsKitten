/**
 * Classe abstraite dont les objets du jeu vont h�riter
 */
public abstract class Case {

	// La repr�sentation ASCII des objets 
	protected String representation= "";
	
	public String getRepresentation() {
		return this.representation;
	}

	/**
	 * M�thode abstraite qui d�finit si une interaction entre le Robot robot et une case est possible
	 * @param un robot de type Robot 
	 * @return un bool�en d�finissant la possibilit� de l'interaction
	 */
	public abstract boolean interactionPossible(Robot robot);

	/**
	 * M�thode abstraite qui d�finit l'interaction entre une case et le Robot robot
	 * @param un robot de type Robot
	 */
	public abstract void interagir(Robot robot);

	/**
	 * M�thode qui retourne un nombre al�atoire entre 1 et 83 (image des nki)
	 * @return symbole ASCII de type char
	 */
	public static int getRandomNumber(int Min, int Max) {
		
		return Min + (int)(Math.random() * ((Max - Min) + 1)) ;
	}

}
