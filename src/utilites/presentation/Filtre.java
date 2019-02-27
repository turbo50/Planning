/**
 * Filtre.java
 *
 * Created on 18-06-2011 05:11 PM
 *
 */
package utilites.presentation;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Filtre extends AbstractTableModel //implements MouseListener
{ private Modele modele;
	private Ligne [] ligne;
	private int coloneTri;
	public Filtre(Modele modele)
	{ this.modele=modele;
	   ligne=new Ligne[modele.getRowCount()];
	   for(int i=0; i<ligne.length; i++)
	      ligne[i]=new Ligne(i);
	}
	
	public Object getValueAt(int i,int j)
    { return modele.getValueAt(ligne[i].index,j);
    }
	
   public int getRowCount()
   {  return modele.getRowCount();
   }
   
   public int getColumnCount()
   { return modele.getColumnCount();
   }
   
  public String getColumnName(int i)
  { return modele.getColumnName(i);
  }
  
  public Class getColumnClass(int i,Object o)
  {   Object o1=modele.getColumnClass(i);
	  return o1.getClass();
	  //return (i==1 || i==2) ? Number.class:Object.class;
  }
  
   public void sort(int c){
      coloneTri = c; 
      try{
          Arrays.sort(ligne);
          fireTableDataChanged();
      }catch (RuntimeException e){System.out.println("Echec lors de la comparaison des données de la table");
	  }  // les données ne sont pas comparables !
   }

  public void addEcouteur(final JTable table)
  {
    table.getTableHeader().addMouseListener(new MouseAdapter()
	          { public void mouseClicked(MouseEvent me)
			    { 
				  int tableCol=table.columnAtPoint(me.getPoint());
				  int modeleCol=table.convertColumnIndexToModel(tableCol);
				  sort(modeleCol);
				}
			  });
  }
  
  private class Ligne implements Comparable
  {
    public int index;
	public Ligne(int i)
	{ index=i;
	}
	
    public int compareTo(Object autre)
    {
	  Ligne autreLigne=(Ligne)autre;
	  Object Cellule=modele.getValueAt(index,coloneTri);
	  Object autreCellule=modele.getValueAt(autreLigne.index,coloneTri);
	  return ((Comparable)Cellule).compareTo(autreCellule);
	}
  }
  
}
