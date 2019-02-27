package entite;

import entite.Activite;
import entite.Jour;
import entite.Proclamateur;
import entite.THoraire;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T14:19:41")
@StaticMetamodel(Planning.class)
public class Planning_ { 

    public static volatile ListAttribute<Planning, Activite> activiteList;
    public static volatile SingularAttribute<Planning, Jour> idjour;
    public static volatile SingularAttribute<Planning, Date> datej;
    public static volatile SingularAttribute<Planning, Proclamateur> idproclamateur;
    public static volatile SingularAttribute<Planning, Integer> groupe;
    public static volatile SingularAttribute<Planning, THoraire> idtHoraire;
    public static volatile SingularAttribute<Planning, Integer> idplanning;

}