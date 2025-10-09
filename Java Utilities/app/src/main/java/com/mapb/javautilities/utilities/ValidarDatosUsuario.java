package com.mapb.javautilities.utilities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author MAPB
 */
public class ValidarDatosUsuario {

	private static final String ER_TLF = "^[67][0-9]{8}$";
	private static final String ER_DNICOMPLETO = "([0-9]{8})([A-Z]{1})$";
	private static final String ER_DNISINLETRA = "(\\d{8})";
	private static char[] letrasDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S',
			'Q', 'V', 'H', 'L', 'C', 'K', 'E' };

	/**
	 * Constructor privado para evitar que java genere el suyo por defecto y así
	 * evitar que se puedan instanciar objetos
	 */
	private ValidarDatosUsuario() {
		// No se permiten instanciar objetos de esta clase
	}

	// Saber si un usuario es mayor de edad o no con su fecha de nacimiento de tipo Date
	public static boolean esMayorDeEdad(Date fecha) {
		boolean esMayor = false;
		LocalDate fechaNueva = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (ChronoUnit.YEARS.between(fechaNueva, LocalDate.now()) >= 18) {
			esMayor = true;
		}
		return esMayor;
	}

	// Saber si un usuario es mayor de edad o no con su fecha de nacimiento en
	// localdate
	public static boolean esMayorDeEdad(LocalDate fecha) {
		boolean esMayor = false;
		LocalDate fechaNueva = fecha;
		if (ChronoUnit.YEARS.between(fechaNueva, LocalDate.now()) >= 18) {
			esMayor = true;
		}
		return esMayor;
	}

	// Validar dni pasado pro parametro
	public static boolean validarDni(String dni) {
		boolean comprobarDni = false;
		Pattern p = Pattern.compile(ER_DNICOMPLETO);
		Matcher m = p.matcher(dni);
		String numeros = "";
		String letra = "";
		if (m.matches()) {
			numeros = m.group(1);
			letra = m.group(2);
			int dniInt = Integer.parseInt(numeros);
			int resto = dniInt % 23;
			char letraObtenida = letrasDni[resto];
			String letraObtenidaSt = Character.toString(letraObtenida);
			if (letra.toUpperCase().equals(letraObtenidaSt)) {
				comprobarDni = true;
			}
		}
		return comprobarDni;
	}

	// Obtener la letra correspondiente a un dni dado por parametro
	public static String obtenerLetraDni(String dni) {
		Pattern p = Pattern.compile(ER_DNISINLETRA);
		Matcher m = p.matcher(dni);
		String numeros = "";
		if (m.matches()) {
			numeros = m.group(1);
		}
		int dniInt = Integer.parseInt(numeros);
		int resto = dniInt % 23;
		return String.valueOf(letrasDni[resto]);
	}
	
	// Validar número de teléfono
    public static boolean validarTelefono(String campo) {
    	Pattern pattern = Pattern.compile(ER_TLF);
    	Matcher matcher = pattern.matcher(campo);
        boolean matchFound = matcher.find();
        return !campo.isBlank() && matchFound;
    }

}
