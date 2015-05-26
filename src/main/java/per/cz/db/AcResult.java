/**
 * 
 */
package per.cz.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class AcResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1185460849191495012L;
	private List<Object[]> values = new ArrayList<Object[]>();
	private String[] fieldNames = null;
	private String[] filedClassNames = null;
	private int[] fieldTypes = null;
	private Object other;
	public AcResult() {
	}

	public List<Object[]> getValues() {
		return values;
	}

	public void setValues(List<Object[]> values) {
		this.values = values;
	}

	public int getRecordCount() {
		return values.size();
	}

	public int getFieldCount() {
		return (fieldNames == null) ? 0 : fieldNames.length;
	}

	public String[] getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	public String[] getFiledClassNames() {
		return filedClassNames;
	}

	public void setFiledClassNames(String[] filedClassNames) {
		this.filedClassNames = filedClassNames;
	}

	public int[] getFieldTypes() {
		return fieldTypes;
	}

	public void setFieldTypes(int[] fieldTypes) {
		this.fieldTypes = fieldTypes;
	}

	public int getFieldIndex(String fieldName) {
		if (fieldNames == null)
			return -1;
		for (int i = 0; i < fieldNames.length; i++) {
			if (fieldName.equalsIgnoreCase(fieldNames[i])) {
				return i;
			}
		}
		return -1;
	}

	// index base 0
	public Object getObject(int rowIndex, int fieldIndex) {
		if (values.size() <= rowIndex)
			return null;
		if (fieldIndex < 0)
			return null;
		if (fieldNames == null) {
			if (values.get(0).length <= fieldIndex)
				return null;
		} else {
			if (fieldNames.length <= fieldIndex)
				return null;
		}
		return values.get(rowIndex)[fieldIndex];
	}

	public Object getObject(int rowIndex, String fieldName) {
		return getObject(rowIndex, getFieldIndex(fieldName));
	}

	public String getString(int rowIndex, int fieldIndex) {
		Object o = getObject(rowIndex, fieldIndex);
		return (o == null) ? null : o.toString();
	}

	public String getString(int rowIndex, String fieldName) {
		return getString(rowIndex, getFieldIndex(fieldName));
	}
	public void setString(int rowIndex, String fieldName, String fieldValue) {
		values.get(rowIndex)[getFieldIndex(fieldName)] = fieldValue;
	}
	public int getInt(int rowIndex, int fieldIndex) {
		int n = Integer.MIN_VALUE;
		Object o = getObject(rowIndex, fieldIndex);
		if (o != null) {
			if (o instanceof Integer) {
				n = ((Integer) o).intValue();
			} else {
				try {
					n = Integer.parseInt(o.toString());
				} catch (Exception e) {
				}
			}
		}
		return n;
	}

	public int getInt(int rowIndex, String fieldName) {
		return getInt(rowIndex, getFieldIndex(fieldName));
	}

	public Object getOther()
	{
		return other;
	}

	public void setOther(Object other)
	{
		this.other = other;
	}

	public void add(Object[] row) {
		values.add(row);
	}

	public StringBuffer toXML()
	{
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<this.values.size();i++)
		{
			buffer.append("\t<row num='"+i+"'>");
			buffer.append("\n");
			for(int j=0;j<this.fieldNames.length;j++)
			{
				buffer.append("\t\t<col fieldName='"+this.fieldNames[j]+"' fieldTypes='"+this.fieldTypes[j]+"' filedClassNames='"+this.filedClassNames[j]+"'>");
				if(this.getObject(i, j)!=null)
					buffer.append(this.getObject(i, j));
				buffer.append("</col>");
				buffer.append("\n");
			}
			buffer.append("\t</row>");
			buffer.append("\n");
		}
		return buffer;
	}
	public String toString()
	{
		return this.toXML().toString();
	}
}
