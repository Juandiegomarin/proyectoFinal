/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
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
        if (habilidad.getHabilidadpokemonList() == null) {
            habilidad.setHabilidadpokemonList(new ArrayList<HabilidadPokemon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HabilidadPokemon> attachedHabilidadpokemonList = new ArrayList<HabilidadPokemon>();
            for (HabilidadPokemon habilidadpokemonListHabilidadpokemonToAttach : habilidad.getHabilidadpokemonList()) {
                habilidadpokemonListHabilidadpokemonToAttach = em.getReference(habilidadpokemonListHabilidadpokemonToAttach.getClass(), habilidadpokemonListHabilidadpokemonToAttach.getHabilidadpokemonPK());
                attachedHabilidadpokemonList.add(habilidadpokemonListHabilidadpokemonToAttach);
            }
            habilidad.setHabilidadpokemonList(attachedHabilidadpokemonList);
            em.persist(habilidad);
            for (HabilidadPokemon habilidadpokemonListHabilidadpokemon : habilidad.getHabilidadpokemonList()) {
                Habilidad oldHabilidadOfHabilidadpokemonListHabilidadpokemon = habilidadpokemonListHabilidadpokemon.getHabilidad();
                habilidadpokemonListHabilidadpokemon.setHabilidad(habilidad);
                habilidadpokemonListHabilidadpokemon = em.merge(habilidadpokemonListHabilidadpokemon);
                if (oldHabilidadOfHabilidadpokemonListHabilidadpokemon != null) {
                    oldHabilidadOfHabilidadpokemonListHabilidadpokemon.getHabilidadpokemonList().remove(habilidadpokemonListHabilidadpokemon);
                    oldHabilidadOfHabilidadpokemonListHabilidadpokemon = em.merge(oldHabilidadOfHabilidadpokemonListHabilidadpokemon);
                }
            }
            em.getTransaction().commit();
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
            List<HabilidadPokemon> habilidadpokemonListOld = persistentHabilidad.getHabilidadpokemonList();
            List<HabilidadPokemon> habilidadpokemonListNew = habilidad.getHabilidadpokemonList();
            List<String> illegalOrphanMessages = null;
            for (HabilidadPokemon habilidadpokemonListOldHabilidadpokemon : habilidadpokemonListOld) {
                if (!habilidadpokemonListNew.contains(habilidadpokemonListOldHabilidadpokemon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Habilidadpokemon " + habilidadpokemonListOldHabilidadpokemon + " since its habilidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HabilidadPokemon> attachedHabilidadpokemonListNew = new ArrayList<HabilidadPokemon>();
            for (HabilidadPokemon habilidadpokemonListNewHabilidadpokemonToAttach : habilidadpokemonListNew) {
                habilidadpokemonListNewHabilidadpokemonToAttach = em.getReference(habilidadpokemonListNewHabilidadpokemonToAttach.getClass(), habilidadpokemonListNewHabilidadpokemonToAttach.getHabilidadpokemonPK());
                attachedHabilidadpokemonListNew.add(habilidadpokemonListNewHabilidadpokemonToAttach);
            }
            habilidadpokemonListNew = attachedHabilidadpokemonListNew;
            habilidad.setHabilidadpokemonList(habilidadpokemonListNew);
            habilidad = em.merge(habilidad);
            for (HabilidadPokemon habilidadpokemonListNewHabilidadpokemon : habilidadpokemonListNew) {
                if (!habilidadpokemonListOld.contains(habilidadpokemonListNewHabilidadpokemon)) {
                    Habilidad oldHabilidadOfHabilidadpokemonListNewHabilidadpokemon = habilidadpokemonListNewHabilidadpokemon.getHabilidad();
                    habilidadpokemonListNewHabilidadpokemon.setHabilidad(habilidad);
                    habilidadpokemonListNewHabilidadpokemon = em.merge(habilidadpokemonListNewHabilidadpokemon);
                    if (oldHabilidadOfHabilidadpokemonListNewHabilidadpokemon != null && !oldHabilidadOfHabilidadpokemonListNewHabilidadpokemon.equals(habilidad)) {
                        oldHabilidadOfHabilidadpokemonListNewHabilidadpokemon.getHabilidadpokemonList().remove(habilidadpokemonListNewHabilidadpokemon);
                        oldHabilidadOfHabilidadpokemonListNewHabilidadpokemon = em.merge(oldHabilidadOfHabilidadpokemonListNewHabilidadpokemon);
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
            List<HabilidadPokemon> habilidadpokemonListOrphanCheck = habilidad.getHabilidadpokemonList();
            for (HabilidadPokemon habilidadpokemonListOrphanCheckHabilidadpokemon : habilidadpokemonListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Habilidad (" + habilidad + ") cannot be destroyed since the Habilidadpokemon " + habilidadpokemonListOrphanCheckHabilidadpokemon + " in its habilidadpokemonList field has a non-nullable habilidad field.");
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
