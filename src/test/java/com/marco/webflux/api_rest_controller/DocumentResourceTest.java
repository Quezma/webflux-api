package com.marco.webflux.api_rest_controller;

import com.marco.webflux.dtos.DocumentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
class DocumentResourceTest {

    @Autowired
    private RestService restService;

    @Test
    void create() {
        DocumentDTO documentDTO = new DocumentDTO(true, new int[5], "5f8b3b4fb2c40c0c3f52d136", "document test", "test", "Tester");
        String get = this.restService.restbuilder().post()
                .uri(DocumentResource.DOCUMENTS).body(BodyInserters.fromValue(documentDTO))
                .exchange().expectStatus().isCreated().expectBody(String.class).returnResult().getResponseBody();
        assertNotNull(get);
        assertEquals("Document created", get);
    }

    @Test
    void readDocument() {
        DocumentDTO documentDTO = this.restService.restbuilder().get()
                .uri(DocumentResource.DOCUMENTS + DocumentResource.ID, "5f8b3b4fb2c40c0c3f52d136")
                .exchange().expectStatus().isOk().expectBody(DocumentDTO.class)
                .returnResult().getResponseBody();
        assertNotNull(documentDTO);
        assertEquals("5f8b3b4fb2c40c0c3f52d136", documentDTO.getId());
    }

    @Test
    void search() {
        this.restService.restbuilder().get().uri(DocumentResource.DOCUMENTS).exchange().expectStatus().isOk();
    }

    @Test
    void update() {
        DocumentDTO documentDTO = new DocumentDTO(false, new int[2], "5f8b3b4fb2c40c0c3f52d136", "doc", "tester", "Tester");
        String get = this.restService.restbuilder()
                .put().uri(DocumentResource.DOCUMENTS + DocumentResource.ID, "5f8b3b4fb2c40c0c3f52d136")
                .body(BodyInserters.fromValue(documentDTO))
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(String.class).returnResult().getResponseBody();
        assertNotNull(get);
        assertEquals("Document updated", get);
    }

    @Test
    void deleteDocument() {
        String delete = this.restService.restbuilder()
                .delete().uri(DocumentResource.DOCUMENTS + DocumentResource.ID, "5f8b3b4fb2c40c0c3f52d136")
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(String.class).returnResult().getResponseBody();
        assertNotNull(delete);
        assertEquals("Document deleted", delete);
    }
}