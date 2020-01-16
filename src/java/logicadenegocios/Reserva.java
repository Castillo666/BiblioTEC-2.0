/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicadenegocios;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Kevin Castillo
 */
@Entity
@Table(name = "Reserva")
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findByNumero", query = "SELECT r FROM Reserva r WHERE r.numero = :numero")
    , @NamedQuery(name = "Reserva.findByEstado", query = "SELECT r FROM Reserva r WHERE r.estado = :estado")
    , @NamedQuery(name = "Reserva.findByFecha", query = "SELECT r FROM Reserva r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "Reserva.findByHoraInicio", query = "SELECT r FROM Reserva r WHERE r.horaInicio = :horaInicio")
    , @NamedQuery(name = "Reserva.findByHoraFin", query = "SELECT r FROM Reserva r WHERE r.horaFin = :horaFin")
    , @NamedQuery(name = "Reserva.findByCodigoCalificacion", query = "SELECT r FROM Reserva r WHERE r.codigoCalificacion = :codigoCalificacion")
    , @NamedQuery(name = "Reserva.findByAsunto", query = "SELECT r FROM Reserva r WHERE r.asunto = :asunto")
    , @NamedQuery(name = "Reserva.findByHaSidoCalificado", query = "SELECT r FROM Reserva r WHERE r.haSidoCalificado = :haSidoCalificado")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero", nullable = false)
    private Integer numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "horaInicio", nullable = false, length = 20)
    private String horaInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "horaFin", nullable = false, length = 20)
    private String horaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "codigoCalificacion", nullable = false, length = 500)
    private String codigoCalificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "asunto", nullable = false, length = 500)
    private String asunto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "haSidoCalificado", nullable = false)
    private int haSidoCalificado;
    @JoinColumn(name = "organizador", referencedColumnName = "carnet", nullable = false)
    @ManyToOne(optional = false)
    private Estudiante organizador;
    @JoinColumn(name = "idSala", referencedColumnName = "identificador", nullable = false)
    @ManyToOne(optional = false)
    private Sala idSala;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReserva")
    private List<ParticipantesReserva> participantesReservaList;

    public Reserva() {
    }

    public Reserva(Integer numero) {
        this.numero = numero;
    }

    public Reserva(Integer numero, String estado, Date fecha, String horaInicio, String horaFin, String codigoCalificacion, String asunto, int haSidoCalificado) {
        this.numero = numero;
        this.estado = estado;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.codigoCalificacion = codigoCalificacion;
        this.asunto = asunto;
        this.haSidoCalificado = haSidoCalificado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getCodigoCalificacion() {
        return codigoCalificacion;
    }

    public void setCodigoCalificacion(String codigoCalificacion) {
        this.codigoCalificacion = codigoCalificacion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public int getHaSidoCalificado() {
        return haSidoCalificado;
    }

    public void setHaSidoCalificado(int haSidoCalificado) {
        this.haSidoCalificado = haSidoCalificado;
    }

    public Estudiante getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Estudiante organizador) {
        this.organizador = organizador;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public List<ParticipantesReserva> getParticipantesReservaList() {
        return participantesReservaList;
    }

    public void setParticipantesReservaList(List<ParticipantesReserva> participantesReservaList) {
        this.participantesReservaList = participantesReservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logicadenegocios.Reserva[ numero=" + numero + " ]";
    }
    
}
