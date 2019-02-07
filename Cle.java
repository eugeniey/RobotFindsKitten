/**
 * Classe qui représente les clés
 */
public class Cle extends Case {

	// Assigne un symbole ASCII aléatoire à la clé
	public Cle() {
		this.representation = "key";
	}
	
	// Robot robot peut marcher sur une clé
	@Override
	public boolean interactionPossible(Robot robot) {
		return true;
	}

	// Le nombre de clé du Robot robot augmente s'il interagit avec la clé
	@Override
	public void interagir(Robot robot) {
		robot.setnCles(robot.getnCles()+1);
	}

}
