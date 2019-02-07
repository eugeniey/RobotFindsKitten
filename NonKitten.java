/**
 * Classe qui représente les Non Kitten Items
 */
public class NonKitten extends Case {

	// Phrase descriptive d'un NKI
	private String description;

	public NonKitten() {

		// Assigne une phrase aléatoire de la banque
		this.description = RobotFindsKitten.getDescriptions()[(int) (Math.random() * RobotFindsKitten.getDescriptions().length)];
		this.representation = "nki/" + String.valueOf(getRandomNumber(1,82)) ;
	}

	// Robot robot peut marcher sur un NKI
	@Override
	public boolean interactionPossible(Robot robot) {
		return true;
	}

	// Fait rien
	@Override
	public void interagir(Robot robot) {
	}

	public String getDescription() {
		return this.description ;
	}
	
}
