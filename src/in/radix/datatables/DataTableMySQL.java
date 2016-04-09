package in.radix.datatables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;


public class DataTableMySQL implements DataTable {

	@Override
	public ResultSet getResultSet(Connection conn, String sql)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray getData(Connection conn, String sql, boolean withSR) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(Connection conn, String sql) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSQL(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSQL(int sortColumn, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSQL(int sortColumn, String sortOrder, String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCountSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCountSQL(String q) {
		// TODO Auto-generated method stub
		return null;
	}

			
	
}