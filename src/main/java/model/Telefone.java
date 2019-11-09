package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "telefones")
@Table(name = "telefones")
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 50, name = "tipo", nullable = false)
	private String tipo;
	
	@Column(length = 10, name = "ddd", nullable = false)
	private String ddd;
	
	@Column(length = 50, name = "numero", nullable = false)
	private String numero;
	
	@Column(length = 5, name = "ramal")
	private String ramal;
	
	@JoinColumn(name = "cod_usuario")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private UsuarioPessoa usuarioPessoa;
	
	public Telefone() {}
	
	public Telefone(String tipo, String ddd, String numero, String ramal, UsuarioPessoa usuarioPessoa) {
		super();
		this.tipo = tipo;
		this.ddd = ddd;
		this.numero = numero;
		this.ramal = ramal;
		this.usuarioPessoa = usuarioPessoa;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}
	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", tipo=" + tipo + ", ddd=" + ddd + ", numero=" + numero + ", ramal=" + ramal
				+ ", usuarioPessoa=" + usuarioPessoa + "]";
	}
	
	
	
}
