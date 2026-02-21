package br.edu.ufop.web.users.domain.usecase;

import br.edu.ufop.web.users.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class CreateUserUseCase {
    private UserDomain userDomain;

    public void validate() {
        // Validaçoes conforme a regra de negocio para a criaçao do usuario
        validateName();

        // Demais validaçoes
    }

    private void validateName() {
        if (this.userDomain.getName() == null) {
            throw new RuntimeException("Name is null.");
        }
    }
}
