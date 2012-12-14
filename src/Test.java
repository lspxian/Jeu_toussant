
public class Test {
	public static void main(String[] args) {
		Pion p=new DameJoueur(new PionJoueur(1,2));
		Class c=PionJoueur.class;
		if(p!=null)
			System.out.println(p.getClass().equals(c)||p.getClass().getSuperclass().equals(c)); 
	}
}
