package com.dutchgrammar.dutchgrammarapp.dao;

import com.dutchgrammar.dutchgrammarapp.entity.PresentTense;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PresentTenseDAO{

    private EntityManager entityManager;

    public PresentTenseDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public PresentTense findById(Integer id) {
        return entityManager.find(PresentTense.class, id);
    }

    public List<PresentTense> findAll(){
        TypedQuery<PresentTense> theQuery = entityManager.createQuery("FROM PresentTense", PresentTense.class);
        return theQuery.getResultList();
    }

    public long exercisesNumber() {
        Query countQuery = entityManager.createQuery("SELECT count(row) FROM PresentTense row", PresentTense.class);
        long count = (long)countQuery.getSingleResult();
        return count;
    }

}
