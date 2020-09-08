package com.protosstechnology.train.bootexamine.repository;

import com.protosstechnology.train.bootexamine.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
