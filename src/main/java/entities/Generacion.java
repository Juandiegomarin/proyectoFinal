/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Juan Diego
 */
@Entity
@Table(name = "generacion")
@NamedQueries({
    @NamedQuery(name = "Generacion.findAll", query = "SELECT g FROM Generacion g"),
    @NamedQuery(name = "Generacion.findByIdGeneracion", query = "SELECT g FROM Generacion g WHERE g.idGeneracion = :idGeneracion"),
    @NamedQuery(name = "Generacion.findByNumeroGeneracion", query = "SELECT g FROM Generacion g WHERE g.numeroGeneracion = :numeroGeneracion"),
    @NamedQuery(name = "Generaciones.deleteAll", query = "DELETE FROM Generacion"),
    @NamedQuery(name = "Generacion.findByNumeroPokemon", query = "SELECT g FROM Generacion g WHERE g.numeroPokemon = :numeroPokemon"),
    @NamedQuery(name = "Generacion.findByNombreRegion", query = "SELECT g FROM Generacion g WHERE g.nombreRegion = :nombreRegion")})
public class Generacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGeneracion")
    private Integer idGeneracion;
    @Column(name = "numeroGeneracion")
    private Integer numeroGeneracion;
    @Column(name = "numeroPokemon")
    private Integer numeroPokemon;
    @Column(name = "nombreRegion")
    private String nombreRegion;
    @OneToMany(mappedBy = "generacion")
    private List<Pokemon> pokemonList;

    public Generacion() {
    }

    public Generacion(Integer idGeneracion) {
        this.idGeneracion = idGeneracion;
    }

    public Integer getIdGeneracion() {
        return idGeneracion;
    }

    public void setIdGeneracion(Integer idGeneracion) {
        this.idGeneracion = idGeneracion;
    }

    public Integer getNumeroGeneracion() {
        return numeroGeneracion;
    }

    public void setNumeroGeneracion(Integer numeroGeneracion) {
        this.numeroGeneracion = numeroGeneracion;
    }

    public Integer getNumeroPokemon() {
        return numeroPokemon;
    }

    public void setNumeroPokemon(Integer numeroPokemon) {
        this.numeroPokemon = numeroPokemon;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGeneracion != null ? idGeneracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Generacion)) {
            return false;
        }
        Generacion other = (Generacion) object;
        if ((this.idGeneracion == null && other.idGeneracion != null) || (this.idGeneracion != null && !this.idGeneracion.equals(other.idGeneracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idGeneracion + ";" + numeroGeneracion + ";" + numeroPokemon + ";" + nombreRegion;
    }

}
