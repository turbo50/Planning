/**
 * Modele.java
 *
 * Created on 05-02-2004 12:36 AM
 *
 */
package utilites.presentation;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class Modele extends AbstractTableModel{
	private Vector<Vector>GV;
	private String[] pv;
	private Class[] tabClass;
	
	
	
	
	
	
	public Modele(Vector<Vector>GV,String []pv,Class[] tabClass)
	{ this.GV=GV;
	  this.pv=pv;
	  this.tabClass=tabClass;
	}
	public int getRowCount()
    {  return GV.size();
	}
	public int getColumnCount()
	{ return  pv.length;
	}
	public Object getValueAt(int ligne, int colone)
	{ Vector<Object>v=(Vector)GV.elementAt(ligne);
	  Object o=v.elementAt(colone);
	  return o;
	}
	public Class getColumnClass(int columnIndex) {
		return tabClass[columnIndex];
	}
	public String getColumnName(int i)
	{ 
		return ""+pv[i];
	
	}
	public void setValueAt(int ligne,int colone , Object value)
	{ Vector<Object>v=(Vector)GV.elementAt(ligne);
	  v.insertElementAt(value, colone);
	  
      }
}

