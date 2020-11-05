package com.marco.webflux.repositories;

import com.marco.webflux.documents.Document;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface DocumentRepository extends ReactiveSortingRepository<Document, String> {
}
