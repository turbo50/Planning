/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* action a definer sur les champs qui doivent conternir les  dates
 * 
	*	nom_du_champ_de_text.addFocusListener(new java.awt.event.FocusAdapter()
	*	                                   { public void focusLost(java.awt.event.FocusEvent evt) 
	*	                                        { String date = nom_du_champ_de_text.getText(); 
	*	                                          new FDate().setDate(date,nom_du_champ_de_text,nom_du_calendrier);
	*	                                        } 
	*	                                   });
   */

/* action a definer sur les calendrier eux meme
 * nom_du_calendrier.addPropertyChangeListener(new java.beans.PropertyChangeListener(){
            public void propertyChange(java.beans.PropertyChangeEvent evt){
         	   if (evt.getNewValue() instanceof Date) 
         		   new FDate().setDate((Date)evt.getNewValue(),nom_du_champ_de_text,nom_du_calendrier); 
            } 
     });
 */


package utilites.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JTextField;

import org.jbundle.thin.base.screen.jcalendarbutton.*;


/**
 *
 * @author Administrateur
 */
public class FDate {
    public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    public static DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
    public static DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
    /* transforme un String en type sql.date */
    public  java.sql.Date getStringToDate(String sDate)  {
         SimpleDateFormat sdf=null;
         try{
              //Declaration du SimpleDateFormat
               sdf = new SimpleDateFormat ("dd/MM/yyyy");
             //Conversion en java.util.Date
               java.util.Date date = sdf.parse(sDate);
             //Si tu as besoin d'un sql date
               return  new java.sql.Date(date.getTime());
         }catch(Exception e){
            return null;
         }         
    }
    
    public String formatDateFrom_FR_to_SQL(String date_FR,String separateur){
        StringTokenizer st=new StringTokenizer(date_FR,separateur);
        String year; String mois;String day;
        day=st.nextToken(); mois=st.nextToken(); year=st.nextToken();
        return year+"-"+mois+"-"+day;
    }
    
    //methode pour fixer la date  a partir de JCalendarButton
    public void setDate(String dateString,JTextField tf,JCalendarButton jcb){ 
    	java.util.Date date = null; 
    	try { 
    		if ((dateString != null) && (dateString.length() > 0)) 
    			date = dateFormat.parse(dateString);
    		} catch (Exception e) { 
    			date = null; 
    		} 
    	this.setDate(date,tf,jcb);
    	} 
    
    public void setTime(String timeString,JTextField jtf,JTimeButton jtb)
    {
		java.util.Date time = null;
		try	{
            if ((timeString != null) && (timeString.length() > 0))
                time = timeFormat.parse(timeString);
		} catch (Exception e)	{
            time = null;
		}
        this.setTime(time,jtf,jtb);
    }
    
    public void setTime(Date time,JTextField jtf,JTimeButton jtb)
    {
        String timeString = "";
        if (time != null)
    		timeString = timeFormat.format(time);
        jtf.setText(timeString);
        jtb.setTargetDate(time);
    }
    
    public void setDate(java.util.Date date, JTextField tf,JCalendarButton jcb) {
    	String dateString = "";
    	if (date != null)
    		dateString = dateFormat.format(date);
    	tf.setText(dateString);
    	jcb.setTargetDate(date);
    	tf.revalidate();
    }
    
    //methode qui revoi un composant de la date Jour/mois/Annee
    public int getComposanteDate(String composant,String date,String separateur){
        StringTokenizer st=new StringTokenizer(date,separateur);
        String year; String mois;String day;
        System.out.println("Date :"+date);
        day=st.nextToken(); mois=st.nextToken(); year=st.nextToken();
        if(composant.equalsIgnoreCase("jour"))
            return Integer.parseInt(day);
        else if(composant.equalsIgnoreCase("mois")){
            
            return Integer.parseInt(mois);
        }
        else{
           
            return Integer.parseInt(year);
        }
    }
    //methode qui fait un tri des date sous le format DD MM YYYY
    public Vector triDate(Vector<String> vDate, String separateur){
        String aux="";
        
        for(int i=0; i<vDate.size()-1; i++){
           for(int j=i+1; j<vDate.size(); j++){
              if(getComposanteDate("annee", vDate.elementAt(i), separateur)>getComposanteDate("annee", vDate.elementAt(j), separateur)){
                  aux=vDate.elementAt(i);
                  vDate.setElementAt(vDate.elementAt(j), i);
                  vDate.setElementAt(aux,j);
              }else if(getComposanteDate("mois", vDate.elementAt(i), separateur)>getComposanteDate("mois", vDate.elementAt(j), separateur)){
                  aux=vDate.elementAt(i);
                  vDate.setElementAt(vDate.elementAt(j), i);
                  vDate.setElementAt(aux,j); 
              }
              else if(getComposanteDate("jour", vDate.elementAt(i), separateur)>getComposanteDate("jour", vDate.elementAt(j), separateur)){
              
                  aux=vDate.elementAt(i);
                  vDate.setElementAt(vDate.elementAt(j), i);
                  vDate.setElementAt(aux,j);
              }
            
                  
           
        }
        
     }
        return vDate;
   }

}
