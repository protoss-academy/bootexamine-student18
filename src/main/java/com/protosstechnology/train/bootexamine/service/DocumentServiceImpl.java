package com.protosstechnology.train.bootexamine.service;

import com.protosstechnology.train.bootexamine.model.Document;
import com.protosstechnology.train.bootexamine.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService{

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Optional<Document> getById(Integer id) {
        return documentRepository.findById(id);
    }

    @Override
    public Optional<Document> update(Document document) {
        return Optional.of(documentRepository.save(document));
    }

    @Override
    public void delete(Integer id){
        documentRepository.deleteById(id);
    }

    @Override
    public Optional<Document> insert(Document document){
        return Optional.of(documentRepository.save(document));
    }
}
