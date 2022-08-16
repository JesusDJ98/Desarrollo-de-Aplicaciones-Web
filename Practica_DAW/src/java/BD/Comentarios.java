package BD;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Comentarios")
//Consultas estaticas
@NamedQueries({
    @NamedQuery(name = "Comentarios.findAll", 
            query = "SELECT c FROM Comentarios c")    
    , @NamedQuery(name = "Comentarios.findById", 
            query = "SELECT c FROM Comentarios c WHERE c.id = :id")
    , @NamedQuery(name = "Comentarios.findBytexto",
            query = "SELECT c FROM Comentarios c WHERE c.texto = :texto")
    , @NamedQuery(name = "Comentarios.findByPrivacidad", 
            query = "SELECT c FROM Comentarios c WHERE c.privacidad = :privacidad")

})

public class Comentarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Basic(optional = false)        //Tipos de acceso
    private Long id;
    @Column(name="texto")
    @NotNull
    private String texto;
    @Column(name="privacidad")
    @NotNull
    private String privacidad;
    
    @JoinColumn(name="idUser", referencedColumnName = "id")
    @ManyToOne(optional=false)
    private Usuarios idUser;
    
    @JoinColumn(name="idArticulo", referencedColumnName = "id")
    @ManyToOne(optional=false)
    private Articulos idArticulo;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(String privacidad) {
        this.privacidad = privacidad;
    }

    
    public Usuarios getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuarios idUser) {
        this.idUser = idUser;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
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
        if (!(object instanceof Comentarios)) {
            return false;
        }
        Comentarios other = (Comentarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Comentarios[ id=" + id + " ]";
    }
    
}
