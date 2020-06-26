package Factory;


import java.util.Iterator;

import Decorator.DecoratorCelular;
import Decorator.DecoratorTelFijo;
import Decorator.DecoratorTv;
import Excepciones.DomicilioRepetidoException;
import Modelo.Empresa;
import Modelo.IContratacion;
import Modelo.Internet100;
import Modelo.Internet500;
import Modelo.Titular;

/**
 * @author Grupo 10
 * 
 * Esta clase se encarga de instanciar las contrataciones de los titulares.
 */
public class ContratacionFactory {
	
	/**
	 *  Este metodo estatico permite instanciar una nueva IContratacion <br>
	 *  
	 * <b>pre:</b> El titular existe (no es null). <br>
	 * <b>post:</b> El titular tiene una nueva IContratacion. <br>
	 * 
	 * @param direccion Es la direccion a la cual se contratará el servicio, tiene que ser !=null o !=""<br>
	 * @param internet Es el tipo de servicio, debe ser "Internet100" o "Internet500".<br>
	 * @param celular Es un booleano que indica si contrata Celular.<br>
	 * @param telFijo Es un booleano que indica si contrata Telefono Fijo.<br>
	 * @param tv Es un booleano que indica si contrata TV-Cable.<br>
	 * 
	 * @return Retorna una nueva IContratacion que será agregada al Titular.<br>
	 * 
	 * @throws DomicilioRepetidoException si el domicilio ingresado ya existe en el sistema.
	 */
	public static IContratacion getContratacion(String direccion, String internet, boolean celular, boolean telFijo, boolean tv) throws DomicilioRepetidoException {
		IContratacion contratacion = null;

		if (repetido(direccion))
			throw new DomicilioRepetidoException("Ya fue contratado el domicilio: ", direccion);

		if (internet.equals("Internet100"))
			contratacion = new Internet100(direccion);
		else if (internet.equals("Internet500"))
			contratacion = new Internet500(direccion);

		if (celular)
			contratacion = new DecoratorCelular(contratacion);
		if (telFijo)
			contratacion = new DecoratorTelFijo(contratacion);
		if (tv)
			contratacion = new DecoratorTv(contratacion);

		return contratacion;
	}
	
	/**
	 * Este metodo permite saber si una direccion ya esta registrada o no. <br>
	 * 
	 * <b>pre:</b> El titular del domicilio existe (no es null) al igual que la Empresa. <br>
	 * <b>post:</b> Retorna verdadero o falso segun si la direccion es repetida o no. <br>
	 * 
	 * @param direccion Es la direccion que se buscará en la Empresa.<br>
	 * 
	 * @return Retorna un boolean que indica si es una direccion repetida o no.
	 */
	private static boolean repetido(String direccion) {
		boolean respuesta = false;
		Iterator<Titular> itTitular = Empresa.getInstance().getTitulares().iterator();
		while (itTitular.hasNext() && (!respuesta)) {
			Titular titular = itTitular.next();
			Iterator<IContratacion> itContrataciones = titular.getContrataciones().iterator();
			while (itContrataciones.hasNext() && (!respuesta)) {
				IContratacion contratacion = itContrataciones.next();
				respuesta = contratacion.getDomicilio().equals(direccion);
			}

		}
		return respuesta;
	}
}
