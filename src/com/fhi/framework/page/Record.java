/**
 * 
 */
package com.fhi.framework.page;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Administrator 分条检索纪录
 */
public class Record {
	private Map records = new HashMap();

	private Object value = null; 

	private String key = "0";

	public boolean hasNext() {
		String tmpKey = Integer.toString(Integer.parseInt(key) + 1);
		Object tmpValue = records.get(tmpKey);
		if (tmpValue == null)
			return false;
		return true;
	}

	public boolean hasPrev() {
		String tmpKey = Integer.toString(Integer.parseInt(key) - 1);
		Object tmpValue = records.get(tmpKey);
		if (tmpValue == null)
			return false;
		return true;
	}

	public Object next() {
		if (!hasNext())
			return null;
		key = Integer.toString(Integer.parseInt(key) + 1);
		value = records.get(key);
		return value;
	}

	public void last() {
		if (records.size() == 0) {
			key = "0";
			value = null;
		}
		for (Iterator it = records.entrySet().iterator(); it.hasNext();) {
			Entry entry = (Entry) it.next();
			if (entry != null) {
				String tmpKey = (String) entry.getKey();
				if (Integer.parseInt(key) < Integer.parseInt(tmpKey)) {
					key = tmpKey;
					value = entry.getValue();
				}
			}
		}
	}

	public Object prev() {
		if (!hasPrev())
			return null;
		key = Integer.toString(Integer.parseInt(key) - 1);
		value = records.get(key);
		return value;
	}

	public void setPoint(Object result) {
		if (records.size() == 0)
			return;
		for (Iterator it = records.entrySet().iterator(); it.hasNext();) {
			Entry entry = (Entry) it.next();
			if (entry != null && entry.getValue().toString().equals("")) {
				key = (String) entry.getKey();

				entry.setValue(result);
				value = result;
				break;
			}else if(entry != null && entry.getValue().toString().equals(result.toString())){
				key = (String) entry.getKey();
				entry.setValue(result);
				value = result;
				break;
			}
		}
	}
	
	public Object getPoint(Object result) {
		if (records.size() == 0)
			return null;
		for (Iterator it = records.entrySet().iterator(); it.hasNext();) {
			Entry entry = (Entry) it.next();
			if (entry != null && entry.getValue() == null && result == null) {
				key = (String) entry.getKey();
				value = entry.getValue();
				break;
			}else if(entry != null && entry.getValue().toString().equals(result.toString())){
				key = (String) entry.getKey();
				value = entry.getValue();
				break;
			}
		}
		return value;
	}

	public void setRecords(List results) {
		for (int i = 0; i < results.size(); i++) {
			records.put(Integer.toString(i+1), results.get(i));
		}
	}

	public void add(Object result) {
		records.put(Integer.toString(records.size() + 1), result);
	}

	public int getCounts() {
		return records.size();
	}

	/**
	 * @return Returns the key.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return Returns the records.
	 */
	public Map getRecords() {
		return records;
	}

	/**
	 * @param records
	 *            The records to set.
	 */
	public void setRecords(Map records) {
		this.records = records;
	}

	/**
	 * @return Returns the value.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(Object value) {
		this.value = value;
	}

}
