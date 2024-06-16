package projetoapi.profarlindo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import projetoapi.profarlindo.model.FuncionarioModel;
import projetoapi.profarlindo.model.repository.FuncionarioRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

    @Autowired
    public FuncionarioRepository repository;

    @GetMapping("/{codigo}")
    public ResponseEntity<?> consultar(@PathVariable("codigo") String codigo) {
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
            String mensagemErro = "O parâmetro fornecido é inválido.";
            return ResponseEntity.status(400).body(mensagemErro);
        } catch (Exception e) {
            String mensagemErro = "Erro no servidor: " + e.getMessage();
            return ResponseEntity.status(500).body(mensagemErro);
        }
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody FuncionarioModel funcionario) {
        if (funcionario.getEmail() == null || funcionario.getEmail().isEmpty()) {
            return ResponseEntity.status(400).body("O campo email é obrigatório.");
        }
        return ResponseEntity.status(201).body(repository.save(funcionario));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> deletar(@PathVariable("codigo") String codigo) {
        try {
            Integer codigoInt = Integer.parseInt(codigo);
            Optional<FuncionarioModel> funcionario = repository.findById(codigoInt);
            if (funcionario.isPresent()) {
                repository.deleteById(codigoInt);
                return ResponseEntity.ok().body("Funcionário com código " + codigoInt + " foi deletado com sucesso.");
            } else {
                String mensagemErro = "Funcionário com código " + codigoInt + " não foi encontrado.";
                return ResponseEntity.status(404).body(mensagemErro);
            }
        } catch (NumberFormatException e) {
            String mensagemErro = "O parâmetro fornecido é inválido.";
            return ResponseEntity.status(400).body(mensagemErro);
        } catch (Exception e) {
            String mensagemErro = "Erro ao deletar funcionário: " + e.getMessage();
            return ResponseEntity.status(500).body(mensagemErro);
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> atualizar(@PathVariable("codigo") String codigo,
                                       @RequestBody FuncionarioModel funcionarioAtualizado) {
        try {
            Integer codigoInt = Integer.parseInt(codigo);
            Optional<FuncionarioModel> funcionario = repository.findById(codigoInt);
            if (funcionario.isPresent()) {
                // Atualizar os dados do funcionário existente
                FuncionarioModel funcionarioExistente = funcionario.get();
                funcionarioExistente.setNome(funcionarioAtualizado.getNome());
                funcionarioExistente.setEmail(funcionarioAtualizado.getEmail());
                // Adicione outros campos conforme necessário

                // Salvar e retornar o funcionário atualizado
                repository.save(funcionarioExistente);
                return ResponseEntity.ok().body(funcionarioExistente);
            } else {
                String mensagemErro = "Funcionário com código " + codigoInt + " não foi encontrado.";
                return ResponseEntity.status(404).body(mensagemErro);
            }
        } catch (NumberFormatException e) {
            String mensagemErro = "O parâmetro fornecido é inválido.";
            return ResponseEntity.status(400).body(mensagemErro);
        } catch (Exception e) {
            String mensagemErro = "Erro ao atualizar funcionário: " + e.getMessage();
            return ResponseEntity.status(500).body(mensagemErro);
        }
    }
}