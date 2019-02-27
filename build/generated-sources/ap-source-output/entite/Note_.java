package entite;

import entite.Evaluation;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T14:19:41")
@StaticMetamodel(Note.class)
public class Note_ { 

    public static volatile SingularAttribute<Note, String> libelle;
    public static volatile CollectionAttribute<Note, Evaluation> evaluationCollection;
    public static volatile SingularAttribute<Note, Integer> idnote;
    public static volatile SingularAttribute<Note, Integer> point;

}