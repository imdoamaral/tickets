package br.edu.ufop.web.users.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumUserType {
    // Cliente - comprar ticket
    CUSTOMER(1, "Customer"),
    // Empresa - Organização do evento
    ENTERPRISE(2, "Enterprise"),
    // Administrador
    ADMIN(3, "Admin");

    private Integer id;
    private String description;
}
