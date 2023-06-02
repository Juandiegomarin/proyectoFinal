/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Juan Diego
 */
@Embeddable
public class HabilidadpokemonPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idHabilidad")
    private int idHabilidad;
    @Basic(optional = false)
    @Column(name = "idPokemon")
    private int idPokemon;

    public HabilidadpokemonPK() {
    }

    public HabilidadpokemonPK(int idHabilidad, int idPokemon) {
        this.idHabilidad = idHabilidad;
        this.idPokemon = idPokemon;
    }

    public int getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idHabilidad;
        hash += (int) idPokemon;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HabilidadpokemonPK)) {
            return false;
        }
        HabilidadpokemonPK other = (HabilidadpokemonPK) object;
        if (this.idHabilidad != other.idHabilidad) {
            return false;
        }
        if (this.idPokemon != other.idPokemon) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HabilidadpokemonPK[ idHabilidad=" + idHabilidad + ", idPokemon=" + idPokemon + " ]";
    }
    
}
