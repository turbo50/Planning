/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "s43", catalog = "mlr2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IDACTIVITE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "S43.findAll", query = "SELECT s FROM S43 s"),
    @NamedQuery(name = "S43.findByIds43", query = "SELECT s FROM S43 s WHERE s.ids43 = :ids43"),
    @NamedQuery(name = "S43.findBySexe", query = "SELECT s FROM S43 s WHERE s.sexe = :sexe")})
public class S43 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDS43", nullable = false)
    private Integer ids43;
    @Column(name = "SEXE", length = 128)
    private String sexe;
    @JoinColumn(name = "IDACTIVITE", referencedColumnName = "IDACTIVITE", nullable = false)
    @OneToOne(optional = false)
    private Activite idactivite;
    @JoinColumn(name = "IDCONGREGATION", referencedColumnName = "IDCONGREGATION", nullable = false)
    @ManyToOne(optional = false)
    private Congregation idcongregation;

    public S43() {
    }

    public S43(Integer ids43) {
        this.ids43 = ids43;
    }

    public Integer getIds43() {
        return ids43;
    }

    public void setIds43(Integer ids43) {
        this.ids43 = ids43;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Activite getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(Activite idactivite) {
        this.idactivite = idactivite;
    }

    public Congregation getIdcongregation() {
        return idcongregation;
    }

    public void setIdcongregation(Congregation idcongregation) {
        this.idcongregation = idcongregation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ids43 != null ? ids43.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S43)) {
            return false;
        }
        S43 other = (S43) object;
        if ((this.ids43 == null && other.ids43 != null) || (this.ids43 != null && !this.ids43.equals(other.ids43))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.S43[ ids43=" + ids43 + " ]";
    }
    
}
