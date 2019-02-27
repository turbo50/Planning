/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilites.table.combo;

import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

/**
 *
 * @author daniel
 */

class MyComboBoxEditor extends DefaultCellEditor {
     public MyComboBoxEditor(Vector items) {
         super(new JComboBox(items));
     }
  
}
