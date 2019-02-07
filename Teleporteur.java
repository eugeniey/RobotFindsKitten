/**
 * Classe qui repr�sente les t�l�porteurs
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

	// �tat du Robot robot change, poss�de un t�l�porteur
	@Override
	public void interagir(Robot robot) {
		robot.setTel(true);
	}

}
