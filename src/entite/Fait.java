/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.sql.*;

import java.util.Collection;
import java.util.Vector;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import utilites.MaConnexion;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "fait")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fait.findAll", query = "SELECT f FROM Fait f"),
    @NamedQuery(name = "Fait.findByIdfait", query = "SELECT f FROM Fait f WHERE f.idfait = :idfait"),
    @NamedQuery(name = "Fait.findByContenu", query = "SELECT f FROM Fait f WHERE f.contenu = :contenu"),
    @NamedQuery(name = "Fait.findBySexeacteur", query = "SELECT f FROM Fait f WHERE f.sexeacteur = :sexeacteur"),
    @NamedQuery(name = "Fait.findByNote", query = "SELECT f FROM Fait f WHERE f.note = :note")})
public class Fait implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFAIT")
    private Integer idfait;
    @Basic(optional = false)
    
    
    @Column(name = "CONTENU")
    private String contenu;
    @Column(name = "SEXEACTEUR")
    private String sexeacteur;
    @Basic(optional = false)
    @Column(name = "note")
    private String note;
    @JoinTable(name = "fait_planning", joinColumns = {
        @JoinColumn(name = "idFait", referencedColumnName = "IDFAIT")}, inverseJoinColumns = {
        @JoinColumn(name = "idPlanning", referencedColumnName = "IDPLANNING")})
    @ManyToMany
    private Collection<Planning> planningCollection;

    public Fait() {
    }

    public Fait(Integer idfait) {
        this.idfait = idfait;
    }

    public Fait(Integer idfait,  String contenu, String note) {
        this.idfait = idfait;
        
        this.contenu = contenu;
        this.note = note;
    }

    public Integer getIdfait() {
        return idfait;
    }

    public void setIdfait(Integer idfait) {
        this.idfait = idfait;
    }


    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getSexeacteur() {
        return sexeacteur;
    }

    public void setSexeacteur(String sexeacteur) {
        this.sexeacteur = sexeacteur;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @XmlTransient
    public Collection<Planning> getPlanningCollection() {
        return planningCollection;
    }

    public void setPlanningCollection(Collection<Planning> planningCollection) {
        this.planningCollection = planningCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfait != null ? idfait.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fait)) {
            return false;
        }
        Fait other = (Fait) object;
        if ((this.idfait == null && other.idfait != null) || (this.idfait != null && !this.idfait.equals(other.idfait))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Fait[ idfait=" + idfait + " ]";
    }
//    public Vector<Fait> getFait(entite.Planning p,accesData.AccesData ad){
//		 Connection con=null; CallableStatement cs=null; PreparedStatement ps=null; ResultSet rs=null;
//		String req="select idFait,idplanning,contenu,note from fait where idplanning=?";
//                int idplanning=0;Fait f;Vector<Fait> vf=new Vector<Fait>();
//		try{
//			con= new MaConnexion().getInstance();
//			ps=con.prepareCall(req);
//                        ps.setInt(1,p.getIdplanning());
//                        rs=ps.executeQuery();
//                        while(rs.next()){
//                            f=new Fait();
//                            f.setIdfait(rs.getInt(1));
////                            f.setIdplanning(ad.getEm().find(Planning.class,rs.getInt(2)));
//                            f.setContenu(rs.getString(3));
//                            f.setNote(rs.getString(4));
//                            vf.addElement(f);
//                            
//                        }
//                        
//			
//				
//			
//		}catch(Exception e1){
//			e1.printStackTrace();
//		}finally{
//			try{  ps.close(); con.close();}
//			catch(Exception e2){
//				e2.printStackTrace();
//			}
//		}
//                return vf;
//	}
    
    public Vector dataFait(Vector<Fait> vf){
        Vector v;
        Vector gv=new Vector();
        for(int i=0; i<vf.size(); i++){
            v=new Vector();
            v.addElement(vf.elementAt(i).getNote());
            v.addElement(vf.elementAt(i).getContenu());
            gv.addElement(v);
                   
        }
        return gv;
    }
    
    public void insertFait(Vector v){
		 Connection con=null; CallableStatement cs=null; Statement st=null; ResultSet rs=null;
		String req="{?= call insertFait(?,?)}";
                int idFait=0;
		try{
			con= new MaConnexion().getInstance();
			cs=con.prepareCall(req);
                        cs.registerOutParameter(1,java.sql.Types.INTEGER);
                        cs.setString(2,this.contenu);
                        cs.setString(3,this.getNote());
                        
                        cs.execute();
                        idFait = cs.getInt(1);
                        st=con.createStatement();
                        for(int i=0; i<v.size(); i++){
                            st.addBatch("insert into fait_planning(idfait,idplanning) values("+idFait+" ,"+Integer.parseInt(""+v.elementAt(i))+")");
                        }
                        st.executeBatch();
			
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{  st.close(); con.close();}
			catch(Exception e){
				e.printStackTrace();
			}
		}
                
	}
    
    public Vector<Fait> getFaits(Vector v){
		 Connection con=null; CallableStatement cs=null; PreparedStatement ps=null; ResultSet rs=null; Vector V=new Vector();
                 String liste=""; Fait f;
                 for(int i=0; i<v.size(); i++)
                     liste+=v.elementAt(i)+",";
                 liste=liste.substring(0, liste.lastIndexOf(","));
                 
		String req="select f.idfait,contenu,note from fait f, fait_planning fp where f.idfait=fp.idfait and fp.idplanning in("+liste+") group by f.idfait";
               
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req);
                       
                        rs=ps.executeQuery();
                        while(rs.next()){
                           f=new Fait();
                           f.setIdfait(rs.getInt(1));
                           f.setContenu(rs.getString(2));
                           f.setNote(rs.getString(3));
                           V.addElement(f);
                        }
                        
			
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{  ps.close(); con.close();}
			catch(Exception e){
				e.printStackTrace();
			}
		}
                return V;
	}
    
}
