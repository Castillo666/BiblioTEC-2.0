/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicadenegocios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Kevin Castillo
 */
@Entity
@Table(name = "Participante")
@NamedQueries({
    @NamedQuery(name = "Participante.findAll", query = "SELECT p FROM Participante p")
    , @NamedQuery(name = "Participante.findByIdPaticipante", query = "SELECT p FROM Participante p WHERE p.idPaticipante = :idPaticipante")
    , @NamedQuery(name = "Participante.findByNombre", query = "SELECT p FROM Participante p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Participante.findByEmail", query = "SELECT p FROM Participante p WHERE p.email = :email")})
public class Participante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPaticipante", nullable = false)
    private Integer idPaticipante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "email", nullable = false, length = 30)
    private String email;

    public Participante() {
    }

    public Participante(Integer idPaticipante) {
        this.idPaticipante = idPaticipante;
    }

    public Participante(Integer idPaticipante, String nombre, String email) {
        this.idPaticipante = idPaticipante;
        this.nombre = nombre;
        this.email = email;
    }

    public Integer getIdPaticipante() {
        return idPaticipante;
    }

    public void setIdPaticipante(Integer idPaticipante) {
        this.idPaticipante = idPaticipante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaticipante != null ? idPaticipante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participante)) {
            return false;
        }
        Participante other = (Participante) object;
        if ((this.idPaticipante == null && other.idPaticipante != null) || (this.idPaticipante != null && !this.idPaticipante.equals(other.idPaticipante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logicadenegocios.Participante[ idPaticipante=" + idPaticipante + " ]";
    }
    
}
