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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "Articulos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="Articulos.findAll",
                query="SELECT r FROM Articulos r"),
    @NamedQuery(name="Articulos.findById",
                query="SELECT r FROM Articulos r WHERE r.id = :id"),
    @NamedQuery(name="Articulos.findByName",
                query="SELECT r FROM Articulos r WHERE r.nombre = :name")
})
public class Articulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Basic(optional = false) 
    private Long id;
    @Column(name="nombre")
    @NotNull
    private String nombre;
    @Column(name="categoria")
    @NotNull
    private String categoria;
    @Column(name="precio")
    @NotNull
    private float precio;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="estado")
    private String estado;
    @Column(name="anio")
    private String anio;
    
    /*
        Muchos articulos pertenecen a un usuario (favoritos)
    */
    @JoinColumn(name="Creador", referencedColumnName = "id")
    @ManyToOne(optional=false)
    private Usuarios Creador;
    /*
        Un articulo puede tener muchos comentarios
    */
    @OneToMany(mappedBy="idArticulo", cascade = CascadeType.PERSIST)
    private List<Comentarios> coment; //*/
    
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    
    
    public Usuarios getCreador() {
        return Creador;
    }

    public void setCreador(Usuarios Creador) {
        this.Creador = Creador;
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
        if (!(object instanceof Articulos)) {
            return false;
        }
        Articulos other = (Articulos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.EntityArticulos[ id=" + id + " ]";
    }
    
}
