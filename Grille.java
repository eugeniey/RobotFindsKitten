/**
 * Classe grille permet l'instanciation d'une map, son affichage et de g�rer les interractions entre cases. <br/>
 * Il est � noter que l'axe des x est positif vers la droite
 * et que l'axe des y est positif vers le bas. <br/>
 * Ainsi, l'origine (0,0) est en haut � gauche.
 * 
 */
public class Grille {
	
	
	private Case[][] grille;
	
	/**
	 * Constructeur de grilles
	 * @param nbrPiecesX le nombre de pi�ce horizontale
	 * @param nbrPiecesY le nombre de pi�ce verticale 
	 * @param largeurPiece la largueur d'une pi�ce
	 * @param hauteurPiece la hauteur d'une pi�ce
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
		
		// Place al�atoirement les non-kitten-items parmis les cases vides
		while(nbrNonKitten != 0) {  
			
			Point p = this.randomEmptyCell();
			this.grille[p.getY()][p.getX()] = new NonKitten(); 
			
			nbrNonKitten--; 
		}
		
		// Place le Kitten al�atoirement
		Point p1 = this.randomEmptyCell();
		this.grille[p1.getY()][p1.getX()] = new Kitten();
		
		// Place le T�leporteur al�atoirement
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
	 * M�thode qui retourne une case al�atoire de la grille
	 * @return un Point de coordonn�es (x,y)
	 */
	public Point randomCell() {
		
		int y = (int) (Math.random()*this.grille.length);
		int x = (int) (Math.random()*this.grille[0].length);
		
		Point coord = new Point(x,y);
		return coord;
	}
	
	
	/**
	 * M�thode qui retourne une case al�atoire vide de la grille
	 * @return un Point dont les coordonn�es sont celles d'une case vide (espace)
	 */
	public Point randomEmptyCell() {
		
		Point p;
		
		// s'assure que la case soit un espace vide
		do {p = this.randomCell();}
		while(this.grille[p.getY()][p.getX()].representation != "back");
		
		return p;
	}
	
	
	/**
	 * M�thode qui d�termine une position al�atoire pour les cl�s dans chaque pi�ce
	 * @param nbrPiecesX le nombre de pi�ces horizontales
	 * @param nbrPiecesY le nombre de pi�ces verticales
	 * @param largeurPiece la largueur d'une pi�ce
	 * @param hauteurPiece la hauteur d'une pi�ce
	 * @return un tableau de positions al�atoires de type Point (une position par pi�ce)
	 */
	public Point[][] positionsCles(int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece) {
		
		Point[][] pointsCles = new Point[nbrPiecesY][nbrPiecesX];
		
		for(int i = 0; i < nbrPiecesY; i++) {
			for(int j = 0; j < nbrPiecesX; j++) {
				
				// cr�er coordonn�es al�atoire du point
				int x = (int) (Math.random()*largeurPiece + 1);
				int y = (int) (Math.random()*hauteurPiece + 1);
				
				// d�cale la coordonn� pour chaque pi�ce
				pointsCles[i][j] = new  Point( x + (j*(largeurPiece+1)) , y + (i*(hauteurPiece+1)) );	
			}	
		}
		return pointsCles;	
	}
	
	
	/**
	 * M�thode qui affiche la grille et le robot
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
	 * M�thode qui indique si le robot peut se d�placer sur une case de coordonn�e (x, y)
	 * @param robot le robot de type Robot
	 * @param x coordonn�e en x de la case
	 * @param y coordonn�e en y de la case
	 * @return un bool�en qui indique si le d�placement est possible ou non
	 */
	public boolean deplacementPossible(Robot robot, int x, int y) {	
		return this.grille[y][x].interactionPossible(robot);
	}
	

	/**
	 * M�thode qui lance l'interction entre le Robot robot et la case de la grille sur laquelle il se trouve
	 * @param robot
	 */
	void interagir(Robot robot) {
		
		// la case d'interaction
		Case cInteraction = this.grille[robot.getPosition().getY()][robot.getPosition().getX()];
		
		// lance l'interaction avec le robot
		cInteraction.interagir(robot);
		
		// permet de faire dispara�tre les cl�, les portes et le t�l�porteur
		if( cInteraction instanceof Cle || cInteraction instanceof Porte || cInteraction instanceof Teleporteur) 
			this.grille[robot.getPosition().getY()][robot.getPosition().getX()]= new Espace(); 
	}
	
}
