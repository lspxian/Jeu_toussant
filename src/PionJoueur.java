class PionJoueur extends Pion {
	protected Class advs=PionOrdinateur.class;
	PionJoueur(int i, int j) {
		super(i, j);
	}

	public boolean regleDeplacement(Case arrivee) {
		return regleDeplaceVersHaut(arrivee);
	}

	public boolean reglePrise(Case arrivee) {
		return reglePriseVersHaut(advs,arrivee);
	}
	
	public int[] cherchePrise(){
		return cherchePriseVersHaut(advs);
	}
}