import java.util.ArrayList;
import java.util.Collections;
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
				//extinction de l'application
				case 0:
				   System.out.println("arrêt de l'application");
					//fermeture du programme
				   mongoClient.close();
			    break;
			    
			    //création d'un compte client
				case 1:
				   System.out.println("création du client");
				   // TODO Auto-generated method stub					
					
					
					
					//initialisation des variables
					String nom, prenom, login;
					float solde, epargne;
					
					System.out.println("Entrer un nom");
					nom = sc.next();
					System.out.println("Entrer un prenom");
					prenom = sc.next();
					System.out.println("Entrer un login");
					login = sc.next();
					System.out.println("Entrer un solde initiale");
					solde = sc.nextFloat();
					System.out.println("Entrer un solde initiale pour compte épargne");
					epargne = sc.nextFloat();
					System.out.println(nom + " " + prenom + " " + login + " " + solde+ " " +epargne);
					
					//insertion de nouveaux documents clients
					Document doc=new Document()
							.append("nom", nom)
							.append("prenom", prenom)
							.append("login",login)
							.append("password", "secret");
					
					
					//insertion des nouveaux clients comptes
					Document compte = new Document()
							.append("libelle", "compte_courant")
							.append("solde", solde);
					cc.insertOne(compte);
					List<Document> docs=new ArrayList<Document>();
					Document comptee = new Document()
							.append("libelle", "compte_epargne")
							.append("epargne", epargne);
					cc.insertOne(comptee);
					List<Document> docse=new ArrayList<Document>();
					docs.add(compte);
					docse.add(compte);
					doc.append("comptescourant", docs);
					doc.append("comptesepargne", docse);
					clients.insertOne(doc);
					
			break;
			
			//liste de tout les documents de la collection + nombre de comptes par clients
			case 2:
				MongoCollection<Document> clients1 = client.getCollection("persons");
				

				//définition des itérators
				Object name, forname, log, ID;
				Object nbcompte=0;
				Object nbcompte2=0;
				Iterator<Document> clientelle = clients1.find().iterator();
				//ArrayList<Document> compteList = new ArrayList<Document>();
				

				//affichage des différentes informations client dans la console
				while(clientelle.hasNext()){
					
					Document listclient = clientelle.next();
					name = listclient.get("nom");
					forname = listclient.get("prenom");
					log = listclient.get("login");
					ID = listclient.get("_id");
					nbcompte = listclient.get("comptescourant");
					nbcompte2 = listclient.get("comptesepargne");
					System.out.println("log: "+log+" ID: "+ID+" nom: "+name+" prenom: "+forname+" nombre de comptes courants: "+nbcompte+" nombre de comptes épargne: "+nbcompte2);
				}
				
				
			break;

			default:
			   System.out.println("recommencer");
			}
			
		}while(key!=0);
		
	
	}

}
