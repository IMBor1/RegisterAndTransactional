package t1.java.demo.registerAndTransaction.mapper;

import org.mapstruct.*;
import t1.java.demo.registerAndTransaction.dto.ClientDto;
import t1.java.demo.registerAndTransaction.model.Client;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    Client toEntity(ClientDto clientDto);

    ClientDto toDto(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Client partialUpdate(ClientDto clientDto, @MappingTarget Client client);
}