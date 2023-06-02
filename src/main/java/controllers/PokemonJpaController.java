/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Generacion;
import entities.Habilidadpokemon;
import entities.Pokemon;
import java.util.ArrayList;
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

    public void create(Pokemon pokemon) throws PreexistingEntityException, Exception {
        if (pokemon.getHabilidadpokemonList() == null) {
            pokemon.setHabilidadpokemonList(new ArrayList<Habilidadpokemon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Generacion generacion = pokemon.getGeneracion();
            if (generacion != null) {
                generacion = em.getReference(generacion.getClass(), generacion.getIdGeneracion());
                pokemon.setGeneracion(generacion);
            }
            List<Habilidadpokemon> attachedHabilidadpokemonList = new ArrayList<Habilidadpokemon>();
            for (Habilidadpokemon habilidadpokemonListHabilidadpokemonToAttach : pokemon.getHabilidadpokemonList()) {
                habilidadpokemonListHabilidadpokemonToAttach = em.getReference(habilidadpokemonListHabilidadpokemonToAttach.getClass(), habilidadpokemonListHabilidadpokemonToAttach.getHabilidadpokemonPK());
                attachedHabilidadpokemonList.add(habilidadpokemonListHabilidadpokemonToAttach);
            }
            pokemon.setHabilidadpokemonList(attachedHabilidadpokemonList);
            em.persist(pokemon);
            if (generacion != null) {
                generacion.getPokemonList().add(pokemon);
                generacion = em.merge(generacion);
            }
            for (Habilidadpokemon habilidadpokemonListHabilidadpokemon : pokemon.getHabilidadpokemonList()) {
                Pokemon oldPokemonOfHabilidadpokemonListHabilidadpokemon = habilidadpokemonListHabilidadpokemon.getPokemon();
                habilidadpokemonListHabilidadpokemon.setPokemon(pokemon);
                habilidadpokemonListHabilidadpokemon = em.merge(habilidadpokemonListHabilidadpokemon);
                if (oldPokemonOfHabilidadpokemonListHabilidadpokemon != null) {
                    oldPokemonOfHabilidadpokemonListHabilidadpokemon.getHabilidadpokemonList().remove(habilidadpokemonListHabilidadpokemon);
                    oldPokemonOfHabilidadpokemonListHabilidadpokemon = em.merge(oldPokemonOfHabilidadpokemonListHabilidadpokemon);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPokemon(pokemon.getIdPokemon()) != null) {
                throw new PreexistingEntityException("Pokemon " + pokemon + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pokemon pokemon) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pokemon persistentPokemon = em.find(Pokemon.class, pokemon.getIdPokemon());
            Generacion generacionOld = persistentPokemon.getGeneracion();
            Generacion generacionNew = pokemon.getGeneracion();
            List<Habilidadpokemon> habilidadpokemonListOld = persistentPokemon.getHabilidadpokemonList();
            List<Habilidadpokemon> habilidadpokemonListNew = pokemon.getHabilidadpokemonList();
            List<String> illegalOrphanMessages = null;
            for (Habilidadpokemon habilidadpokemonListOldHabilidadpokemon : habilidadpokemonListOld) {
                if (!habilidadpokemonListNew.contains(habilidadpokemonListOldHabilidadpokemon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Habilidadpokemon " + habilidadpokemonListOldHabilidadpokemon + " since its pokemon field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (generacionNew != null) {
                generacionNew = em.getReference(generacionNew.getClass(), generacionNew.getIdGeneracion());
                pokemon.setGeneracion(generacionNew);
            }
            List<Habilidadpokemon> attachedHabilidadpokemonListNew = new ArrayList<Habilidadpokemon>();
            for (Habilidadpokemon habilidadpokemonListNewHabilidadpokemonToAttach : habilidadpokemonListNew) {
                habilidadpokemonListNewHabilidadpokemonToAttach = em.getReference(habilidadpokemonListNewHabilidadpokemonToAttach.getClass(), habilidadpokemonListNewHabilidadpokemonToAttach.getHabilidadpokemonPK());
                attachedHabilidadpokemonListNew.add(habilidadpokemonListNewHabilidadpokemonToAttach);
            }
            habilidadpokemonListNew = attachedHabilidadpokemonListNew;
            pokemon.setHabilidadpokemonList(habilidadpokemonListNew);
            pokemon = em.merge(pokemon);
            if (generacionOld != null && !generacionOld.equals(generacionNew)) {
                generacionOld.getPokemonList().remove(pokemon);
                generacionOld = em.merge(generacionOld);
            }
            if (generacionNew != null && !generacionNew.equals(generacionOld)) {
                generacionNew.getPokemonList().add(pokemon);
                generacionNew = em.merge(generacionNew);
            }
            for (Habilidadpokemon habilidadpokemonListNewHabilidadpokemon : habilidadpokemonListNew) {
                if (!habilidadpokemonListOld.contains(habilidadpokemonListNewHabilidadpokemon)) {
                    Pokemon oldPokemonOfHabilidadpokemonListNewHabilidadpokemon = habilidadpokemonListNewHabilidadpokemon.getPokemon();
                    habilidadpokemonListNewHabilidadpokemon.setPokemon(pokemon);
                    habilidadpokemonListNewHabilidadpokemon = em.merge(habilidadpokemonListNewHabilidadpokemon);
                    if (oldPokemonOfHabilidadpokemonListNewHabilidadpokemon != null && !oldPokemonOfHabilidadpokemonListNewHabilidadpokemon.equals(pokemon)) {
                        oldPokemonOfHabilidadpokemonListNewHabilidadpokemon.getHabilidadpokemonList().remove(habilidadpokemonListNewHabilidadpokemon);
                        oldPokemonOfHabilidadpokemonListNewHabilidadpokemon = em.merge(oldPokemonOfHabilidadpokemonListNewHabilidadpokemon);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Habilidadpokemon> habilidadpokemonListOrphanCheck = pokemon.getHabilidadpokemonList();
            for (Habilidadpokemon habilidadpokemonListOrphanCheckHabilidadpokemon : habilidadpokemonListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pokemon (" + pokemon + ") cannot be destroyed since the Habilidadpokemon " + habilidadpokemonListOrphanCheckHabilidadpokemon + " in its habilidadpokemonList field has a non-nullable pokemon field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Generacion generacion = pokemon.getGeneracion();
            if (generacion != null) {
                generacion.getPokemonList().remove(pokemon);
                generacion = em.merge(generacion);
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
