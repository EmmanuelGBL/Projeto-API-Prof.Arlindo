package projetoapi.profarlindo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projetoapi.profarlindo.model.FuncionarioModel;
import projetoapi.profarlindo.model.repository.FuncionarioRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping("/{codigo}")
    private ResponseEntity<?> consultar(@PathVariable("codigo") String codigo) {
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
        } catch (Exception e) {
            String mensagemErro = "Erro no servidor: " + e.getMessage();
            return ResponseEntity.status(500).body(mensagemErro);
        }
    }

    @PostMapping("/salvar")
    private ResponseEntity<?> salvar(@RequestBody FuncionarioModel funcionario) {
        if (funcionario.getEmail() == null || funcionario.getEmail().isEmpty()) {
            return ResponseEntity.status(400).body("O campo email é obrigatório.");
        }
        return ResponseEntity.status(201).body(repository.save(funcionario));
    }
}