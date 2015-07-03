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
		//récupère la database de Mongo
		MongoDatabase client = mongoClient.getDatabase("persondb");
		//récupération dans la collection (table) de mongo les objets persons
		MongoCollection<Document> clients = client.getCollection("persons");
		//récupère la database de Mongo
		MongoDatabase comptecourant = mongoClient.getDatabase("persondb");
		//récupération dans la collection (table) de mongo les objets comptes
		MongoCollection<Document> cc = comptecourant.getCollection("persons");
		do{
			System.out.println("Bienvenue opérateur");
			System.out.println("0. Arrêter le programme");
			System.out.println("1. ouvrir un compte");
			System.out.println("2. lister tout les clients");
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
							.append("password", "secret");
					clients.insertOne(doc);
					
					//insertion des nouveaux clients comptes
					Document compte = new Document()
							.append("libelle", "compte_courant")
							.append("solde", solde);
					cc.insertOne(compte);
					List<Document> docs=new ArrayList<Document>();
					doc.append("comptescourant", docs);
					
			break;
			
			
			case 2:
				MongoCollection<Document> clients1 = client.getCollection("persons");
				MongoCollection<Document> cc1 = comptecourant.getCollection("persons");
				//liste de tout les documents de la collection
				//définition des itérators
				Object name, forname, log, ID;
				Iterator<Document> clientelle = clients1.find().iterator();
				Iterator<Document> comptelecture = cc1.find().iterator();
				//affichage des différentes informations client dans la console
				while(clientelle.hasNext()){
					
					Document listclient = clientelle.next();
					name = listclient.get("nom");
					forname = listclient.get("prenom");
					log = listclient.get("login");
					ID = listclient.get("_id");
					System.out.println("log: "+log+" ID: "+ID+" nom: "+name+" prenom: "+forname);
				}
				
//				while(comptelecture.hasNext()){
//					System.out.println(comptelecture.next());
//				}
			break;

			default:
			   System.out.println("recommencer");
			}
			
		}while(key!=0);
		
	
	}

}
