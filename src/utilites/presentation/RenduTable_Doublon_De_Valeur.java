/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilites.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author daniel
 */
public class RenduTable_Doublon_De_Valeur extends JPanel implements TableCellRenderer{
    JLabel labelR=new JLabel();
    private String nomP;

   JPanel P=new JPanel(new BorderLayout());
   public RenduTable_Doublon_De_Valeur(String nomP)
   {
	   this.setOpaque(true);
           this.nomP=nomP;
	   
   }
  
  public Component getTableCellRendererComponent(JTable t, Object o, boolean b1,boolean b2, int i,int j)
  { 
     
      String nom=o.toString();
      String nomT;
      //on fixe d'abord la ligne en fond blanc
      P.setBackground(new Color(255,255,255));  
      labelR.setText(nom);
      P.add(labelR,BorderLayout.NORTH);
      
      
      nomT=t.getValueAt(i, 0).toString();
      if(nomT.equalsIgnoreCase(nomP)){
                P.setBackground(new Color(51,204,204));
		labelR.setText(nom);
                P.removeAll();
		P.add(labelR,BorderLayout.NORTH);
      }
    
		
      labelR.revalidate();
      return P;
	
  }
}
