/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherwin.repository;

import com.sherwin.dto.PhraseDTO;
import com.sherwin.model.Phrase;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

@Repository
public abstract class PhraseRepository extends AbstractEntityRepository<Phrase, Long> {

    
    public abstract List<Phrase> findByAuthorLikeAndPhraseLike(String author, String phrase);
    
    @Query("SELECT p FROM Phrase p WHERE p.author LIKE ?1 and p.phrase LIKE ?2")
    public abstract List<Phrase> superFind(String author, String phrase);
    
    public List<Phrase> customFind(String author, String phrase){
        String queryjpql = "SELECT p FROM Phrase p "
                + "WHERE p.author LIKE :author and p.phrase LIKE :phrase";
        
        return this.typedQuery(queryjpql)
                .setParameter("author", author)
                .setParameter("phrase", phrase)
                .getResultList();
    }
    
    public List<PhraseDTO> fasterFind(String author, String phrase){
        String queryjpql = 
            "SELECT NEW com.sherwin.dto.PhraseDTO(p.author) "
                + "FROM Phrase AS p "
                + "WHERE p.author LIKE :author and p.phrase LIKE :phrase";
        
        javax.persistence.Query query 
                = this.entityManager().createQuery(queryjpql)
                    .setParameter("author", author)
                    .setParameter("phrase", phrase);
        
        return query.getResultList();
    }
    
}
