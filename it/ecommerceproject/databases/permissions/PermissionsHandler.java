package it.ecommerceproject.databases.permissions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ecommerceproject.databases.DatabaseHandler;
import it.ecommerceproject.util.KeyValuePair;

public class PermissionsHandler extends DatabaseHandler<Permission> {

	
	private static final String DATABASE_NAME = "accounts";
	private static final String TABLE_NAME ="permissions";
	
	private static final String ID_KEY ="id";
	private static final String PERMISSION_KEY="permission";
	
	
	public PermissionsHandler() {
		super(DATABASE_NAME,TABLE_NAME);
	}


	@Override
	protected List<KeyValuePair> getEntryList(Permission obj) {
		List<KeyValuePair> list = new ArrayList<KeyValuePair>();
		list.add( new KeyValuePair(PERMISSION_KEY, ""+obj.getPermission() ));
		return list;
	}

	@Override
	protected Permission getItemByCurrentResultSet(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(ID_KEY);
		int permission = resultSet.getInt(PERMISSION_KEY);
                return new Permission(id,permission);
	}

}