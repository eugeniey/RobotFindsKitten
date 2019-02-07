/**
 * Classe qui représente une case espace vide
 */
public class Espace extends Case {

	public Espace() {
		this.representation = "back";
	}
	
	// Le Robot robot peut marcher sur une case vide
	@Override
	public boolean interactionPossible(Robot robot) {
		return true;
	}

	// Aucune interraction
	@Override
	public void interagir(Robot robot) {}
	

}
