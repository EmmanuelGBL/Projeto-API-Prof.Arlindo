package projetoapi.profarlindo.model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import projetoapi.profarlindo.Services.FuncionarioRequestDTO;

@Entity(name = "funcionarioModel")
public class FuncionarioModel {

    @Id
    public Integer codigo;

    @Column
    public String nome;

    @Column(nullable = false)
    public String email;

    @Column
    public String telefone;

    @Column
    public String endereco;

    @Column
    public String cargo;

    @Column
    public String departamento;

    @Column
    public String avaliacao;

    @Column
    public String comentarios;

    public FuncionarioModel(Integer codigo, String nome, String email, String telefone, String endereco, String cargo, String departamento, String avaliacao, String comentarios) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cargo = cargo;
        this.departamento = departamento;
        this.avaliacao = avaliacao;
        this.comentarios = comentarios;
    }

    // Getters e Setters
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }


}
