import java.util.Iterator;
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
public class CreationClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger.getLogger("").setLevel(Level.SEVERE);
		
		//Création d'un client avec MongoClient
		MongoClient mongoClient = new MongoClient();
		//récupère la database de Mongo
		MongoDatabase client = mongoClient.getDatabase("persondb");
		//récupération dans la collection (table) de mongo les objets persons
		MongoCollection<Document> clients = client.getCollection("persons");
		
		try{
			String selection=null;
			
			String nom, prenom, login, password;
			float solde;
			Scanner sc=new Scanner(System.in);
			do{
				System.out.println("Entrer un nom");
				nom = sc.next();
				System.out.println("Entrer un prenom");
				prenom = sc.next();
				System.out.println("Entrer un login");
				login = sc.next();
				System.out.println("Entrer un mot de passe");
				password = sc.next();
				System.out.println("Entrer un solde initiale");
				solde = sc.nextFloat();
				
				System.out.println(nom + " " + prenom + " " + login + " " + password);
				
				//insertion de nouveaux documents
				Document doc=new Document()
						.append("nom", nom)
						.append("prenom", prenom)
						.append("login",login)
						.append("password", password)
						.append("solde", solde);
				clients.insertOne(doc);
				
			}while(!nom.equals("") && !prenom.equals("") && !login.equals("") && !password.equals(""));
		}finally {
			mongoClient.close();
		}

	}

}
