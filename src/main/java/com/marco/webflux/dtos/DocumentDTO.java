package com.marco.webflux.dtos;

import com.marco.webflux.documents.Document;

public class DocumentDTO {

    private String id, name, type, user;

    private Boolean active;

    private int[] data;

    public DocumentDTO() {
    }

    public DocumentDTO(Boolean active, int[] data, String id, String name, String type, String user) {
        this.active = active;
        this.data = data;
        this.id = id;
        this.name = name;
        this.type = type;
        this.user = user;
    }

    public DocumentDTO(Document document) {
        this(
                document.getActive(),
                document.getData(),
                document.getId(),
                document.getName(),
                document.getType(),
                document.getUser()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
