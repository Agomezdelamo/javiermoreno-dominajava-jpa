/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiermoreno.anotaciones.demo;

import com.javiermoreno.anotaciones.Auditar;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ciberado
 */
public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);
     
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        log.info("Inicializando.");
        
        HistorialClinicoService hc = new HistorialClinicoServiceImpl();
        Method contarPacientesMethod = hc.getClass().getDeclaredMethod("contarPacientesPorPlanta", String.class);
        Auditar auditar = contarPacientesMethod.getAnnotation(Auditar.class);
        if (auditar != null) {
            log.info("Método auditable localizado.");
        }
        int pacientes = (Integer) contarPacientesMethod.invoke(hc, "");
        log.info(MessageFormat.format("Encontrados {0} pacientes.", pacientes));
        
        log.info("Fin.");
    }
    
}
