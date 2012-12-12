public class Jeu {
	private Damier damier;

	Jeu() {
		damier = new Damier();
	}

	public boolean regles(int ligneArri, int colonneArri, int ligneDepa,
			int colonneDepa) {
		Case depart = damier.getCases()[ligneDepa][colonneDepa];
		Case arrivee = damier.getCases()[ligneArri][colonneArri];
		Case milieu = damier.getCases()[(ligneDepa + ligneArri) / 2][(colonneDepa + colonneArri) / 2];
		if (damier.regleDeplacement(depart, arrivee)) 
			return true;
		else if (damier.reglePrise(depart, arrivee,milieu)) 
			return true;
		return false;
	}
	
	public void jeuOrdinateur(){
		damier.deplacementAleatoire();
}

	public Damier getDamier() {
		return damier;
	}
}
