package entite;

import entite.Planning;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T14:19:41")
@StaticMetamodel(Activite.class)
public class Activite_ { 

    public static volatile SingularAttribute<Activite, Integer> idactivite;
    public static volatile SingularAttribute<Activite, Integer> heure;
    public static volatile SingularAttribute<Activite, Integer> tract;
    public static volatile SingularAttribute<Activite, Integer> s43;
    public static volatile SingularAttribute<Activite, Integer> brochure;
    public static volatile ListAttribute<Activite, Planning> planningList;
    public static volatile SingularAttribute<Activite, Integer> periodique;
    public static volatile SingularAttribute<Activite, Integer> livre;

}