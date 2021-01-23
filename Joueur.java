import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author LoganTann
 *
 */

public class Joueur {

	private String nom;
	private int PV;
	private ArrayList<Attaque> attaques;
	private int nbAttaquesMax;
	
	/**
	 * @brief Ceci est le constructeur de mon personnage
	 */
	public Joueur(String nom, int PV) {
		this.nom = nom;
		this.PV = PV;
		
		this.nbAttaquesMax = 4; // const
		this.attaques = new ArrayList<Attaque>(nbAttaquesMax);
	}
	
	/**
	 * @brief Ceci est l'affichage de l'�tat de mon perso
	 */
	public String toString() {
		String retval = "";
		retval += "Name : " + this.nom + "\n";
		retval += "PV : " + this.PV + "\n";
		retval += "Ses " + this.attackList();
		return retval;
	}

	public String attackList() {
		String retval = "attaques disponibles: ";
		boolean notfirst = false;
		
		for (Attaque attaque : this.attaques) {
			if (notfirst) {
				retval += " / ";
			}
			retval += "[" + attaque.getNom() + ": " + attaque.getDamage() + "PV]";
			
			notfirst = true;
		}
		return retval;
	}
	
	
	/**
	 * @brief Attaque un ennemi
	 * @param enemy la r�f�rence vers l'entit� � attaquer 
	 * @param idAttaque l'identifiant de l'attaque � employer
	 * @return String l'affichage textuel de l'action employ�e
	 * @see readAttaque pour obtenir un indice d'attaque � partir de l'entr�e utilisateur
	 */
	public String attaquer(Joueur enemy, int idAttaque) {
		// juste pour tester les string builders mais en vrai une String suffit
		StringBuilder retval = new StringBuilder(); 
		Attaque attckCourante = this.getAttaque(idAttaque);
		retval.append( this.nom + " attaque " + enemy.nom + " avec ");
		retval.append( attckCourante.getNom());
		
		int damage = attckCourante.getDamage();
		int actualHP = enemy.getPV();
		enemy.setPV(actualHP - damage);
		
		retval.append( "\nCela effectue " + damage + " points de d�gats !");
		return retval.toString();
	}
	
	/**
	 * @brief Ajoute une attaque. Limit� � [this.nbAttaquesMax] ajouts.
	 * @param attaque : l'attaque � ajouter
	 * @return String : affichage sous forme d'une string
	 */
	public String addAttaque(Attaque attaque) {
		if (this.attaques.size() < this.nbAttaquesMax) {
			this.attaques.add(attaque);
			return "ok";
		}
		
		// faudra throw mais je crois que ce sera dans un futur cours
		return "Erreur : pas possible d'ajouter plus de " + this.nbAttaquesMax + "attaques !";
	}
	
	/**
	 * @brief Obtenir un indice d'attaque � partir de l'entr�e utilisateur
	 * @param sc : un scanner qui permettra de lire ais�ment le flot d'entr�e
	 * @return L'identifiant de l'attaque mentionn�e par l'utilisateur
	 */
	public int readAttaque(Scanner sc) {
		// todo : flux de sortie comme avec la sda et ostream
		System.out.println("Choisissez une attaque parmi vos " + this.attackList());
		
		while (true) {
			System.out.print("Attaque ? : ");
			String labelAttaque = sc.next("\\w+");
			
			// recherche lin�aire de l'attaque et retourne l'ID si trouv�
			// on aurait pu mettre la suite dans un bout de code diff�rent
			int retval = 0;
			for (Attaque attaque : this.attaques) {
				if (attaque.getNom().toLowerCase().equals( labelAttaque.toLowerCase() )) {
					return retval;
				}
				retval++;
			}
			
			System.out.println("Entr�e invalide !");
		}
	}
	
	

	
	/**
	 * @return son joli nom 
	 */
	public String getNom() {
		return nom;
	}
	
	public int getPV() {
		return PV;
	}
	public void setPV(int pV) {
		PV = pV;
	}
	
	public Attaque getAttaque(int i) {
		return this.attaques.get(i);
	}

	/**
	 * @return Vrai si l'entit� est vivante, faux sinon
	 */
	public boolean estVivant() {
		return this.PV > 0;
	}
}
