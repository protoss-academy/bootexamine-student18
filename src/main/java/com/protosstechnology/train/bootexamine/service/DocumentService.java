package com.protosstechnology.train.bootexamine.service;

import com.protosstechnology.train.bootexamine.model.Document;

import java.util.Optional;

public interface DocumentService {

    Optional<Document> getById(Integer id);
    Optional<Document> update(Document document);
    void delete(Integer id);
    Optional<Document> insert(Document document);


}
