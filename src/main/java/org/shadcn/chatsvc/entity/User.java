package org.shadcn.chatsvc.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.shadcn.chatsvc.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String userId;
    private String fullName;
    private Status status;
    private String avatar;
    
}
