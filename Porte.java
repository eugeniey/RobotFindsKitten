/**
 * Classe qui repr�sente les portes
 */
public class Porte extends Case {

	public Porte() {
		this.representation = "door";
	}

	// Si le Robot robot poss�de au moins une cl�, il peut ouvrir avec la porte
	@Override
	public boolean interactionPossible(Robot robot) {
		if (robot.getnCles() > 0)
			return true;
		return false;
	}

	// Nombre de cl� diminue apr�s utilisation
	@Override
	public void interagir(Robot robot) {
		robot.setnCles(robot.getnCles() - 1);
	}

}
