package br.com.cabueta.mapper;

import br.com.cabueta.entity.RegistrationClient;
import br.com.cabueta.entity.response.RegistrationClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ClientMapper {

    RegistrationClientResponse toResponse(RegistrationClient cadastroClient);

    RegistrationClient toEntity(RegistrationClientResponse response);


}
