/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilites.table.combo;

import java.awt.Component;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author daniel
 */
class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
  public MyComboBoxRenderer(Vector items) {
    super(items);
  }

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
      boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      super.setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(table.getBackground());
    }
    setSelectedItem(value);
    return this;
  }
}
