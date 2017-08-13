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
@Table(name = "aeropuerto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aeropuerto.findAll", query = "SELECT a FROM Aeropuerto a")
    , @NamedQuery(name = "Aeropuerto.findByIdAeropuerto", query = "SELECT a FROM Aeropuerto a WHERE a.idAeropuerto = :idAeropuerto")
    , @NamedQuery(name = "Aeropuerto.findByNombre", query = "SELECT a FROM Aeropuerto a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Aeropuerto.findByDireccion", query = "SELECT a FROM Aeropuerto a WHERE a.direccion = :direccion")
    , @NamedQuery(name = "Aeropuerto.findByNumPista", query = "SELECT a FROM Aeropuerto a WHERE a.numPista = :numPista")
    , @NamedQuery(name = "Aeropuerto.findByPais", query = "SELECT a FROM Aeropuerto a WHERE a.pais = :pais")
    , @NamedQuery(name = "Aeropuerto.findByTelefono", query = "SELECT a FROM Aeropuerto a WHERE a.telefono = :telefono")
    , @NamedQuery(name = "Aeropuerto.findByEliminado", query = "SELECT a FROM Aeropuerto a WHERE a.eliminado = :eliminado")})
public class Aeropuerto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aeropuerto")
    private Integer idAeropuerto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_pista")
    private int numPista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pais")
    private String pais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefono")
    private int telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAeropuerto")
    private Collection<Avion> avionCollection;

    public Aeropuerto() {
    }

    public Aeropuerto(String nombre, String direccion, int numPista, String pais, int telefono, boolean eliminado) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numPista = numPista;
        this.pais = pais;
        this.telefono = telefono;
        this.eliminado = eliminado;
    }
    

    public Aeropuerto(Integer idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
    }

    public Aeropuerto(Integer idAeropuerto, String nombre, String direccion, int numPista, String pais, int telefono, boolean eliminado) {
        this.idAeropuerto = idAeropuerto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numPista = numPista;
        this.pais = pais;
        this.telefono = telefono;
        this.eliminado = eliminado;
    }

    public Integer getIdAeropuerto() {
        return idAeropuerto;
    }

    public void setIdAeropuerto(Integer idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
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

    public int getNumPista() {
        return numPista;
    }

    public void setNumPista(int numPista) {
        this.numPista = numPista;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Avion> getAvionCollection() {
        return avionCollection;
    }

    public void setAvionCollection(Collection<Avion> avionCollection) {
        this.avionCollection = avionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAeropuerto != null ? idAeropuerto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aeropuerto)) {
            return false;
        }
        Aeropuerto other = (Aeropuerto) object;
        if ((this.idAeropuerto == null && other.idAeropuerto != null) || (this.idAeropuerto != null && !this.idAeropuerto.equals(other.idAeropuerto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Aeropuerto[ idAeropuerto=" + idAeropuerto + " ]";
    }
    
}
