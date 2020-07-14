package br.com.testepetz.exception;

public class MensagemException extends Exception {
	private static final long serialVersionUID = 5385025522248849949L;

	private String mensagem;

	public MensagemException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
