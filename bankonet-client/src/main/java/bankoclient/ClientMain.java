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
		
		//Cr�ation d'un client avec MongoClient
		MongoClient mongoClient = new MongoClient();
		//r�cup�re la database de Mongo
		MongoDatabase client = mongoClient.getDatabase("persondb");
		//d�finition des it�rators
		MongoCollection<Document> clients1 = client.getCollection("persons");
		Iterator<Document> clientelle = clients1.find().iterator();
		ArrayList<Document> compteList = new ArrayList<Document>();

		//test mdp/login
		do{
			String login, password;
			 clients1.find(new Document().append("login", clients1).append("password", clients1));
			Object log = listclient.get("login");
			Object pass = listclient.get("password");
			
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
		
		
		
		
		//console du client
		do{
			
			System.out.println("Bienvenue cher client");
			System.out.println("0. Arr�ter le programme");
			System.out.println("1. afficher vos comptes");
			System.out.println("s�lectionner un choix");
			keycli=sc.nextInt();
			
			switch (keycli)
			{ 
				//extinction de l'application
				case 0:
				   System.out.println("arr�t de l'application");
					//fermeture du programme
				   mongoClient.close();
			    break;
				case 1:
					MongoCollection<Document> clients1 = client.getCollection("persons");
					

					//d�finition des it�rators
					Object name, forname, log, ID;
					Object nbcompte=0;
					Object nbcompte2=0;
					Iterator<Document> clientelle = clients1.find().iterator();
					//ArrayList<Document> compteList = new ArrayList<Document>();
					

					//affichage des diff�rentes informations client dans la console
					while(clientelle.hasNext()){
						
						Document listclient = clientelle.next();
						name = listclient.get("nom");
						forname = listclient.get("prenom");
						log = listclient.get("login");
						ID = listclient.get("_id");
						nbcompte = listclient.get("comptescourant");
						nbcompte2 = listclient.get("comptesepargne");
						System.out.println("log: "+log+" ID: "+ID+" nom: "+name+" prenom: "+forname+" nombre de comptes courants: "+nbcompte+" nombre de comptes �pargne: "+nbcompte2);
					}
				break;

				default:
			   System.out.println("recommencer");
			}
			
		}while(keycli!=0);

	}

}
