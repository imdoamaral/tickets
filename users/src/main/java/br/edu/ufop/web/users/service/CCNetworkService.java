package br.edu.ufop.web.users.service;

import br.edu.ufop.web.users.converter.CCNetworkConverter;
import br.edu.ufop.web.users.domain.CCNetworkDomain;
import br.edu.ufop.web.users.dto.CreateCreditCardNetworkDTO;
import br.edu.ufop.web.users.dto.CreditCardNetworkDTO;
import br.edu.ufop.web.users.entity.CCNetworkEntity;
import br.edu.ufop.web.users.repository.ICreditCardNetworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CCNetworkService {
    private final ICreditCardNetworkRepository repository;

    // get all
    public List<CreditCardNetworkDTO> getAll() {
        List<CCNetworkEntity> lista = repository.findAll();
        return lista.stream()
                .map(CCNetworkConverter::toDto)
                .toList();
    }

    // get by id

    // get by name

    // create
    public CreditCardNetworkDTO create(CreateCreditCardNetworkDTO createDto) {
        // DTO -> entrada
        // DTO -> converter para o dominio: domain
        // Domain -> aplicar as regras de negocio, conforme o use case: create

        // use case - correto: ok; invalida: false/exception

        // regra de negocio: "name" nao nulo e tem que ter valor
        CCNetworkDomain domain = CCNetworkConverter.toDomain(createDto);
        if(domain.getName() == null || domain.getName().isBlank()) {
            // nao permite a persistencia - dados incosistentes
            return null;
        }

        // domain valido
        // domain -> converter para entity
        CCNetworkEntity entity = CCNetworkConverter.toEntity(domain);

        // invocar o repositorio para a persistencia
        return CCNetworkConverter.toDto(
                repository.save(entity)
        );
    }
}
