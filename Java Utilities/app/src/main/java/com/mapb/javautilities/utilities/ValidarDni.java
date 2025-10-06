package com.mapb.javautilities.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author MAPB
 */
public class ValidarDni {
	private static final String ER_DNICOMPLETO = "(\\d{8})([A-Za-z]{1})";
	private static final String ER_DNISINLETRA = "(\\d{8})";
	private static char letras[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
			'L', 'C', 'K', 'E' };

    /**
     * Constructor privado para evitar que java genere el suyo por defecto y así
     * evitar que se puedan instanciar objetos
     */
	private ValidarDni() {
		// No se permiten instanciar objetos de esta clase
	}

	// Validar dni pasado pro parametro
	public static boolean validarDni(String dni) {
		boolean comprobarDni = false;
		Pattern p = Pattern.compile(ER_DNICOMPLETO);
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
	
	// Obtener la letra correspondiente a un dni dado por parametro
	public static char obtenerLetraDni(String dni) {
		Pattern p = Pattern.compile(ER_DNISINLETRA);
		Matcher m = p.matcher(dni);
		String numeros = "";
		while (m.matches()) {
			numeros = m.group(1);
		}
		int dniInt = Integer.parseInt(numeros);
		int resto = dniInt % 23;
		return letras[resto];
	}

}
