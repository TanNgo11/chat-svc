package org.shadcn.chatsvc.dto.response;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import org.shadcn.chatsvc.enums.Gender;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileResponse {
    String id;
    String firstName;
    String lastName;
    String username;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dateOfBirth;

    String address;
    String email;
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String avatarPath;

    Set<String> roles;
}
