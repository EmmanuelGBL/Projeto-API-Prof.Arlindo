package projetoapi.profarlindo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping(path = "/api/status")
    public String status() {
        return "Online, API FUNCIONANDO!!";
    }
}