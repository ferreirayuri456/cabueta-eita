package br.com.cabueta.mapper;

import br.com.cabueta.entity.RegistrationClient;
import br.com.cabueta.entity.response.RegistrationClientResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClientMapper {

    RegistrationClientResponse toResponse(RegistrationClient cadastroClient);

}
