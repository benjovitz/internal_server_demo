package dk.kea.internal_server_demo.service;


import dk.kea.internal_server_demo.api.RemoteApiTester;
import dk.kea.internal_server_demo.dto.*;
import dk.kea.internal_server_demo.entity.Person;
import dk.kea.internal_server_demo.repository.PersonRepo;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.data.web.JsonPath;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class NameService {
    PersonRepo personRepo;
    public NameService(PersonRepo personRepo){
    this.personRepo = personRepo;
        System.out.println(getInfo2("Bitten"));
    }

    public Mono<GenderReponse> getInfo(String name) {
        if(personRepo.existsById(name)){
            Person p = personRepo.findById(name).orElseThrow();
            GenderReponse genderReponse = new GenderReponse(p);
            System.out.println("from database");
            return Mono.just(genderReponse);
        }
        Mono<POJOGender> gender = getGenderForName(name);
        Mono<POJOAge> age = getAgeForName(name);
        Mono<Nation> nation = getNationForName(name);
        return Mono.zip(gender, age, nation)
                .flatMap(t -> {
                    Person p = new Person();
                    p.setName(name);
                    p.setGender(t.getT1().getGender());
                    p.setGenderProbability(t.getT1().getProbability());
                    p.setAge(t.getT2().getAge());
                    p.setAgeCount(t.getT2().getCount());
                    List<POJONation> nations = t.getT3().getCountry();
                    p.setCountry(nations.get(0).getCountry_id());
                    p.setCountryProb(nations.get(0).getProbability());
                    GenderReponse genderReponse = new GenderReponse(p);
                    personRepo.save(p);
                    System.out.println("from api");
                    return Mono.just(genderReponse);
                });
    }
    //cant get this one to work with the endpoint, it works when calling it from the code
    //There was an unexpected error (type=Internal Server Error, status=500).
    //block()/blockFirst()/blockLast() are blocking, which is not supported in thread reactor-http-nio-6
    public GenderReponse getInfo2(String name) {
        if(personRepo.existsById(name)){
            Person p = personRepo.findById(name).orElseThrow();
            GenderReponse genderReponse = new GenderReponse(p);
            System.out.println("from database");
            return genderReponse;
        }
        Mono<POJOGender> gender = getGenderForName(name);
        Mono<POJOAge> age = getAgeForName(name);
        Mono<Nation> nation = getNationForName(name);
        var rs = Mono.zip(gender, age, nation)
                .map(t -> {
                    Person p = new Person();
                    p.setName(name);
                    p.setGender(t.getT1().getGender());
                    p.setGenderProbability(t.getT1().getProbability());
                    p.setAge(t.getT2().getAge());
                    p.setAgeCount(t.getT2().getCount());
                    List<POJONation> nations = t.getT3().getCountry();
                    p.setCountry(nations.get(0).getCountry_id());
                    p.setCountryProb(nations.get(0).getProbability());
                    GenderReponse genderReponse = new GenderReponse(p);
                    personRepo.save(p);
                    System.out.println("from api");
                    return genderReponse;
                });
        GenderReponse genderReponse = rs.block();
        return genderReponse;
    }





    public Mono<POJOGender> getGenderForName(String name) {
        WebClient client = WebClient.create();
        Mono<POJOGender> gender = client.get()
                .uri("https://api.genderize.io/?name="+name)
                .retrieve()
                .bodyToMono(POJOGender.class);
        return gender;
    }
    public Mono<POJOAge> getAgeForName(String name) {
        WebClient client = WebClient.create();
        Mono<POJOAge> age = client.get()
                .uri("https://api.agify.io/?name="+name)
                .retrieve()
                .bodyToMono(POJOAge.class);
        return age;
    }

    public Mono<Nation> getNationForName(String name) {
        WebClient client = WebClient.create();
        Mono<Nation> nation = client.get()
                .uri("https://api.nationalize.io/?name="+name)
                .retrieve()
                .bodyToMono(Nation.class);
        return nation;
    }

}
