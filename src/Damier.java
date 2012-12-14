import java.util.ArrayList;
import java.util.List;

//
public class Damier {
	private static final int TAILLE = 10;
	private static Case[][] cases = new Case[TAILLE][TAILLE];
	private List<Pion> pionOrdinateur = new ArrayList<Pion>();
	private List<Pion> pionJoueur = new ArrayList<Pion>();

	Damier() {
		for (int i = 0; i < TAILLE; i++)
			for (int j = 0; j < TAILLE; j++) {
				cases[i][j] = new Case(i, j);
				// initialisation de chaque case, les Pions sont fabriqué dans
				// Case()
				Pion p = cases[i][j].getPion();
				if (p instanceof PionJoueur)
					this.pionJoueur.add(p);// add Pion dans la liste
				else if (p instanceof PionOrdinateur)
					this.pionOrdinateur.add(p);
			}
	}

	public Case[][] getCases() {
		return cases;
	}

	public boolean regleDeplacement(Case depart, Case arrivee) {
		if (depart.getPion().regleDeplacement(arrivee)) {
			echangeDesPions(depart, arrivee);// pion-espace
			return true;
		}
		return false;
	}

	// regle et modification
	public boolean reglePrise(Case depart, Case arrivee, Case milieu) {
		if (depart.getPion().reglePrise(arrivee)) {
			echangeDesPions(depart, arrivee);// pion-espace
			pionOrdinateur.remove(milieu.getPion()); // supprimer le pion
			pionJoueur.remove(milieu.getPion());
			milieu.setPion(null);
			return true;
		}
		return false;
	}

	public void echangeDesPions(Case d, Case a) {
		Pion p = d.getPion();// changement d'un pion et d'un espace
		p.setLigne(a.getLigne());
		p.setColonne(a.getColonne());
		d.setPion(a.getPion());
		// un pion devient un dame
		if (a.getLigne() == 0 && p instanceof PionJoueur) {
			Pion dame = new DameJoueur(p);
			a.setPion(dame);
			pionJoueur.set(pionJoueur.indexOf(p), dame);
		} else if (a.getLigne() == TAILLE && p instanceof PionOrdinateur) {
			Pion dame = new DameOrdinateur(p);
			a.setPion(dame);
			pionOrdinateur.set(pionOrdinateur.indexOf(p), dame);
		} else
			a.setPion(p);// pas de changement de dame
	}

	public boolean regles(int ligneArri, int colonneArri, int ligneDepa,
			int colonneDepa) {
		Case depart = cases[ligneDepa][colonneDepa];
		Case arrivee = cases[ligneArri][colonneArri];
		Case milieu = cases[(ligneDepa + ligneArri) / 2][(colonneDepa + colonneArri) / 2];
		int[] a = cherchePrise(pionJoueur);
		if (a != null) {
			if (reglePrise(depart, arrivee, milieu)) {
				a = arrivee.getPion().cherchePrise();
				reprise(a, PionOrdinateur.class);
				return true;
			} else
				return false;
		} else if (regleDeplacement(depart, arrivee))
			return true;
		return false;
	}

	public void deplacementAleatoire() {
		boolean etat = false;
		Case arrivee = new Case();
		Case depart = new Case();
		while (!etat) {
			int num = (int) (Math.random() * pionOrdinateur.size());
			Pion p = pionOrdinateur.get(num);
			double direction = Math.random();
			depart = cases[p.getLigne()][p.getColonne()];
			try {
				if (direction < 0.5)
					arrivee = cases[p.getLigne() + 1][p.getColonne() - 1];
				else
					arrivee = cases[p.getLigne() + 1][p.getColonne() + 1];
				etat = this.regleDeplacement(depart, arrivee);
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
	}

	public List<Pion> getPionOrdinateur() {
		return pionOrdinateur;
	}

	public List<Pion> getPionJoueur() {
		return pionJoueur;
	}

	public void jeuOrdinateur() {
		int[] a = cherchePrise(pionOrdinateur);
		if (a != null) {
			reprise(a, PionJoueur.class);
		} else
			deplacementAleatoire();
	}

	public boolean gagnantHomme() {
		if (finDuJeu(pionOrdinateur))
			return true;
		return false;
	}

	public boolean gagnantOrdi() {
		if (finDuJeu(pionJoueur))
			return true;
		return false;
	}

	public boolean finDuJeu(List<Pion> pions) {
		if (pions.size() == 0)
			return true;// il y a plus de pion
		// tous les pions sont bloqués
		else if (cherchePrise(pions) == null && !chercheDeplace(pions))
			return true;
		return false;
	}

	// chercher un deplacement
	private boolean chercheDeplace(List<Pion> pions) {
		for (Pion p : pions) {
			if (p.chercheDeplace())
				return true;// trouver un, c bon
		}
		return false;
	}

	// chercher une prise pour tous les pions
	public int[] cherchePrise(List<Pion> pions) {
		for (Pion p : pions) {
			int[] a = p.cherchePrise();
			if (a != null)
				return a;
		}
		return null;
	}

	// faire la prise et la reprise
	public void reprise(int[] a, Class c) {
		while (a != null) {
			Case depart = cases[a[0]][a[1]];
			Case arrivee = cases[a[2]][a[3]];
			Case milieu = cases[(a[0] + a[2]) / 2][(a[1] + a[3]) / 2];
			reglePrise(depart, arrivee, milieu);
			a = arrivee.getPion().cherchePrise();
		}
	}

	// chercher la prise dans un des quatre sens, c pion d'adversaire
	public static int[] priseSens(int x, int y, int i, int j, Class c) {
		int[] a = new int[4];
		try {
			if (isPionC(x + i, y + j, c)
					&& cases[x + 2 * i][y + 2 * j].getPion() == null) {
				a[0] = x;
				a[1] = y;
				a[2] = x + 2 * i;
				a[3] = y + 2 * j;
				return a;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return null;
	}

	// chercher la deplacement dans un des quatre sens
	public static boolean deplaceSens(int x, int y, int i, int j) {
		try {
			if (cases[x + i][y + j].getPion() == null)
				return true;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return false;
	}

	// le pion de case(x)(y) est un pion de type c
	public static boolean isPionC(int x, int y, Class c) {
		Pion p = cases[x][y].getPion();
		if (p != null)// le pion existe et comparer les Class
			return p.getClass().equals(c)
					|| p.getClass().getSuperclass().equals(c);
		return false;
		// return c.isInstance(cases[x][y].getPion());
	}
}
