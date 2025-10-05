package com.mapb.javautilities.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author MAPB
 */
public class ValidarDni {
	private static final String ER_DNI = "(\\d{8})([A-Za-z]{1})";
	private static char letras[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
			'L', 'C', 'K', 'E' };

	private ValidarDni() {
		// No se permiten instanciar objetos de esta clase
	}

	public static boolean validarDni(String dni) {
		boolean comprobarDni = false;
		Pattern p = Pattern.compile(ER_DNI);
		Matcher m = p.matcher(dni);
		String numeros = "";
		String letra = "";
		while (m.find()) {
			numeros = m.group(1);
			letra = m.group(2);
		}

		int dniInt = Integer.parseInt(numeros);
		int resto = dniInt % 23;
		char letraObtenida = letras[resto];
		String letraObtenidaSt = Character.toString(letraObtenida);
		if (letra.toUpperCase().equals(letraObtenidaSt)) {
			comprobarDni = true;
		}
		return comprobarDni;
	}

}
