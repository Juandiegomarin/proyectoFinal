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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juandi
 */
@Entity
@Table(name = "generacion")
@NamedQueries({
    @NamedQuery(name = "Generacion.findAll", query = "SELECT g FROM Generacion g"),
    @NamedQuery(name = "Generacion.findByNumeroGeneracion", query = "SELECT g FROM Generacion g WHERE g.numeroGeneracion = :numeroGeneracion"),
    @NamedQuery(name = "Generacion.findByNumeroPokemon", query = "SELECT g FROM Generacion g WHERE g.numeroPokemon = :numeroPokemon"),
    @NamedQuery(name = "Generacion.findByNombreRegion", query = "SELECT g FROM Generacion g WHERE g.nombreRegion = :nombreRegion")})
public class Generacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
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

    public Generacion(Integer numeroGeneracion) {
        this.numeroGeneracion = numeroGeneracion;
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
        hash += (numeroGeneracion != null ? numeroGeneracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Generacion)) {
            return false;
        }
        Generacion other = (Generacion) object;
        if ((this.numeroGeneracion == null && other.numeroGeneracion != null) || (this.numeroGeneracion != null && !this.numeroGeneracion.equals(other.numeroGeneracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Generacion[ numeroGeneracion=" + numeroGeneracion + " ]";
    }
    
}
