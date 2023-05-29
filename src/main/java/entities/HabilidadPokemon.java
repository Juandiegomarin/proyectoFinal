/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Juan Diego
 */
@Entity
@Table(name = "habilidadpokemon")
@NamedQueries({
    @NamedQuery(name = "Habilidadpokemon.findAll", query = "SELECT h FROM HabilidadPokemon h"),
    @NamedQuery(name = "Habilidadpokemon.findByIdHabilidad", query = "SELECT h FROM HabilidadPokemon h WHERE h.habilidadpokemonPK.idHabilidad = :idHabilidad"),
    @NamedQuery(name = "Habilidadpokemon.findByIdPokemon", query = "SELECT h FROM HabilidadPokemon h WHERE h.habilidadpokemonPK.idPokemon = :idPokemon"),
    @NamedQuery(name = "Habilidadpokemon.findByOculta", query = "SELECT h FROM HabilidadPokemon h WHERE h.oculta = :oculta")})
public class HabilidadPokemon implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HabilidadPokemonPK habilidadpokemonPK;
    @Column(name = "oculta")
    private Boolean oculta;
    @JoinColumn(name = "idHabilidad", referencedColumnName = "idHabilidad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Habilidad habilidad;
    @JoinColumn(name = "idPokemon", referencedColumnName = "idPokemon", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pokemon pokemon;

    public HabilidadPokemon() {
    }

    public HabilidadPokemon(HabilidadPokemonPK habilidadpokemonPK) {
        this.habilidadpokemonPK = habilidadpokemonPK;
    }

    public HabilidadPokemon(int idHabilidad, int idPokemon) {
        this.habilidadpokemonPK = new HabilidadPokemonPK(idHabilidad, idPokemon);
    }

    public HabilidadPokemonPK getHabilidadpokemonPK() {
        return habilidadpokemonPK;
    }

    public void setHabilidadpokemonPK(HabilidadPokemonPK habilidadpokemonPK) {
        this.habilidadpokemonPK = habilidadpokemonPK;
    }

    public Boolean getOculta() {
        return oculta;
    }

    public void setOculta(Boolean oculta) {
        this.oculta = oculta;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (habilidadpokemonPK != null ? habilidadpokemonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HabilidadPokemon)) {
            return false;
        }
        HabilidadPokemon other = (HabilidadPokemon) object;
        if ((this.habilidadpokemonPK == null && other.habilidadpokemonPK != null) || (this.habilidadpokemonPK != null && !this.habilidadpokemonPK.equals(other.habilidadpokemonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Habilidadpokemon[ habilidadpokemonPK=" + habilidadpokemonPK + " ]";
    }
    
}
