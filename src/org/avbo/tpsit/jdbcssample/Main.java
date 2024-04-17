package org.avbo.tpsit.jdbcssample;

import java.sql.*;

public class Main {
	
	private static String DB_Path = "example.db";
	/**
	 * Metodo che fornisce la stringa di connessione
	 * al database
	 * @return Stringa di connessione da utilizzare 
	 * per connettersi al database
	 */
	private static String GetConnectionString()
	{
		return "jdbc:sqlite:" + DB_Path;
	}
	
	private static String GetUsername()
	{
		return null;
	}
	
	private static String GetPassword()
	{
		return null;
	}
	
	
	public static void main(String[] args) {
		//Apre la connessione con il database
		try (Connection conn = 
				DriverManager.getConnection(
						//Utilizzando la stringa di connessione
						GetConnectionString(), 
						//E nome utente e password se presenti
						GetUsername(), GetPassword());)
		{
			System.out.println("Connessione al database effettuata");
			//Si assicura che non ci siano vecchi dati di esempio
			ClearDB(conn);
			
		} catch (SQLException e) {
			//Se arriva qui significa che non è
			//	riuscito a connettersi al database
			System.out.println("Errore durante la connessione al database");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo che si occupa di creare una tabella di esempio
	 * chiamata studenti e metterci dentro qualche dato
	 * @param conn Connessione da utilizzare
	 */
	private static void InitTestData(Connection conn) {
		// Query necessaria a creare la tabella di esempio
		// se presente
		String command = "CREATE TABLE studenti (\r\n"
				+ "   matricola INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "   nome text NOT NULL,\r\n"
				+ "   cognome text NOT NULL\r\n"
				+ ");";
		try (Statement stmt = conn.createStatement();) {
			// Esegue la query per creare la tabella
			stmt.execute(command);
		} catch (SQLException e) {
			// Se arriva qui significa che c'è stato un errore
			// nella query
			System.out.println("Errore durante la creazione della tabella studenti");
			e.printStackTrace();
		}
	}
	
	/**
	 * Comando che fa in modo di cancellare la tabella studenti
	 * @param conn Connessione da utilizzare
	 */
	private static void ClearDB(Connection conn)
	{
		//Query necessaria a cancella la tabella di esempio
		//	se presente
		String command = "DROP TABLE IF EXISTS studenti";
		try (Statement stmt = conn.createStatement();){
			//Esegue la query
			stmt.execute(command);
		} catch (SQLException e) {
			//Se arriva qui significa che c'è stato un errore
			//	nella query
			System.out.println("Errore durante la pulizia del database");
			e.printStackTrace();
		}
	}

}
