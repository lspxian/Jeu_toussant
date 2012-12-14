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
		if (damier.cherchePrise(damier.getPionJoueur(), PionOrdinateur.class)!=null){
			if(damier.reglePrise(depart, arrivee,milieu)) 
				return true;
			else return false;
		}
		else if (damier.regleDeplacement(depart, arrivee)) 
			return true;
		return false;
	}
	
	public void jeuOrdinateur(){
		int[] a=damier.cherchePrise(damier.getPionOrdinateur(), PionJoueur.class);
		if(a!=null){
			Case depart=damier.getCases()[a[0]][a[1]];
			Case arrivee=damier.getCases()[a[2]][a[3]];
			Case milieu=damier.getCases()[(a[0]+a[2])/2][(a[1]+a[3])/2];
			damier.reglePrise(depart, arrivee, milieu);
		}
		else damier.deplacementAleatoire();
}

	public Damier getDamier() {
		return damier;
	}
}
