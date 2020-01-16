/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicadenegocios;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Kevin Castillo
 */
@Entity
@Table(name = "Sala")
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s")
    , @NamedQuery(name = "Sala.findByIdentificador", query = "SELECT s FROM Sala s WHERE s.identificador = :identificador")
    , @NamedQuery(name = "Sala.findByUbicacion", query = "SELECT s FROM Sala s WHERE s.ubicacion = :ubicacion")
    , @NamedQuery(name = "Sala.findByCapacidadMax", query = "SELECT s FROM Sala s WHERE s.capacidadMax = :capacidadMax")
    , @NamedQuery(name = "Sala.findByEstado", query = "SELECT s FROM Sala s WHERE s.estado = :estado")
    , @NamedQuery(name = "Sala.findByCalificacion", query = "SELECT s FROM Sala s WHERE s.calificacion = :calificacion")
    , @NamedQuery(name = "Sala.findByNumCalificaciones", query = "SELECT s FROM Sala s WHERE s.numCalificaciones = :numCalificaciones")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "identificador", nullable = false, length = 20)
    private String identificador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ubicacion", nullable = false, length = 500)
    private String ubicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidadMax", nullable = false)
    private int capacidadMax;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calificacion", nullable = false)
    private int calificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numCalificaciones", nullable = false)
    private int numCalificaciones;
    @ManyToMany(mappedBy = "salaList")
    private List<Horario> horarioList;
    @JoinTable(name = "SalaRecurso", joinColumns = {
        @JoinColumn(name = "idSala", referencedColumnName = "identificador", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "nombreRecurso", referencedColumnName = "nombre", nullable = false)})
    @ManyToMany
    private List<Recurso> recursoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSala")
    private List<Reserva> reservaList;

    public Sala() {
    }

    public Sala(String identificador) {
        this.identificador = identificador;
    }

    public Sala(String identificador, String ubicacion, int capacidadMax, String estado, int calificacion, int numCalificaciones) {
        this.identificador = identificador;
        this.ubicacion = ubicacion;
        this.capacidadMax = capacidadMax;
        this.estado = estado;
        this.calificacion = calificacion;
        this.numCalificaciones = numCalificaciones;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getNumCalificaciones() {
        return numCalificaciones;
    }

    public void setNumCalificaciones(int numCalificaciones) {
        this.numCalificaciones = numCalificaciones;
    }

    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public List<Recurso> getRecursoList() {
        return recursoList;
    }

    public void setRecursoList(List<Recurso> recursoList) {
        this.recursoList = recursoList;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificador != null ? identificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logicadenegocios.Sala[ identificador=" + identificador + " ]";
    }
    
}
