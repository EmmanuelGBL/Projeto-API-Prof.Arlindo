package projetoapi.profarlindo.Services;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public record FuncionarioRequestDTO(

        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        String endereco,

        @NotBlank
        String cargo,

        @NotBlank
        String departamento,

        @NotBlank
        String avaliacao,

        @NotBlank
        String comentarios
        ){

}
