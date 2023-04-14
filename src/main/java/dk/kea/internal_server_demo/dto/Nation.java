package dk.kea.internal_server_demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Nation {

    String name;
    List<POJONation> country;
}
