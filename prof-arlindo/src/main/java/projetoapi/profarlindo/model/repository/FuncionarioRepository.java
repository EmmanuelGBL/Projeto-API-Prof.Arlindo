package projetoapi.profarlindo.model.repository;

import org.springframework.data.repository.CrudRepository;
import projetoapi.profarlindo.model.FuncionarioModel;

public interface FuncionarioRepository extends CrudRepository<FuncionarioModel, Integer> {
}