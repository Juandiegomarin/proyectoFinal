/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Generacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Pokemon;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Diego
 */
public class GeneracionJpaController implements Serializable {

    public GeneracionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Generacion generacion) {
        if (generacion.getPokemonList() == null) {
            generacion.setPokemonList(new ArrayList<Pokemon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pokemon> attachedPokemonList = new ArrayList<Pokemon>();
            for (Pokemon pokemonListPokemonToAttach : generacion.getPokemonList()) {
                pokemonListPokemonToAttach = em.getReference(pokemonListPokemonToAttach.getClass(), pokemonListPokemonToAttach.getIdPokemon());
                attachedPokemonList.add(pokemonListPokemonToAttach);
            }
            generacion.setPokemonList(attachedPokemonList);
            em.persist(generacion);
            for (Pokemon pokemonListPokemon : generacion.getPokemonList()) {
                Generacion oldGeneracionOfPokemonListPokemon = pokemonListPokemon.getGeneracion();
                pokemonListPokemon.setGeneracion(generacion);
                pokemonListPokemon = em.merge(pokemonListPokemon);
                if (oldGeneracionOfPokemonListPokemon != null) {
                    oldGeneracionOfPokemonListPokemon.getPokemonList().remove(pokemonListPokemon);
                    oldGeneracionOfPokemonListPokemon = em.merge(oldGeneracionOfPokemonListPokemon);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Generacion generacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Generacion persistentGeneracion = em.find(Generacion.class, generacion.getIdGeneracion());
            List<Pokemon> pokemonListOld = persistentGeneracion.getPokemonList();
            List<Pokemon> pokemonListNew = generacion.getPokemonList();
            List<Pokemon> attachedPokemonListNew = new ArrayList<Pokemon>();
            for (Pokemon pokemonListNewPokemonToAttach : pokemonListNew) {
                pokemonListNewPokemonToAttach = em.getReference(pokemonListNewPokemonToAttach.getClass(), pokemonListNewPokemonToAttach.getIdPokemon());
                attachedPokemonListNew.add(pokemonListNewPokemonToAttach);
            }
            pokemonListNew = attachedPokemonListNew;
            generacion.setPokemonList(pokemonListNew);
            generacion = em.merge(generacion);
            for (Pokemon pokemonListOldPokemon : pokemonListOld) {
                if (!pokemonListNew.contains(pokemonListOldPokemon)) {
                    pokemonListOldPokemon.setGeneracion(null);
                    pokemonListOldPokemon = em.merge(pokemonListOldPokemon);
                }
            }
            for (Pokemon pokemonListNewPokemon : pokemonListNew) {
                if (!pokemonListOld.contains(pokemonListNewPokemon)) {
                    Generacion oldGeneracionOfPokemonListNewPokemon = pokemonListNewPokemon.getGeneracion();
                    pokemonListNewPokemon.setGeneracion(generacion);
                    pokemonListNewPokemon = em.merge(pokemonListNewPokemon);
                    if (oldGeneracionOfPokemonListNewPokemon != null && !oldGeneracionOfPokemonListNewPokemon.equals(generacion)) {
                        oldGeneracionOfPokemonListNewPokemon.getPokemonList().remove(pokemonListNewPokemon);
                        oldGeneracionOfPokemonListNewPokemon = em.merge(oldGeneracionOfPokemonListNewPokemon);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = generacion.getIdGeneracion();
                if (findGeneracion(id) == null) {
                    throw new NonexistentEntityException("The generacion with id " + id + " no longer exists.");
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
            Generacion generacion;
            try {
                generacion = em.getReference(Generacion.class, id);
                generacion.getIdGeneracion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The generacion with id " + id + " no longer exists.", enfe);
            }
            List<Pokemon> pokemonList = generacion.getPokemonList();
            for (Pokemon pokemonListPokemon : pokemonList) {
                pokemonListPokemon.setGeneracion(null);
                pokemonListPokemon = em.merge(pokemonListPokemon);
            }
            em.remove(generacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Generacion> findGeneracionEntities() {
        return findGeneracionEntities(true, -1, -1);
    }

    public List<Generacion> findGeneracionEntities(int maxResults, int firstResult) {
        return findGeneracionEntities(false, maxResults, firstResult);
    }

    private List<Generacion> findGeneracionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Generacion.class));
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

    public Generacion findGeneracion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Generacion.class, id);
        } finally {
            em.close();
        }
    }

    public Generacion findGeneracion(String nombre) {

        Generacion g = new Generacion();

        List<Generacion> gens = findGeneracionEntities();

        for (Generacion gen : gens) {

            if (gen.getNombreRegion().equalsIgnoreCase(nombre)) {
                
                g.setIdGeneracion(gen.getIdGeneracion());
                g.setNombreRegion(gen.getNombreRegion());
                g.setNumeroGeneracion(gen.getNumeroGeneracion());
                g.setNumeroPokemon(gen.getNumeroPokemon());
                
                break;
            }
        }

        return g;
    }

    public int getGeneracionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Generacion> rt = cq.from(Generacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
