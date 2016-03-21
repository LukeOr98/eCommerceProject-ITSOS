package it.ecommerceproject.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import it.ecommerceproject.util.KeyValuePair;


public class DatabaseHelper {
	
	
	// viene caricato il driver jdbc una volta sola
	static{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver caricati con successo");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	private String databaseName;
	private String table;
	private static Connection connection;
	private Statement statement;
	
	
	public DatabaseHelper(String databaseName,String table){
		this.databaseName = databaseName;
		this.table = table;
	}
	
	
	//stabilisce una connessione con il server MySql
	public static void openConnection(Credentials credentials) throws SQLException{
		connection = DriverManager.getConnection(credentials.getConnectionURL(),credentials.getUsername(),credentials.getPassword());
	}
	
	//apertura di uno specifico db
	public void open() throws SQLException{
		connection.setCatalog(databaseName);
		statement = connection.createStatement();
	}
	
	
	
	//restituisce tutti gli elementi della tabella
	protected ResultSet getAllItems() throws SQLException{
			return statement.executeQuery("SELECT * from "+table);
		
	}
	
	
	//inserimento di un nuovo record nella tabella
	protected void insert(List< KeyValuePair > entryList) throws SQLException{
		
		statement.executeUpdate(getInsertQueryString(entryList));		
	}
	
	
	
	// aggiornamento di un record tramite id 
	protected void update(int id,List< KeyValuePair > entryList) throws SQLException {
		statement.executeUpdate(getUpdateQueryString(id,entryList));
	}
	
	
	
	// restituisce gli elementi in base a delle condizioni (WHERE key = value)
	protected ResultSet getElementsByFilter(List< KeyValuePair > entryList) throws SQLException{
		return statement.executeQuery(getSelectQueryString(entryList));
		
	}
	
	// elimina un record
	public void delete(int id) throws SQLException{
		statement.executeUpdate("DELETE FROM "+table+" WHERE id="+id+";");
	}
	 
	
	// restituisce una stringa contenente la query per inserire
	private String getInsertQueryString(List< KeyValuePair > entryList){
		
		String insertQuery = "INSERT INTO "+ table+" (";
		String valuesQuery = "VALUES (";
		
		for(int i=0;i<entryList.size();i++){
			KeyValuePair entry = entryList.get(i);
	
			insertQuery += entry.getKey()+ ( (i == (entryList.size() -1 )) ? "" : ", ");
			valuesQuery += entry.getValue()+ ( (i == (entryList.size() -1 )) ? "" : ", ");
		}
		
		insertQuery += ") ";
		valuesQuery +=")";
		return insertQuery+valuesQuery+";";
	}
	
	// restituisce una stringa contenente la query per  il select
	private String getSelectQueryString(List<KeyValuePair> entryList){
		String selectQuery ="SELECT *";
		String fromQuery =" FROM "+table;
		String whereQuery=" WHERE ";
		
		for(int i=0;i<entryList.size();i++){
			
			KeyValuePair entry = entryList.get(i);
			
			whereQuery += entry.getKey() +"="+entry.getValue() + ( (i == (entryList.size() -1 )) ? "" : " AND ");
			
		}
		return selectQuery+fromQuery+whereQuery;
	}
	
	// restituisce una stringa contente la query per aggiornare un record
	private String getUpdateQueryString(int id,List< KeyValuePair > entryList){
		
		String updateQuery = "UPDATE "+table+" ";
		String valuesQuery = "SET ";
		
		for(int i=0;i<entryList.size();i++){
			KeyValuePair entry = entryList.get(i);
			valuesQuery += entry.getKey()+ "=" + entry.getValue() + ( (i == (entryList.size() -1 )) ? "" : ", ");
		}
		
		valuesQuery +=" ";
		
		return updateQuery+valuesQuery+"WHERE id="+id+";";
	}
	
	
	//close the statement
	public void close(){
		try{
			if(statement != null){
				statement.close();
			}
		}catch(Exception e){}
	}
	//chiude la connessione
	
	public static void closeConnection(){
		try{
			if(connection != null)
				connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
