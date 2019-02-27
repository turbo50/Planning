package entite;

import entite.Proclamateur;
import entite.S43;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T14:19:41")
@StaticMetamodel(Congregation.class)
public class Congregation_ { 

    public static volatile SingularAttribute<Congregation, Integer> idcongregation;
    public static volatile CollectionAttribute<Congregation, S43> s43Collection;
    public static volatile SingularAttribute<Congregation, String> nom;
    public static volatile CollectionAttribute<Congregation, Proclamateur> proclamateurCollection;

}