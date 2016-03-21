package it.ecommerceproject.databases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ecommerceproject.util.Identificable;
import it.ecommerceproject.util.KeyValuePair;

public abstract class DatabaseHandler<Type extends Identificable> extends DatabaseHelper{
	
	

	public DatabaseHandler(String database,String table) {
		super(database,table);
	}
	
	
	//restituisce tutti gli elementi da attraverso una lista
	public List<Type> getData(){
		
		//ottengo il ResultSet dalla sopraclasse 
		try{
			ResultSet resultSet = super.getAllItems();
			//ritorno il resultset convertito in lista
			return getListFromResultSet(resultSet);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	
	//aggiornamento di un record
	public void update(Type obj) throws SQLException{
		
		//aggiorno tramite il metodo della sopraclasse passando id e i valori per ogni colonna
		super.update(obj.getID(), getEntryList(obj));
		
	}
	
	public List<Type> getFilteredData(List<KeyValuePair> list){
		
		try{
			ResultSet resultSet = super.getElementsByFilter(list);
			
			return getListFromResultSet(resultSet);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//converte da resultset a list
	protected List<Type> getListFromResultSet(ResultSet resultSet){
			List<Type> list = new ArrayList<Type>();
			
			if(resultSet != null){
				try{
					resultSet.beforeFirst();
					while(resultSet.next()){
						list.add(getItemByCurrentResultSet(resultSet));
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			return list;
	}
	
	//ottengo un elemento dal resultset
	protected abstract Type getItemByCurrentResultSet(ResultSet resultSet) throws SQLException;
	
	//inserimento di un record
	public void insert(Type obj)  throws SQLException{
		//inserimento tramite metodo della sopraclasse
		super.insert(getEntryList(obj));
	}
	
	//ottengo la lista di { colonna valore } dal mio oggetto
	protected abstract List<KeyValuePair> getEntryList(Type obj);
}
