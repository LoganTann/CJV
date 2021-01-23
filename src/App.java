import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
		Joueur prof = new Joueur("Ilié", 50);
		prof.addAttaque(new Attaque ("pasJuste", 255));
		prof.addAttaque(new Attaque ("obsolescence", 5));
		prof.addAttaque(new Attaque ("retard", 1));
		prof.addAttaque(new Attaque ("absent", 0));
		
		Joueur etudiant = new Joueur("Etudiant", 18);
		etudiant.addAttaque(new Attaque ("superpuissance", 15));
		etudiant.addAttaque(new Attaque ("puissance", 10));
		etudiant.addAttaque(new Attaque ("inutile", 1));
		etudiant.addAttaque(new Attaque ("suicide", -10));
		
		int i = 0;
		boolean playing = true;
		boolean tictac = true;

		try (Scanner sc = new Scanner(System.in)) {	
			while (playing) {
				i++;
				
				Joueur currentEnemy = (tictac) ? etudiant : prof;
				Joueur currentPlayer = (tictac) ? prof : etudiant; 
				
				System.out.println("--------- tour " + i + ", "+ currentPlayer.getNom() + " joue ---------");
				System.out.print("Votre action (check/attack/panik) : ");
			
				String action = sc.next("\\w+"); // un mot
				
				// interprétation de l'action
				switch (action.toLowerCase()) {
					case "check": {
						System.out.println("Stats de votre ennemi : ");
						System.out.println(currentEnemy);
						break;
					}
					case "attack": {
						
						int idAttaque = currentPlayer.readAttaque(sc);
						
						System.out.println( currentPlayer.attaquer(currentEnemy, idAttaque) );
						
						// je suppose qu'on change le joueur seulement à l'attaque
						tictac = !tictac; 
						break;
					}
					case "panik": {
						System.out.println(currentPlayer.getNom() + " a fui. " + currentEnemy.getNom() + " gagne donc la partie.");
						playing = false;
						break;
					}
				
					default:
						System.out.println("Une bonne valeur stp !");
				}
				
				// vérif fin de vie
				if (! currentEnemy.estVivant()) {
					System.out.println(currentPlayer.getNom() + " a vaincu " + currentEnemy.getNom());
					playing = false;
				} else if (! currentPlayer.estVivant()) { // ??
					System.out.println(currentEnemy.getNom() + " a vaincu " + currentPlayer.getNom());
					playing = false;
				}
			}	
		}
		System.out.println("Bye Bye !");
	}

}
