package dk.kea.internal_server_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Person {

    @Id
    String name;
    String gender;
    double genderProbability;
    int age;
    int ageCount;
    String country;
    double countryProb;
}
