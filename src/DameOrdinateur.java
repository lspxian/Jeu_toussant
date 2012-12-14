class DameOrdinateur extends PionOrdinateur {
	DameOrdinateur(int i, int j) {
		super(i, j);
	}

	public boolean regleDeplacement(Case arrivee) {
		return regleDeplaceVersBas(arrivee)||regleDeplaceVersHaut(arrivee);
	}

		public boolean reglePrise(Case arrivee) {
			return reglePriseVersBas(advs,arrivee)||reglePriseVersHaut(advs,arrivee);
		}
	
	public int[] cherchePrise(){
		int[] a=super.cherchePrise();
		if(a==null) return a;
		else return cherchePriseVersHaut(advs);
	}
}