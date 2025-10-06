package com.mapb.javautilities.utilities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author MAPB
 */
public class ValidadFechaNac {
	
    /**
     * Constructor privado para evitar que java genere el suyo por defecto y así
     * evitar que se puedan instanciar objetos
     */
	private ValidadFechaNac() {
        // No se permiten instanciar objetos de esta clase
	}
	
	// Con date
	public static boolean esMayorDeEdad(Date fecha) {
		boolean esMayor = false;
		LocalDate fechaNueva = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (ChronoUnit.YEARS.between(fechaNueva, LocalDate.now()) >= 18) {
        	esMayor = true;
		}
		return esMayor;
	}
	
	// Con localdate
	public static boolean esMayorDeEdad(LocalDate fecha) {
		boolean esMayor = false;
		LocalDate fechaNueva = fecha;
        if (ChronoUnit.YEARS.between(fechaNueva, LocalDate.now()) >= 18) {
        	esMayor = true;
		}
		return esMayor;
	}
	
	
}
