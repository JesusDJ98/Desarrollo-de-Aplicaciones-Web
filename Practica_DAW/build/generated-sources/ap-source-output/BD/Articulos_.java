package BD;

import BD.Comentarios;
import BD.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-02-03T12:53:39")
@StaticMetamodel(Articulos.class)
public class Articulos_ { 

    public static volatile SingularAttribute<Articulos, String> descripcion;
    public static volatile SingularAttribute<Articulos, Float> precio;
    public static volatile SingularAttribute<Articulos, String> estado;
    public static volatile ListAttribute<Articulos, Comentarios> coment;
    public static volatile SingularAttribute<Articulos, String> categoria;
    public static volatile SingularAttribute<Articulos, Usuarios> Creador;
    public static volatile SingularAttribute<Articulos, Long> id;
    public static volatile SingularAttribute<Articulos, String> nombre;
    public static volatile SingularAttribute<Articulos, String> anio;

}