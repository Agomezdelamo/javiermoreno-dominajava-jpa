/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiermoreno.anotaciones.demo;

import com.javiermoreno.anotaciones.Auditar;
import com.javiermoreno.anotaciones.AuditoriaType;

/**
 *
 * @author ciberado
 */
public class HistorialClinicoServiceImpl implements HistorialClinicoService {

    public HistorialClinicoServiceImpl() {
    }

    
    @Auditar(AuditoriaType.NORMAL)
    @Override
	public int contarPacientesPorPlanta(String planta) {
		return 0;
	}

    
    
}
