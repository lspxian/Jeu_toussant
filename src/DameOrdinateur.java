class DameOrdinateur extends PionOrdinateur {
	DameOrdinateur(Pion p) {
		this.ligne = p.getLigne();
		this.colonne = p.getColonne();
	}

	public boolean regleDeplacement(Case arrivee) {
		return regleDeplaceVersBas(arrivee) || regleDeplaceVersHaut(arrivee);
	}

	public boolean reglePrise(Case arrivee) {
		return reglePriseVersBas(advs, arrivee)
				|| reglePriseVersHaut(advs, arrivee);
	}

	public int[] cherchePrise() {
		int[] a = super.cherchePrise();
		if (a != null)
			return a;
		else
			return cherchePriseVersHaut(advs);
	}

	public boolean chercheDeplace() {
		return chercheDeplaceVersBas() || chercheDeplaceVersHaut();
	}
}