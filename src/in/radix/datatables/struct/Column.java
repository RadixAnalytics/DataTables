package in.radix.datatables.struct;

public class Column {
	private String name;
	private int index;
	private boolean isSearch;
	private DTDataType type;
	
	public Column(String name, int index, DTDataType type, boolean isSearch) {
		this.name = name;
		this.index = index;
		this.type = type;
		this.isSearch = isSearch;
	}
	
	/**
	 * @return the name
	 */
	public String getname() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the type
	 */
	public DTDataType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(DTDataType type) {
		this.type = type;
	}

	/**
	 * @return the searchable
	 */
	public boolean getIsSearch() {
		return isSearch;
	}

	/**
	 * @param searchable the searchable to set
	 */
	public void setIsSearch(boolean searchable) {
		this.isSearch = searchable;
	}
	

}
