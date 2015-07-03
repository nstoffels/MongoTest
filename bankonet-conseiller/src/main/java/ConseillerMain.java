import java.util.Scanner;



/**
 * 
 */

/**
 * @author ETY
 *
 */
public class ConseillerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			String selection=null;
			
			String key;
			Scanner sc=new Scanner(System.in);
			do{
				System.out.println("Bienvenue opérateur");
				System.out.println("0. Arrêter le programme");
				System.out.println("1. ouvrir un compte");
				System.out.println("sélectionner un choix");
				key=sc.next();
				
				
				System.out.println(key + "");
				return;
				
			}while(!key.equals(""));
		}finally {
			
		}
		
	}

}
