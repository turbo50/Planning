package entite;

import entite.Planning;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T14:19:41")
@StaticMetamodel(Fait.class)
public class Fait_ { 

    public static volatile SingularAttribute<Fait, String> sexeacteur;
    public static volatile SingularAttribute<Fait, String> note;
    public static volatile SingularAttribute<Fait, Integer> idfait;
    public static volatile SingularAttribute<Fait, String> contenu;
    public static volatile CollectionAttribute<Fait, Planning> planningCollection;

}