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
@Table(name = "congregation", catalog = "mlr2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Congregation.findAll", query = "SELECT c FROM Congregation c order by c.nom"),
    @NamedQuery(name = "Congregation.findByIdcongregation", query = "SELECT c FROM Congregation c WHERE c.idcongregation = :idcongregation"),
    @NamedQuery(name = "Congregation.findByNom", query = "SELECT c FROM Congregation c WHERE c.nom = :nom")})
public class Congregation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCONGREGATION", nullable = false)
    private Integer idcongregation;
    @Column(name = "NOM", length = 128)
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcongregation")
    private Collection<S43> s43Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcongregation")
    private Collection<Proclamateur> proclamateurCollection;

    public Congregation() {
    }

    public Congregation(Integer idcongregation) {
        this.idcongregation = idcongregation;
    }

    public Integer getIdcongregation() {
        return idcongregation;
    }

    public void setIdcongregation(Integer idcongregation) {
        this.idcongregation = idcongregation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public Collection<S43> getS43Collection() {
        return s43Collection;
    }

    public void setS43Collection(Collection<S43> s43Collection) {
        this.s43Collection = s43Collection;
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
        hash += (idcongregation != null ? idcongregation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Congregation)) {
            return false;
        }
        Congregation other = (Congregation) object;
        if ((this.idcongregation == null && other.idcongregation != null) || (this.idcongregation != null && !this.idcongregation.equals(other.idcongregation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Congregation[ idcongregation=" + idcongregation + " ]";
    }
    
     public Vector listToVector(List<Congregation> l){
        Vector V=new Vector();
        Iterator<Congregation> i=l.iterator();
        while(i.hasNext()){
            V.addElement(i.next().getNom());
        }
        return V;
        
    }
     
     public Vector dataTable(List<Congregation> list){
        Vector v=new Vector(); Vector V=new Vector();
        Iterator<Congregation> i=list.iterator();
        while(i.hasNext()){
            v.addElement(i.next().getNom());
            
            V.addElement(v);
            v=new Vector();
        }
        return V;
    }
    
}
