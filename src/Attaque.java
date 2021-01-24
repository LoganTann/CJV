
public class Attaque {

	private String nom;
	private int damage;
	
	public Attaque(String nom, int damage) {
		this.nom = nom;
		this.damage = damage;
	}
	
	public String toString() {
		return this.nom + " (" + this.damage + " points de dégats)"; 
	}
	
	/*
	 * Getters et Setters
	 * */
	public String getNom() {
		return nom;
	}

	public int getDamage() {
		return damage;
	}

}
