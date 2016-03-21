package it.ecommerceproject.databases.orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ecommerceproject.databases.DatabaseHandler;
import it.ecommerceproject.util.KeyValuePair;

public class OrdersHandler extends DatabaseHandler<Order> {

	
	private static final String DATABASE_NAME = "accounts";
	private static final String TABLE_NAME ="orders";
	
	private static final String ID_KEY ="id";
	private static final String USER_KEY ="user";
	private static final String PRODUCTID_KEY="productid";
	
	
	public OrdersHandler() {
		super(DATABASE_NAME,TABLE_NAME);
	}


	@Override
	protected List<KeyValuePair> getEntryList(Order obj) {
		List<KeyValuePair> list = new ArrayList<KeyValuePair>();
		
		list.add( new KeyValuePair(USER_KEY,("'"+obj.getUser()+"'")) );
		list.add( new KeyValuePair(PRODUCTID_KEY, ""+obj.getProductID() ));
		
		return list;
	}

	@Override
	protected Order getItemByCurrentResultSet(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(ID_KEY);
		String user = resultSet.getString(USER_KEY);
		int productID = resultSet.getInt(PRODUCTID_KEY);
		
		return new Order(id,user,productID);
	}

}
