package projetoapi.profarlindo.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Operation(description = "Confirmar se a API está online!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API - Online"),
    })
    @GetMapping(path = "/api/status")
    private String status() {
        return "Online, API FUNCIONANDO PERFEITAMENTE!!";
    }
}