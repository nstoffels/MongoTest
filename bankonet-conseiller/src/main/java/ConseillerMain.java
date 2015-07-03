import java.util.ArrayList;
import java.util.List;
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

	/**
	 * @param args
	 * @return 
	 */
	public static void main(String[] args) {
		Logger.getLogger("").setLevel(Level.SEVERE);
		
		Scanner sc = new Scanner(System.in);
		int key;
		
		
		//Création d'un client avec MongoClient
		MongoClient mongoClient = new MongoClient();
		do{
			System.out.println("Bienvenue opérateur");
			System.out.println("0. Arrêter le programme");
			System.out.println("1. ouvrir un compte");
			System.out.println("sélectionner un choix");
			key=sc.nextInt();

			switch (key)
			{ 
				case 0:
				   System.out.println("arrêt de l'application");
					//fermeture du programme
				   mongoClient.close();
			    break;
			    
			    
				case 1:
				   System.out.println("création du client");
				   // TODO Auto-generated method stub					
					//récupère la database de Mongo
					MongoDatabase client = mongoClient.getDatabase("persondb");
					//récupération dans la collection (table) de mongo les objets persons
					MongoCollection<Document> clients = client.getCollection("persons");
					//récupère la database de Mongo
					MongoDatabase comptecourant = mongoClient.getDatabase("persondb");
					//récupération dans la collection (table) de mongo les objets comptes
					MongoCollection<Document> cc = comptecourant.getCollection("persons");
					
					
					//initialisation des variables
					String nom, prenom, login;
					float solde;
					
					System.out.println("Entrer un nom");
					nom = sc.next();
					System.out.println("Entrer un prenom");
					prenom = sc.next();
					System.out.println("Entrer un login");
					login = sc.next();
					System.out.println("Entrer un solde initiale");
					solde = sc.nextFloat();
					
					System.out.println(nom + " " + prenom + " " + login + " " + solde);
					
					//insertion de nouveaux documents clients
					Document doc=new Document()
							.append("nom", nom)
							.append("prenom", prenom)
							.append("login",login)
							.append("password", "secret");1
					clients.insertOne(doc);
					
					//insertion des nouveaux clients comptes
					Document compte = new Document()
							.append("libelle", "compte_courant")
							.append("solde", solde);
					cc.insertOne(compte);
					List<Document> docs=new ArrayList<Document>();
					doc.append("comptescourant", docs);
			break;
			

			default:
			   System.out.println("recommencer");
			}
			
		}while(key!=0);
		
	
	}

}
