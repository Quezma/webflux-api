package com.marco.webflux.api_rest_controller;

import com.marco.webflux.bussiness_controllers.DocumentController;
import com.marco.webflux.dtos.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(DocumentResource.DOCUMENTS)
public class DocumentResource {

    public static final String DOCUMENTS = "/documents";
    public static final String ID = "/{id}";

    private final DocumentController documentController;

    @Autowired
    public DocumentResource(DocumentController documentController) {
        this.documentController = documentController;
    }

    @PostMapping(produces = {"application/json"})
    public Mono<ResponseEntity> create(@RequestBody DocumentDTO documentDTO) {
        return this.documentController.createDocument(documentDTO);
    }

    @GetMapping(value = ID)
    public Mono<DocumentDTO> readDocument(@PathVariable String id) {
        return this.documentController.findDocument(id);
    }

    @GetMapping
    public Flux<DocumentDTO> search() {
        return  this.documentController.search();
    }

    @PutMapping(value = ID)
    public Mono<ResponseEntity> updateDocument(@PathVariable String id, @RequestBody DocumentDTO documentDTO) {
        return this.documentController.editDocument(id, documentDTO);
    }

    @DeleteMapping(value = ID)
    public Mono<ResponseEntity> deleteDocument(@PathVariable String id) {
        return this.documentController.deleteDocument(id);
    }
}

