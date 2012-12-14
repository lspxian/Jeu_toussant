class PionOrdinateur extends Pion {
	protected Class advs=PionJoueur.class;
	PionOrdinateur(int i, int j) {
		super(i, j);
	}

	public boolean regleDeplacement(Case arrivee) {
		return regleDeplaceVersBas(arrivee);
	}

	public boolean reglePrise(Case arrivee) {
		return reglePriseVersBas(advs,arrivee);
	}
	
	public int[] cherchePrise(){
		return cherchePriseVersBas(advs);
	}
}