/**
 * Classe qui repr�sente les cl�s
 */
public class Cle extends Case {

	// Assigne un symbole ASCII al�atoire � la cl�
	public Cle() {
		this.representation = "key";
	}
	
	// Robot robot peut marcher sur une cl�
	@Override
	public boolean interactionPossible(Robot robot) {
		return true;
	}

	// Le nombre de cl� du Robot robot augmente s'il interagit avec la cl�
	@Override
	public void interagir(Robot robot) {
		robot.setnCles(robot.getnCles()+1);
	}

}
