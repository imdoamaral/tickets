package br.edu.ufop.web.users.domain;

import br.edu.ufop.web.users.enums.EnumUserType;
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

    // network
    private CCNetworkDomainJava ccNetwork;

    // user type
    private EnumUserType type;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
