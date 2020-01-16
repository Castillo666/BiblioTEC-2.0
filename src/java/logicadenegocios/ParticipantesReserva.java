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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ParticipantesReserva")
@NamedQueries({
    @NamedQuery(name = "ParticipantesReserva.findAll", query = "SELECT p FROM ParticipantesReserva p")
    , @NamedQuery(name = "ParticipantesReserva.findByIdPaticipanteReserva", query = "SELECT p FROM ParticipantesReserva p WHERE p.idPaticipanteReserva = :idPaticipanteReserva")
    , @NamedQuery(name = "ParticipantesReserva.findByNombre", query = "SELECT p FROM ParticipantesReserva p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "ParticipantesReserva.findByEmail", query = "SELECT p FROM ParticipantesReserva p WHERE p.email = :email")})
public class ParticipantesReserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPaticipanteReserva", nullable = false)
    private Integer idPaticipanteReserva;
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
    @JoinColumn(name = "idReserva", referencedColumnName = "numero", nullable = false)
    @ManyToOne(optional = false)
    private Reserva idReserva;

    public ParticipantesReserva() {
    }

    public ParticipantesReserva(Integer idPaticipanteReserva) {
        this.idPaticipanteReserva = idPaticipanteReserva;
    }

    public ParticipantesReserva(Integer idPaticipanteReserva, String nombre, String email) {
        this.idPaticipanteReserva = idPaticipanteReserva;
        this.nombre = nombre;
        this.email = email;
    }

    public Integer getIdPaticipanteReserva() {
        return idPaticipanteReserva;
    }

    public void setIdPaticipanteReserva(Integer idPaticipanteReserva) {
        this.idPaticipanteReserva = idPaticipanteReserva;
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

    public Reserva getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Reserva idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaticipanteReserva != null ? idPaticipanteReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipantesReserva)) {
            return false;
        }
        ParticipantesReserva other = (ParticipantesReserva) object;
        if ((this.idPaticipanteReserva == null && other.idPaticipanteReserva != null) || (this.idPaticipanteReserva != null && !this.idPaticipanteReserva.equals(other.idPaticipanteReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logicadenegocios.ParticipantesReserva[ idPaticipanteReserva=" + idPaticipanteReserva + " ]";
    }
    
}
