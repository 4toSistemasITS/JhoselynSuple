/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhoselin Trujillo
 */
@Entity
@Table(name = "vuelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vuelo.findAll", query = "SELECT v FROM Vuelo v")
    , @NamedQuery(name = "Vuelo.findByIdVuelo", query = "SELECT v FROM Vuelo v WHERE v.idVuelo = :idVuelo")
    , @NamedQuery(name = "Vuelo.findByNumVuelo", query = "SELECT v FROM Vuelo v WHERE v.numVuelo = :numVuelo")
    , @NamedQuery(name = "Vuelo.findByOrigen", query = "SELECT v FROM Vuelo v WHERE v.origen = :origen")
    , @NamedQuery(name = "Vuelo.findByDestino", query = "SELECT v FROM Vuelo v WHERE v.destino = :destino")
    , @NamedQuery(name = "Vuelo.findByHoraSalida", query = "SELECT v FROM Vuelo v WHERE v.horaSalida = :horaSalida")
    , @NamedQuery(name = "Vuelo.findByHoraLlegada", query = "SELECT v FROM Vuelo v WHERE v.horaLlegada = :horaLlegada")
    , @NamedQuery(name = "Vuelo.findByFechaSalida", query = "SELECT v FROM Vuelo v WHERE v.fechaSalida = :fechaSalida")
    , @NamedQuery(name = "Vuelo.findByFechaLlegada", query = "SELECT v FROM Vuelo v WHERE v.fechaLlegada = :fechaLlegada")
    , @NamedQuery(name = "Vuelo.findByEliminado", query = "SELECT v FROM Vuelo v WHERE v.eliminado = :eliminado")})
public class Vuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vuelo")
    private Integer idVuelo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_vuelo")
    private int numVuelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "origen")
    private String origen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "destino")
    private String destino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_llegada")
    @Temporal(TemporalType.TIME)
    private Date horaLlegada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_llegada")
    @Temporal(TemporalType.DATE)
    private Date fechaLlegada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private boolean eliminado;
    @JoinColumn(name = "id_avion", referencedColumnName = "id_avion")
    @ManyToOne(optional = false)
    private Avion idAvion;
    @JoinColumn(name = "id_pasajero", referencedColumnName = "id_pasajero")
    @ManyToOne(optional = false)
    private Pasajero idPasajero;

    public Vuelo() {
    }

    public Vuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Vuelo(int numVuelo, String origen, String destino, Date horaSalida, Date horaLlegada, Date fechaSalida, Date fechaLlegada, boolean eliminado, Avion idAvion, Pasajero idPasajero) {
        this.numVuelo = numVuelo;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.eliminado = eliminado;
        this.idAvion = idAvion;
        this.idPasajero = idPasajero;
    }
    

    public Vuelo(Integer idVuelo, int numVuelo, String origen, String destino, Date horaSalida, Date horaLlegada, Date fechaSalida, Date fechaLlegada, boolean eliminado) {
        this.idVuelo = idVuelo;
        this.numVuelo = numVuelo;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.eliminado = eliminado;
    }

    public Integer getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getNumVuelo() {
        return numVuelo;
    }

    public void setNumVuelo(int numVuelo) {
        this.numVuelo = numVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Avion getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(Avion idAvion) {
        this.idAvion = idAvion;
    }

    public Pasajero getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(Pasajero idPasajero) {
        this.idPasajero = idPasajero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVuelo != null ? idVuelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vuelo)) {
            return false;
        }
        Vuelo other = (Vuelo) object;
        if ((this.idVuelo == null && other.idVuelo != null) || (this.idVuelo != null && !this.idVuelo.equals(other.idVuelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Vuelo[ idVuelo=" + idVuelo + " ]";
    }
    
}
