/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.util.HashMap;
import javax.swing.UIManager;




/**
 *
 * @author daniel
 */




public class Principale
{
    public static void main(String[] args)
    {
//        Date d=new Date();
//        DateFormat df=DateFormat.getDateInstance();
//        //System.out.format("Local time: %tT", Calendar.getInstance());
//        
//        System.out.format( "heure: "+d.);
//          HashT m=new HashMap();
//          m.put("a",1);
//          m.put("a",2);
//          m.put("b", 3);
//          System.out.println(m.get("a"));
//       
     
         try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
         }
        new FenetreDeFond().setVisible(true);
      
        }
       
}