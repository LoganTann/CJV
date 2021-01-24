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
	
	private float statAttck, statDef, statSpeed;
	
	/**
	 * @brief Ceci est le constructeur de mon personnage
	 */
	public Joueur(String nom, int PV, double StatAttck, double StatDef, double statSpeed) {
		this.nom = nom;
		this.PV = PV;
		
		this.nbAttaquesMax = 4; // const
		this.attaques = new ArrayList<Attaque>(nbAttaquesMax);
	}
	
	/**
	 * @brief Ceci est l'affichage de l'état de mon perso
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
	 * @param enemy la référence vers l'entité à attaquer 
	 * @param idAttaque l'identifiant de l'attaque à employer
	 * @return String l'affichage textuel de l'action employée
	 * @see readAttaque pour obtenir un indice d'attaque à partir de l'entrée utilisateur
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
		
		retval.append( "\nCela effectue " + damage + " points de dégats !");
		return retval.toString();
	}
	
	/**
	 * @brief Ajoute une attaque. Limité à [this.nbAttaquesMax] ajouts.
	 * @param attaque : l'attaque à ajouter
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
	 * @return Vrai si l'entité est vivante, faux sinon
	 */
	public boolean estVivant() {
		return this.PV > 0;
	}

	public ArrayList<Attaque> getAttaques() {
		return attaques;
	}
}
