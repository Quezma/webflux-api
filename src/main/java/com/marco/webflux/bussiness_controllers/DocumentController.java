package com.marco.webflux.bussiness_controllers;

import com.marco.webflux.documents.Document;
import com.marco.webflux.dtos.DocumentDTO;
import com.marco.webflux.repositories.DocumentRepository;
import javassist.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class DocumentController {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Mono<ResponseEntity> createDocument(DocumentDTO documentDTO) {
        Document document = new Document();
        document.setId(documentDTO.getId());
        document.setActive(documentDTO.getActive());
        document.setData(documentDTO.getData());
        document.setName(documentDTO.getName());
        document.setType(documentDTO.getType());
        document.setUser(documentDTO.getUser());
        return this.documentRepository.save(document).map(callback -> (
                new ResponseEntity("Document created", HttpStatus.CREATED)
        )).onErrorReturn(new ResponseEntity("Product doesn't created", HttpStatus.NOT_ACCEPTABLE));
    };

    public Mono<DocumentDTO> findDocument(String id) {
        return this.documentRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException(" document " + id)))
                .map(DocumentDTO::new);
    }

    public Flux<DocumentDTO> search() {
        return this.documentRepository.findAll().map(DocumentDTO::new);
    }

    public Mono<ResponseEntity> editDocument(String id, DocumentDTO documentDTO) {
        Mono<Document> document = this.documentRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException(" document " + id)))
                .map(documentDb -> {
                    documentDb.setUser(documentDTO.getUser());
                    documentDb.setName(documentDTO.getName());
                    documentDTO.setActive(documentDTO.getActive());
                    documentDTO.setData(documentDTO.getData());
                    return documentDb;
                });
        return Mono.when(document)
                .then(this.documentRepository.save(document.block()))
                .map(callback -> {
                    return new ResponseEntity("Document updated", HttpStatus.ACCEPTED);
                });
    }
}
