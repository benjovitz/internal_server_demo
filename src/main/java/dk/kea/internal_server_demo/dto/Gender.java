package dk.kea.internal_server_demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Gender {
    String gender;
    String name;
    int count;
    double probability;
}

