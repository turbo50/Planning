package utilites;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

 /* cette classe permet le chargement d'un arbre */
public class Theme {

    private String theme,pere,fils;
    private int idpere,cle;
    private Vector<String> themeFils;

    public String getFils() {
        return fils;
    }

    public void setFils(String fils) {
        this.fils = fils;
    }

    public String getPere() {
        return pere;
    }

    public void setPere(String pere) {
        this.pere = pere;
    }



    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public static Vector getListeFils(Vector<Theme> vt, String pere){
        Vector v=new Vector();
        for(int i=0; i<vt.size(); i++){
            if(vt.elementAt(i).getPere().trim().equalsIgnoreCase(pere.trim())){
                v.addElement(vt.elementAt(i).getFils().trim());
               
            }
        }
        return v;
    }
    
    public static void deleteCouplePereFils(Vector<Theme> vt, Vector fils){
        for(int i=0; i<fils.size(); i++){
            for(int j=0; j<vt.size(); j++){
                if(vt.elementAt(j).getFils().trim().equalsIgnoreCase(fils.elementAt(i).toString().trim()))
                    vt.removeElementAt(j);
            }
        }
    }
    
    
    
    /* la liste des themes pouvant contenir chacun  des sous themes*/
    public Vector<Theme> listeTheme(){
    	Vector<Theme> VT=new Vector<Theme>();
        MaConnexion con=new MaConnexion(); PreparedStatement ps=null;ResultSet rs=null;
        Theme t=new Theme(); 
   	   try{
   		   ps=con.getInstance().prepareStatement("select a.nom,case when b.nom is null then '' else b.nom end from perefils a ,perefils b where b.idPere=a.id order by a.idpere ");
   		   rs=ps.executeQuery();
   		   while(rs.next()){
                   t=new Theme();
                   t.setPere(rs.getString(1));
                   t.setFils(rs.getString(2));
                   VT.addElement(t);

              }
             }catch(Exception e){
              e.printStackTrace();
          }
           
           
    	return VT;
      }
    
    public Vector<Theme> listeTheme(String dateD,String dateF){
    	Vector<Theme> VT=new Vector<Theme>();
        MaConnexion con=new MaConnexion(); PreparedStatement ps=null;ResultSet rs=null;
        CallableStatement cs=null;
        Theme t=new Theme(); 
   	   try{
                   cs=con.getInstance().prepareCall("{call chargeArbrePlanning(?,?)}");
                   cs.setString(1, dateD);
                   cs.setString(2, dateF);
                   cs.executeUpdate();
                   
   		   ps=con.getInstance().prepareStatement("select a.nom,case when b.nom is null then '' else b.nom end from perefils a ,perefils b where b.idPere=a.id order by a.idpere,a.nom,b.nom ");
   		   rs=ps.executeQuery();
   		   while(rs.next()){
                       t=new Theme();
                       t.setPere(rs.getString(1));
                       t.setFils(rs.getString(2));
                       VT.addElement(t);

              }
             }catch(Exception e){
              e.printStackTrace();
          }finally{
               try{
                   ps.close(); cs.close();rs.close();
               }catch(Exception e1){e1.printStackTrace();}
           }
           
           
    	return VT;
      }
}