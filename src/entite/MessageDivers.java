/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "message_divers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageDivers.findAll", query = "SELECT m FROM MessageDivers m"),
    @NamedQuery(name = "MessageDivers.findByIdMessage", query = "SELECT m FROM MessageDivers m WHERE m.idMessage = :idMessage"),
    @NamedQuery(name = "MessageDivers.findBySms", query = "SELECT m FROM MessageDivers m WHERE m.sms = :sms")})
public class MessageDivers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMessage")
    private Integer idMessage;
    @Basic(optional = false)
    @Column(name = "sms")
    private String sms;

    public MessageDivers() {
    }

    public MessageDivers(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public MessageDivers(Integer idMessage, String sms) {
        this.idMessage = idMessage;
        this.sms = sms;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMessage != null ? idMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageDivers)) {
            return false;
        }
        MessageDivers other = (MessageDivers) object;
        if ((this.idMessage == null && other.idMessage != null) || (this.idMessage != null && !this.idMessage.equals(other.idMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.MessageDivers[ idMessage=" + idMessage + " ]";
    }
    
    public Vector dataTable(List<MessageDivers> list){
        Vector v=new Vector(); Vector V=new Vector();MessageDivers md;
        Iterator<MessageDivers> i=list.iterator();
        while(i.hasNext()){
            md=i.next();
            v.addElement(md.getSms());           
            V.addElement(v);
            v=new Vector();
        }
        return V;
    }
    
}
