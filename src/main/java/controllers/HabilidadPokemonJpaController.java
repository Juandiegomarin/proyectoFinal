/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Habilidad;
import entities.HabilidadPokemon;
import entities.HabilidadPokemonPK;
import entities.Pokemon;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Diego
 */
public class HabilidadPokemonJpaController implements Serializable {

    public HabilidadPokemonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HabilidadPokemon habilidadpokemon) throws PreexistingEntityException, Exception {
        if (habilidadpokemon.getHabilidadpokemonPK() == null) {
            habilidadpokemon.setHabilidadpokemonPK(new HabilidadPokemonPK());
        }
        habilidadpokemon.getHabilidadpokemonPK().setIdHabilidad(habilidadpokemon.getHabilidad().getIdHabilidad());
        habilidadpokemon.getHabilidadpokemonPK().setIdPokemon(habilidadpokemon.getPokemon().getIdPokemon());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habilidad habilidad = habilidadpokemon.getHabilidad();
            if (habilidad != null) {
                habilidad = em.getReference(habilidad.getClass(), habilidad.getIdHabilidad());
                habilidadpokemon.setHabilidad(habilidad);
            }
            Pokemon pokemon = habilidadpokemon.getPokemon();
            if (pokemon != null) {
                pokemon = em.getReference(pokemon.getClass(), pokemon.getIdPokemon());
                habilidadpokemon.setPokemon(pokemon);
            }
            em.persist(habilidadpokemon);
            if (habilidad != null) {
                habilidad.getHabilidadpokemonList().add(habilidadpokemon);
                habilidad = em.merge(habilidad);
            }
            if (pokemon != null) {
                pokemon.getHabilidadpokemonList().add(habilidadpokemon);
                pokemon = em.merge(pokemon);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHabilidadpokemon(habilidadpokemon.getHabilidadpokemonPK()) != null) {
                throw new PreexistingEntityException("Habilidadpokemon " + habilidadpokemon + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HabilidadPokemon habilidadpokemon) throws NonexistentEntityException, Exception {
        habilidadpokemon.getHabilidadpokemonPK().setIdHabilidad(habilidadpokemon.getHabilidad().getIdHabilidad());
        habilidadpokemon.getHabilidadpokemonPK().setIdPokemon(habilidadpokemon.getPokemon().getIdPokemon());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HabilidadPokemon persistentHabilidadpokemon = em.find(HabilidadPokemon.class, habilidadpokemon.getHabilidadpokemonPK());
            Habilidad habilidadOld = persistentHabilidadpokemon.getHabilidad();
            Habilidad habilidadNew = habilidadpokemon.getHabilidad();
            Pokemon pokemonOld = persistentHabilidadpokemon.getPokemon();
            Pokemon pokemonNew = habilidadpokemon.getPokemon();
            if (habilidadNew != null) {
                habilidadNew = em.getReference(habilidadNew.getClass(), habilidadNew.getIdHabilidad());
                habilidadpokemon.setHabilidad(habilidadNew);
            }
            if (pokemonNew != null) {
                pokemonNew = em.getReference(pokemonNew.getClass(), pokemonNew.getIdPokemon());
                habilidadpokemon.setPokemon(pokemonNew);
            }
            habilidadpokemon = em.merge(habilidadpokemon);
            if (habilidadOld != null && !habilidadOld.equals(habilidadNew)) {
                habilidadOld.getHabilidadpokemonList().remove(habilidadpokemon);
                habilidadOld = em.merge(habilidadOld);
            }
            if (habilidadNew != null && !habilidadNew.equals(habilidadOld)) {
                habilidadNew.getHabilidadpokemonList().add(habilidadpokemon);
                habilidadNew = em.merge(habilidadNew);
            }
            if (pokemonOld != null && !pokemonOld.equals(pokemonNew)) {
                pokemonOld.getHabilidadpokemonList().remove(habilidadpokemon);
                pokemonOld = em.merge(pokemonOld);
            }
            if (pokemonNew != null && !pokemonNew.equals(pokemonOld)) {
                pokemonNew.getHabilidadpokemonList().add(habilidadpokemon);
                pokemonNew = em.merge(pokemonNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HabilidadPokemonPK id = habilidadpokemon.getHabilidadpokemonPK();
                if (findHabilidadpokemon(id) == null) {
                    throw new NonexistentEntityException("The habilidadpokemon with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HabilidadPokemonPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HabilidadPokemon habilidadpokemon;
            try {
                habilidadpokemon = em.getReference(HabilidadPokemon.class, id);
                habilidadpokemon.getHabilidadpokemonPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habilidadpokemon with id " + id + " no longer exists.", enfe);
            }
            Habilidad habilidad = habilidadpokemon.getHabilidad();
            if (habilidad != null) {
                habilidad.getHabilidadpokemonList().remove(habilidadpokemon);
                habilidad = em.merge(habilidad);
            }
            Pokemon pokemon = habilidadpokemon.getPokemon();
            if (pokemon != null) {
                pokemon.getHabilidadpokemonList().remove(habilidadpokemon);
                pokemon = em.merge(pokemon);
            }
            em.remove(habilidadpokemon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HabilidadPokemon> findHabilidadpokemonEntities() {
        return findHabilidadpokemonEntities(true, -1, -1);
    }

    public List<HabilidadPokemon> findHabilidadpokemonEntities(int maxResults, int firstResult) {
        return findHabilidadpokemonEntities(false, maxResults, firstResult);
    }

    private List<HabilidadPokemon> findHabilidadpokemonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HabilidadPokemon.class));
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

    public HabilidadPokemon findHabilidadpokemon(HabilidadPokemonPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HabilidadPokemon.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabilidadpokemonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HabilidadPokemon> rt = cq.from(HabilidadPokemon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
