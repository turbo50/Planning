/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author daniel
 */
@Embeddable
public class EmploiTPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDJOUR", nullable = false)
    private int idjour;
    @Basic(optional = false)
    @Column(name = "IDPROCLAMATEUR", nullable = false)
    private int idproclamateur;
    @Basic(optional = false)
    @Column(name = "IDT_HORAIRE", nullable = false)
    private int idtHoraire;

    public EmploiTPK() {
    }

    public EmploiTPK(int idjour, int idproclamateur, int idtHoraire) {
        this.idjour = idjour;
        this.idproclamateur = idproclamateur;
        this.idtHoraire = idtHoraire;
    }

    public int getIdjour() {
        return idjour;
    }

    public void setIdjour(int idjour) {
        this.idjour = idjour;
    }

    public int getIdproclamateur() {
        return idproclamateur;
    }

    public void setIdproclamateur(int idproclamateur) {
        this.idproclamateur = idproclamateur;
    }

    public int getIdtHoraire() {
        return idtHoraire;
    }

    public void setIdtHoraire(int idtHoraire) {
        this.idtHoraire = idtHoraire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idjour;
        hash += (int) idproclamateur;
        hash += (int) idtHoraire;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmploiTPK)) {
            return false;
        }
        EmploiTPK other = (EmploiTPK) object;
        if (this.idjour != other.idjour) {
            return false;
        }
        if (this.idproclamateur != other.idproclamateur) {
            return false;
        }
        if (this.idtHoraire != other.idtHoraire) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.EmploiTPK[ idjour=" + idjour + ", idproclamateur=" + idproclamateur + ", idtHoraire=" + idtHoraire + " ]";
    }
    
}
