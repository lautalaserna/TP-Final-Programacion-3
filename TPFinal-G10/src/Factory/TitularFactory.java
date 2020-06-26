package Factory;

import java.util.Observable;

import Modelo.Titular;
import Modelo.TitularFisico;
import Modelo.TitularJuridico;

/**
 * @author Grupo 10
 * 
 * Esta clase se encarga de instanciar los titulares de la empresa.
 */
public class TitularFactory {
	
	/**
	 * Este metodo estatico permite crear un nuevo titular. <br>
	 * 
	 * <b>pre:</b> La empresa existe (no es null). <br>
	 * <b>post:</b> Se crea un nuevo titular. <br>
	 * 
	 * @param tipo Es el tipo de titular que sera creado, puede ser "Fisico" o "Juridico".
	 * @param nombre Es el nombre del titular que sera creado !=null !="".
	 * @param dni Es el dni del titular, debe ser positivo.
	 * @param tipoDePago Es el tipo de pago, podra ser "Efectivo", "Cheque" o "Tarjeta".
	 * @param o Es el observado, es decir, el EPT.
	 * 
	 * @return Retorna el titular que será agregado a la empresa.
	 */
	public static Titular getTitular(String tipo, String nombre, int dni, String tipoDePago, Observable o) {
		Titular respuesta = null;
		if (tipo.equals("Fisico")) {
			respuesta = new TitularFisico(nombre, dni, tipoDePago,o);
		} else {
			if (tipo.equals("Juridico")) {
				respuesta = new TitularJuridico(nombre, dni, tipoDePago,o);
			}
		}
		return respuesta;
	}
}
