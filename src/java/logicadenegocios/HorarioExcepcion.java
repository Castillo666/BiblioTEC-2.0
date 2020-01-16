/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicadenegocios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "HorarioExcepcion")
@NamedQueries({
    @NamedQuery(name = "HorarioExcepcion.findAll", query = "SELECT h FROM HorarioExcepcion h")
    , @NamedQuery(name = "HorarioExcepcion.findByIdHorarioEsp", query = "SELECT h FROM HorarioExcepcion h WHERE h.idHorarioEsp = :idHorarioEsp")
    , @NamedQuery(name = "HorarioExcepcion.findByHoraInicio", query = "SELECT h FROM HorarioExcepcion h WHERE h.horaInicio = :horaInicio")
    , @NamedQuery(name = "HorarioExcepcion.findByHoraFin", query = "SELECT h FROM HorarioExcepcion h WHERE h.horaFin = :horaFin")
    , @NamedQuery(name = "HorarioExcepcion.findByFecha", query = "SELECT h FROM HorarioExcepcion h WHERE h.fecha = :fecha")
    , @NamedQuery(name = "HorarioExcepcion.findByDescripcion", query = "SELECT h FROM HorarioExcepcion h WHERE h.descripcion = :descripcion")})
public class HorarioExcepcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idHorarioEsp", nullable = false)
    private Integer idHorarioEsp;
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
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    public HorarioExcepcion() {
    }

    public HorarioExcepcion(Integer idHorarioEsp) {
        this.idHorarioEsp = idHorarioEsp;
    }

    public HorarioExcepcion(Integer idHorarioEsp, String horaInicio, String horaFin, Date fecha, String descripcion) {
        this.idHorarioEsp = idHorarioEsp;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Integer getIdHorarioEsp() {
        return idHorarioEsp;
    }

    public void setIdHorarioEsp(Integer idHorarioEsp) {
        this.idHorarioEsp = idHorarioEsp;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorarioEsp != null ? idHorarioEsp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioExcepcion)) {
            return false;
        }
        HorarioExcepcion other = (HorarioExcepcion) object;
        if ((this.idHorarioEsp == null && other.idHorarioEsp != null) || (this.idHorarioEsp != null && !this.idHorarioEsp.equals(other.idHorarioEsp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logicadenegocios.HorarioExcepcion[ idHorarioEsp=" + idHorarioEsp + " ]";
    }
    
}
