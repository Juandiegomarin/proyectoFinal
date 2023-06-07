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
@Table(name = "habilidad")
@NamedQueries({
    @NamedQuery(name = "Habilidad.findAll", query = "SELECT h FROM Habilidad h"),
    @NamedQuery(name = "Habilidad.findByIdHabilidad", query = "SELECT h FROM Habilidad h WHERE h.idHabilidad = :idHabilidad"),
    @NamedQuery(name = "Habilidades.deleteAll", query = "DELETE FROM Habilidad"),
    @NamedQuery(name = "Habilidad.findByNombreHabilidad", query = "SELECT h FROM Habilidad h WHERE h.nombreHabilidad = :nombreHabilidad"),
    @NamedQuery(name = "Habilidad.findByDescripcion", query = "SELECT h FROM Habilidad h WHERE h.descripcion = :descripcion")})
public class Habilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHabilidad")
    private Integer idHabilidad;
    @Column(name = "nombreHabilidad")
    private String nombreHabilidad;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "habilidad")
    private List<Pokemon> pokemonList;

    public Habilidad() {
    }

    public Habilidad(Integer idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public Integer getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(Integer idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getNombreHabilidad() {
        return nombreHabilidad;
    }

    public void setNombreHabilidad(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idHabilidad != null ? idHabilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habilidad)) {
            return false;
        }
        Habilidad other = (Habilidad) object;
        if ((this.idHabilidad == null && other.idHabilidad != null) || (this.idHabilidad != null && !this.idHabilidad.equals(other.idHabilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idHabilidad + ";" + nombreHabilidad + ";" + descripcion;
    }

    
    
}
