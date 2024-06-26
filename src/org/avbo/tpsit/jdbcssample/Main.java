package org.avbo.tpsit.jdbcssample;

import java.sql.*;

public class Main {
	private static Studente[] studenti = {
			new Studente("MATTEO", "BALLOTTA"),
			new Studente("GIUSEPPE", "BUTERA"),
			new Studente("ENRICO", "CAPORALI"),
			new Studente("MARICA", "CHENG"),
			new Studente("SEBASTIAN STEFAN", "CIRMIS"),
			new Studente("NICOLO", "DALL OLIO"),
			new Studente("ALESSANDRO", "FALSINO"),
			new Studente("RICCARDO", "FOSCHI"),
			new Studente("EMANUELE", "GISONNA"),
			new Studente("AMAR", "ISHTIAQ"),
			new Studente("MATTIA", "MICILLO"),
			new Studente("LORENZO", "RANDELLINI"),
			new Studente("SIMONE", "RIGENERATO"),
			new Studente("DAVIDE", "SATRIANO"),
			new Studente("DAVIDE", "SCIARABBA"),
			new Studente("AYMEN", "SEHBAOUI"),
			new Studente("CHIARA", "SIGNANI"),
			new Studente("OMAR", "ZHAR"),
	};
	
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
			InitTestData(conn);
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
			
			for (Studente studente : studenti) {
				String insertQuery = "INSERT INTO studenti (nome,cognome) VALUES('"+ 
						studente.getNome() +
						"','" + 						studente.getCognome() +"');";
				//Aggiunte l'insert fra i comandi da eseguire
				stmt.addBatch(insertQuery);
			}
			//Aggiuge i vari studenti
			stmt.executeBatch();
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
