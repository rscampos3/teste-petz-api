package br.com.testepetz.utils;

import java.util.Optional;

public class Validadores {
	public static String validarString(String valor) {
		Optional<String> vOp = Optional.ofNullable(valor);
		return vOp.isPresent() ? vOp.get() : "";
	}

	public static Object validarNulo(Object objeto) {
		return !Optional.ofNullable(objeto).isPresent() ? null : objeto;
	}
}
