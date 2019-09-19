/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guatejug.repository;

import com.guatejug.model.Phrase;
import java.util.List;

import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

@Repository
public abstract class PhraseRepository extends AbstractEntityRepository<Phrase, Long> {

    @Query("SELECT p FROM Phrase p WHERE p.author LIKE ?1 and p.phrase LIKE ?2")
    public abstract List<Phrase> superFind(String author, String phrase);

    
}
