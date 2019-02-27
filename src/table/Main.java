import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class Main {
  public static void main(String[] argv) throws Exception {
    JTable table = new JTable();
    DefaultTableModel model = (DefaultTableModel) table.getModel();

    model.addColumn("A", new Object[] { "item1" });
    model.addColumn("B", new Object[] { "item2" });

    String[] values = new String[] { "1", "2", "3" };

    TableColumn col = table.getColumnModel().getColumn(0);
    col.setCellEditor(new MyComboBoxEditor(values));
    col.setCellRenderer(new MyComboBoxRenderer(values));
    table.setModel(model);
    JFrame jf=new JFrame();
    Container c=jf.getContentPane();
    JScrollPane jsp=new JScrollPane();
    jsp.setViewportView(table);
    c.setLayout(new BorderLayout());
    c.add(jsp,BorderLayout.CENTER);
    jf.setVisible(true);
  }
}

class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
  public MyComboBoxRenderer(String[] items) {
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

class MyComboBoxEditor extends DefaultCellEditor {
  public MyComboBoxEditor(String[] items) {
    super(new JComboBox(items));
  }
}