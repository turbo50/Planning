package entite;

import entite.EmploiT;
import entite.Planning;
import entite.Site;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T14:19:41")
@StaticMetamodel(THoraire.class)
public class THoraire_ { 

    public static volatile CollectionAttribute<THoraire, Site> siteCollection;
    public static volatile SingularAttribute<THoraire, Date> heureDebut;
    public static volatile CollectionAttribute<THoraire, EmploiT> emploiTCollection;
    public static volatile SingularAttribute<THoraire, Integer> idtHoraire;
    public static volatile SingularAttribute<THoraire, Date> heureFin;
    public static volatile CollectionAttribute<THoraire, Planning> planningCollection;

}