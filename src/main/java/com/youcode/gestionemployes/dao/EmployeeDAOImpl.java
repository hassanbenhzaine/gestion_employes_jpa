package com.youcode.gestionemployes.dao;

import com.youcode.gestionemployes.entity.Employe;
import com.youcode.gestionemployes.shared.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
public class EmployeeDAOImpl implements GenericDAO<Employe, Integer> {
    private final EntityManager em;

    public EmployeeDAOImpl() {
        EntityManagerFactory emf = EntityManagerFactoryProvider.getInstance().get();
        this.em = emf.createEntityManager();
    }

    @Override
    public Employe create(Employe employe) {
        em.getTransaction().begin();
        em.persist(employe);
        em.getTransaction().commit();
        em.close();
        return employe;
    }

    @Override
    public Optional<Employe> get(Integer id) {
        Optional<Employe> employe = Optional
                .of(em.find(Employe.class, id));
        em.close();
        return employe;
    }

    @Override
    public Collection<Employe> getAll() {
        List<Employe> employeList = em
                .createNamedQuery("Employe.findAll", Employe.class)
                .getResultList();
        em.close();
        return employeList;
    }

    @Override
    public Employe update(Employe employe) {
        em.getTransaction().begin();
        Employe updatedEmploye = em.merge(employe);
        em.getTransaction().commit();
        em.close();
        return updatedEmploye;
    }

    @Override
    public void delete(Employe employe) {
        em.getTransaction().begin();
        em.remove(employe);
        em.getTransaction().commit();
        em.close();
    }

}
