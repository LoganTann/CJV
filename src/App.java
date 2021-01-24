import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
		Joueur prof = new Joueur("Ilié", 50, 1.5, 0.5, 2.);
		prof.addAttaque(new Attaque ("fuck le cahier des charges", 6));
		prof.addAttaque(new Attaque ("obsolescence", 5));
		prof.addAttaque(new Attaque ("retard", 1));
		prof.addAttaque(new Attaque ("absent", 0));
		
		Joueur etudiant = new Joueur("Etudiant", 18, 1.2, 0.3, 6.);
		etudiant.addAttaque(new Attaque ("superpuissance", 25));
		etudiant.addAttaque(new Attaque ("puissance", 10));
		etudiant.addAttaque(new Attaque ("inutile", 1));
		etudiant.addAttaque(new Attaque ("suicide", -10));
		
	
		
		startGame(etudiant, prof);
		
		System.out.println("Bye Bye !");
	}
	private static void startGame(Joueur currentPlayer, Joueur currentEnemy) {
		int i = 0;
		do {
			System.out.println("Tour " + i++ + "-------------");
		} while (loop(currentPlayer, currentEnemy) == 0);
	}
	
	private static String getWord() {
		Scanner sc = new Scanner(System.in);
		String action = sc.next("\\w+"); // un mot
		System.out.println(action);
		sc.close();
		return action.trim().toLowerCase();
	}
	
	private static boolean someoneDead(Joueur currentPlayer, Joueur currentEnemy) {
		if (! currentEnemy.estVivant()) {
			System.out.println(currentPlayer.getNom() + " a vaincu " + currentEnemy.getNom());
			return true;
		}
		if (! currentPlayer.estVivant()) { // ??
			System.out.println(currentEnemy.getNom() + " a vaincu " + currentPlayer.getNom());
			return true;
		}
		return false;
	}

	/**
	 * @brief Obtenir un indice d'attaque à partir de l'entrée utilisateur
	 * @return L'identifiant de l'attaque mentionnée par l'utilisateur
	 */
	private static int readAttack(Joueur target) {
		System.out.println("Choisissez une attaque parmi vos " + target.attackList());
		while (true) {
			System.out.print("Attaque ? : ");
			int retval = 0;
			String input = getWord();
			for (Attaque attaque : target.getAttaques()) {
				if (attaque.getNom().toLowerCase().equals( input )) {
					return retval;
				}
				retval++;
			}

			System.out.println("Entrée invalide !");
		}
	}
	
	private static int loop(Joueur currentPlayer, Joueur currentEnemy) {
		System.out.print("Votre action (check/attack/panik) : ");
		
		switch ( getWord() ) {
		case "check": {
			System.out.println("Stats de votre ennemi : ");
			System.out.println(currentEnemy);
			break;
		}
		case "attack": {
			
			int idAttaque = readAttack(currentPlayer);
			
			System.out.println( currentPlayer.attaquer(currentEnemy, idAttaque) );
			
			break;
		}
		case "panik": {
			System.out.println(currentPlayer.getNom() + " a fui. " + currentEnemy.getNom() + " gagne donc la partie.");
			return 1;
		}
	
		default:
			System.out.println("Une bonne valeur stp !");
		}
		
		
		return (someoneDead(currentPlayer, currentEnemy)) ? 1 : 0; // conv bool vers int
	}	
}
