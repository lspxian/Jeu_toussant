import java.util.ArrayList;

//
public class Damier {
	private static final int TAILLE = 10;
	private static Case[][] cases = new Case[TAILLE][TAILLE];
	private ArrayList<Pion> pionOrdinateur=new ArrayList<Pion>();
	private ArrayList<Pion> pionJoueur=new ArrayList<Pion>();

	Damier() {
		for (int i = 0; i < TAILLE; i++) 
			for (int j = 0; j < TAILLE; j++){
				Damier.cases[i][j] = new Case(i, j); //initialisation de chaque case
				Pion p = Damier.cases[i][j].getPion();
				if (p instanceof PionJoueur)
					this.pionJoueur.add((PionJoueur) p);
				else if (p instanceof PionOrdinateur)
					this.pionOrdinateur.add((PionOrdinateur) p);
			}
		System.out.println();
	}

	public static Case[][] getCases() {
		return cases;
	}

	public static void setCases(Case[][] cases) {
		Damier.cases = cases;
	}

	public boolean regleDeplacement(Case depart, Case arrivee) {
		if(depart.getPion().regleDeplacement(arrivee)){
			echangeDesPions(depart, arrivee);
			return true;
		}
		return false;
	}

	public boolean reglePrise(Case depart, Case arrivee,Case milieu) {
		if(depart.getPion().reglePrise(arrivee)){
			echangeDesPions(depart, arrivee);
			pionOrdinateur.remove(milieu.getPion());
			pionJoueur.remove(milieu.getPion());
			milieu.setPion(null);
			return true;
		}
		return false;
	}
	
	public void echangeDesPions(Case d, Case a) {
		Pion p = d.getPion();
		p.setLigne(a.getLigne());
		p.setColonne(a.getColonne());
		d.setPion(a.getPion());
		a.setPion(p);
	}

	public void deplacementAleatoire() {
		boolean etat=false;
		Case arrivee=new Case();
		Case depart=new Case();
		while(!etat){
			int num=(int)(Math.random()*pionOrdinateur.size());
			Pion p=pionOrdinateur.get(num);
			double direction=Math.random();
			depart=Damier.cases[p.getLigne()][p.getColonne()];
			try{
			if(direction<0.5)
				arrivee=Damier.cases[p.getLigne()+1][p.getColonne()-1];
			else	arrivee=Damier.cases[p.getLigne()+1][p.getColonne()+1];
			etat=this.regleDeplacement(depart, arrivee);
			}catch(ArrayIndexOutOfBoundsException e){}
		}
	}

	public ArrayList<Pion> getPionOrdinateur() {
		return pionOrdinateur;
	}

	public ArrayList<Pion> getPionJoueur() {
		return pionJoueur;
	}
	
	public boolean finDuJeu(){
		if(pionOrdinateur.size()==0)
			return true;
		else{
			for(Pion p: pionOrdinateur){
/*				try{
					cases[];
				}catch();*/
			}
		}
		return false;
	}
		
	public int[] cherchePrise(ArrayList<Pion> pion1, Class c){
		for(Pion p:pion1){
			int[] a=p.cherchePrise();
			if(a!=null) return a;
		}
		return null;
	}
	
	public static int[] priseSens(int x, int y, int i, int j, Class c){
		int[] a=new int[4];
		try{
			if(c.isInstance(cases[x+i][y+j].getPion())&&cases[x+2*i][y+2*j].getPion()==null){
				a[0]=x;
				a[1]=y;
				a[2]=x+2*i;
				a[3]=y+2*j;
				return a;
			}				
		}catch(ArrayIndexOutOfBoundsException e){}
		return null;
	}
	
	public void reprise(int[] a, Class c){
		while(a!=null){
			Case depart=cases[a[0]][a[1]];
			Case arrivee=cases[a[2]][a[3]];
			Case milieu=cases[(a[0]+a[2])/2][(a[1]+a[3])/2];
			reglePrise(depart, arrivee, milieu);
			a=arrivee.getPion().cherchePrise();
		}
	}

	public static boolean isPionC(int x, int y, Class c){
		return c.isInstance(cases[x][y].getPion());
	}
}
