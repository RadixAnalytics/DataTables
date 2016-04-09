package in.radix.datatables.struct;

import java.util.ArrayList;

public class Table {
	private String tableName;
	private String tableAlias;
	private String primaryKey;
	private String sortBy;
	private ArrayList<Column> columns = new ArrayList<Column>();
	private ArrayList<String> joins = new ArrayList<String>();
	private String whereClause = "";
	private boolean withSR = false; 
	
	public Table(String tableName, String tableAlias, String primaryKey, String sortBy) {
		this.tableName = tableName;
		this.tableAlias = tableAlias;
		this.primaryKey = primaryKey;
		this.sortBy = sortBy;
	}
	
	public void addColumn(String name, int index, DTDataType type, boolean isSearch) {
		Column c = new Column(name, index, type, isSearch);
		this.columns.add(c);
	}
	
	public ArrayList<Column> getColumns() {
		return columns;
	}
	
	public String getColumnList() {
		StringBuilder nameBuilder = new StringBuilder();
		
	    for (Column n : columns) {
	        nameBuilder.append(n.getname().replace("\"", "")).append(",");
	    }
	    
	    nameBuilder.deleteCharAt(nameBuilder.length() - 1);
	
	    return nameBuilder.toString();
	}
	
	
	//Join
	public void join(String joinTable, String joinTableAlias, String joinType, String colLeft, String colRight) {
		//INNER JOIN TABLE2 AS T2 ON  
		joins.add(" "+joinType+" "+joinTable+" AS "+joinTableAlias+" ON "+joinTableAlias+"."+colLeft+" = "+this.getTableAlias()+"."+colRight);		
	}
	
	public ArrayList<String> getjoins() {
		return joins;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the primaryKey
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the tableAlias
	 */
	public String getTableAlias() {
		return tableAlias;
	}

	/**
	 * @param tableAlias the tableAlias to set
	 */
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	/**
	 * @return the whereClause
	 */
	public String getWhereClause() {
		return whereClause;
	}

	/**
	 * @param whereClause the whereClause to set
	 */
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

	/**
	 * @return the withSR
	 */
	public boolean isWithSR() {
		return withSR;
	}

	/**
	 * @param withSR the withSR to set
	 */
	public void setWithSR(boolean withSR) {
		this.withSR = withSR;
	}
}
