/**
 * 
 */
package bankoclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author ETY
 *
 */
public class ClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger.getLogger("").setLevel(Level.SEVERE);
		
		Scanner sc = new Scanner(System.in);
		int keycli;
		String token = null;
		
		//Création d'un client avec MongoClient
		MongoClient mongoClient = new MongoClient();
		//récupère la database de Mongo
		MongoDatabase client = mongoClient.getDatabase("persondb");
		//définition des itérators
		MongoCollection<Document> clients1 = client.getCollection("persons");
		Iterator<Document> clientelle = clients1.find().iterator();
		//ArrayList<Document> compteList = new ArrayList<Document>();
		

		//affichage des différentes informations client dans la console
		
		do{
			Document listclient = clientelle.next();
			Object log = listclient.get("login");
			Object pass = listclient.get("password");
			String login, password;
			System.out.println("Bienvenue cher client");
			System.out.println("login");
			login = sc.next();
			System.out.println("mot de passe");
			password=sc.next();
			if(log.equals(login) && pass.equals(password))
			{
				token="Ok";
			}
			
		}while(!token.equals("Ok"));
		
		do{
			
			
			System.out.println("Bienvenue cher client");
			System.out.println("0. Arrêter le programme");
			System.out.println("sélectionner un choix");
			keycli=sc.nextInt();
			
			switch (keycli)
			{ 
				//extinction de l'application
				case 0:
				   System.out.println("arrêt de l'application");
					//fermeture du programme
				   mongoClient.close();
			    break;
			    

				default:
			   System.out.println("recommencer");
			}
			
		}while(keycli!=0);

	}

}
