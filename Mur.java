/**
 * Classe qui représente les murs
 */
public class Mur extends Case {

	public Mur() {
		this.representation = "wall";
	}
	
	// Robot robot ne peut pas marcher sur un mur
	@Override
	public boolean interactionPossible(Robot robot) {
		return false;
	}

	// Aucune interaction
	@Override
	public void interagir(Robot robot) {}

}
