/**
 * Classe qui représente le robot
 */
public class Robot {

	private String nom; // nom du robot
	private Point position; // position du robot
	private int nCles; // trousseau de clé
	private boolean tel; // si robot possède le téléporteur
	private String representation = "rob";

	/**
	 * Contruscteur qui initialise l'état du Robot robot
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
