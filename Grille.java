/**
 * Classe grille permet l'instanciation d'une map, son affichage et de gérer les interractions entre cases. <br/>
 * Il est à noter que l'axe des x est positif vers la droite
 * et que l'axe des y est positif vers le bas. <br/>
 * Ainsi, l'origine (0,0) est en haut à gauche.
 * 
 */
public class Grille {
	
	
	private Case[][] grille;
	
	/**
	 * Constructeur de grilles
	 * @param nbrPiecesX le nombre de pièce horizontale
	 * @param nbrPiecesY le nombre de pièce verticale 
	 * @param largeurPiece la largueur d'une pièce
	 * @param hauteurPiece la hauteur d'une pièce
	 * @param nbrNonKitten le nombre de non-kitten-items
	 */
	public Grille(int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece, int nbrNonKitten) {

		grille = new Case[nbrPiecesY * hauteurPiece + nbrPiecesY +1][nbrPiecesX * largeurPiece + nbrPiecesX +1]; 

		// Remplissage de la grille
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				
				// initialement les cases sont vides (Espace)
				grille[i][j] = new Espace(); 
				
				// murs verticaux
				if (j % (largeurPiece+1) == 0) {
					grille[i][j] = new Mur();
				}
				// murs horizontaux
				if (i % (hauteurPiece+1) == 0) {
					grille[i][j] = new Mur();				
				}		
			}
		}
		
		// Place les portes verticales
		for(int iY = hauteurPiece/2 +1; iY < grille.length; iY += hauteurPiece +1 ){ 
			for(int iX = largeurPiece+1; iX < grille[0].length-1; iX += largeurPiece +1)
				grille[iY][iX] = new Porte();
		}
		
		// Place les portes horizontales
		for(int iY = hauteurPiece+1; iY < grille.length-1; iY += hauteurPiece +1) {
			for(int iX = largeurPiece/2 +1; iX < grille[0].length; iX += largeurPiece+1)
				grille[iY][iX] = new Porte();
		}		

		// Instanciation des clefs
		Point[][] coordCles = this.positionsCles(nbrPiecesX, nbrPiecesY, largeurPiece, hauteurPiece);
		for(int i = 0; i < nbrPiecesY; i++) {
			for(int j = 0; j < nbrPiecesX; j++) {
				this.grille[coordCles[i][j].getY()][coordCles[i][j].getX()] = new Cle();
			}
		}
		
		// Place aléatoirement les non-kitten-items parmis les cases vides
		while(nbrNonKitten != 0) {  
			
			Point p = this.randomEmptyCell();
			this.grille[p.getY()][p.getX()] = new NonKitten(); 
			
			nbrNonKitten--; 
		}
		
		// Place le Kitten aléatoirement
		Point p1 = this.randomEmptyCell();
		this.grille[p1.getY()][p1.getX()] = new Kitten();
		
		// Place le Téleporteur aléatoirement
		Point p2 = this.randomEmptyCell();
		this.grille[p2.getY()][p2.getX()] = new Teleporteur();	
	}
	
	
	public Case[][] getGrille(){
		return this.grille;
	}
	
	
	/**
	 * Retourne grandeur du jeu en x
	 */
	public int getDimensionX() {
		return this.grille[0].length;
	}

	
	/*
	 * Retourne grandeur du jeu en y
	*/ 
	public int getDimensionY() {
		return this.grille.length ;
	}
	
	
	/**
	 * Méthode qui retourne une case aléatoire de la grille
	 * @return un Point de coordonnées (x,y)
	 */
	public Point randomCell() {
		
		int y = (int) (Math.random()*this.grille.length);
		int x = (int) (Math.random()*this.grille[0].length);
		
		Point coord = new Point(x,y);
		return coord;
	}
	
	
	/**
	 * Méthode qui retourne une case aléatoire vide de la grille
	 * @return un Point dont les coordonnées sont celles d'une case vide (espace)
	 */
	public Point randomEmptyCell() {
		
		Point p;
		
		// s'assure que la case soit un espace vide
		do {p = this.randomCell();}
		while(this.grille[p.getY()][p.getX()].representation != "back");
		
		return p;
	}
	
	
	/**
	 * Méthode qui détermine une position aléatoire pour les clés dans chaque pièce
	 * @param nbrPiecesX le nombre de pièces horizontales
	 * @param nbrPiecesY le nombre de pièces verticales
	 * @param largeurPiece la largueur d'une pièce
	 * @param hauteurPiece la hauteur d'une pièce
	 * @return un tableau de positions aléatoires de type Point (une position par pièce)
	 */
	public Point[][] positionsCles(int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece) {
		
		Point[][] pointsCles = new Point[nbrPiecesY][nbrPiecesX];
		
		for(int i = 0; i < nbrPiecesY; i++) {
			for(int j = 0; j < nbrPiecesX; j++) {
				
				// créer coordonnées aléatoire du point
				int x = (int) (Math.random()*largeurPiece + 1);
				int y = (int) (Math.random()*hauteurPiece + 1);
				
				// décale la coordonné pour chaque pièce
				pointsCles[i][j] = new  Point( x + (j*(largeurPiece+1)) , y + (i*(hauteurPiece+1)) );	
			}	
		}
		return pointsCles;	
	}
	
	
	/**
	 * Méthode qui affiche la grille et le robot
	 * @param robot
	 */
	public void afficher(Robot robot) {
		
		for(int i = 0; i < this.grille.length; i++) {
			for(int j = 0; j < this.grille[0].length; j++)
				if (j == this.grille[i].length-1)
					System.out.println(this.grille[i][j].representation); // imprime avec saut de ligne
				else if (i == robot.getPosition().getY() && j == robot.getPosition().getX()){ // emplacement du robot
					System.out.print("#");
				}
				else {	System.out.print(this.grille[i][j].representation); }
		}
	}
	

	/**
	 * Méthode qui indique si le robot peut se déplacer sur une case de coordonnée (x, y)
	 * @param robot le robot de type Robot
	 * @param x coordonnée en x de la case
	 * @param y coordonnée en y de la case
	 * @return un booléen qui indique si le déplacement est possible ou non
	 */
	public boolean deplacementPossible(Robot robot, int x, int y) {	
		return this.grille[y][x].interactionPossible(robot);
	}
	

	/**
	 * Méthode qui lance l'interction entre le Robot robot et la case de la grille sur laquelle il se trouve
	 * @param robot
	 */
	void interagir(Robot robot) {
		
		// la case d'interaction
		Case cInteraction = this.grille[robot.getPosition().getY()][robot.getPosition().getX()];
		
		// lance l'interaction avec le robot
		cInteraction.interagir(robot);
		
		// permet de faire disparaître les clé, les portes et le téléporteur
		if( cInteraction instanceof Cle || cInteraction instanceof Porte || cInteraction instanceof Teleporteur) 
			this.grille[robot.getPosition().getY()][robot.getPosition().getX()]= new Espace(); 
	}
	
}
