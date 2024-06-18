package projetoapi.profarlindo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Buscar o funcionario pelo codigo(Id)")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Retorna todas as informações do funcionario cadastrado"),
            @ApiResponse(responseCode = "400", description = "O parametro fornecido é invalido"),
            @ApiResponse(responseCode = "404", description = "Funcionario nao encontrado com este codigo")
    })
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
        }
    }

    @Operation(description = "Salvar funcionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Confirma o cadastro do funcionario, repetindo os dados inseridos"),
            @ApiResponse(responseCode = "400", description = "Indica que o campo email é obrigatorio para cadastrar o novo funcionario"),
    })

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody FuncionarioModel funcionario) {
        if (funcionario.getEmail() == null || funcionario.getEmail().isEmpty()) {
            return ResponseEntity.status(400).body("O campo email é obrigatório.");
        }
        return ResponseEntity.status(201).body(repository.save(funcionario));
    }


    @Operation(description = "Deletar funcionario inserido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionario excluido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionario com codigo inserido, nao existe"),
            @ApiResponse(responseCode = "400", description = "O codigo fornecido é invalido"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar funcionario")
    })

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> deletar(@PathVariable("codigo") String codigo) {
        try {
            Integer codigoInt = Integer.parseInt(codigo);
            Optional<FuncionarioModel> funcionario = repository.findById(codigoInt);
            if (funcionario.isPresent()) {
                repository.deleteById(codigoInt);
                return ResponseEntity.status(200).body("Funcionário com código " + codigoInt + " foi deletado com sucesso.");
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


    @Operation(description = "Alterar informações do funcionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do funcionario alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionario com codigo inserido, nao existe"),
            @ApiResponse(responseCode = "400", description = "O codigo fornecido é invalido"),
            @ApiResponse(responseCode = "500", description = "Erro ao alterar informações desse funcionario")
    })
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
                funcionarioExistente.setTelefone(funcionarioAtualizado.getTelefone());
                funcionarioExistente.setEndereco(funcionarioAtualizado.getEndereco());
                funcionarioExistente.setCargo(funcionarioAtualizado.getCargo());
                funcionarioExistente.setDepartamento(funcionarioAtualizado.getDepartamento());
                funcionarioExistente.setAvaliacao(funcionarioAtualizado.getAvaliacao());
                funcionarioExistente.setComentarios(funcionarioAtualizado.getComentarios());
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