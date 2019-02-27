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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import utilites.MaConnexion;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "evaluation", catalog = "mlr2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IDPLANNING"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluation.findAll", query = "SELECT e FROM Evaluation e"),
    @NamedQuery(name = "Evaluation.findByIdevaluation", query = "SELECT e FROM Evaluation e WHERE e.idevaluation = :idevaluation"),
    @NamedQuery(name = "Evaluation.findByIdplanning", query = "SELECT e FROM Evaluation e WHERE e.idplanning = :idplanning")})

public class Evaluation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEVALUATION", nullable = false)
    private Integer idevaluation;
    @JoinColumn(name = "IDNOTE", referencedColumnName = "IDNOTE", nullable = false)
    @ManyToOne(optional = false)
    private Note idnote;
    @JoinColumn(name = "IDPLANNING", referencedColumnName = "IDPLANNING", nullable = false)
    @OneToOne(optional = false)
    private Planning idplanning;

    public Evaluation() {
    }

    public Evaluation(Integer idevaluation) {
        this.idevaluation = idevaluation;
    }

    public Integer getIdevaluation() {
        return idevaluation;
    }

    public void setIdevaluation(Integer idevaluation) {
        this.idevaluation = idevaluation;
    }

    public Note getIdnote() {
        return idnote;
    }

    public void setIdnote(Note idnote) {
        this.idnote = idnote;
    }

    public Planning getIdplanning() {
        return idplanning;
    }

    public void setIdplanning(Planning idplanning) {
        this.idplanning = idplanning;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevaluation != null ? idevaluation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluation)) {
            return false;
        }
        Evaluation other = (Evaluation) object;
        if ((this.idevaluation == null && other.idevaluation != null) || (this.idevaluation != null && !this.idevaluation.equals(other.idevaluation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Evaluation[ idevaluation=" + idevaluation + " ]";
    }
    
    public Evaluation getEvaluation(entite.Planning p,accesData.AccesData ad){
		 Connection con=null; CallableStatement cs=null; PreparedStatement ps=null; ResultSet rs=null;
		String req="select idevaluation,idplanning,idnote from evaluation where idplanning=?";
                int idplanning=0;Evaluation e=new Evaluation();
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall(req);
                        ps.setInt(1,p.getIdplanning());
                        rs=ps.executeQuery();
                        while(rs.next()){
                            e.setIdevaluation(rs.getInt(1));
                            e.setIdplanning(ad.getEm().find(Planning.class,rs.getInt(2)));
                            e.setIdnote(ad.getEm().find(Note.class,rs.getInt(3)));
                            
                        }
                        
			
				
			
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			try{  ps.close(); con.close();}
			catch(Exception e2){
				e2.printStackTrace();
			}
		}
                if(e.getIdevaluation()!=null)
                    return e;
                else return null;
	}
    
}
