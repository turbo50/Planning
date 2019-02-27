package entite;

import entite.EmploiT;
import entite.Planning;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T14:19:41")
@StaticMetamodel(Jour.class)
public class Jour_ { 

    public static volatile SingularAttribute<Jour, Integer> idjour;
    public static volatile SingularAttribute<Jour, String> nomjour;
    public static volatile CollectionAttribute<Jour, EmploiT> emploiTCollection;
    public static volatile CollectionAttribute<Jour, Planning> planningCollection;

}