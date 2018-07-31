package br.com.asas.lojinha.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fabricante")
public class Fabricante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4805458940792126378L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_fabricante")
    private Long codigo;
    
	@NotNull(message = "Informe o nome do fabricante")
	@NotEmpty(message = "Informe o nome do fabricante")
    @Column(name = "nome_fabricante", nullable = false)
    private String nome;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}
