package br.com.cabueta.mapper;

import br.com.cabueta.entity.CabuetaClient;
import br.com.cabueta.entity.response.CabuetaClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ClientMapper {

    CabuetaClientResponse toResponse(CabuetaClient cabuetaClient);

}
