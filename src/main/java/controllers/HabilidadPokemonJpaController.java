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
 * @author juandi
 */
public class HabilidadPokemonJpaController implements Serializable {

    public HabilidadPokemonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HabilidadPokemon habilidadPokemon) throws PreexistingEntityException, Exception {
        if (habilidadPokemon.getHabilidadPokemonPK() == null) {
            habilidadPokemon.setHabilidadPokemonPK(new HabilidadPokemonPK());
        }
        habilidadPokemon.getHabilidadPokemonPK().setIdHabilidad(habilidadPokemon.getHabilidad().getIdHabilidad());
        habilidadPokemon.getHabilidadPokemonPK().setIdPokemon(habilidadPokemon.getPokemon().getIdPokemon());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habilidad habilidad = habilidadPokemon.getHabilidad();
            if (habilidad != null) {
                habilidad = em.getReference(habilidad.getClass(), habilidad.getIdHabilidad());
                habilidadPokemon.setHabilidad(habilidad);
            }
            Pokemon pokemon = habilidadPokemon.getPokemon();
            if (pokemon != null) {
                pokemon = em.getReference(pokemon.getClass(), pokemon.getIdPokemon());
                habilidadPokemon.setPokemon(pokemon);
            }
            em.persist(habilidadPokemon);
            if (habilidad != null) {
                habilidad.getHabilidadPokemonList().add(habilidadPokemon);
                habilidad = em.merge(habilidad);
            }
            if (pokemon != null) {
                pokemon.getHabilidadPokemonList().add(habilidadPokemon);
                pokemon = em.merge(pokemon);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHabilidadPokemon(habilidadPokemon.getHabilidadPokemonPK()) != null) {
                throw new PreexistingEntityException("HabilidadPokemon " + habilidadPokemon + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HabilidadPokemon habilidadPokemon) throws NonexistentEntityException, Exception {
        habilidadPokemon.getHabilidadPokemonPK().setIdHabilidad(habilidadPokemon.getHabilidad().getIdHabilidad());
        habilidadPokemon.getHabilidadPokemonPK().setIdPokemon(habilidadPokemon.getPokemon().getIdPokemon());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HabilidadPokemon persistentHabilidadPokemon = em.find(HabilidadPokemon.class, habilidadPokemon.getHabilidadPokemonPK());
            Habilidad habilidadOld = persistentHabilidadPokemon.getHabilidad();
            Habilidad habilidadNew = habilidadPokemon.getHabilidad();
            Pokemon pokemonOld = persistentHabilidadPokemon.getPokemon();
            Pokemon pokemonNew = habilidadPokemon.getPokemon();
            if (habilidadNew != null) {
                habilidadNew = em.getReference(habilidadNew.getClass(), habilidadNew.getIdHabilidad());
                habilidadPokemon.setHabilidad(habilidadNew);
            }
            if (pokemonNew != null) {
                pokemonNew = em.getReference(pokemonNew.getClass(), pokemonNew.getIdPokemon());
                habilidadPokemon.setPokemon(pokemonNew);
            }
            habilidadPokemon = em.merge(habilidadPokemon);
            if (habilidadOld != null && !habilidadOld.equals(habilidadNew)) {
                habilidadOld.getHabilidadPokemonList().remove(habilidadPokemon);
                habilidadOld = em.merge(habilidadOld);
            }
            if (habilidadNew != null && !habilidadNew.equals(habilidadOld)) {
                habilidadNew.getHabilidadPokemonList().add(habilidadPokemon);
                habilidadNew = em.merge(habilidadNew);
            }
            if (pokemonOld != null && !pokemonOld.equals(pokemonNew)) {
                pokemonOld.getHabilidadPokemonList().remove(habilidadPokemon);
                pokemonOld = em.merge(pokemonOld);
            }
            if (pokemonNew != null && !pokemonNew.equals(pokemonOld)) {
                pokemonNew.getHabilidadPokemonList().add(habilidadPokemon);
                pokemonNew = em.merge(pokemonNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HabilidadPokemonPK id = habilidadPokemon.getHabilidadPokemonPK();
                if (findHabilidadPokemon(id) == null) {
                    throw new NonexistentEntityException("The habilidadPokemon with id " + id + " no longer exists.");
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
            HabilidadPokemon habilidadPokemon;
            try {
                habilidadPokemon = em.getReference(HabilidadPokemon.class, id);
                habilidadPokemon.getHabilidadPokemonPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habilidadPokemon with id " + id + " no longer exists.", enfe);
            }
            Habilidad habilidad = habilidadPokemon.getHabilidad();
            if (habilidad != null) {
                habilidad.getHabilidadPokemonList().remove(habilidadPokemon);
                habilidad = em.merge(habilidad);
            }
            Pokemon pokemon = habilidadPokemon.getPokemon();
            if (pokemon != null) {
                pokemon.getHabilidadPokemonList().remove(habilidadPokemon);
                pokemon = em.merge(pokemon);
            }
            em.remove(habilidadPokemon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HabilidadPokemon> findHabilidadPokemonEntities() {
        return findHabilidadPokemonEntities(true, -1, -1);
    }

    public List<HabilidadPokemon> findHabilidadPokemonEntities(int maxResults, int firstResult) {
        return findHabilidadPokemonEntities(false, maxResults, firstResult);
    }

    private List<HabilidadPokemon> findHabilidadPokemonEntities(boolean all, int maxResults, int firstResult) {
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

    public HabilidadPokemon findHabilidadPokemon(HabilidadPokemonPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HabilidadPokemon.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabilidadPokemonCount() {
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
