package dk.kea.internal_server_demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class POJOGender {
    public int count;
    public String gender;
    public String name;
    public double probability;
}
