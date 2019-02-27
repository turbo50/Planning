/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "proclamateur", catalog = "mlr2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proclamateur.findAll", query = "SELECT p FROM Proclamateur p order by p.nom"),
    @NamedQuery(name = "Proclamateur.findByIdproclamateur", query = "SELECT p FROM Proclamateur p WHERE p.idproclamateur = :idproclamateur"),
    @NamedQuery(name = "Proclamateur.findByNom", query = "SELECT p FROM Proclamateur p WHERE p.nom = :nom"),
    @NamedQuery(name = "Proclamateur.findByEMail", query = "SELECT p FROM Proclamateur p WHERE p.eMail = :eMail"),
    @NamedQuery(name = "Proclamateur.findByPhone1", query = "SELECT p FROM Proclamateur p WHERE p.phone1 = :phone1"),
    @NamedQuery(name = "Proclamateur.findByPhone2", query = "SELECT p FROM Proclamateur p WHERE p.phone2 = :phone2"),
    @NamedQuery(name = "Proclamateur.findByPhone3", query = "SELECT p FROM Proclamateur p WHERE p.phone3 = :phone3"),
    @NamedQuery(name = "Proclamateur.findBySexe", query = "SELECT p FROM Proclamateur p WHERE p.sexe = :sexe"),
    @NamedQuery(name = "Proclamateur.findByCongregation", query = "SELECT p FROM Proclamateur p WHERE p.idcongregation = :idcongregation")

})
public class Proclamateur implements Serializable {
    @Column(name = "Sexe")
    private String sexe;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPROCLAMATEUR", nullable = false)
    private Integer idproclamateur;
    @Basic(optional = false)
    @Column(name = "NOM", nullable = false, length = 128)
    private String nom;
    @Column(name = "E_MAIL", length = 128)
    private String eMail;
    @Column(name = "phone1", length = 128)
    private String phone1;
    @Column(name = "phone2", length = 128)
    private String phone2;
    @Column(name = "phone3", length = 128)
    private String phone3;
   
    @ManyToMany(mappedBy = "proclamateurCollection")
    private Collection<Site> siteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproclamateur")
    private Collection<Planning> planningCollection;
    @JoinColumn(name = "IDCONGREGATION", referencedColumnName = "IDCONGREGATION", nullable = false)
    @ManyToOne(optional = false)
    private Congregation idcongregation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proclamateur")
    private Collection<EmploiT> emploiTCollection;

    public Proclamateur() {
    }

    public Proclamateur(Integer idproclamateur) {
        this.idproclamateur = idproclamateur;
    }

    public Proclamateur(Integer idproclamateur, String nom) {
        this.idproclamateur = idproclamateur;
        this.nom = nom;
    }

    public Integer getIdproclamateur() {
        return idproclamateur;
    }

    public void setIdproclamateur(Integer idproclamateur) {
        this.idproclamateur = idproclamateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }
    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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

    public Congregation getIdcongregation() {
        return idcongregation;
    }

    public void setIdcongregation(Congregation idcongregation) {
        this.idcongregation = idcongregation;
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
        hash += (idproclamateur != null ? idproclamateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proclamateur)) {
            return false;
        }
        Proclamateur other = (Proclamateur) object;
        if ((this.idproclamateur == null && other.idproclamateur != null) || (this.idproclamateur != null && !this.idproclamateur.equals(other.idproclamateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Proclamateur[ idproclamateur=" + idproclamateur + " ]";
    }
    
    public Vector dataTable(List<Proclamateur> list){
        Vector v=new Vector(); Vector V=new Vector();Proclamateur p;
        Iterator<Proclamateur> i=list.iterator();
        while(i.hasNext()){
            p=i.next();
            v.addElement(p.getNom());
            v.addElement(p.getEMail());
            v.addElement(p.getPhone1());
            v.addElement(p.getPhone2());
            v.addElement(p.getPhone3());
            v.addElement(p.getIdcongregation().getNom());
            V.addElement(v);
            v=new Vector();
        }
        return V;
    }
    
     public Vector dataTableL(List<Proclamateur> list){
        Vector v=new Vector(); Vector V=new Vector();Proclamateur p;
        Iterator<Proclamateur> i=list.iterator();
        while(i.hasNext()){
            p=i.next();
            v.addElement(p.getNom());
            v.addElement(p.getEMail());
            v.addElement(p.getPhone1());
            v.addElement(p.getPhone2());
            v.addElement(p.getPhone3());
            v.addElement(p.getSexe());
            V.addElement(v);
            v=new Vector();
        }
        return V;
    }
     public Vector getNomProc(List<Proclamateur> list){
        Vector v=new Vector(); Proclamateur p;
        Iterator<Proclamateur> i=list.iterator();
        while(i.hasNext()){
            p=i.next();
            v.addElement(p.getNom());
            
        }
        return v;
    }
    
    public Vector dataTableET(Collection<EmploiT> l){
        Vector V=new Vector();
        Vector v; EmploiT et;
        SimpleDateFormat formater = new SimpleDateFormat("k:mm");
        Iterator<EmploiT> i=l.iterator();
        while(i.hasNext()){
            v=new Vector();
            et=i.next();
            v.addElement(et.getIDEmploit());
            v.addElement(et.getJour().getNomjour());
            v.addElement(formater.format(et.getTHoraire().getHeureDebut()));
            v.addElement(formater.format(et.getTHoraire().getHeureFin()));
            V.addElement(v);
            
        }
        return V;
    }
    
    public Vector dataTableSite(Collection<Site> l){
        Vector V=new Vector();
        Vector v; Site s;
        Iterator<Site> i=l.iterator();
        while(i.hasNext()){
            v=new Vector();
            s=i.next();
            v.addElement(s.getLieu());
            V.addElement(v);
            
        }
        return V;
    }
    
    public Proclamateur getProcFromListe(List<Proclamateur> l){
        Iterator<Proclamateur> i=l.iterator(); Proclamateur p;
        while(i.hasNext()){
            p=i.next();
            if(p.getIdproclamateur()==this.getIdproclamateur())
                return p;
        }
        return null;
    }

    
   
}
