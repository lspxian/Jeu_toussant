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
		int[] a=damier.cherchePrise(damier.getPionJoueur());
		if (a!=null){
			if(damier.reglePrise(depart, arrivee,milieu)){
				a=arrivee.getPion().cherchePrise();
				damier.reprise(a, PionOrdinateur.class);
				return true;
			}
			else return false;
		}
		else if (damier.regleDeplacement(depart, arrivee)) 
			return true;
		return false;
	}
	
	
	public void jeuOrdinateur(){
		int[] a=damier.cherchePrise(damier.getPionOrdinateur());
		if(a!=null){
			damier.reprise(a,PionJoueur.class);
		}
		else damier.deplacementAleatoire();
}

	public Damier getDamier() {
		return damier;
	}

	public boolean gagnantHomme() {
		if(damier.finDuJeu(damier.getPionOrdinateur()))
			return true;
		return false;
	}

	public boolean gagnantOrdi() {
		if(damier.finDuJeu(damier.getPionJoueur()))
			return true;
		return false;
	}

}
