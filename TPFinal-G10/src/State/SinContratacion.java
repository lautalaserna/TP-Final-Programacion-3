package State;

import java.io.Serializable;

import Excepciones.AccionInvalidaException;
import Excepciones.DomicilioRepetidoException;
import Factory.ContratacionFactory;
import Modelo.IContratacion;
import Modelo.TitularFisico;

public class SinContratacion implements IState, Serializable {
	private TitularFisico titular = null;

	public SinContratacion(TitularFisico titular) {
		this.titular = titular;
	}
	
	@Override
	public void darDeBaja(int index) throws AccionInvalidaException{

		throw new AccionInvalidaException("No hay servicios para dar de baja");
	}

	@Override
	public void pagarFactura(int index) throws AccionInvalidaException{
		
		throw new AccionInvalidaException("No hay facturas para pagar");
	}

	@Override
	public void addContratacion(IContratacion c) throws DomicilioRepetidoException {
		
		this.titular.getContrataciones().add(c);
		
		this.titular.setEstado(new ConContratacion(this.titular));
	}
	
	@Override
	public String toString() {
		
		return "Sin contratacion";
	}

}
