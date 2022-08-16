package BD;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@Table(name = "Usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="Usuarios.findAll",
                query="SELECT r FROM Usuarios r"),
    @NamedQuery(name="Usuarios.findByCorreo",
                query="SELECT r FROM Usuarios r WHERE r.correo = :correo"),
    @NamedQuery(name="Usuarios.findById",
                query="SELECT r FROM Usuarios r WHERE r.id = :id"),
})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Basic(optional = false)        //Tipos de acceso --> Se pasan en tiempo de ejecucion
    private Long id;
    
    @Column(name="correo", unique = true)
    @Basic(optional = false)
    @NotNull
    private String correo;      //Unico
    @Column(name="contraseña")
    @Basic(optional = false)
    @NotNull
    private String contraseña;
    @Column(name="nombre")
    @Basic(optional = false)
    @NotNull
    private String nombre;
    @Column(name="direccion")
    private String direccion;
    @Column(name="postal")
    @Basic(optional = false)
    @NotNull
    private int postal;
    @Column(name="Twitter")
    private String Twitter;
    @Column(name="Facebook")
    private String Facebook;
    @Column(name="telefono")
    @NotNull
    private int telefono;
    
    /*
        Un usuario puede publicar muchos articulos
    */
    @OneToMany(mappedBy="Creador", cascade = CascadeType.PERSIST)
    private List<Articulos> MisArt;
    /*
        Un usuario puede hacer muchos comentarios
    */
    @OneToMany(mappedBy="idUser", cascade = CascadeType.PERSIST)
    private List<Comentarios> coment;//*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }

    public String getTwitter() {
        return Twitter;
    }

    public void setTwitter(String Twitter) {
        this.Twitter = Twitter;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String Facebook) {
        this.Facebook = Facebook;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public List<Comentarios> getComent() {
        return coment;
    }

    public void setComent(List<Comentarios> coment) {
        this.coment = coment;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.EntityUsuarios[ id=" + id + " ]";
    }
    
}
