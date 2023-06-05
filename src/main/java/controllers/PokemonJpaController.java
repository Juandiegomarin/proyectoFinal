/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Generacion;
import entities.Habilidad;
import entities.Pokemon;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Diego
 */
public class PokemonJpaController implements Serializable {

    public PokemonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pokemon pokemon) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Generacion generacion = pokemon.getGeneracion();
            if (generacion != null) {
                generacion = em.getReference(generacion.getClass(), generacion.getIdGeneracion());
                pokemon.setGeneracion(generacion);
            }
            Habilidad habilidad = pokemon.getHabilidad();
            if (habilidad != null) {
                habilidad = em.getReference(habilidad.getClass(), habilidad.getIdHabilidad());
                pokemon.setHabilidad(habilidad);
            }
            em.persist(pokemon);
            if (generacion != null) {
                generacion.getPokemonList().add(pokemon);
                generacion = em.merge(generacion);
            }
            if (habilidad != null) {
                habilidad.getPokemonList().add(pokemon);
                habilidad = em.merge(habilidad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pokemon pokemon) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pokemon persistentPokemon = em.find(Pokemon.class, pokemon.getIdPokemon());
            Generacion generacionOld = persistentPokemon.getGeneracion();
            Generacion generacionNew = pokemon.getGeneracion();
            Habilidad habilidadOld = persistentPokemon.getHabilidad();
            Habilidad habilidadNew = pokemon.getHabilidad();
            if (generacionNew != null) {
                generacionNew = em.getReference(generacionNew.getClass(), generacionNew.getIdGeneracion());
                pokemon.setGeneracion(generacionNew);
            }
            if (habilidadNew != null) {
                habilidadNew = em.getReference(habilidadNew.getClass(), habilidadNew.getIdHabilidad());
                pokemon.setHabilidad(habilidadNew);
            }
            pokemon = em.merge(pokemon);
            if (generacionOld != null && !generacionOld.equals(generacionNew)) {
                generacionOld.getPokemonList().remove(pokemon);
                generacionOld = em.merge(generacionOld);
            }
            if (generacionNew != null && !generacionNew.equals(generacionOld)) {
                generacionNew.getPokemonList().add(pokemon);
                generacionNew = em.merge(generacionNew);
            }
            if (habilidadOld != null && !habilidadOld.equals(habilidadNew)) {
                habilidadOld.getPokemonList().remove(pokemon);
                habilidadOld = em.merge(habilidadOld);
            }
            if (habilidadNew != null && !habilidadNew.equals(habilidadOld)) {
                habilidadNew.getPokemonList().add(pokemon);
                habilidadNew = em.merge(habilidadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pokemon.getIdPokemon();
                if (findPokemon(id) == null) {
                    throw new NonexistentEntityException("The pokemon with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pokemon pokemon;
            try {
                pokemon = em.getReference(Pokemon.class, id);
                pokemon.getIdPokemon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pokemon with id " + id + " no longer exists.", enfe);
            }
            Generacion generacion = pokemon.getGeneracion();
            if (generacion != null) {
                generacion.getPokemonList().remove(pokemon);
                generacion = em.merge(generacion);
            }
            Habilidad habilidad = pokemon.getHabilidad();
            if (habilidad != null) {
                habilidad.getPokemonList().remove(pokemon);
                habilidad = em.merge(habilidad);
            }
            em.remove(pokemon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pokemon> findPokemonEntities() {
        return findPokemonEntities(true, -1, -1);
    }

    public List<Pokemon> findPokemonEntities(int maxResults, int firstResult) {
        return findPokemonEntities(false, maxResults, firstResult);
    }

    private List<Pokemon> findPokemonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pokemon.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pokemon findPokemon(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pokemon.class, id);
        } finally {
            em.close();
        }
    }

    public int getPokemonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pokemon> rt = cq.from(Pokemon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
