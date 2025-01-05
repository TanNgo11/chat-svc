package org.shadcn.chatsvc.entity;

import org.shadcn.chatsvc.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class User {
    @Id
    private String id;
    private String fullName;
    private Status status;
}