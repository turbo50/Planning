/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "t_horaire", catalog = "mlr2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "THoraire.findAll", query = "SELECT t FROM THoraire t"),
    @NamedQuery(name = "THoraire.findByIdtHoraire", query = "SELECT t FROM THoraire t WHERE t.idtHoraire = :idtHoraire"),
    @NamedQuery(name = "THoraire.findByHeureDebut", query = "SELECT t FROM THoraire t WHERE t.heureDebut = :heureDebut"),
    @NamedQuery(name = "THoraire.findByHeureFin", query = "SELECT t FROM THoraire t WHERE t.heureFin = :heureFin")
   // @NamedQuery(name = "THoraire.findByIdSite", query = "SELECT t FROM THoraire t, WHERE t.heureFin = :heureFin")
})
public class THoraire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDT_HORAIRE", nullable = false)
    private Integer idtHoraire;
    @Basic(optional = false)
    @Column(name = "HEURE_DEBUT", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
    @Basic(optional = false)
    @Column(name = "HEURE_FIN", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date heureFin;
    @ManyToMany(mappedBy = "tHoraireCollection")
    private Collection<Site> siteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtHoraire")
    private Collection<Planning> planningCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tHoraire")
    private Collection<EmploiT> emploiTCollection;
    
    public static String[] TabHeure={"00:00:00","00:30:00","01:00:00","01:30:00","02:00:00","02:30:00","03:00:00","03:30:00",
                               "04:00:00","04:30:00","05:00:00","05:30:00","06:00:00","06:30:00","07:00:00","07:30:00"
                               ,"08:00:00","08:30:00","09:00:00","09:30:00","10:00:00","10:30:00","11:00:00","11:30:00"
                               ,"12:00:00","12:30:00","13:00:00","13:30:00","14:00:00","14:30:00","15:00:00","15:30:00"
                               ,"16:00:00","16:30:00","17:00:00","17:30:00","18:00:00","18:30:00","19:00:00","19:30:00"
                               ,"20:00:00","20:30:00","21:00:00","21:30:00","22:00:00","22:30:00","23:00:00","23:30:00"
                              };
    

    public THoraire() {
    }

    public THoraire(Integer idtHoraire) {
        this.idtHoraire = idtHoraire;
    }

    public THoraire(Integer idtHoraire, Date heureDebut, Date heureFin) {
        this.idtHoraire = idtHoraire;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public Integer getIdtHoraire() {
        return idtHoraire;
    }

    public void setIdtHoraire(Integer idtHoraire) {
        this.idtHoraire = idtHoraire;
    }
    
    
    

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    @XmlTransient
    public Collection<Site> getSiteCollection() {
        return siteCollection;
    }

    public void setSiteCollection(Collection<Site> siteCollection) {
        this.siteCollection = siteCollection;
    }

    @XmlTransient
    public Collection<Planning> getPlanningCollection() {
        return planningCollection;
    }

    public void setPlanningCollection(Collection<Planning> planningCollection) {
        this.planningCollection = planningCollection;
    }

    @XmlTransient
    public Collection<EmploiT> getEmploiTCollection() {
        return emploiTCollection;
    }

    public void setEmploiTCollection(Collection<EmploiT> emploiTCollection) {
        this.emploiTCollection = emploiTCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtHoraire != null ? idtHoraire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof THoraire)) {
            return false;
        }
        THoraire other = (THoraire) object;
        if ((this.idtHoraire == null && other.idtHoraire != null) || (this.idtHoraire != null && !this.idtHoraire.equals(other.idtHoraire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.THoraire[ idtHoraire=" + idtHoraire + " ]";
    }
    
    public Vector getHeuresDebut(List<THoraire> l){
        SimpleDateFormat formater = new SimpleDateFormat("k:mm");
        Vector V=new Vector();
        Iterator<THoraire> i=l.iterator();
        while(i.hasNext()){
            V.addElement(formater.format(i.next().getHeureDebut()));
        }
        return V;
        
    }
    
    public Vector getHeureFins(String HD){
        int pos=0;
        for(int i=0; i<TabHeure.length; i++){
            if(TabHeure[i].equalsIgnoreCase(HD)){
                pos=i;
            }
        }
        Vector Vec=new Vector();
        for(int i=pos+1; i<TabHeure.length; i++){
            Vec.addElement(TabHeure[i]);
        }
        return Vec;
    }
    
     public Vector dataTable(List<THoraire> l){
        Vector V=new Vector();
        Vector v; THoraire th;
        SimpleDateFormat formater = new SimpleDateFormat("k:mm");
        Iterator<THoraire> i=l.iterator();
        while(i.hasNext()){
            th=i.next();
            v=new Vector();
            v.addElement(formater.format(th.getHeureDebut()));
            v.addElement(formater.format(th.getHeureFin()));
            V.addElement(v);
            
        }
        return V;
    }
    
}
