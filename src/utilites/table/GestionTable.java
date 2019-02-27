package utilites.table;

import com.sun.rowset.internal.Row;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextField;

import utilites.presentation.Modele;

public class GestionTable {
	
	
	public void chargeTable(JTable table, Vector<Vector> Donnee,String[]pv, Class[] tabClass){
		
		table.setRowMargin(2);
		table.setAutoResizeMode(1);
		table.setModel(new Modele(Donnee, pv,tabClass));
		/*for(int i=0; i<Donnee.size(); i++){
			V=Donnee.elementAt(i);
			for(int j=0; j<V.size(); j++){
				
				table.setValueAt(V.elementAt(j),i,j);
			}
		}*/
		table.validate();
                
               // table.getParent().validate();
		
		
	}
	
        public void insertTable(JTable table,String[]pv, int[] tabCol,int ligne){
		
		table.setRowMargin(2);
		table.setAutoResizeMode(1);
		for(int i=0; i<pv.length; i++){
                    table.setValueAt(pv[i], ligne, tabCol[i]);
                }
		table.validate();
		
		
	}
        
	//methode qui remplit des textField avec une ligne selectionee dans la table, en fonction de la valeur de colone[i] 
    public void actionClicTable(JTable table,JTextField[] tf,int[]colone){
    	
    	
          int rwowS=table.getSelectedRow();
         
        
          ici:for(int i=0; i<tf.length; i++){
    	          for(int j=0; j<colone.length; j++){
        		       if(colone[j]==1){//On peut lire cette colone dans la table
            		      tf[i].setText(table.getValueAt(rwowS, j).toString());
        	              tf[i].revalidate();
        	              colone[j]=0;//on marque cette colone comme lu
        	              continue ici; //on passe au prochain tour de boucle
        	           }
        	
                   }
        		
               }
    }
    
  }
