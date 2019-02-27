
package utilites.presentation;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class RenduTable extends JPanel implements TableCellRenderer
{  JLabel labelR=new JLabel();

   JPanel P=new JPanel(new BorderLayout());
   public RenduTable()
   {
	   this.setOpaque(true);
	   
   }
  
  public Component getTableCellRendererComponent (JTable t, Object o, boolean b1,boolean b2, int i,int j)
  { int ligne =t.getRowCount(); 
    String s="  "+o;
	
    if(i%2==0)
       {
		
		P.setBackground(new Color(204,204,204));
		labelR.setText(s);
		P.add(labelR,BorderLayout.NORTH);
	    }
	    
	else{
		P.setBackground(Color.WHITE);
		labelR.setText(s);
		P.add(labelR,BorderLayout.NORTH);
	}
		
		
    labelR.revalidate();
    return P;
	
  }
}