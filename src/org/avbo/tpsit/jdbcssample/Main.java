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
		} catch (SQLException e) {
			//Se arriva qui significa che non è
			//	riuscito a connettersi al database
			System.out.println("Errore durante la connessione al database");
			e.printStackTrace();
		}
		
	}

}
