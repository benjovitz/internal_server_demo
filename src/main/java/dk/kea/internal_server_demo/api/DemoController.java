package dk.kea.internal_server_demo.api;

import dk.kea.internal_server_demo.dto.GenderReponse;
import dk.kea.internal_server_demo.service.NameService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class DemoController {
    NameService nameService;

    public DemoController(NameService nameService){
        this.nameService=nameService;
    }

    private final int SLEEP_TIME = 1000*3;

    @GetMapping(value = "/random-string-slow")
    public String slowEndpoint() throws InterruptedException {
        Thread.sleep(SLEEP_TIME);
        return RandomStringUtils.randomAlphanumeric(10);
    }
    @GetMapping("/name-info/{name}")
    public Mono<GenderReponse> getInfo(@PathVariable String name){
        return nameService.getInfo(name);
    }

}
