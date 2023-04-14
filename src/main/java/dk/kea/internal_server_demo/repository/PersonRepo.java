package dk.kea.internal_server_demo.repository;

import dk.kea.internal_server_demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person,String> {
}
