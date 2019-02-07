/**
 * Classe qui repr�sente le robot
 */
public class Robot {

	private String nom; // nom du robot
	private Point position; // position du robot
	private int nCles; // trousseau de cl�
	private boolean tel; // si robot poss�de le t�l�porteur
	private String representation = "rob";

	/**
	 * Contruscteur qui initialise l'�tat du Robot robot
	 * 
	 * @param nom
	 *            (String) du Robot robot
	 * @param g
	 *            une Grille
	 */
	public Robot(String nom, Grille g) {
		this.setNom(nom);
		this.setPosition(g.randomEmptyCell());
		this.setnCles(0);
		this.setTel(false);
	}
	
	public String getRep() {
		return this.representation ;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getnCles() {
		return nCles;
	}

	public void setnCles(int nCles) {
		this.nCles = nCles;
	}

	public boolean isTel() {
		return tel;
	}

	public void setTel(boolean tel) {
		this.tel = tel;
	}
}
