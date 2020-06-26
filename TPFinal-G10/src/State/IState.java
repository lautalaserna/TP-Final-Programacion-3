package State;

import Excepciones.AccionInvalidaException;
import Excepciones.DomicilioRepetidoException;
import Modelo.IContratacion;

public interface IState {

	public void addContratacion(IContratacion c)throws DomicilioRepetidoException, AccionInvalidaException;
	
	public void darDeBaja(int index) throws AccionInvalidaException;
	
	public void pagarFactura(int index) throws AccionInvalidaException;

}
