/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import entite.Fait;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import utilites.MaConnexion;

/**
 *
 * @author daniel
 */
public class Infos_Diverse {
    
      public void ajouteInfos(String respo,String contact,String sms){
		Connection con=null; CallableStatement cs=null; PreparedStatement ps=null;
		String req1="{insert into infos_diverse(responsable,message) values(?,?)}";
                String req2="{insert into message_divers(sms) values(?)}";
               
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req1);
                        ps.setString(1, respo);
                        ps.setString(2, contact);
                        
                        ps.executeUpdate();
                        
                        ps=con.prepareCall(req2);
                        ps.setString(1, sms);
                        ps.executeUpdate();
                        
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{  ps.close(); con.close();}
			catch(Exception e){
				e.printStackTrace();
			}
		}
                
	}
      
      public void updateInfos(String respo,String contact,String sms,int idinfos,int idmessage){
		Connection con=null; CallableStatement cs=null; PreparedStatement ps=null;     
		String req1="{update infos_diverse set responsable=?, contact=? where idinfos=?}";
                String req2="{update  message_divers set sms=? where idmessage=?}";
               
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req1);
                        ps.setString(1, respo);
                        ps.setString(2, contact);
                        ps.setInt(3, idinfos);
                        
                        ps.executeUpdate();
                        
                        ps=con.prepareCall(req2);
                        ps.setString(1, sms);
                        ps.setInt(2, idmessage);
                        ps.executeUpdate();
                        
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{  ps.close(); con.close();}
			catch(Exception e){
				e.printStackTrace();
			}
		}
                
	}
      
      public void deleteInfos(int id){
		Connection con=null; CallableStatement cs=null; PreparedStatement ps=null;
		String req1="{delete from infos_diverse where idinfos=?}";
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req1);
                        ps.setInt(1, id);           
                        ps.executeUpdate();                      
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{  ps.close(); con.close();}
			catch(Exception e){
				e.printStackTrace();
			}
		}
                
	}
    
}
