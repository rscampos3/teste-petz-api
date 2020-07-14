package br.com.testepetz.exception;

public class EnderecoException extends Exception{

	private static final long serialVersionUID = 8859800931513056600L;

	private String mensagem;
	
	public EnderecoException(String mensagem){
		super(mensagem);
		this.mensagem = mensagem;
	}
	
	public EnderecoException(String mensagem, Throwable cause){
		super(mensagem, cause);
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	
	
}
