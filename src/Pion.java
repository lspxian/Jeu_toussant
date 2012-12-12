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


	Pion(int i, int j) {
		ligne = i;
		colonne = j;
	}

	public abstract boolean regleDeplacement(Case c);

	public abstract boolean reglePrise(Case c);
}

class PionJoueur extends Pion {
	PionJoueur(int i, int j) {
		super(i, j);
	}

	public boolean regleDeplacement(Case c) {
		int decalageVertical = c.getLigne() - this.ligne;	
		int decalageHorizontal = c.getColonne() - this.colonne;
		Pion p = c.getPion();
		if (decalageVertical == -1 && Math.abs(decalageHorizontal) == 1
				&& p == null)
			return true;
		return false;
	}

	public boolean reglePrise(Case c) {
		int decalageVertical = c.getLigne() - this.ligne;
		int decalageHorizontal = c.getColonne() - this.colonne;
		Pion p1 = c.getPion();
		if (decalageVertical == -2 && Math.abs(decalageHorizontal) == 2
				&& p1 == null) {
			Pion p2 = Damier.getCases()[(c.getLigne() + this.ligne) / 2][(c.getColonne() + this.colonne) / 2]
					.getPion();
			if (p2 == null)
				return false;
			else if (p2 instanceof PionOrdinateur)
				return true;
		}
		return false;
	}
}

class PionOrdinateur extends Pion {
	PionOrdinateur(int i, int j) {
		super(i, j);
	}

	public boolean regleDeplacement(Case c) {
		int decalageVertical = c.getLigne() - this.ligne;
		int decalageHorizontal = c.getColonne() - this.colonne;
		Pion p = c.getPion();
		if (decalageVertical == 1 && Math.abs(decalageHorizontal) == 1
				&& p == null)
			return true;
		return false;
	}

	public boolean reglePrise(Case c) {
		int decalageVertical = c.getLigne() - this.ligne;
		int decalageHorizontal = c.getColonne() - this.colonne;
		Pion p1 = c.getPion();
		if (decalageVertical == 2 && Math.abs(decalageHorizontal) == 2
				&& p1 == null) {
			Pion p2 = Damier.getCases()[(c.getLigne() + this.ligne) / 2][(c.getColonne() + this.colonne) / 2]
					.getPion();
			if (p2 == null)
				return false;
			else if (p2 instanceof PionJoueur)
				return true;
		}
		return false;
	}
}

class DameJoueur extends PionOrdinateur {
	DameJoueur(int i, int j) {
		super(i, j);
	}

	public boolean regleDeplacement(Case c) {
		int decalageVertical = c.getLigne() - this.ligne;
		int decalageHorizontal = c.getColonne() - this.colonne;
		Pion p = c.getPion();
		if (Math.abs(decalageVertical) == 1
				&& Math.abs(decalageHorizontal) == 1 && p == null)
			return true;
		return false;
	}

	public boolean reglePrise(Case c) {
		int decalageVertical = c.getLigne() - this.ligne;
		int decalageHorizontal = c.getColonne() - this.colonne;
		Pion p1 = c.getPion();
		if (Math.abs(decalageVertical) == 2
				&& Math.abs(decalageHorizontal) == 2 && p1 == null) {
			Pion p2 = Damier.getCases()[(c.getLigne() + this.ligne) / 2][(c.getColonne() + this.colonne) / 2]
					.getPion();
			if (p2 == null)
				return false;
			else if (p2 instanceof PionOrdinateur)
				return true;
		}
		return false;
	}
}

class DameOrdinateur extends PionOrdinateur {
	DameOrdinateur(int i, int j) {
		super(i, j);
	}

	public boolean regleDeplacement(Case c) {
		int decalageVertical = c.getLigne() - this.ligne;
		int decalageHorizontal = c.getColonne() - this.colonne;
		Pion p = c.getPion();
		if (Math.abs(decalageVertical) == 1
				&& Math.abs(decalageHorizontal) == 1 && p == null)
			return true;
		return false;
	}

	public boolean reglePrise(Case c) {
		int decalageVertical = c.getLigne() - this.ligne;
		int decalageHorizontal = c.getColonne() - this.colonne;
		Pion p1 = c.getPion();
		if (Math.abs(decalageVertical) == 2
				&& Math.abs(decalageHorizontal) == 2 && p1 == null) {
			Pion p2 = Damier.getCases()[(c.getLigne() + this.ligne) / 2][(c.getColonne() + this.colonne) / 2]
					.getPion();
			if (p2 == null)
				return false;
			else if (p2 instanceof PionJoueur)
				return true;
		}
		return false;
	}
}
