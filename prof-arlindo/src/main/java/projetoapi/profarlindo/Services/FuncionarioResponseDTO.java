package projetoapi.profarlindo.Services;

import projetoapi.profarlindo.model.FuncionarioModel;

public record FuncionarioResponseDTO(Integer Codigo, String Nome, String email, String Telefone, String Endereco, String Cargo, String Departamento, String Avaliacao, String Comentarios) {
    public FuncionarioResponseDTO(FuncionarioModel funcionarioModel) {
        this(funcionarioModel.getCodigo(),
                funcionarioModel.getNome(),
                funcionarioModel.getEmail(),
                funcionarioModel.getTelefone(),
                funcionarioModel.getEndereco(),
                funcionarioModel.getCargo(),
                funcionarioModel.getDepartamento(),
                funcionarioModel.getAvaliacao(),
                funcionarioModel.getComentarios());
    }
}