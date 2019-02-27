package entite;

import entite.EmploiTPK;
import entite.Jour;
import entite.Proclamateur;
import entite.THoraire;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T14:19:41")
@StaticMetamodel(EmploiT.class)
public class EmploiT_ { 

    public static volatile SingularAttribute<EmploiT, EmploiTPK> emploiTPK;
    public static volatile SingularAttribute<EmploiT, Jour> jour;
    public static volatile SingularAttribute<EmploiT, THoraire> tHoraire;
    public static volatile SingularAttribute<EmploiT, Integer> iDEmploit;
    public static volatile SingularAttribute<EmploiT, Proclamateur> proclamateur;

}