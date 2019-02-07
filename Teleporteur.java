/**
 * Classe qui représente les téléporteurs
 */
public class Teleporteur extends Case {

	public Teleporteur() {
		this.representation = "nki/" + String.valueOf(getRandomNumber(1,82)) ;
	}

	// Robot robot peut marcher sur un teleporteur
	@Override
	public boolean interactionPossible(Robot robot) {
		return true;
	}

	// État du Robot robot change, possède un téléporteur
	@Override
	public void interagir(Robot robot) {
		robot.setTel(true);
	}

}
