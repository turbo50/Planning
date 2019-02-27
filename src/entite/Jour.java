/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "jour", catalog = "mlr2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jour.findAll", query = "SELECT j FROM Jour j order by j.idjour"),
    @NamedQuery(name = "Jour.findByIdjour", query = "SELECT j FROM Jour j WHERE j.idjour = :idjour"),
    @NamedQuery(name = "Jour.findByNomjour", query = "SELECT j FROM Jour j WHERE j.nomjour = :nomjour")})
public class Jour implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDJOUR", nullable = false)
    private Integer idjour;
    @Basic(optional = false)
    @Column(name = "NOMJOUR", nullable = false, length = 128)
    private String nomjour;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idjour")
    private Collection<Planning> planningCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jour")
    private Collection<EmploiT> emploiTCollection;

    public Jour() {
    }

    public Jour(Integer idjour) {
        this.idjour = idjour;
    }

    public Jour(Integer idjour, String nomjour) {
        this.idjour = idjour;
        this.nomjour = nomjour;
    }

    public Integer getIdjour() {
        return idjour;
    }

    public void setIdjour(Integer idjour) {
        this.idjour = idjour;
    }

    public String getNomjour() {
        return nomjour;
    }

    public void setNomjour(String nomjour) {
        this.nomjour = nomjour;
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
        hash += (idjour != null ? idjour.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jour)) {
            return false;
        }
        Jour other = (Jour) object;
        if ((this.idjour == null && other.idjour != null) || (this.idjour != null && !this.idjour.equals(other.idjour))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Jour[ idjour=" + idjour + " ]";
    }
    
    public Vector getAllDays(List<Jour> l){
        Vector v=new Vector();
        Iterator<Jour> i=l.iterator();
        while(i.hasNext()){
            v.addElement(i.next());
        }
        return v;
    }
    
}
