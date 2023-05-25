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
import entities.HabilidadPokemon;
import entities.Pokemon;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juandi
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
        if (pokemon.getHabilidadPokemonList() == null) {
            pokemon.setHabilidadPokemonList(new ArrayList<HabilidadPokemon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Generacion generacion = pokemon.getGeneracion();
            if (generacion != null) {
                generacion = em.getReference(generacion.getClass(), generacion.getNumeroGeneracion());
                pokemon.setGeneracion(generacion);
            }
            List<HabilidadPokemon> attachedHabilidadPokemonList = new ArrayList<HabilidadPokemon>();
            for (HabilidadPokemon habilidadPokemonListHabilidadPokemonToAttach : pokemon.getHabilidadPokemonList()) {
                habilidadPokemonListHabilidadPokemonToAttach = em.getReference(habilidadPokemonListHabilidadPokemonToAttach.getClass(), habilidadPokemonListHabilidadPokemonToAttach.getHabilidadPokemonPK());
                attachedHabilidadPokemonList.add(habilidadPokemonListHabilidadPokemonToAttach);
            }
            pokemon.setHabilidadPokemonList(attachedHabilidadPokemonList);
            em.persist(pokemon);
            if (generacion != null) {
                generacion.getPokemonList().add(pokemon);
                generacion = em.merge(generacion);
            }
            for (HabilidadPokemon habilidadPokemonListHabilidadPokemon : pokemon.getHabilidadPokemonList()) {
                Pokemon oldPokemonOfHabilidadPokemonListHabilidadPokemon = habilidadPokemonListHabilidadPokemon.getPokemon();
                habilidadPokemonListHabilidadPokemon.setPokemon(pokemon);
                habilidadPokemonListHabilidadPokemon = em.merge(habilidadPokemonListHabilidadPokemon);
                if (oldPokemonOfHabilidadPokemonListHabilidadPokemon != null) {
                    oldPokemonOfHabilidadPokemonListHabilidadPokemon.getHabilidadPokemonList().remove(habilidadPokemonListHabilidadPokemon);
                    oldPokemonOfHabilidadPokemonListHabilidadPokemon = em.merge(oldPokemonOfHabilidadPokemonListHabilidadPokemon);
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
            List<HabilidadPokemon> habilidadPokemonListOld = persistentPokemon.getHabilidadPokemonList();
            List<HabilidadPokemon> habilidadPokemonListNew = pokemon.getHabilidadPokemonList();
            List<String> illegalOrphanMessages = null;
            for (HabilidadPokemon habilidadPokemonListOldHabilidadPokemon : habilidadPokemonListOld) {
                if (!habilidadPokemonListNew.contains(habilidadPokemonListOldHabilidadPokemon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HabilidadPokemon " + habilidadPokemonListOldHabilidadPokemon + " since its pokemon field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (generacionNew != null) {
                generacionNew = em.getReference(generacionNew.getClass(), generacionNew.getNumeroGeneracion());
                pokemon.setGeneracion(generacionNew);
            }
            List<HabilidadPokemon> attachedHabilidadPokemonListNew = new ArrayList<HabilidadPokemon>();
            for (HabilidadPokemon habilidadPokemonListNewHabilidadPokemonToAttach : habilidadPokemonListNew) {
                habilidadPokemonListNewHabilidadPokemonToAttach = em.getReference(habilidadPokemonListNewHabilidadPokemonToAttach.getClass(), habilidadPokemonListNewHabilidadPokemonToAttach.getHabilidadPokemonPK());
                attachedHabilidadPokemonListNew.add(habilidadPokemonListNewHabilidadPokemonToAttach);
            }
            habilidadPokemonListNew = attachedHabilidadPokemonListNew;
            pokemon.setHabilidadPokemonList(habilidadPokemonListNew);
            pokemon = em.merge(pokemon);
            if (generacionOld != null && !generacionOld.equals(generacionNew)) {
                generacionOld.getPokemonList().remove(pokemon);
                generacionOld = em.merge(generacionOld);
            }
            if (generacionNew != null && !generacionNew.equals(generacionOld)) {
                generacionNew.getPokemonList().add(pokemon);
                generacionNew = em.merge(generacionNew);
            }
            for (HabilidadPokemon habilidadPokemonListNewHabilidadPokemon : habilidadPokemonListNew) {
                if (!habilidadPokemonListOld.contains(habilidadPokemonListNewHabilidadPokemon)) {
                    Pokemon oldPokemonOfHabilidadPokemonListNewHabilidadPokemon = habilidadPokemonListNewHabilidadPokemon.getPokemon();
                    habilidadPokemonListNewHabilidadPokemon.setPokemon(pokemon);
                    habilidadPokemonListNewHabilidadPokemon = em.merge(habilidadPokemonListNewHabilidadPokemon);
                    if (oldPokemonOfHabilidadPokemonListNewHabilidadPokemon != null && !oldPokemonOfHabilidadPokemonListNewHabilidadPokemon.equals(pokemon)) {
                        oldPokemonOfHabilidadPokemonListNewHabilidadPokemon.getHabilidadPokemonList().remove(habilidadPokemonListNewHabilidadPokemon);
                        oldPokemonOfHabilidadPokemonListNewHabilidadPokemon = em.merge(oldPokemonOfHabilidadPokemonListNewHabilidadPokemon);
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
            List<HabilidadPokemon> habilidadPokemonListOrphanCheck = pokemon.getHabilidadPokemonList();
            for (HabilidadPokemon habilidadPokemonListOrphanCheckHabilidadPokemon : habilidadPokemonListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pokemon (" + pokemon + ") cannot be destroyed since the HabilidadPokemon " + habilidadPokemonListOrphanCheckHabilidadPokemon + " in its habilidadPokemonList field has a non-nullable pokemon field.");
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
