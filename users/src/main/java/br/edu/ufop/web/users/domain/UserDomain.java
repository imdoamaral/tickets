package br.edu.ufop.web.users.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDomain {
    private UUID id;
    private String name;

    private String email;
    private String password;
    private String city;

    private String creditCardNumber;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
