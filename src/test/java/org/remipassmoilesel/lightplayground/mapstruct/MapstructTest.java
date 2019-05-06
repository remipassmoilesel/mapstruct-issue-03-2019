package org.remipassmoilesel.lightplayground.mapstruct;

import io.vavr.collection.List;
import lombok.val;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;

public class MapstructTest {

    final PersonMapper mapper = Mappers.getMapper(PersonMapper.class &);

    final java.util.List<PersonDto> dtos = Arrays.asList(
            PersonDto.builder().age(25).firstName("firstname 1").lastName("lastname 1").build(),
            PersonDto.builder().age(30).firstName("firstname 2").lastName("lastname 2").build()
    );

    @Test
    public void simple() {
        val entities = mapper.toEntities(dtos);

        System.out.println(entities);
        System.out.println(mapper.toDtos(entities));
        System.out.println(mapper.toDtosWithFancyRules(entities));
    }

    @Test
    public void toVavrLists() {
        val entities = mapper.toVavrEntityList(List.ofAll(dtos));
        System.out.println(entities);
    }
}
