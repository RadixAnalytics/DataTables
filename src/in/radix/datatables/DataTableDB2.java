package in.radix.datatables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONArray;
import in.radix.datatables.struct.Column;
import in.radix.datatables.struct.Table;

public class DataTableDB2 implements DataTable {
	
	private boolean Debug = false;
	private Statement st;
	private ResultSet rs;
	
	String searchSQL = "";
    String fetch_query = "";
    
    Table t;
    int start = 0;
    int length = 0;
    
    public DataTableDB2(Table t, int start, int length) {
    	this.t = t;
    	this.start = start;
    	this.length = length;
    }
	
    /**
     * Simple QL
     * @param t
     * @param start
     * @param length
     * @return
     */
	public String getSQL() {
		//Base
        fetch_query = "SELECT row_number() OVER(ORDER BY "+t.getSortBy()+" , "+t.getTableAlias()+"."+t.getPrimaryKey()+") AS RID, "+t.getColumnList()+" FROM "+t.getTableName()+" AS "+t.getTableAlias();
        //Join
        for(String s: t.getjoins()) {
        	fetch_query += s;
        }
        
        //Where clause
        String where = " WHERE";
        if(!t.getWhereClause().equals(""))
        	where += " "+t.getWhereClause();
        
        if(where.equals(" WHERE"))
        	where = "";
        
        fetch_query += where;
        
        //Ordering
        fetch_query += " ORDER BY "+t.getSortBy();
        
        if(start == -1)
        	return fetch_query;
        else
        	return "SELECT * FROM ("+fetch_query+") AS t WHERE t.RID BETWEEN "+(start+1)+" AND "+(start+length);
	}
	
	
	/**
     * Simple QL
     * @param t
     * @param start
     * @param length
     * @return
     */
	public String getSQL(String q) {
		//Base
        fetch_query = "SELECT row_number() OVER(ORDER BY "+t.getSortBy()+" , "+t.getTableAlias()+"."+t.getPrimaryKey()+") AS RID, "+t.getColumnList()+" FROM "+t.getTableName()+" AS "+t.getTableAlias();
        //Join
        for(String s: t.getjoins()) {
        	fetch_query += s;
        }
        
      //Where clause
        String where = " WHERE";
        if(!t.getWhereClause().equals(""))
        	where += " "+t.getWhereClause();
        
        if(!q.equals("")) {
        	if(where.equals(" WHERE"))
        		where = " WHERE (";
        	else
        		where += " AND (";
        	
        	for(Column c: t.getColumns()) {
        		if(c.getIsSearch()) {
        			if(c.getname().toUpperCase().contains(" AS "))
        				where += " UPPER("+c.getname().toUpperCase().split(" AS ")[0].trim()+") LIKE '%"+q.toUpperCase()+"%' OR ";
        			else
        				where += " UPPER("+c.getname()+") LIKE '%"+q.toUpperCase()+"%' OR ";
        		}
        	}
        	
        	where = where.substring(0, where.length()-3);
        	
        	where += ")";
        }
        
        if(where.equals(" WHERE"))
        	where = "";
        
        fetch_query += where;
        
        //Ordering
        fetch_query += " ORDER BY "+t.getSortBy();
        
        if(start == -1)
        	return fetch_query;
        else
        	return "SELECT * FROM ("+fetch_query+") AS t WHERE t.RID BETWEEN "+(start+1)+" AND "+(start+length);
	}
	
	/**
	 * Manual Sorting
	 * @param t
	 * @param start
	 * @param length
	 * @param sortColumn
	 * @param sortOrder
	 * @return
	 */
	public String getSQL(int sortColumn, String sortOrder) {
		String sortingColumnName = "";
		
		if(t.isWithSR()) {
			if(t.getColumns().get(sortColumn-1).getname().toUpperCase().contains(" AS "))
				sortingColumnName = t.getColumns().get(sortColumn-1).getname().split(" AS ")[0].trim();
			else
				sortingColumnName = t.getColumns().get(sortColumn-1).getname();
		}
		else {
			if(t.getColumns().get(sortColumn).getname().toUpperCase().contains(" AS "))
				sortingColumnName = t.getColumns().get(sortColumn).getname().split(" AS ")[0].trim();
			else
				sortingColumnName = t.getColumns().get(sortColumn).getname();
		}
		
		//Base
		fetch_query = "SELECT row_number() OVER(ORDER BY "+sortingColumnName+" "+sortOrder+") AS RID, "+t.getColumnList()+" FROM "+t.getTableName()+" AS "+t.getTableAlias();
		//Join
		for(String s: t.getjoins()) {
        	fetch_query += s;
        }
		
		//Where clause
        String where = " WHERE";
        if(!t.getWhereClause().equals(""))
        	where += " "+t.getWhereClause();
        
        if(where.equals(" WHERE"))
        	where = "";
        
        fetch_query += where;
		
		//Ordering
		fetch_query += " ORDER BY "+sortingColumnName+" "+sortOrder; // -1 Because We are skipping first index in config array of columns
		
		if(start == -1)
        	return fetch_query;
        else
        	return "SELECT * FROM ("+fetch_query+") AS t WHERE t.RID BETWEEN "+(start+1)+" AND "+(start+length);
	}
	
	/**
	 * Filtering
	 * @param t
	 * @param start
	 * @param length
	 * @param sortColumn
	 * @param sortOrder
	 * @param q
	 * @return
	 */
	public String getSQL(int sortColumn, String sortOrder, String q) {
		
		String sortingColumnName = "";
		
		if(t.isWithSR()) {
			if(t.getColumns().get(sortColumn-1).getname().toUpperCase().contains(" AS "))
				sortingColumnName = t.getColumns().get(sortColumn-1).getname().split(" AS ")[0].trim();
			else
				sortingColumnName = t.getColumns().get(sortColumn-1).getname();
		}
		else {
			if(t.getColumns().get(sortColumn).getname().toUpperCase().contains(" AS "))
				sortingColumnName = t.getColumns().get(sortColumn).getname().split(" AS ")[0].trim();
			else
				sortingColumnName = t.getColumns().get(sortColumn).getname();
		}
		
		//Base
		fetch_query = "SELECT row_number() OVER(ORDER BY "+sortingColumnName+" "+sortOrder+") AS RID, "+t.getColumnList()+" FROM "+t.getTableName()+" AS "+t.getTableAlias();
		
		//Join
		for(String s: t.getjoins()) {
        	fetch_query += s;
        }
		
		//Where clause
        String where = " WHERE";
        if(!t.getWhereClause().equals(""))
        	where += " "+t.getWhereClause();
        
        if(!q.equals("")) {
        	if(where.equals(" WHERE"))
        		where = " WHERE (";
        	else
        		where += " AND (";
        	
        	for(Column c: t.getColumns()) {
        		if(c.getIsSearch()) {
        			if(c.getname().toUpperCase().contains(" AS "))
        				where += " UPPER("+c.getname().toUpperCase().split(" AS ")[0].trim()+") LIKE '%"+q.toUpperCase()+"%' OR ";
        			else
        				where += " UPPER("+c.getname()+") LIKE '%"+q.toUpperCase()+"%' OR ";
        		}
        	}
        	
        	where = where.substring(0, where.length()-3);
        	
        	where += ")";
        }
        
        if(where.equals(" WHERE"))
        	where = "";
        
        fetch_query += where;
		
		//Ordering
		fetch_query += " ORDER BY "+sortingColumnName+" "+sortOrder; // -1 Because We are skipping first index in config array of columns
		
		if(start == -1)
        	return fetch_query;
        else
        	return "SELECT * FROM ("+fetch_query+") AS t WHERE t.RID BETWEEN "+(start+1)+" AND "+(start+length);
	}
	
	public String getCountSQL() {
		//Where clause
        String where = " WHERE";
        if(!t.getWhereClause().equals(""))
        	where += " "+t.getWhereClause();
        if(where.equals(" WHERE"))
        	where = "";
        
		return "SELECT COUNT(*) AS TOTAL FROM "+t.getTableName()+" AS "+t.getTableAlias()+" "+where;
	}
	
	public String getCountSQL(String q) {
		//Where clause
        String where = " WHERE";
        if(!t.getWhereClause().equals(""))
        	where += " "+t.getWhereClause();
        
        if(!q.equals("")) {
        	if(where.equals(" WHERE"))
        		where = " WHERE (";
        	else
        		where += " AND (";
        	
        	for(Column c: t.getColumns()) {
        		if(c.getIsSearch()) {
        			if(c.getname().toUpperCase().contains(" AS "))
        				where += " UPPER("+c.getname().toUpperCase().split(" AS ")[0].trim()+") LIKE '%"+q.toUpperCase()+"%' OR ";
        			else
        				where += " UPPER("+c.getname()+") LIKE '%"+q.toUpperCase()+"%' OR ";
        		}
        	}
        	
        	where = where.substring(0, where.length()-3);
        	
        	where += ")";
        }
        
        if(where.equals(" WHERE"))
        	where = "";
        
        String join = "";
        if(t.getjoins().size() > 0)
        	for(String s: t.getjoins())
        		join += " "+s;
        
		return "SELECT COUNT(*) over() AS TOTAL FROM "+t.getTableName()+" AS "+t.getTableAlias()+" "+join+" "+where;
	}
	
	/**
	 * 
	 */
	public JSONArray getData(Connection conn, String sql, boolean withSR) throws SQLException {
		if(Debug)
			System.err.println(sql);
		
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		int SR = start+1;
		
		Formatter f = new Formatter();
		
		JSONArray data = new JSONArray();
		
		while(rs.next()) {
			JSONArray row = new JSONArray();
			
			//ADD SR NUMBER
			if(t.isWithSR())
				row.put(SR);
            
			for(Column c : t.getColumns()) {
            	//Proper Naming Convention Based on Columns: Ref: Dbeaver.
            	if(c.getname().toUpperCase().contains(" AS "))
            		row.put(f.get(c.getType(), rs.getString(c.getname().toUpperCase().split(" AS ")[1].trim())));
            	else if(c.getname().contains("."))
            		row.put(f.get(c.getType(), rs.getString(c.getname().toUpperCase().split("\\.")[1].trim())));
            	else
            		row.put(f.get(c.getType(), rs.getString(c.getname().toUpperCase().trim())));
            }
            data.put(row);
            SR++;
		}
		
		rs.close();
		st.close();
		
		return data;
	}
	
	/**
	 * 
	 */
	public ResultSet getResultSet(Connection conn, String sql) throws SQLException {
		return conn.createStatement().executeQuery(sql);
	}
	
	/**
	 * 
	 */
	public int getCount(Connection conn, String sql) throws SQLException {
		if(Debug)
			System.err.println(sql);
		int count = 0;
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		if(rs.next()) {
			count = Integer.parseInt(rs.getString("TOTAL"));
		} 
		
		return count;
	}
	
	/**
	 * @return the ps
	 */
	public Statement getSt() {
		return st;
	}

	/**
	 * @param ps the ps to set
	 */
	public void setPs(Statement st) {
		this.st = st;
	}

	/**
	 * @return the rs
	 */
	public ResultSet getRs() {
		return rs;
	}

	/**
	 * @param rs the rs to set
	 */
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return Debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		Debug = debug;
	}
	
}
