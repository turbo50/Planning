/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import utilites.MaConnexion;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "infos_diverse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfosDiverse.findAll", query = "SELECT i FROM InfosDiverse i"),
    @NamedQuery(name = "InfosDiverse.findByResponsable", query = "SELECT i FROM InfosDiverse i WHERE i.responsable = :responsable"),
    @NamedQuery(name = "InfosDiverse.findByTel", query = "SELECT i FROM InfosDiverse i WHERE i.tel = :tel"),
    @NamedQuery(name = "InfosDiverse.findByIdInfos", query = "SELECT i FROM InfosDiverse i WHERE i.idInfos = :idInfos")})
public class InfosDiverse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Responsable")
    private String responsable;
    @Column(name = "Tel")
    private String tel;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInfos")
    private Integer idInfos;

    public InfosDiverse() {
    }

    public InfosDiverse(Integer idInfos) {
        this.idInfos = idInfos;
    }

    public InfosDiverse(Integer idInfos, String responsable) {
        this.idInfos = idInfos;
        this.responsable = responsable;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getIdInfos() {
        return idInfos;
    }

    public void setIdInfos(Integer idInfos) {
        this.idInfos = idInfos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInfos != null ? idInfos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfosDiverse)) {
            return false;
        }
        InfosDiverse other = (InfosDiverse) object;
        if ((this.idInfos == null && other.idInfos != null) || (this.idInfos != null && !this.idInfos.equals(other.idInfos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.InfosDiverse[ idInfos=" + idInfos + " ]";
    }
    
    public Vector dataTable(List<InfosDiverse> list){
        Vector v=new Vector(); Vector V=new Vector();InfosDiverse id;
        Iterator<InfosDiverse> i=list.iterator();
        while(i.hasNext()){
            id=i.next();
            v.addElement(id.getResponsable());
            v.addElement(id.getTel());
            V.addElement(v);
            v=new Vector();
        }
        return V;
    }
    
//    public class Infos_Diverse {
//    
//      public void ajouteInfos(String respo,String contact,String sms){
//		Connection con=null; CallableStatement cs=null; PreparedStatement ps=null;
//		String req1="{insert into infos_diverse(responsable,message) values(?,?)}";
//                String req2="{insert into message_divers(sms) values(?)}";
//               
//		try{
//			con= new MaConnexion().getInstance();
//			ps=con.prepareCall(req1);
//                        ps.setString(1, respo);
//                        ps.setString(2, contact);
//                        
//                        ps.executeUpdate();
//                        
//                        ps=con.prepareCall(req2);
//                        ps.setString(1, sms);
//                        ps.executeUpdate();
//                        
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			try{  ps.close(); con.close();}
//			catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//                
//	}
//      
//      public void updateInfos(String respo,String contact,String sms,int idinfos,int idmessage){
//		Connection con=null; CallableStatement cs=null; PreparedStatement ps=null;     
//		String req1="{update infos_diverse set responsable=?, contact=? where idinfos=?}";
//                String req2="{update  message_divers set sms=? where idmessage=?}";
//               
//		try{
//			con= new MaConnexion().getInstance();
//			ps=con.prepareCall(req1);
//                        ps.setString(1, respo);
//                        ps.setString(2, contact);
//                        ps.setInt(3, idinfos);
//                        
//                        ps.executeUpdate();
//                        
//                        ps=con.prepareCall(req2);
//                        ps.setString(1, sms);
//                        ps.setInt(2, idmessage);
//                        ps.executeUpdate();
//                        
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			try{  ps.close(); con.close();}
//			catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//                
//	}
//      
//      public void deleteInfos(int id){
//		Connection con=null; CallableStatement cs=null; PreparedStatement ps=null;
//		String req1="{delete from infos_diverse where idinfos=?}";
//		try{
//			con= new MaConnexion().getInstance();
//			ps=con.prepareCall(req1);
//                        ps.setInt(1, id);           
//                        ps.executeUpdate();                      
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			try{  ps.close(); con.close();}
//			catch(Exception e){
//				e.printStackTrace();
//			}
//		}
                
	}
    
    

