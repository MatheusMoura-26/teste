package br.com.Wl.Cafemanha.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.Wl.Cafemanha.model.Colaborador;

//Classe DTO(Data Transfer Object)
public class RequisicaoFormColaborador {

	@NotBlank
	@NotNull
	@CPF(message = "CPF inválido")
	private String cpf;
	@NotBlank
	private String nome;
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Colaborador toColaborador() {
		Colaborador colaborador = new Colaborador();
		colaborador.setCpf(this.cpf);
		colaborador.setNome(this.nome);
		return colaborador;
	}
	
	public void fromColaborador(Colaborador colaborador) {
		this.nome = colaborador.getNome();
	}
	
	public Colaborador toColaborador(Colaborador colaborador) {
		colaborador.setNome(this.nome);
		return colaborador;
	}
	
	@Override
	public String toString() {
		return "RequisicaoNovoColaborador [cpf=" + cpf + ", nome=" + nome + "]";
	}
	
	
	
	
}
