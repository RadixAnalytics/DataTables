package in.radix.datatables;

import in.radix.datatables.struct.Table;

public class DataTableFactory {

	public DataTable getDataTable(String dataSourceType, Table t, int start, int end) {
		if (dataSourceType == null) {
			return null;
		}
		if (dataSourceType.equalsIgnoreCase("DB2")) {
			return new DataTableDB2(t, start, end);

		} else if (dataSourceType.equalsIgnoreCase("MYSQL")) {
			return new DataTableDB2(t, start, end);
			//new DataTableMySQL(t, start, end);
		}

		return null;
	}

}
