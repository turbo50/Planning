/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "emploi_t", catalog = "mlr2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmploiT.findAll", query = "SELECT e FROM EmploiT e"),
    @NamedQuery(name = "EmploiT.findByIdjour", query = "SELECT e FROM EmploiT e WHERE e.emploiTPK.idjour = :idjour"),
    @NamedQuery(name = "EmploiT.findByIdproclamateur", query = "SELECT e FROM EmploiT e WHERE e.emploiTPK.idproclamateur = :idproclamateur"),
    @NamedQuery(name = "EmploiT.findByIdtHoraire", query = "SELECT e FROM EmploiT e WHERE e.emploiTPK.idtHoraire = :idtHoraire"),
    @NamedQuery(name = "EmploiT.findByIDEmploit", query = "SELECT e FROM EmploiT e WHERE e.iDEmploit = :iDEmploit")})
public class EmploiT implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmploiTPK emploiTPK;
    @Basic(optional = false)
    @Column(name = "IDEmploi_t", nullable = false)
    private int iDEmploit;
    @JoinColumn(name = "IDT_HORAIRE", referencedColumnName = "IDT_HORAIRE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private THoraire tHoraire;
    @JoinColumn(name = "IDPROCLAMATEUR", referencedColumnName = "IDPROCLAMATEUR", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proclamateur proclamateur;
    @JoinColumn(name = "IDJOUR", referencedColumnName = "IDJOUR", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Jour jour;

    public EmploiT() {
    }

    public EmploiT(EmploiTPK emploiTPK) {
        this.emploiTPK = emploiTPK;
    }

    public EmploiT(EmploiTPK emploiTPK, int iDEmploit) {
        this.emploiTPK = emploiTPK;
        this.iDEmploit = iDEmploit;
    }

    public EmploiT(int idjour, int idproclamateur, int idtHoraire) {
        this.emploiTPK = new EmploiTPK(idjour, idproclamateur, idtHoraire);
    }

    public EmploiTPK getEmploiTPK() {
        return emploiTPK;
    }

    public void setEmploiTPK(EmploiTPK emploiTPK) {
        this.emploiTPK = emploiTPK;
    }

    public int getIDEmploit() {
        return iDEmploit;
    }

    public void setIDEmploit(int iDEmploit) {
        this.iDEmploit = iDEmploit;
    }

    public THoraire getTHoraire() {
        return tHoraire;
    }

    public void setTHoraire(THoraire tHoraire) {
        this.tHoraire = tHoraire;
    }

    public Proclamateur getProclamateur() {
        return proclamateur;
    }

    public void setProclamateur(Proclamateur proclamateur) {
        this.proclamateur = proclamateur;
    }

    public Jour getJour() {
        return jour;
    }

    public void setJour(Jour jour) {
        this.jour = jour;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emploiTPK != null ? emploiTPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmploiT)) {
            return false;
        }
        EmploiT other = (EmploiT) object;
        if ((this.emploiTPK == null && other.emploiTPK != null) || (this.emploiTPK != null && !this.emploiTPK.equals(other.emploiTPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.EmploiT[ emploiTPK=" + emploiTPK + " ]";
    }
    
    public Collection<EmploiT> effacerETAt(Collection<EmploiT> col, int pos){
        Collection<EmploiT> col2=null;
        Iterator<EmploiT> it=col.iterator();
        List<EmploiT> list=null;
        EmploiT et;
        
        for(int i=0; i<col.size(); i++){
             if(i==pos){
                col.remove(col.toArray()[i]);
            }
        }
        return col;
        
    }
    
      public Collection<EmploiT> changeETAt(Collection<EmploiT> col,EmploiT et, int pos){
        
        for(int i=0; i<col.size(); i++){
            if(i==pos){
                col.remove(col.toArray()[i]);
                col.add(et);
            }
        }
        return col;   
      }
     
    
    public EmploiT getETAt(Collection<EmploiT> col, int pos){
         
        for(int i=0; i<col.size(); i++){
            if(i==pos){
               return (EmploiT)col.toArray()[i];
            }
            
        }
            
        return null;
    }
    
    
    
}
