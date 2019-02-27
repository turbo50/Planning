package utilites;

import java.sql.*;

public class MaConnexion {
    private   Connection con=null;
   
   
    
    public  Connection getInstance(){
    	try{

    		Lis_Utilites lu=new Lis_Utilites("propriete/database.properties");


		   String User=lu.getValeurProperties("database.user");
		   String Pilote=lu.getValeurProperties("database.pilote");
		   String MDP=lu.getValeurProperties("database.password");		   
		   String Url=lu.getValeurProperties("database.url");

    		//Class.forName(Pilote).newInstance();
    		con=DriverManager.getConnection(Url, User, MDP);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return con;
    }
    
    public  void fermeConnexion(){
    	try{
    		con.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}