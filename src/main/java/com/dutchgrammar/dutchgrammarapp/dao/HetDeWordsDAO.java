package com.dutchgrammar.dutchgrammarapp.dao;

import com.dutchgrammar.dutchgrammarapp.entity.HetDeWords;
import com.dutchgrammar.dutchgrammarapp.entity.PresentTense;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HetDeWordsDAO {

    private EntityManager entityManager;

    public HetDeWordsDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public HetDeWords findById(Integer id){
        return entityManager.find(HetDeWords.class, id);
    }

    public List<HetDeWords> findAll(){
        TypedQuery<HetDeWords> theQuery = entityManager.createQuery("FROM HetDeWords", HetDeWords.class);
        return theQuery.getResultList();
    }

    public long exercisesNumber() {
        Query countQuery = entityManager.createQuery("SELECT count(row) FROM HetDeWords row", PresentTense.class);
        long count = (long)countQuery.getSingleResult();
        return count;
    }

}
