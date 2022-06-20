package br.com.Wl.Cafemanha.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.NonNull;

@Entity
public class Colaborador {
	
	@Id
	@CPF(message = "CPF inválido")
	private String cpf;
	@NotNull(message = "Nome é obrigatório")
	private String nome;
	
	public Colaborador() {
		
	}
	
	

	public Colaborador(@CPF(message = "CPF inválido") String cpf,
			@NotNull(message = "Nome é obrigatório") String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}



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



	@Override
	public String toString() {
		return "Colaborador [cpf=" + cpf + ", nome=" + nome + "]";
	}
	
	

}
