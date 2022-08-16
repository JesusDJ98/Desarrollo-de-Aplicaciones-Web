package BD;

import BD.Articulos;
import BD.Comentarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-02-03T12:53:39")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile ListAttribute<Usuarios, Comentarios> coment;
    public static volatile SingularAttribute<Usuarios, String> correo;
    public static volatile SingularAttribute<Usuarios, String> direccion;
    public static volatile SingularAttribute<Usuarios, String> Twitter;
    public static volatile SingularAttribute<Usuarios, Long> id;
    public static volatile SingularAttribute<Usuarios, Integer> postal;
    public static volatile SingularAttribute<Usuarios, Integer> telefono;
    public static volatile SingularAttribute<Usuarios, String> nombre;
    public static volatile SingularAttribute<Usuarios, String> Facebook;
    public static volatile ListAttribute<Usuarios, Articulos> MisArt;
    public static volatile SingularAttribute<Usuarios, String> contraseña;

}