/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
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

/**
 *
 * @author juandi
 */
@Entity
@Table(name = "pokemon")
@NamedQueries({
    @NamedQuery(name = "Pokemon.findAll", query = "SELECT p FROM Pokemon p"),
    @NamedQuery(name = "Pokemon.findByIdPokemon", query = "SELECT p FROM Pokemon p WHERE p.idPokemon = :idPokemon"),
    @NamedQuery(name = "Pokemon.findByNumeroPokedex", query = "SELECT p FROM Pokemon p WHERE p.numeroPokedex = :numeroPokedex"),
    @NamedQuery(name = "Pokemon.findByNombre", query = "SELECT p FROM Pokemon p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Pokemon.findByTipo1", query = "SELECT p FROM Pokemon p WHERE p.tipo1 = :tipo1"),
    @NamedQuery(name = "Pokemon.findByTipo2", query = "SELECT p FROM Pokemon p WHERE p.tipo2 = :tipo2")})
public class Pokemon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPokemon")
    private Integer idPokemon;
    @Column(name = "numeroPokedex")
    private Integer numeroPokedex;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo1")
    private String tipo1;
    @Basic(optional = false)
    @Column(name = "tipo2")
    private String tipo2;
    @JoinColumn(name = "generacion", referencedColumnName = "numeroGeneracion")
    @ManyToOne
    private Generacion generacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pokemon")
    private List<HabilidadPokemon> habilidadPokemonList;

    public Pokemon() {
    }

    public Pokemon(Integer idPokemon) {
        this.idPokemon = idPokemon;
    }

    public Pokemon(Integer idPokemon, String tipo2) {
        this.idPokemon = idPokemon;
        this.tipo2 = tipo2;
    }

    public Integer getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(Integer idPokemon) {
        this.idPokemon = idPokemon;
    }

    public Integer getNumeroPokedex() {
        return numeroPokedex;
    }

    public void setNumeroPokedex(Integer numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public Generacion getGeneracion() {
        return generacion;
    }

    public void setGeneracion(Generacion generacion) {
        this.generacion = generacion;
    }

    public List<HabilidadPokemon> getHabilidadPokemonList() {
        return habilidadPokemonList;
    }

    public void setHabilidadPokemonList(List<HabilidadPokemon> habilidadPokemonList) {
        this.habilidadPokemonList = habilidadPokemonList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPokemon != null ? idPokemon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pokemon)) {
            return false;
        }
        Pokemon other = (Pokemon) object;
        if ((this.idPokemon == null && other.idPokemon != null) || (this.idPokemon != null && !this.idPokemon.equals(other.idPokemon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pokemon[ idPokemon=" + idPokemon + " ]";
    }
    
}