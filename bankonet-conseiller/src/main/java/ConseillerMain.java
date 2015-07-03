import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



/**
 * 
 */

/**
 * @author ETY
 *
 */
public class ConseillerMain {

	private Scanner sc;

	/**
	 * @param args
	 * @return 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bienvenue opérateur");
		System.out.println("0. Arrêter le programme");
		System.out.println("1. ouvrir un compte");
		System.out.println("sélectionner un choix");
		Scanner sc = new Scanner(System.in);
		int key=0;
		key=sc.nextInt();

		
		switch (key)
		{ 
			case 0:
			   System.out.println("arrêt de l'application");
			   Logger.getLogger("").setLevel(Level.SEVERE);
				
				//fermeture du programme
			   	MongoClient.close();
			   	
		    break;
			case 1:
			   System.out.println("création du client");
			   // TODO Auto-generated method stub
				Logger.getLogger("").setLevel(Level.SEVERE);
				
				//Création d'un client avec MongoClient
				MongoClient mongoClient = new MongoClient();
				//récupère la database de Mongo
				MongoDatabase client = mongoClient.getDatabase("persondb");
				//récupération dans la collection (table) de mongo les objets persons
				MongoCollection<Document> clients = client.getCollection("persons");
				//récupère la database de Mongo
				MongoDatabase comptecourant = mongoClient.getDatabase("persondb");
				//récupération dans la collection (table) de mongo les objets comptes
				MongoCollection<Document> cc = comptecourant.getCollection("persons");
				
				try{
					
					String nom, prenom, login;
					float solde;
					sc = new Scanner(System.in);
					do{
						System.out.println("Entrer un nom");
						nom = sc.next();
						System.out.println("Entrer un prenom");
						prenom = sc.next();
						System.out.println("Entrer un login");
						login = sc.next();
						System.out.println("Entrer un solde initiale");
						solde = sc.nextFloat();
						
						System.out.println(nom + " " + prenom + " " + login + " " + solde);
						
						//insertion de nouveaux documents
						Document doc=new Document()
								.append("nom", nom)
								.append("prenom", prenom)
								.append("login",login)
								.append("password", "secret");
						clients.insertOne(doc);
						
						Document compte = new Document()
								.append("solde", solde);
						cc.insertOne(compte);
					
				}while(!nom.equals("") && !prenom.equals("") && !login.equals("") && solde!=0);
			}finally {
				mongoClient.close();
			}
		break;

		default:
		   System.out.println("recommencer");
		}
	
	}

}
