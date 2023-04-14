package dk.kea.internal_server_demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.internal_server_demo.entity.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenderReponse {
    String name;
    String gender;
    double genderProbability;
    int age;
    int ageCount;
    String country;
    double countryProb;

    @Override
    public String toString() {
        return String.format("Name: %s, Gender: %s (%.2f%%), Age: %d (%d counts), Country: %s (%.2f%%)",
                name, gender, genderProbability*100, age, ageCount, country, countryProb*100);
    }

    public GenderReponse(Person p) {
        this.name = p.getName();
        this.gender = p.getGender();
        this.genderProbability = p.getGenderProbability();
        this.age = p.getAge();
        this.ageCount = p.getAgeCount();
        this.country = p.getCountry();
        this.countryProb = p.getCountryProb();
    }
}
