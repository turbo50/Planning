/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
@Table(name = "site", catalog = "mlr2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Site.findAll", query = "SELECT s FROM Site s"),
    @NamedQuery(name = "Site.findByIdsite", query = "SELECT s FROM Site s WHERE s.idsite = :idsite"),
    @NamedQuery(name = "Site.findByLieu", query = "SELECT s FROM Site s WHERE s.lieu = :lieu"),
    @NamedQuery(name = "Site.findByAltitude", query = "SELECT s FROM Site s WHERE s.altitude = :altitude"),
    @NamedQuery(name = "Site.findByLongitude", query = "SELECT s FROM Site s WHERE s.longitude = :longitude")
})
public class Site implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSITE", nullable = false)
    private Integer idsite;
    @Basic(optional = false)
    @Column(name = "LIEU", nullable = false, length = 128)
    private String lieu;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ALTITUDE", precision = 5, scale = 2)
    private Double altitude;
    @Column(name = "LONGITUDE", precision = 5, scale = 2)
    private Double longitude;
    @JoinTable(name = "site_t_horaire", joinColumns = {
        @JoinColumn(name = "IDSITE", referencedColumnName = "IDSITE", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "IDT_HORAIRE", referencedColumnName = "IDT_HORAIRE", nullable = false)})
    @ManyToMany
    private Collection<THoraire> tHoraireCollection;
    @JoinTable(name = "proc_site", joinColumns = {
        @JoinColumn(name = "IDSITE", referencedColumnName = "IDSITE", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "IDPROCLAMATEUR", referencedColumnName = "IDPROCLAMATEUR", nullable = false)})
    @ManyToMany
    private Collection<Proclamateur> proclamateurCollection;

    public Site() {
    }

    public Site(Integer idsite) {
        this.idsite = idsite;
    }

    public Site(Integer idsite, String lieu) {
        this.idsite = idsite;
        this.lieu = lieu;
    }

    public Integer getIdsite() {
        return idsite;
    }

    public void setIdsite(Integer idsite) {
        this.idsite = idsite;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public Collection<THoraire> getTHoraireCollection() {
        return tHoraireCollection;
    }

    public void setTHoraireCollection(Collection<THoraire> tHoraireCollection) {
        this.tHoraireCollection = tHoraireCollection;
    }

    @XmlTransient
    public Collection<Proclamateur> getProclamateurCollection() {
        return proclamateurCollection;
    }

    public void setProclamateurCollection(Collection<Proclamateur> proclamateurCollection) {
        this.proclamateurCollection = proclamateurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsite != null ? idsite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Site)) {
            return false;
        }
        Site other = (Site) object;
        if ((this.idsite == null && other.idsite != null) || (this.idsite != null && !this.idsite.equals(other.idsite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Site[ idsite=" + idsite + " ]";
    }
    
    public Vector dataTable(List<Site> l){
        Vector V=new Vector();
        Vector v;
        Iterator<Site> i=l.iterator();
        while(i.hasNext()){
            v=new Vector();
            v.addElement(i.next().getLieu());
            V.addElement(v);
            
        }
        return V;
    }
    
     public Vector dataTableTHoraire(Collection<THoraire> col){
        Vector V=new Vector();
        Vector v; THoraire th;
        SimpleDateFormat formater = new SimpleDateFormat("k:mm");
        Iterator<THoraire> i=col.iterator();
        while(i.hasNext()){
            th=i.next();
            v=new Vector();
            v.addElement(formater.format(th.getHeureDebut()));
            v.addElement(formater.format(th.getHeureFin()));
            V.addElement(v);
            
        }
        return V;
    }
    
    public Vector listSiteNom(List<Site> l){
        Vector V=new Vector();
        Iterator<Site> i=l.iterator();
        while(i.hasNext()){
            V.addElement(i.next().getLieu());
        }
        return V;
        
    }
    
    public Vector<Proclamateur> canShare(Collection<Proclamateur> col){
        Vector v=new Vector();
        Iterator i=col.iterator();Proclamateur p;
        while(i.hasNext()){
            v.addElement(i.next());
        }
        
        return v;
    }
    
    public Vector<Date> getHeureDeb(Site s){
        Vector<Date> vth=new Vector<Date>();
        Collection<THoraire> col=s.getTHoraireCollection();
        Iterator<THoraire> it=col.iterator();
        while(it.hasNext()){
            vth.addElement(it.next().getHeureDebut());
        }
        return vth;
        
    }
     public Vector getHeureDeb(Collection<Site> colSite){
         SimpleDateFormat formater = new SimpleDateFormat("k:mm");
        Vector vth=new Vector();
        Iterator<Site> its=colSite.iterator();
        Site s; Iterator<THoraire> itTh;
        while(its.hasNext()){
            s=its.next();
            itTh=s.getTHoraireCollection().iterator();
            while(itTh.hasNext()){
               vth.addElement(formater.format(itTh.next().getHeureDebut())); 
            }
            
        }
        return vth;
        
    }
     public Vector<Date> getHeureFin(Site s){
        Vector<Date> vth=new Vector<Date>();
        Collection<THoraire> col=s.getTHoraireCollection();
        Iterator<THoraire> it=col.iterator();
        while(it.hasNext()){
            vth.addElement(it.next().getHeureFin());
        }
        return vth;
        
    }
      public Collection<THoraire> effacerTHAt(Collection<THoraire> col, int pos){

        for(int i=0; i<col.size(); i++){
             if(i==pos){
                col.remove(col.toArray()[i]);
            }
        }
        return col;
        
    }
      
     public void setActiviteSite(String hD,String hF,String lieu){
		 Connection con=null; CallableStatement cs=null; PreparedStatement ps=null;
                
		String req="{call Stat_activite_site(?,?,?)}";
            
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req);
                        ps.setString(1,hD);
                        ps.setString(2,hF);
                        ps.setString(3,lieu);
                       
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
      public void setActiviteSiteGenerale(String hD,String hF){
		 Connection con=null; CallableStatement cs=null; PreparedStatement ps=null;
                
		String req="{call stat_activite_site_Generale(?,?)}";
            
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req);
                        ps.setString(1,hD);
                        ps.setString(2,hF);
                       
                       
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
