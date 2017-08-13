/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jhoselin Trujillo
 */
@Entity
@Table(name = "pasajero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pasajero.findAll", query = "SELECT p FROM Pasajero p")
    , @NamedQuery(name = "Pasajero.findByIdPasajero", query = "SELECT p FROM Pasajero p WHERE p.idPasajero = :idPasajero")
    , @NamedQuery(name = "Pasajero.findByNombre", query = "SELECT p FROM Pasajero p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Pasajero.findByDireccion", query = "SELECT p FROM Pasajero p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Pasajero.findByPasaporte", query = "SELECT p FROM Pasajero p WHERE p.pasaporte = :pasaporte")
    , @NamedQuery(name = "Pasajero.findByNumPasajero", query = "SELECT p FROM Pasajero p WHERE p.numPasajero = :numPasajero")
    , @NamedQuery(name = "Pasajero.findByEliminado", query = "SELECT p FROM Pasajero p WHERE p.eliminado = :eliminado")})
public class Pasajero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pasajero")
    private Integer idPasajero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "pasaporte")
    private String pasaporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_pasajero")
    private int numPasajero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPasajero")
    private Collection<Vuelo> vueloCollection;

    public Pasajero() {
    }

    public Pasajero(String nombre, String direccion, String pasaporte, int numPasajero, boolean eliminado) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.pasaporte = pasaporte;
        this.numPasajero = numPasajero;
        this.eliminado = eliminado;
    }
    

    public Pasajero(Integer idPasajero) {
        this.idPasajero = idPasajero;
    }

    public Pasajero(Integer idPasajero, String nombre, String direccion, String pasaporte, int numPasajero, boolean eliminado) {
        this.idPasajero = idPasajero;
        this.nombre = nombre;
        this.direccion = direccion;
        this.pasaporte = pasaporte;
        this.numPasajero = numPasajero;
        this.eliminado = eliminado;
    }

    public Integer getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(Integer idPasajero) {
        this.idPasajero = idPasajero;
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

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public int getNumPasajero() {
        return numPasajero;
    }

    public void setNumPasajero(int numPasajero) {
        this.numPasajero = numPasajero;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Vuelo> getVueloCollection() {
        return vueloCollection;
    }

    public void setVueloCollection(Collection<Vuelo> vueloCollection) {
        this.vueloCollection = vueloCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPasajero != null ? idPasajero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasajero)) {
            return false;
        }
        Pasajero other = (Pasajero) object;
        if ((this.idPasajero == null && other.idPasajero != null) || (this.idPasajero != null && !this.idPasajero.equals(other.idPasajero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Pasajero[ idPasajero=" + idPasajero + " ]";
    }
    
}
