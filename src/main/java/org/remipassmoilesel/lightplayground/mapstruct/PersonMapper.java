package org.remipassmoilesel.lightplayground.mapstruct;

import io.vavr.collection.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.stream.Collectors;

@Mapper
public interface PersonMapper {

    @Mappings(
            @Mapping(source = "age", target = "ageOfPerson")
    )
    PersonEntity toEntity(PersonDto dto);

    java.util.List<PersonEntity> toEntities(java.util.List<PersonDto> dtos);

    // Cause a stackoverlow. Why ?
    // List<PersonEntity> toEntitiesVavr(List<PersonDto> dtos);

    default List<PersonEntity> toEntitiesVavrDefaultImplementation(List<PersonDto> dtos) {
        return dtos.map(this::toEntity);
    }

    @Mappings(
            @Mapping(source = "ageOfPerson", target = "age")
    )
    PersonDto toDto(PersonEntity entity);

    java.util.List<PersonDto> toDtos(java.util.List<PersonEntity> entities);

    default java.util.List<PersonDto> toDtosWithFancyRules(java.util.List<PersonEntity> entities) {
        return toDtos(entities).stream()
                .map(person -> person.toBuilder().age(person.getAge() * 2).build())
                .collect(Collectors.toList());
    }

}
