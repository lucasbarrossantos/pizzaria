package com.pizzaria.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasbarros on 25/07/2017.
 */

@Entity
@Table(name = "usuario")
public class Usuario extends Versao {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String nome;

    @CPF(message = "CPF inválido")
    @NotBlank(message = "CPF deve ser informado")
    @Column(length = 14)
    private String cpf;

    @NotBlank(message = "Senha obrigatória")
    @Column(length = 100)
    private String senha;

    @NotBlank(message = "Confirme a senha")
    @Column(length = 100)
    private String confirmeSenha;

    @Column(length = 20)
    private String telefone;

    @Email(message = "E-mail inválido")
    @Column(length = 60)
    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento")
    private LocalDate dataDeNascimento;

    @Embedded
    private Endereco endereco;

    @ManyToOne
    private Estabelecimento estabelecimento;

    @OneToMany(mappedBy = "vendedor")
    private List<Pedido> pedidos = new ArrayList<>();

    @Size(min = 1, message = "Selecione pelo menos um grupo")
    @ManyToMany
    @JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "codigo_usuario")
            , inverseJoinColumns = @JoinColumn(name = "codigo_grupo"))
    private List<Grupo> grupos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmeSenha() {
        return confirmeSenha;
    }

    public void setConfirmeSenha(String confirmeSenha) {
        this.confirmeSenha = confirmeSenha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                ", confirmeSenha='" + confirmeSenha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                '}';
    }
}
