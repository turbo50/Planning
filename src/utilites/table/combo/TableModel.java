/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilites.table.combo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

class TableModel extends AbstractTableModel {
      private Vector<Vector> rowData ;
      private String columnNames[];
      private int numberCol;// colone qui contient le combo
      private Class[] tabClass;
      
      public TableModel(Vector<Vector> data,String[] col,Class[] tabClass,int numberCol){
          this.columnNames=col;
          this.rowData=data;
          this.numberCol=numberCol;
          this.tabClass=tabClass;
      }

  public int getColumnCount() {
    return columnNames.length;
  }

  public String getColumnName(int column) {
    return columnNames[column];
  }

  public int getRowCount() {
    return rowData.size();
  }

  public Object getValueAt(int row, int column) {
    return rowData.elementAt(row).elementAt(column);
  }

  public Class getColumnClass(int column) {
    return tabClass[column];
  }

  public void setValueAt(Object value, int row, int column) {
          Vector<Object>v=(Vector)rowData.elementAt(row);
	  v.insertElementAt(value, column);
  }

  public boolean isCellEditable(int row, int column) {
    return (column ==numberCol);
  }
  
  

}
