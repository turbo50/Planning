package entite;

import entite.Congregation;
import entite.EmploiT;
import entite.Planning;
import entite.Site;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T14:19:41")
@StaticMetamodel(Proclamateur.class)
public class Proclamateur_ { 

    public static volatile SingularAttribute<Proclamateur, Congregation> idcongregation;
    public static volatile SingularAttribute<Proclamateur, Integer> idproclamateur;
    public static volatile CollectionAttribute<Proclamateur, Site> siteCollection;
    public static volatile SingularAttribute<Proclamateur, String> phone2;
    public static volatile SingularAttribute<Proclamateur, String> phone3;
    public static volatile SingularAttribute<Proclamateur, String> sexe;
    public static volatile CollectionAttribute<Proclamateur, EmploiT> emploiTCollection;
    public static volatile SingularAttribute<Proclamateur, String> nom;
    public static volatile SingularAttribute<Proclamateur, String> eMail;
    public static volatile SingularAttribute<Proclamateur, String> phone1;
    public static volatile CollectionAttribute<Proclamateur, Planning> planningCollection;

}