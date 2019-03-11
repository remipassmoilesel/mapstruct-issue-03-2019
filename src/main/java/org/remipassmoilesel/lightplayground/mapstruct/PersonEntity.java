package org.remipassmoilesel.lightplayground.mapstruct;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    private String uuid;
    private String firstName;
    private String lastName;
    private long ageOfPerson;
    private String phoneNumber;

}
