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
@Table(name = "note", catalog = "mlr2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n order by n.idnote"),
    @NamedQuery(name = "Note.findByIdnote", query = "SELECT n FROM Note n WHERE n.idnote = :idnote"),
    @NamedQuery(name = "Note.findByLibelle", query = "SELECT n FROM Note n WHERE n.libelle = :libelle"),
    @NamedQuery(name = "Note.findByPoint", query = "SELECT n FROM Note n WHERE n.point = :point")})
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDNOTE", nullable = false)
    private Integer idnote;
    @Basic(optional = false)
    @Column(name = "LIBELLE", nullable = false, length = 255)
    private String libelle;
    @Basic(optional = false)
    @Column(name = "POINT", nullable = false)
    private int point;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idnote")
    private Collection<Evaluation> evaluationCollection;

    public Note() {
    }

    public Note(Integer idnote) {
        this.idnote = idnote;
    }

    public Note(Integer idnote, String libelle, int point) {
        this.idnote = idnote;
        this.libelle = libelle;
        this.point = point;
    }

    public Integer getIdnote() {
        return idnote;
    }

    public void setIdnote(Integer idnote) {
        this.idnote = idnote;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @XmlTransient
    public Collection<Evaluation> getEvaluationCollection() {
        return evaluationCollection;
    }

    public void setEvaluationCollection(Collection<Evaluation> evaluationCollection) {
        this.evaluationCollection = evaluationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnote != null ? idnote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.idnote == null && other.idnote != null) || (this.idnote != null && !this.idnote.equals(other.idnote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Note[ idnote=" + idnote + " ]";
    }
    
    public Vector getLibelle(List<Note> ln){
        Iterator<Note> in=ln.iterator();
        Vector v=new Vector();
        while(in.hasNext()){
            v.addElement(in.next().getLibelle());
        }
        return v;
    }
    
     public Vector dataTable(List<Note> ln){
        Iterator<Note> in=ln.iterator();
        Vector v;Vector V=new Vector();
        Note n;
        while(in.hasNext()){
            v=new Vector();
            n=in.next();
            v.addElement(n.getLibelle());
            v.addElement(n.getPoint());
            V.addElement(v);
        }
        return V;
    }
    
    public Note getNote(List<Note> ln,String value){
        Iterator<Note> i=ln.iterator();Note n;
        while(i.hasNext()){
            n=i.next();
            if(n.getLibelle().equalsIgnoreCase(value))
                return n;
        }
        return null;
    }
    
}
