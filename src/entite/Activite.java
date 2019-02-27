/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.util.*;
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
@Table(name = "activite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activite.findAll", query = "SELECT a FROM Activite a"),
    @NamedQuery(name = "Activite.findByIdactivite", query = "SELECT a FROM Activite a WHERE a.idactivite = :idactivite"),
    @NamedQuery(name = "Activite.findByLivre", query = "SELECT a FROM Activite a WHERE a.livre = :livre"),
    @NamedQuery(name = "Activite.findByTract", query = "SELECT a FROM Activite a WHERE a.tract = :tract"),
    @NamedQuery(name = "Activite.findByBrochure", query = "SELECT a FROM Activite a WHERE a.brochure = :brochure"),
    @NamedQuery(name = "Activite.findByPeriodique", query = "SELECT a FROM Activite a WHERE a.periodique = :periodique"),
    @NamedQuery(name = "Activite.findByHeure", query = "SELECT a FROM Activite a WHERE a.heure = :heure")})
public class Activite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDACTIVITE")
    private Integer idactivite;
    @Column(name = "LIVRE")
    private Integer livre;
    @Column(name = "TRACT")
    private Integer tract;
    @Column(name = "S43")
    private Integer s43;
    @Column(name = "BROCHURE")
    private Integer brochure;
    @Column(name = "PERIODIQUE")
    private Integer periodique;
    @Column(name = "HEURE")
    private Integer heure;
    @JoinTable(name = "activite_planning", joinColumns = {
        @JoinColumn(name = "idActivite", referencedColumnName = "IDACTIVITE")}, inverseJoinColumns = {
        @JoinColumn(name = "idPlanning", referencedColumnName = "IDPLANNING")})
    @ManyToMany
    private List<Planning> planningList;

    public Activite() {
    }

    public Activite(Integer idactivite) {
        this.idactivite = idactivite;
    }

    public Integer getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(Integer idactivite) {
        this.idactivite = idactivite;
    }

    public Integer getS43() {
        return s43;
    }

    public void setS43(Integer s43) {
        this.s43 = s43;
    }

    
    public Integer getLivre() {
        return livre;
    }

    public void setLivre(Integer livre) {
        this.livre = livre;
    }

    public Integer getTract() {
        return tract;
    }

    public void setTract(Integer tract) {
        this.tract = tract;
    }

    public Integer getBrochure() {
        return brochure;
    }

    public void setBrochure(Integer brochure) {
        this.brochure = brochure;
    }

    public Integer getPeriodique() {
        return periodique;
    }

    public void setPeriodique(Integer periodique) {
        this.periodique = periodique;
    }

    public Integer getHeure() {
        return heure;
    }

    public void setHeure(Integer heure) {
        this.heure = heure;
    }

    @XmlTransient
    public List<Planning> getPlanningList() {
        return planningList;
    }

    public void setPlanningList(List<Planning> planningList) {
        this.planningList = planningList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactivite != null ? idactivite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activite)) {
            return false;
        }
        Activite other = (Activite) object;
        if ((this.idactivite == null && other.idactivite != null) || (this.idactivite != null && !this.idactivite.equals(other.idactivite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Activite[ idactivite=" + idactivite + " ]";
    }
    
    public void insertActivite(Vector v){
		 Connection con=null; CallableStatement cs=null; Statement st=null; ResultSet rs=null;
		String req="{?= call insertactivite(?,?,?,?,?)}";
                int idActivite=0;
		try{
			con= new MaConnexion().getInstance();
			cs=con.prepareCall(req);
                        cs.registerOutParameter(1,java.sql.Types.INTEGER);
                        cs.setInt(2,this.getLivre());
                        cs.setInt(3,this.getTract());
                        cs.setInt(4,this.getBrochure());
                        cs.setInt(5,this.getPeriodique());
                        cs.setInt(6,this.getS43());
                        cs.execute();
                        idActivite = cs.getInt(1);
                        st=con.createStatement();
                        for(int i=0; i<v.size(); i++){
                            st.addBatch("insert into activite_planning(idactivite,idplanning) values("+idActivite+" ,"+Integer.parseInt(""+v.elementAt(i))+")");
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
    
      public int getIdActivite(Vector v){
		 Connection con=null; CallableStatement cs=null; PreparedStatement ps=null; ResultSet rs=null;
                 String liste="";
                 for(int i=0; i<v.size(); i++)
                     liste+=v.elementAt(i)+",";
                 liste=liste.substring(0, liste.lastIndexOf(","));
		String req="select a.idactivite from activite a, activite_planning ap where a.idactivite=ap.idactivite and ap.idplanning in("+liste+")";
                int idActivite=0;
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req);
                       
                        rs=ps.executeQuery();
                        while(rs.next()){
                           idActivite=rs.getInt(1);
                        }
                        
			
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{  ps.close(); con.close();}
			catch(Exception e){
				e.printStackTrace();
			}
		}
                return idActivite;
	}
    
}
