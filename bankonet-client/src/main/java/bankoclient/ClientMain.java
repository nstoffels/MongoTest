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
		Document clienttrouver = null;
		//test mdp/login
		do{
			String login=null, pass=null, log = null, password = null;
			//on cherche � r�cup�rer dans clients1 uniquement les login et mot de passe
			//on cr�er l'it�rator servant � extraire chaque login et password.
			
			
			System.out.println("Bienvenue cher client");
			System.out.println("login");
			login = sc.next();
			System.out.println("mot de passe");
			pass=sc.next();
			
			Iterator<Document> logpass = clients1.find(new Document().append("login", login)).iterator();
			
			while(logpass.hasNext() && clienttrouver == null){
				Document passlog = logpass.next();
				log = passlog.getString("login");
				password = passlog.getString("password");
				if(password.equals(pass))
				{
					token="Ok";
					clienttrouver = passlog;
				}
				
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
					//d�finition des it�rators
					Object nbcompte=0;
					Object nbcompte2=0;
					Iterator<Document> compteclient = clients1.find().iterator();
					//ArrayList<Document> compteList = new ArrayList<Document>();
					

					//affichage des diff�rentes informations client dans la console
					while(compteclient.hasNext()){
						Document moncompte = compteclient.next();
						nbcompte = moncompte.get("comptescourant");
						nbcompte2 = moncompte.get("comptesepargne");
						System.out.println(" nombre de comptes courants: "+nbcompte+" nombre de comptes �pargne: "+nbcompte2);
					}
				break;

				default:
			   System.out.println("recommencer");
			}
			
		}while(keycli!=0);

	}

}
