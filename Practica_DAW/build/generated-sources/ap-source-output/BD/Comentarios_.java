package BD;

import BD.Articulos;
import BD.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-02-03T12:53:39")
@StaticMetamodel(Comentarios.class)
public class Comentarios_ { 

    public static volatile SingularAttribute<Comentarios, String> texto;
    public static volatile SingularAttribute<Comentarios, Usuarios> idUser;
    public static volatile SingularAttribute<Comentarios, Articulos> idArticulo;
    public static volatile SingularAttribute<Comentarios, Long> id;
    public static volatile SingularAttribute<Comentarios, String> privacidad;

}