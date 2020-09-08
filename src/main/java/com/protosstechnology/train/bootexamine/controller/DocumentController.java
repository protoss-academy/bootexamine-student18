package com.protosstechnology.train.bootexamine.controller;

import com.protosstechnology.train.bootexamine.model.Document;
import com.protosstechnology.train.bootexamine.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Operation(summary = "Get Document by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the document", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Document.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id document"),
            @ApiResponse(responseCode = "404", description = "Document Not document")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity getDocumentById(@PathVariable(name = "id") String id){
        if(!NumberUtils.isParsable(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id document");
        }
        Optional<Document> retrievedDocument = documentService.getById(Integer.valueOf(id));
        if(!retrievedDocument.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(retrievedDocument.get());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateDocumentById(@PathVariable(name = "id") String id, @RequestBody Document document){
        if(!NumberUtils.isParsable(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id document");
        }
        if(!documentService.getById(Integer.valueOf(id)).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document Not Found");
        }
        document.setId(Integer.valueOf(id));
        Optional<Document> updatedDocument = documentService.update(document);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDocument.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteDocumentById(@PathVariable(name = "id") String id){
        if(!NumberUtils.isParsable(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id document");
        }
        if(!documentService.getById(Integer.valueOf(id)).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document Not Found");
        }
        documentService.delete(Integer.valueOf(id));
        return ResponseEntity.status(HttpStatus.OK).body("Document with ID : " + id + " is deleted");
    }

    @PostMapping
    public ResponseEntity insertDocument(@RequestBody Document document){
        if(documentService.getById(document.getId()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Document with ID " + document.getId() + " already exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body(documentService.insert(document).get());
    }

}
