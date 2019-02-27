package entite;

import entite.Proclamateur;
import entite.THoraire;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T14:19:41")
@StaticMetamodel(Site.class)
public class Site_ { 

    public static volatile SingularAttribute<Site, Integer> idsite;
    public static volatile SingularAttribute<Site, Double> altitude;
    public static volatile CollectionAttribute<Site, THoraire> tHoraireCollection;
    public static volatile CollectionAttribute<Site, Proclamateur> proclamateurCollection;
    public static volatile SingularAttribute<Site, String> lieu;
    public static volatile SingularAttribute<Site, Double> longitude;

}