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
 * @author juandi
 */
@Entity
@Table(name = "habilidadPokemon")
@NamedQueries({
    @NamedQuery(name = "HabilidadPokemon.findAll", query = "SELECT h FROM HabilidadPokemon h"),
    @NamedQuery(name = "HabilidadPokemon.findByIdHabilidad", query = "SELECT h FROM HabilidadPokemon h WHERE h.habilidadPokemonPK.idHabilidad = :idHabilidad"),
    @NamedQuery(name = "HabilidadPokemon.findByIdPokemon", query = "SELECT h FROM HabilidadPokemon h WHERE h.habilidadPokemonPK.idPokemon = :idPokemon"),
    @NamedQuery(name = "HabilidadPokemon.findByOculta", query = "SELECT h FROM HabilidadPokemon h WHERE h.oculta = :oculta")})
public class HabilidadPokemon implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HabilidadPokemonPK habilidadPokemonPK;
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

    public HabilidadPokemon(HabilidadPokemonPK habilidadPokemonPK) {
        this.habilidadPokemonPK = habilidadPokemonPK;
    }

    public HabilidadPokemon(int idHabilidad, int idPokemon) {
        this.habilidadPokemonPK = new HabilidadPokemonPK(idHabilidad, idPokemon);
    }

    public HabilidadPokemonPK getHabilidadPokemonPK() {
        return habilidadPokemonPK;
    }

    public void setHabilidadPokemonPK(HabilidadPokemonPK habilidadPokemonPK) {
        this.habilidadPokemonPK = habilidadPokemonPK;
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
        hash += (habilidadPokemonPK != null ? habilidadPokemonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HabilidadPokemon)) {
            return false;
        }
        HabilidadPokemon other = (HabilidadPokemon) object;
        if ((this.habilidadPokemonPK == null && other.habilidadPokemonPK != null) || (this.habilidadPokemonPK != null && !this.habilidadPokemonPK.equals(other.habilidadPokemonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HabilidadPokemon[ habilidadPokemonPK=" + habilidadPokemonPK + " ]";
    }
    
}
