package com.dutchgrammar.dutchgrammarapp.dao;

import com.dutchgrammar.dutchgrammarapp.entity.Imperfectum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImperfectumDAO {

    private EntityManager entityManager;

    public ImperfectumDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Imperfectum findById(Integer id){
        return entityManager.find(Imperfectum.class, id);
    }

    public List<Imperfectum> findAll() {
        TypedQuery<Imperfectum> theQuery = entityManager.createQuery("FROM Imperfectum", Imperfectum.class);
        return theQuery.getResultList();
    }

    public long ExercisesNumber() {
        Query countQuery = entityManager.createQuery("SELECT count(row) FROM Imperfectum row", Imperfectum.class);
        long count = (long)countQuery.getSingleResult();
        return count;
    }

}
