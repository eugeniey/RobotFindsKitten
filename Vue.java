import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.text.*;

/**
 * Classe qui définit tout l'affichage graphique du jeu
 */
public class Vue extends Application{
	
    private ImageView[][] imageViewsTab;  // Conteneur d'image
	private Text messagesNki, etatRob ;   // message NKI et État du robot
	private Boolean ecranPlein = true ;   // Booléen pour définir le plein écran
	private GridPane grid ;				  
	private Controleur controleur ;
	private Stage stage ;
	
	
	public static void main(String[] args) {
		launch(args) ; // enclenche le jeu
	}    
	
	
	/**
	 * Permet de définir les bases du jeu 
	 */
	@Override
	public void start(Stage stagePrincipal) throws Exception {
		
		// Instancie un controleur
		controleur = new Controleur(this) ;
		
		// Le stage pour le jeu
		this.stage = stagePrincipal ;

		// Créer tableau d'ImageView qui va contenir des ImageViews des images du jeu
		imageViewsTab = new ImageView[controleur.getDimensionGridX()][controleur.getDimensionGridY()] ;
		
		// Racine avec VBox
		VBox root = new VBox() ;
		
		// Définie grandeur de la scène
		Scene scene = new Scene(root,820,430,Color.BLACK);
		
		// Créer la grille
		grid = new GridPane() ;
		
		// Construit la grille
		constructionBaseGrid();
		
		// Texte du message des NKI
		messagesNki = new Text("Bienvenue dans RobotFindsKitten : Super Dungeon Master 3000 Ultra Turbo Deluxe Edition !");
		messagesNki.setFont(Font.font("Verdana", 17));
		messagesNki.setFill(Color.WHITE);
		
		// Texte d'information sur l'état du robot
		etatRob = new Text("");
		etatRob.setFont(Font.font("Verdana", 17));
		etatRob.setFill(Color.WHITE);
		
		// On place tout
		root.getChildren().add(messagesNki) ;
		root.getChildren().add(grid);			
		root.getChildren().add(etatRob);
		root.setSpacing(13);	
		grid.setAlignment(Pos.CENTER);
		root.setAlignment(Pos.CENTER);

		// Set la scène 
		stagePrincipal.setScene(scene);
		stagePrincipal.setTitle("RobotFindsKitten");
		stagePrincipal.show() ;
			
		// Effet du jeu, sur les différents évènements 
		scene.setOnKeyPressed((event) -> {

			if (event.getCode() == KeyCode.W) {
				controleur.deplacer("w");
			}
			else if (event.getCode() == KeyCode.A) {
				controleur.deplacer("a");
			}
			else if (event.getCode() == KeyCode.S) {
				controleur.deplacer("s");
			}
			else if (event.getCode() == KeyCode.D) {
				controleur.deplacer("d");
			}
			else if (event.getCode() == KeyCode.T) {
				controleur.deplacer("t");
			}
			else if (event.getCode() == KeyCode.F5) {
				ecranPlein = !ecranPlein;
				stagePrincipal.setFullScreen(ecranPlein);
			}
			else if (event.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}			
		});		
	}
	
	
	/**
	 *  Construit la grille de départ 
	 */
	public void constructionBaseGrid() {	
		// Passe parmis tous les éléments de la grille du jeu
		for(int i = 0; i < controleur.getDimensionGridX(); i++) {
			for(int j = 0; j < controleur.getDimensionGridY(); j++) {
				
				ImageView iv = new ImageView() ;
				
				imageViewsTab[i][j] = iv; // on ajoute le ImageView vide
			
				// Créer l'image selon la case de la grille où on se trouve
				Image im = new Image ("/images/"+ controleur.getImagePoint(i, j) + ".png") ;
				
				iv.setImage(im);				
				iv.setFitHeight(30);
				iv.setFitWidth(30);
				
				grid.add(iv, i, j); // ajoute au grid
			}
		}
	}
	
	
	/**
	 * Permet de changer l'affichage dans le grid à la position x,y 
	 * @param position tableau qui prend une position x et y 
	 */
	public void deplacement(int i, int j) {

		Image im = new Image ("/images/"+ controleur.getImagePoint(i, j) + ".png") ;
		this.imageViewsTab[i][j].setImage(im); // change image
		
		// Vérifie si c'est la fin d'une partie (tombe sur Kitten)
		controleur.finDePartie(i,j) ;
	}
	
	
	/**
	 * Permet de changer les messages affichés des NKI et de l'état du robot
	 * @param phrase String de phrase des NKI
	 * @param etatRobot String de l'état du robot
	 */
	public void setMessage(String phrase,String etatRobot) {
		messagesNki.setText(phrase);
		etatRob.setText(etatRobot);
	}
	
	
	/**
	 * Permet d'afficher la fin de la partie 
	 * @param nomRob nom du robot
	 * @param nomKitten nom du Kitten
	 */
	public void finParti(String nomRob, String nomKitten) {

		VBox rootFin = new VBox();
		
		// créer scène finale
		Scene sceneFin = new Scene(rootFin, 820, 450, Color.BLACK);
			
		Text messageFin = new Text("You found Kitten! Way to go, Robot!\n" + nomKitten + " <3 " + nomRob);
	
		// Style sur le texte
		messageFin.setFont(Font.font("Verdana", 17));
		messageFin.setTextAlignment(TextAlignment.CENTER);
		messageFin.setFill(Color.RED);
		
		// Image finale
		ImageView imFin = new ImageView(new Image("/images/found-kitten.png")) ;
		
		rootFin.getChildren().add(imFin);
		rootFin.getChildren().add(messageFin);
		rootFin.setAlignment(Pos.CENTER);
		
		stage.setScene(sceneFin);
		
		// Permet de "restarter" le jeu
		sceneFin.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.SPACE) {
				try {
					this.start(stage); // on réaffiche le "stage" de début de la partie et recommence
				} catch (Exception e) {
					System.out.print(" Le jeu ne peux pas ouvrir :'( ");
					e.printStackTrace();
				}
			}
			else if (event.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
		});	
	}


}
