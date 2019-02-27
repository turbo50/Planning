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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import utilites.MaConnexion;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "planning")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planning.findAll", query = "SELECT p FROM Planning p"),
    @NamedQuery(name = "Planning.findByIdplanning", query = "SELECT p FROM Planning p WHERE p.idplanning = :idplanning"),
    @NamedQuery(name = "Planning.findAllGroupe", query = "SELECT distinct(p) FROM Planning p group by p.groupe"),
    @NamedQuery(name = "Planning.findByDatej", query = "SELECT p FROM Planning p WHERE p.datej = :datej"),
    @NamedQuery(name = "Planning.findByGroupe", query = "SELECT p FROM Planning p WHERE p.groupe = :groupe order by p.datej")
   
})
public class Planning implements Serializable {
    @ManyToMany(mappedBy = "planningList")
    private List<Activite> activiteList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPLANNING")
    private Integer idplanning;
    @Basic(optional = false)
    @Column(name = "DATEJ")
    @Temporal(TemporalType.DATE)
    private Date datej;
    @Column(name = "GROUPE")
    private Integer groupe;
    @JoinColumn(name = "IDPROCLAMATEUR", referencedColumnName = "IDPROCLAMATEUR")
    @ManyToOne(optional = false)
    private Proclamateur idproclamateur;
    @JoinColumn(name = "IDT_HORAIRE", referencedColumnName = "IDT_HORAIRE")
    @ManyToOne(optional = false)
    private THoraire idtHoraire;
    @JoinColumn(name = "IDJOUR", referencedColumnName = "IDJOUR")
    @ManyToOne(optional = false)
    private Jour idjour;

    public Planning() {
    }

    public Planning(Integer idplanning) {
        this.idplanning = idplanning;
    }

    public Planning(Integer idplanning, Date datej) {
        this.idplanning = idplanning;
        this.datej = datej;
    }

    public Integer getIdplanning() {
        return idplanning;
    }

    public void setIdplanning(Integer idplanning) {
        this.idplanning = idplanning;
    }

    public Date getDatej() {
        return datej;
    }

    public void setDatej(Date datej) {
        this.datej = datej;
    }

    public Integer getGroupe() {
        return groupe;
    }

    public void setGroupe(Integer groupe) {
        this.groupe = groupe;
    }

    public Proclamateur getIdproclamateur() {
        return idproclamateur;
    }

    public void setIdproclamateur(Proclamateur idproclamateur) {
        this.idproclamateur = idproclamateur;
    }

    public THoraire getIdtHoraire() {
        return idtHoraire;
    }

    public void setIdtHoraire(THoraire idtHoraire) {
        this.idtHoraire = idtHoraire;
    }

    public Jour getIdjour() {
        return idjour;
    }

    public void setIdjour(Jour idjour) {
        this.idjour = idjour;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanning != null ? idplanning.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planning)) {
            return false;
        }
        Planning other = (Planning) object;
        if ((this.idplanning == null && other.idplanning != null) || (this.idplanning != null && !this.idplanning.equals(other.idplanning))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Planning[ idplanning=" + idplanning + " ]";
    }
    
     public Vector dataTablePlanning(List<Planning> lp){
        Vector V=new Vector();Vector v;
        Iterator<Planning>  i=lp.iterator(); Planning p;
        SimpleDateFormat formater = new SimpleDateFormat("k:mm");
        DateFormat df=DateFormat.getDateInstance();
        while(i.hasNext()){
            p=i.next();
            v=new Vector();
            v.addElement(p.getIdproclamateur().getNom());
            v.addElement(p.getIdproclamateur().getIdcongregation().getNom());
            v.addElement(p.getIdjour().getNomjour()+", "+df.format(p.getDatej()));
            v.addElement(formater.format(p.getIdtHoraire().getHeureDebut()));
            v.addElement(formater.format(p.getIdtHoraire().getHeureFin()));
            V.addElement(v);
                    
        }
        return V;
    }
    
     public Vector<metier.Etat> getDataPlanningForEtat(List<Planning> lp){
        Vector V=new Vector();Vector v;
        Iterator<Planning>  i=lp.iterator(); Planning p;
        Proclamateur proc; Congregation cong; String jour,heur_deb,heur_fin;
        while(i.hasNext()){
            p=i.next();
            V.addElement(new metier.Etat(p));
                    
        }
        return V;
    }
     
    public Vector<Vector<Planning>> getDataPlanningForEtatAll(List<Planning> lp){
        Vector V=new Vector();Vector<Planning> v=new Vector<Planning>(); 
        Iterator<Planning>  i=lp.iterator(); Planning p;Planning pp=null;//new Planning();
        Proclamateur proc; Congregation cong; String jour,heur_deb,heur_fin;
        while(i.hasNext()){
            p=i.next();
               
            if(!p.getIdjour().getNomjour().equalsIgnoreCase("Dimanche") && pp!=null && pp.getIdjour().getNomjour().equalsIgnoreCase("Dimanche")){//fin de la semaine, on passe a un autre vecteur
                V.addElement(v);
                v=new Vector();
            }
            v.addElement(p);
//            System.out.println("Jour :"+p.getIdjour().getNomjour()+" Nom "+p.getIdproclamateur().getNom());
           pp=p;//on garde la valeur precedente pour comparaison         
        }
        if(v.size()>0)
             V.addElement(v);
        return V;
    }

   
    
    public Vector getPlanningID(Site s,accesData.AccesData ad,String dateD,String heureDeb,String heureF){
        Vector v=new Vector();
        int e;
        List lp=ad.requeteNative_Vector("select idplanning from planning where idproclamateur in(select idproclamateur from proc_site where idsite="+s.getIdsite()+") and dateJ='"+dateD+"' and idt_horaire=(select t.idt_horaire from t_horaire t,site_T_horaire sth where t.idt_horaire=sth.idt_horaire and heure_debut='"+heureDeb+"' and heure_Fin='"+heureF+"' and sth.idsite="+s.getIdsite()+" ) ");
        Iterator i=lp.iterator();
        while(i.hasNext()){
            e=(Integer)i.next();
            v.addElement(e);
            System.out.print(" idplanning :"+e);
            
        }
        return v;
    }
     public int getPlanningID(Site s,accesData.AccesData ad,String dateD,String heureDeb,String heureF,String nomProc){
        
        int e;
        e=(Integer)ad.requeteNativeQuery("select idplanning from planning where idproclamateur in(select idproclamateur from proclamateur where nom='"+nomProc+"') and dateJ='"+dateD+"' and idt_horaire=(select t.idt_horaire from t_horaire t,site_T_horaire sth where t.idt_horaire=sth.idt_horaire and heure_debut='"+heureDeb+"' and heure_Fin='"+heureF+"' and sth.idsite="+s.getIdsite()+" ) ");
        
        return e;
    }
     
    public Vector<Vector> getProclamateurForChange(String hD,String hF,String jour,int idLeSite){
		 Connection con=null; CallableStatement cs=null; PreparedStatement ps=null; ResultSet rs=null; Vector V;Vector GV=new Vector();
                 String liste=""; Fait f;
                 
                 
		String req="{call getProcRechange(?,?,?,?)}";
               
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req);
                        ps.setString(1, hD);
                        ps.setString(2, hF);
                        ps.setString(3, jour);
                        ps.setInt(4,idLeSite);
                       
                        rs=ps.executeQuery();
                        while(rs.next()){
                           V=new Vector();
                           V.addElement(rs.getInt(1));
                           V.addElement(rs.getString(2));
                           V.addElement(rs.getString(3));
                           V.addElement(rs.getString(4));
                           V.addElement(rs.getInt(5));
                           GV.addElement(V);
                        }
                        
			
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{  ps.close(); con.close();}
			catch(Exception e){
				e.printStackTrace();
			}
		}
                return GV;
	}
    public void doPlanningPdf(int groupe,String LeSite){
		 Connection con=null; CallableStatement cs=null; PreparedStatement ps=null; ResultSet rs=null; Vector V;Vector GV=new Vector();
                 String liste=""; Fait f;
                 
                 
		String req="{call doPlanningForJasper(?,?)}";
               
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req);
                        ps.setInt(1, groupe);
                        ps.setString(2, LeSite);
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
    
     public void deletePlanning(int maxGroupe){
		 Connection con=null; PreparedStatement ps=null;                                            
		String req="delete from planning where groupe=?";
               
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req);
                        ps.setInt(1, maxGroupe);
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
    
    
    public Vector getProcForChangeTable(Vector<Vector> V){
        Vector<Vector> v=new Vector<Vector>();
        for(int i=0; i<V.size(); i++){
            v.addElement(V.elementAt(i));
        }
         for(int i=0; i<V.size(); i++){
            v.elementAt(i).remove(0);
        }
        return v;
             
    }
     
    public Vector getPeriodeFromPlan(Vector<metier.Plan> vp){
        Vector v=new Vector();
        for(int i=0; i<vp.size(); i++){
            v.addElement(vp.elementAt(i).getDatemax());
        }
        return v;
    }

    @XmlTransient
    public List<Activite> getActiviteList() {
        return activiteList;
    }

    public void setActiviteList(List<Activite> activiteList) {
        this.activiteList = activiteList;
    }
    
}
