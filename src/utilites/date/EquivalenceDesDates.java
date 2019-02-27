package utilites.date;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.StringTokenizer;


import utilites.MaConnexion;

public class EquivalenceDesDates  {

 /* les dates en anglais sont au format 12 september 2013 
  * elles sont traduite au format 12 septembre 2013
  * celles en frnacais sont au format 12 sept. 2013
  * elles sont traduite au format 2013-09-12
  */
  private  DateFormatSymbols dfsFR = new DateFormatSymbols(Locale.FRENCH);
  private  DateFormatSymbols dfsEN = new DateFormatSymbols(Locale.ENGLISH);
 // private String[] moisCourtsFR ;
 // private String[] moisCourtsEN;
  private String[] moisFR; 
  private String[] moisEN; 
 
  
  
   public EquivalenceDesDates() {
	
	//this.moisCourtsFR =  dfsFR.getShortMonths();
	//this.moisCourtsEN = dfsEN.getShortMonths();
	this. moisFR=dfsFR.getMonths();
	this. moisEN=dfsEN.getMonths();
  }
  
   public String getMonths_Fr(String months_En){
	   for(int i=0; i<moisEN.length; i++){
		   if(moisEN[i].equalsIgnoreCase(months_En))
			   return moisFR[i];
	   }
	   return null;   
   }
   
   public String getMonths_En(String months_Fr){
	   String mois;
	   if(months_Fr.lastIndexOf(".")==-1)
			   mois=months_Fr;
	   else{
		   mois=months_Fr.substring(0, months_Fr.length());
	   }
	   for(int i=0; i<moisFR.length; i++){
		   if(moisFR[i].startsWith(mois.toLowerCase()))
			   return moisEN[i];
	   }
	   return null;   
   }
   
   public int getPosMonths_En(String months_Fr){
	   String mois;
	   if(months_Fr.lastIndexOf(".")==-1)
			   mois=months_Fr;
	   else{
		   mois=months_Fr.substring(0, months_Fr.length()-1);
	   }
	   for(int i=0; i<moisFR.length; i++){
		   if(moisFR[i].startsWith(mois.toLowerCase()))
			   return i+1;
	   }
	   return 0;   
   }
   
   
   // traduction de la date
   public String getDateFrench(String Date_En){
	   StringBuffer sb=new StringBuffer();
	   StringTokenizer st=new StringTokenizer(Date_En, " ");
	   sb.append(st.nextToken());
	   sb.append(" ");
	   sb.append(getMonths_Fr(st.nextToken()));
	   sb.append(" ");
	   sb.append(st.nextToken());
	   return sb.toString();
   }
   
   public String getDateEnglish(String Date_Fr){
	   StringBuffer sb=new StringBuffer();
	   String day,months,year;
	   
	   StringTokenizer st=new StringTokenizer(Date_Fr, " ");
	   day=st.nextToken();
	   months=""+getPosMonths_En(st.nextToken());
	   year=st.nextToken();
	   
	   sb.append(year);
	   sb.append("-");
	   sb.append(months);
	   sb.append("-");
	   sb.append(day);
	   return sb.toString();
	   
	   
   }
   

}