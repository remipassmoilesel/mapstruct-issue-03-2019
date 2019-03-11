package org.remipassmoilesel.lightplayground.mapstruct;

import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private String firstName;
    private String lastName;

    private long age;

}
