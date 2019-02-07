/**
 * Classe qui permet de faire le lien entre l'affichage (la vue) et le modèle du jeu
 */
public class Controleur {
	
	private Vue vue ;
	private RobotFindsKitten jeu ;
	
	/**
	 * Constructeur qui initialise un jeu et instancie la vue
	 * @param vue type Vue
	 */
	public Controleur(Vue vue) {
		this.vue = vue ; // la vue 
		jeu = new RobotFindsKitten(3, 2, 6, 4, 40,"R.O.B") ; // créer un jeu
	}
	
	
	/**
	 * Permet d'informer le jeu du déplacement de l'utilisateur <br/>
	 * ainsi que d'informer la vue de ce déplacement
	 * @param input input de l'utilisateur de déplacement(a,s,d,w,t)
	 */
	public void deplacer(String input) {
	
		// Va chercher l'ancienne position du robot
		int i = jeu.getRobot().getPosition().getX() ;
		int j = jeu.getRobot().getPosition().getY() ;
		
		// On applique le déplacement dans le jeu
		this.jeu.deplacementJeu(input); 
		
		// On applique dans la vue l'ancienne position
		// on va chercher l'image à l'ancienne position du robot
		vue.deplacement(i,j);		
		
		// on va chercher la nouvelle position du robot
		i = jeu.getRobot().getPosition().getX() ;
		j = jeu.getRobot().getPosition().getY() ;	
		
		// On applique dans la vue cette nouvelle position
		// affiche donc le robot
		vue.deplacement(i,j);
		
		// On demande à la vue de changer les messages 
		vue.setMessage(this.phraseNKI(i, j),getMessEtatRob());	
	}
	
	
	/**
	 * Retourne le String associé (le nom de l'image) à un élément de la grille du jeu 
	 * @param i position en x
	 * @param j position en y
	 * @return
	 */
	public String getImagePoint(int i, int j) {
		
		// Emplacement du robot
		if (i == jeu.getRobot().getPosition().getX() && j == jeu.getRobot().getPosition().getY()){
			return jeu.getRobot().getRep() ;
		}
		
		return this.jeu.getGrilleJeu().getGrille()[j][i].representation ;
	}
	
	
	/**
	 * Avertie la vue que c'est la fin de la partie 
	 * @param i position en x
	 * @param j position en y
	 */
	public void finDePartie(int i, int j) {
		if (this.jeu.estKitten(i, j)) {
			this.vue.finParti(jeu.getRobot().getNom(),jeu.nomKitten(i, j)) ;
		}
	}
		
	
	public Robot getRobot() {
		return this.jeu.getRobot() ;
	}
	
	
	public String getNomRobot() {
		return this.jeu.getRobot().getNom() ;
	}
	
	
	public int getDimensionGridX() {
		return this.jeu.getGrilleJeu().getDimensionX() ;
	}

	
	public int getDimensionGridY() {
		return this.jeu.getGrilleJeu().getDimensionY() ;
	}
	
	
	// Getter des phrases descriptives 
	public String phraseNKI(int i, int j) {
		return jeu.getDescriptionNKI(i,j) ;
	}
	
	
	public String getNomKittenCon(int i, int j) {
		return jeu.nomKitten(i, j);
	}
	
	
	public String getMessEtatRob() {
		return jeu.getEtatRob() ;
	}
		
		
}
	
	
	

