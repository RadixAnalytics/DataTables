package in.radix.datatables;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import in.radix.datatables.struct.DTDataType;

public class Formatter {
	
	//Number Formatting
	private String intPattern = "###,###";
	
	
	//Number Formatting
	private String numPattern = "###,###.##";
	
	
	//Currency Formatting
	private String currPattern = "###,###.##";
	
	//Date Formatting
	private String outDatePattern = "dd/MM/yyyy";
	private String sysDatePattern = "yyyy-MM-dd";
	
	
	public String get(DTDataType type, String data) {
		if(data == null || data.equalsIgnoreCase("")) {
			return "";
		} else if(type == DTDataType.String) {
			return data;
		} else if(type == DTDataType.Integer) {
			DecimalFormat intFormat = new DecimalFormat(intPattern);
			Double d = Double.parseDouble(data);
			return intFormat.format(d);
		} else if(type == DTDataType.Number) {
			DecimalFormat numFormat = new DecimalFormat(numPattern);
			Double d = Double.parseDouble(data);
			return numFormat.format(d);
		} else if(type == DTDataType.Currency) {
			DecimalFormat currFormat = new DecimalFormat(currPattern);
			Double d = Double.parseDouble(data);
			return currFormat.format(d);
		} else if(type == DTDataType.Date) {
			SimpleDateFormat systemDateFormat = new SimpleDateFormat(sysDatePattern);
			SimpleDateFormat outputDateFormat = new SimpleDateFormat(outDatePattern);
			try {
				return outputDateFormat.format(systemDateFormat.parse(data));
			} catch(ParseException e) {
				e.printStackTrace();
			}
			return data;
		} else {
			return "";
		}
	}

	
	/**
	 * @return the intPattern
	 */
	public String getIntPattern() {
		return intPattern;
	}

	/**
	 * @param intPattern the intPattern to set
	 */
	public void setIntPattern(String intPattern) {
		this.intPattern = intPattern;
	}

	/**
	 * @return the currPattern
	 */
	public String getCurrPattern() {
		return currPattern;
	}

	/**
	 * @param currPattern the currPattern to set
	 */
	public void setCurrPattern(String currPattern) {
		this.currPattern = currPattern;
	}

	/**
	 * @return the numPattern
	 */
	public String getNumPattern() {
		return numPattern;
	}

	/**
	 * @param numPattern the numPattern to set
	 */
	public void setNumPattern(String numPattern) {
		this.numPattern = numPattern;
	}
	
	/**
	 * @return the outDatePattern
	 */
	public String getDatePattern() {
		return outDatePattern;
	}

	/**
	 * @param outDatePattern the outDatePattern to set
	 */
	public void setDatePattern(String outDatePattern) {
		this.outDatePattern = outDatePattern;
	}


	/**
	 * @return the sysDatePattern
	 */
	public String getSysDatePattern() {
		return sysDatePattern;
	}

	/**
	 * @param sysDatePattern the sysDatePattern to set
	 */
	public void setSysDatePattern(String sysDatePattern) {
		this.sysDatePattern = sysDatePattern;
	}

}
