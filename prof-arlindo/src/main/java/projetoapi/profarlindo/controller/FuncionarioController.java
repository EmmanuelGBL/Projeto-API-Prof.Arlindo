package projetoapi.profarlindo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projetoapi.profarlindo.model.FuncionarioModel;
import projetoapi.profarlindo.model.repository.FuncionarioRepository;

import java.util.Optional;
//import Error.ResourceNotFoundException;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping(path = "/api/funcionario/{codigo}")
    private ResponseEntity consultar(@PathVariable("codigo") String codigo) {
        try {
            Integer codigoInt = Integer.parseInt(codigo);
            Optional<FuncionarioModel> funcionario = repository.findById(codigoInt);
            if (funcionario.isPresent()) {
                return ResponseEntity.ok().body(funcionario.get());
            } else {
                String mensagemErro = "Funcionário com código " + codigoInt + " não foi encontrado.";
                return ResponseEntity.status(404).body(mensagemErro);
            }
        } catch (NumberFormatException e) {
            String mensagemErro = "O parâmetro fornecido inválido.";
            return ResponseEntity.status(400).body(mensagemErro);
        }
    }

    @PostMapping(path = "/api/funcionario/salvar")
    private ResponseEntity<?> salvar(@RequestBody FuncionarioModel funcionario) {

        if (funcionario.getEmail() == null || funcionario.getEmail().isEmpty()) {
            return ResponseEntity.status(400).body("O campo email é obrigatório.");
        }
        return ResponseEntity.status(201).body(repository.save(funcionario));
    }



}

    //@GetMapping("/api/funcionario/{id}")
    // public ResponseEntity<FuncionarioModel> getFuncionario(@PathVariable Integer id) {
    //     FuncionarioModel funcionario = repository.getFuncionarioById(id);
    //     if (funcionario == null) {
    //         throw new ResourceNotFoundException("Funcionário com ID " + id + " não encontrado");
    //     }
    //     return ResponseEntity.ok(funcionario);
    // }








