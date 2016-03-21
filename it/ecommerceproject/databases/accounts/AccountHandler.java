package it.ecommerceproject.databases.accounts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ecommerceproject.databases.DatabaseHandler;
import it.ecommerceproject.util.KeyValuePair;

public class AccountHandler extends DatabaseHandler<Account>{
	
	private static final String USERNAME_KEY = "username";
	private static final String ID_KEY ="id";
	private static final String PASSWORD_KEY ="password";
	private static final String TABLE_NAME ="accounts";
	private static final String DATABASE_NAME="accounts";

	public AccountHandler() {
		super(DATABASE_NAME,TABLE_NAME);
	}

	
	
	
	@Override
	protected List<KeyValuePair> getEntryList(Account obj) {
		List<KeyValuePair> list = new ArrayList<KeyValuePair>();
		
		list.add(new KeyValuePair(USERNAME_KEY,("'"+obj.getUsername())+"'") );
		list.add(new KeyValuePair(PASSWORD_KEY, ("'"+obj.getPassword())+"'"));
		
		return list;
	}


	@Override
	protected Account getItemByCurrentResultSet(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(ID_KEY);
		String username = resultSet.getString(USERNAME_KEY);
		String password = resultSet.getString(PASSWORD_KEY);
		
		return new Account(id,username,password);
	}
	
	
	

}
