/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Generacion;
import entities.Habilidad;
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
public class HabilidadJpaController implements Serializable {

    public HabilidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habilidad habilidad) {
        if (habilidad.getPokemonList() == null) {
            habilidad.setPokemonList(new ArrayList<Pokemon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pokemon> attachedPokemonList = new ArrayList<Pokemon>();
            for (Pokemon pokemonListPokemonToAttach : habilidad.getPokemonList()) {
                pokemonListPokemonToAttach = em.getReference(pokemonListPokemonToAttach.getClass(), pokemonListPokemonToAttach.getIdPokemon());
                attachedPokemonList.add(pokemonListPokemonToAttach);
            }
            habilidad.setPokemonList(attachedPokemonList);
            em.persist(habilidad);
            for (Pokemon pokemonListPokemon : habilidad.getPokemonList()) {
                Habilidad oldHabilidadOfPokemonListPokemon = pokemonListPokemon.getHabilidad();
                pokemonListPokemon.setHabilidad(habilidad);
                pokemonListPokemon = em.merge(pokemonListPokemon);
                if (oldHabilidadOfPokemonListPokemon != null) {
                    oldHabilidadOfPokemonListPokemon.getPokemonList().remove(pokemonListPokemon);
                    oldHabilidadOfPokemonListPokemon = em.merge(oldHabilidadOfPokemonListPokemon);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
     public Habilidad findHabilidad(String nombre) {

        Habilidad h = new Habilidad();

        List<Habilidad> habs = findHabilidadEntities();

        for (Habilidad hab : habs) {

            if (hab.getNombreHabilidad().equalsIgnoreCase(nombre)) {
                
                h.setIdHabilidad(hab.getIdHabilidad());
                h.setNombreHabilidad(hab.getNombreHabilidad());
                h.setDescripcion(hab.getDescripcion());
                break;
            }
        }

        return h;
    }

    public void edit(Habilidad habilidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habilidad persistentHabilidad = em.find(Habilidad.class, habilidad.getIdHabilidad());
            List<Pokemon> pokemonListOld = persistentHabilidad.getPokemonList();
            List<Pokemon> pokemonListNew = habilidad.getPokemonList();
            List<Pokemon> attachedPokemonListNew = new ArrayList<Pokemon>();
            for (Pokemon pokemonListNewPokemonToAttach : pokemonListNew) {
                pokemonListNewPokemonToAttach = em.getReference(pokemonListNewPokemonToAttach.getClass(), pokemonListNewPokemonToAttach.getIdPokemon());
                attachedPokemonListNew.add(pokemonListNewPokemonToAttach);
            }
            pokemonListNew = attachedPokemonListNew;
            habilidad.setPokemonList(pokemonListNew);
            habilidad = em.merge(habilidad);
            for (Pokemon pokemonListOldPokemon : pokemonListOld) {
                if (!pokemonListNew.contains(pokemonListOldPokemon)) {
                    pokemonListOldPokemon.setHabilidad(null);
                    pokemonListOldPokemon = em.merge(pokemonListOldPokemon);
                }
            }
            for (Pokemon pokemonListNewPokemon : pokemonListNew) {
                if (!pokemonListOld.contains(pokemonListNewPokemon)) {
                    Habilidad oldHabilidadOfPokemonListNewPokemon = pokemonListNewPokemon.getHabilidad();
                    pokemonListNewPokemon.setHabilidad(habilidad);
                    pokemonListNewPokemon = em.merge(pokemonListNewPokemon);
                    if (oldHabilidadOfPokemonListNewPokemon != null && !oldHabilidadOfPokemonListNewPokemon.equals(habilidad)) {
                        oldHabilidadOfPokemonListNewPokemon.getPokemonList().remove(pokemonListNewPokemon);
                        oldHabilidadOfPokemonListNewPokemon = em.merge(oldHabilidadOfPokemonListNewPokemon);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = habilidad.getIdHabilidad();
                if (findHabilidad(id) == null) {
                    throw new NonexistentEntityException("The habilidad with id " + id + " no longer exists.");
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
            Habilidad habilidad;
            try {
                habilidad = em.getReference(Habilidad.class, id);
                habilidad.getIdHabilidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habilidad with id " + id + " no longer exists.", enfe);
            }
            List<Pokemon> pokemonList = habilidad.getPokemonList();
            for (Pokemon pokemonListPokemon : pokemonList) {
                pokemonListPokemon.setHabilidad(null);
                pokemonListPokemon = em.merge(pokemonListPokemon);
            }
            em.remove(habilidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habilidad> findHabilidadEntities() {
        return findHabilidadEntities(true, -1, -1);
    }

    public List<Habilidad> findHabilidadEntities(int maxResults, int firstResult) {
        return findHabilidadEntities(false, maxResults, firstResult);
    }

    private List<Habilidad> findHabilidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habilidad.class));
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

    public Habilidad findHabilidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habilidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabilidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habilidad> rt = cq.from(Habilidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
