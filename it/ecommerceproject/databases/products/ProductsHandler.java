package it.ecommerceproject.databases.products;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ecommerceproject.databases.DatabaseHandler;
import it.ecommerceproject.util.KeyValuePair;

public class ProductsHandler extends DatabaseHandler<Product> {

	
	private static final String DATABASE_NAME = "accounts";
	private static final String TABLE_NAME = "products";
	
	private static final String ID_KEY = "id";
	private static final String NAME_KEY = "name";
	private static final String DESCRIPTION_KEY ="description";
	private static final String PRICE_KEY ="price";
	
	
	
	public ProductsHandler() {
		super(DATABASE_NAME, TABLE_NAME);
	}

	@Override
	protected Product getItemByCurrentResultSet(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt(ID_KEY);
		String name = resultSet.getString(NAME_KEY);
		String description = resultSet.getString(DESCRIPTION_KEY);
		double price = resultSet.getDouble(PRICE_KEY);
		
		return new Product(id,name,description,price);
	}

	@Override
	protected List<KeyValuePair> getEntryList(Product obj) {

		List<KeyValuePair> list = new ArrayList<KeyValuePair>();
		
		list.add(new KeyValuePair(NAME_KEY,("'"+obj.getName())+"'") );
		list.add(new KeyValuePair(DESCRIPTION_KEY, ("'"+obj.getDescription())+"'"));
		list.add(new KeyValuePair(PRICE_KEY, ""+obj.getPrice()) );
		return list;
	}
	
	

}
