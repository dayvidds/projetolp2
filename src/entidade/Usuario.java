package entidade;

import javax.swing.text.MaskFormatter;

public class Usuario {
	private String nome;
	private String email;
	private String celular;
	private TipoUsuario classe;
	private String documento;
	private Status status;

	public Usuario(String documento, String nome, String email, String celular, TipoUsuario classe, Status status) {
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.documento = documento;	
		this.status = status;
	}
	public static String formatString(String value, String pattern) {
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (Exception ex) {
            return value;
        }
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

	public void setEmail(String emial) {
		this.email = emial;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public TipoUsuario getClasse() {
		return classe;
	}

	public void setClasse(TipoUsuario classe) {
		this.classe = classe;
	}

	public String getIdentificacao() {
		return documento;
	}

	public void setIdentificacao(String identificacao) {
		this.documento = identificacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((classe == null) ? 0 : classe.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (classe == null) {
			if (other.classe != null)
				return false;
		} else if (!classe.equals(other.classe))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {

		if (this.classe.getClasse().equals("PESSOA_FISICA")) {
			this.documento = formatString(documento, "###.###.###-##");
		}else {
			this.documento = formatString(documento, "##.###.###/####-##");
		}
		return nome + "/" + documento + ", " + email + ", " + celular + ", status: " +  status.getStatus();
	}

}


