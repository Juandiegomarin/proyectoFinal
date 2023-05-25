/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entities.Habilidad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.HabilidadPokemon;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juandi
 */
public class HabilidadJpaController implements Serializable {

    public HabilidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habilidad habilidad) throws PreexistingEntityException, Exception {
        if (habilidad.getHabilidadPokemonList() == null) {
            habilidad.setHabilidadPokemonList(new ArrayList<HabilidadPokemon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HabilidadPokemon> attachedHabilidadPokemonList = new ArrayList<HabilidadPokemon>();
            for (HabilidadPokemon habilidadPokemonListHabilidadPokemonToAttach : habilidad.getHabilidadPokemonList()) {
                habilidadPokemonListHabilidadPokemonToAttach = em.getReference(habilidadPokemonListHabilidadPokemonToAttach.getClass(), habilidadPokemonListHabilidadPokemonToAttach.getHabilidadPokemonPK());
                attachedHabilidadPokemonList.add(habilidadPokemonListHabilidadPokemonToAttach);
            }
            habilidad.setHabilidadPokemonList(attachedHabilidadPokemonList);
            em.persist(habilidad);
            for (HabilidadPokemon habilidadPokemonListHabilidadPokemon : habilidad.getHabilidadPokemonList()) {
                Habilidad oldHabilidadOfHabilidadPokemonListHabilidadPokemon = habilidadPokemonListHabilidadPokemon.getHabilidad();
                habilidadPokemonListHabilidadPokemon.setHabilidad(habilidad);
                habilidadPokemonListHabilidadPokemon = em.merge(habilidadPokemonListHabilidadPokemon);
                if (oldHabilidadOfHabilidadPokemonListHabilidadPokemon != null) {
                    oldHabilidadOfHabilidadPokemonListHabilidadPokemon.getHabilidadPokemonList().remove(habilidadPokemonListHabilidadPokemon);
                    oldHabilidadOfHabilidadPokemonListHabilidadPokemon = em.merge(oldHabilidadOfHabilidadPokemonListHabilidadPokemon);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHabilidad(habilidad.getIdHabilidad()) != null) {
                throw new PreexistingEntityException("Habilidad " + habilidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habilidad habilidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habilidad persistentHabilidad = em.find(Habilidad.class, habilidad.getIdHabilidad());
            List<HabilidadPokemon> habilidadPokemonListOld = persistentHabilidad.getHabilidadPokemonList();
            List<HabilidadPokemon> habilidadPokemonListNew = habilidad.getHabilidadPokemonList();
            List<String> illegalOrphanMessages = null;
            for (HabilidadPokemon habilidadPokemonListOldHabilidadPokemon : habilidadPokemonListOld) {
                if (!habilidadPokemonListNew.contains(habilidadPokemonListOldHabilidadPokemon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HabilidadPokemon " + habilidadPokemonListOldHabilidadPokemon + " since its habilidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HabilidadPokemon> attachedHabilidadPokemonListNew = new ArrayList<HabilidadPokemon>();
            for (HabilidadPokemon habilidadPokemonListNewHabilidadPokemonToAttach : habilidadPokemonListNew) {
                habilidadPokemonListNewHabilidadPokemonToAttach = em.getReference(habilidadPokemonListNewHabilidadPokemonToAttach.getClass(), habilidadPokemonListNewHabilidadPokemonToAttach.getHabilidadPokemonPK());
                attachedHabilidadPokemonListNew.add(habilidadPokemonListNewHabilidadPokemonToAttach);
            }
            habilidadPokemonListNew = attachedHabilidadPokemonListNew;
            habilidad.setHabilidadPokemonList(habilidadPokemonListNew);
            habilidad = em.merge(habilidad);
            for (HabilidadPokemon habilidadPokemonListNewHabilidadPokemon : habilidadPokemonListNew) {
                if (!habilidadPokemonListOld.contains(habilidadPokemonListNewHabilidadPokemon)) {
                    Habilidad oldHabilidadOfHabilidadPokemonListNewHabilidadPokemon = habilidadPokemonListNewHabilidadPokemon.getHabilidad();
                    habilidadPokemonListNewHabilidadPokemon.setHabilidad(habilidad);
                    habilidadPokemonListNewHabilidadPokemon = em.merge(habilidadPokemonListNewHabilidadPokemon);
                    if (oldHabilidadOfHabilidadPokemonListNewHabilidadPokemon != null && !oldHabilidadOfHabilidadPokemonListNewHabilidadPokemon.equals(habilidad)) {
                        oldHabilidadOfHabilidadPokemonListNewHabilidadPokemon.getHabilidadPokemonList().remove(habilidadPokemonListNewHabilidadPokemon);
                        oldHabilidadOfHabilidadPokemonListNewHabilidadPokemon = em.merge(oldHabilidadOfHabilidadPokemonListNewHabilidadPokemon);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<HabilidadPokemon> habilidadPokemonListOrphanCheck = habilidad.getHabilidadPokemonList();
            for (HabilidadPokemon habilidadPokemonListOrphanCheckHabilidadPokemon : habilidadPokemonListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Habilidad (" + habilidad + ") cannot be destroyed since the HabilidadPokemon " + habilidadPokemonListOrphanCheckHabilidadPokemon + " in its habilidadPokemonList field has a non-nullable habilidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
