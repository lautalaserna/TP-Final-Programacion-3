package Main;

import java.io.IOException;

import Controlador.Controlador;
import Excepciones.DomicilioRepetidoException;
import Factory.ContratacionFactory;
import Factory.TitularFactory;
import Modelo.AFIP;
import Modelo.Empresa;
import Persistencia.IPersistencia;
import Persistencia.PersistenciaBIN;
import Util.EPT;

public class Main {
	//MAIN
	public static void main(String[] args) {
		IPersistencia persistencia = new PersistenciaBIN();
		Empresa e = null;
		EPT ept = EPT.getInstance();
		AFIP afip = AFIP.getInstance();
		try {
			persistencia.abrirInput("Empresa.bin");
			e = (Empresa) persistencia.leer();
			Empresa.setInstance(e); // La persistencia no guarda atributos estaticos
			persistencia.cerrarInput();

			persistencia.abrirInput("EPT.bin");
			ept = (EPT) persistencia.leer();
			EPT.setInstance(ept); // La persistencia no guarda atributos estaticos
			persistencia.cerrarInput();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
			if (e == null)
				e = Empresa.getInstance();
			if (ept == null)
				ept = EPT.getInstance();
		} catch (ClassNotFoundException e2) {
			System.out.println(e2.getMessage());
		}
		
		e.setObservers(); //Metodo que settea la relacion observer-observado entre ept y titulares despues de la lectura
		
		Thread hiloAFIP = new Thread(afip);
		
		Controlador c = new Controlador();
		
		hiloAFIP.start();
		
		//Escritura de los cambios en el main.
		try {
			persistencia.abrirOutput("Empresa.bin");
			persistencia.escribir(e);
			persistencia.cerrarOutput();

			persistencia.abrirOutput("EPT.bin");
			persistencia.escribir(ept);
			persistencia.cerrarOutput();
		} catch (IOException e1) {
		}

	}
}
