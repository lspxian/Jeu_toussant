public abstract class Pion {
	protected int ligne;
	protected int colonne;
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	public int getLigne() {
		return ligne;
	}
	public int getColonne() {
		return colonne;
	}
	Pion(){}
	Pion(int i, int j) {
		ligne = i;
		colonne = j;
	}

	public abstract boolean regleDeplacement(Case arrivee);

	public abstract boolean reglePrise(Case arrivee);
	
	public abstract int[] cherchePrise();
	
	public int[] cherchePriseVersBas(Class c){
		int[] res;
		res=Damier.priseSens(ligne,colonne,1,1,c);
		if(res!=null) return res;
		res=Damier.priseSens(ligne,colonne,1,-1,c);
		if(res!=null) return res;
		return null;
	}
	public int[] cherchePriseVersHaut(Class c){
		int[] res;
		res=Damier.priseSens(ligne,colonne,-1,-1,c);
		if(res!=null) return res;
		res=Damier.priseSens(ligne,colonne,-1,1,c);
		if(res!=null) return res;
		return null;
	}
	
	public boolean regleDeplaceVersBas(Case arrivee){
		int vertical =arrivee.getLigne()-this.ligne;
		int horizontal = arrivee.getColonne()-this.colonne;
		if (vertical == 1 && Math.abs(horizontal) == 1 && arrivee.getPion() == null)
			return true;
		return false;
	}
	public boolean regleDeplaceVersHaut(Case arrivee){
		int vertical =arrivee.getLigne()-this.ligne;	
		int horizontal = arrivee.getColonne()-this.colonne;
		if (vertical == -1 && Math.abs(horizontal) == 1 && arrivee.getPion() == null)
			return true;
		return false;
	}

	public boolean reglePriseVersBas(Class c,Case arrivee){
		int vertical = arrivee.getLigne() - this.ligne;
		int horizontal = arrivee.getColonne() - this.colonne;
		if (vertical == 2 && Math.abs(horizontal) == 2 && arrivee.getPion() == null) {
			int milieuX=(arrivee.getLigne() + this.ligne) / 2;
			int milieuY=(arrivee.getColonne() + this.colonne) / 2;
			return Damier.isPionC(milieuX, milieuY, c);
		}
		return false;
	}
	public boolean reglePriseVersHaut(Class c,Case arrivee){
		int vertical = arrivee.getLigne() - this.ligne;
		int horizontal = arrivee.getColonne() - this.colonne;
		if (vertical == -2 && Math.abs(horizontal) == 2 && arrivee.getPion() == null) {
			int milieuX=(arrivee.getLigne() + this.ligne) / 2;
			int milieuY=(arrivee.getColonne() + this.colonne) / 2;
			return Damier.isPionC(milieuX, milieuY, c);
		}
		return false;
	}
}
