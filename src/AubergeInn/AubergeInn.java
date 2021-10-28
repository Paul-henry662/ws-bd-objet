// Travail fait par : NGANKAM Paul-henry
//   TCHATCHOUA Erwann-Axel
//   SYAPZE Yves Guerard

package AubergeInn;

import java.io.*;
import java.util.StringTokenizer;
import java.sql.*;

/**
 * Fichier de base pour le TP2 du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 7 juillet 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet d'appeler des transactions d'un systeme
 * de gestion utilisant une base de donnees.
 *
 * Param�tres du programme
 * 0- site du serveur SQL ("local" ou "dinf")
 * 1- nom de la BD
 * 2- user id pour etablir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non sp�cifi�, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pr�-condition
 *   - La base de donnees doit exister
 *
 * Post-condition
 *   - Le programme effectue les mises � jour associees � chaque
 *     transaction
 * </pre>
 */
public class AubergeInn
{
    private static Connexion cx;
    private static GestionAubergeInn gestionAubergeInn;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 4)
        {
            System.out.println("Usage: java AubergeInn.AubergeInn <serveur> <bd> <user> <password> [<fichier-transactions>]");
            return;
        }
        
        cx = null;
        
        try
        {
            // Il est possible que vous ayez � d�placer la connexion ailleurs.
            // N'h�sitez pas � le faire!
            cx = new Connexion(args[0], args[1], args[2], args[3]);
            gestionAubergeInn = new GestionAubergeInn(args[0], args[1], args[2], args[3]);
            BufferedReader reader = ouvrirFichier(args);
            String transaction = lireTransaction(reader);
            while (!finTransaction(transaction))
            {
                executerTransaction(transaction);
                transaction = lireTransaction(reader);
            }
        }
        finally
        {
            if (cx != null)
                cx.fermer();
        }
    }

    /**
     * Decodage et traitement d'une transaction
     */
    static void executerTransaction(String transaction) throws Exception, AubergeInnException
    {
        try
        {
            System.out.print(transaction);
            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();
                // Vous devez remplacer la chaine "commande1" et "commande2" par
                // les commandes de votre programme. Vous pouvez ajouter autant
                // de else if que necessaire. Vous n'avez pas a traiter la
                // commande "quitter".
                if (command.equals("ajouterClient"))
                {
                    // Lecture des parametres
                    int idClient = readInt(tokenizer);
                    String prenom = readString(tokenizer);
                    String nom = readString(tokenizer);
                    int age = readInt(tokenizer);
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    gestionAubergeInn.getGestionClients().ajouterClient(new Client(idClient, prenom, nom, age));
                }
                else if (command.equals("supprimerClient"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    // de traitement pour la transaction
                	int idClient = readInt(tokenizer);
                	gestionAubergeInn.getGestionClients().supprimerClient(idClient);
                }
                else if(command.equals("ajouterChambre"))
                {
                	int idChambre = readInt(tokenizer);
                	String nom = readString(tokenizer);
                	String typeLit = readString(tokenizer);
                	double prixDeBase = readDouble(tokenizer);
                	
                	gestionAubergeInn.getGestionChambres().ajouterChambre(idChambre, nom, typeLit, prixDeBase);
                }
                else if(command.equals("supprimerChambre"))
                {
                	int idChambre = readInt(tokenizer);
                	
                	gestionAubergeInn.getGestionChambres().supprimerChambre(idChambre);
                }
                else if(command.equals("creerService"))
                {
                	int idService = readInt(tokenizer);
                	String description = readString(tokenizer);
                	double surplusPrix = readDouble(tokenizer);
                	
                	gestionAubergeInn.getGestionServices().creerService(idService, description, surplusPrix);
                }
                else if(command.equals("inclureService"))
                {
                	int idChambre = readInt(tokenizer);
                	int idService = readInt(tokenizer);
                	
                	gestionAubergeInn.getGestionServices().inclureService(idService, idChambre);
                }
                else if(command.equals("enleverService"))
                {
                	int idChambre = readInt(tokenizer);
                	int idService = readInt(tokenizer);
                	
                	gestionAubergeInn.getGestionServices().enleverService(idService, idChambre);
                }
                else if(command.equals("afficherChambresLibres"))
                {
                	gestionAubergeInn.getGestionChambres().afficherChambresLibres();
                }
                else if(command.equals("afficherClient"))
                {
                	int idClient = readInt(tokenizer);
                	
                	gestionAubergeInn.getGestionClients().afficherClient(idClient);
                }
                else if(command.equals("afficherChambre")) {
                	int idChambre = readInt(tokenizer);
                	
                	gestionAubergeInn.getGestionChambres().afficherChambre(idChambre);
                }
                else if(command.equals("reserver")) {
                	int idClient = readInt(tokenizer);
                	int idChambre = readInt(tokenizer);
                	Date dateDebut = readDate(tokenizer);
                	Date dateFin = readDate(tokenizer);
                	
                	gestionAubergeInn.getGestionReservations().reserver(idClient, idChambre, dateDebut, dateFin);
                }
                else
                {
                    System.out.println(" : Transaction non reconnue");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(" " + e.toString());
            // Ce rollback est ici seulement pour vous aider et �viter des probl�mes lors de la correction
            // automatique. En th�orie, il ne sert � rien et ne devrait pas appara�tre ici dans un programme
            // fini et fonctionnel sans bogues.
            //cx.rollback();
        }
    }

    
    // ****************************************************************
    // *   Les methodes suivantes n'ont pas besoin d'etre modifiees   *
    // ****************************************************************

    /**
     * Ouvre le fichier de transaction, ou lit � partir de System.in
     */
    public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException
    {
        if (args.length < 5)
            // Lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        else
            // Lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }

    /**
     * Verifie si la fin du traitement des transactions est atteinte.
     */
    static boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        return (transaction == null || transaction.equals("quitter"));
    }

    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }
    
    

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Date.valueOf(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }
        
    static Double readDouble(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
            	return Double.valueOf(token).doubleValue();
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Format invalide");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }
    
    public void quitter() {
    	
    }
    

}
