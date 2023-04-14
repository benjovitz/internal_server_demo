package dk.kea.internal_server_demo.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class POJONation {

    String country_id;
    double probability;
}
