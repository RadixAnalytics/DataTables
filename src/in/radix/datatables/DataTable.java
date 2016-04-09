package in.radix.datatables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

public interface DataTable {
	
	public ResultSet getResultSet(Connection conn, String sql) throws SQLException;
	public JSONArray getData(Connection conn, String sql, boolean withSR) throws SQLException;
	public int getCount(Connection conn, String sql) throws SQLException;
	
	public String getSQL();
	public String getSQL(String q);
	public String getSQL(int sortColumn, String sortOrder);
	public String getSQL(int sortColumn, String sortOrder, String q);
	
	public String getCountSQL();
	public String getCountSQL(String q);
}
