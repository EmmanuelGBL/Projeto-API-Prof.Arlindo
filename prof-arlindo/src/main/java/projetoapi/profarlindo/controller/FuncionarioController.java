package projetoapi.profarlindo.controller;


//import Services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoapi.profarlindo.model.FuncionarioModel;
import projetoapi.profarlindo.model.repository.FuncionarioRepository;
//import Error.ResourceNotFoundException;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping(path = "/api/funcionario/{id}")
    public ResponseEntity consultar (@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/api/funcionario/salvar")
    public FuncionarioModel salvar(@RequestBody FuncionarioModel funcionario) {
        return repository.save(funcionario);
    }

    //@GetMapping("/api/funcionario/{id}")
    // public ResponseEntity<FuncionarioModel> getFuncionario(@PathVariable Integer id) {
    //     FuncionarioModel funcionario = repository.getFuncionarioById(id);
    //     if (funcionario == null) {
    //         throw new ResourceNotFoundException("Funcionário com ID " + id + " não encontrado");
    //     }
    //     return ResponseEntity.ok(funcionario);
    // }

}






