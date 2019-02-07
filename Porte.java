/**
 * Classe qui représente les portes
 */
public class Porte extends Case {

	public Porte() {
		this.representation = "door";
	}

	// Si le Robot robot possède au moins une clé, il peut ouvrir avec la porte
	@Override
	public boolean interactionPossible(Robot robot) {
		if (robot.getnCles() > 0)
			return true;
		return false;
	}

	// Nombre de clé diminue après utilisation
	@Override
	public void interagir(Robot robot) {
		robot.setnCles(robot.getnCles() - 1);
	}

}
