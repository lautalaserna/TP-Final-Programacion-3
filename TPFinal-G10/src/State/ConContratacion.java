package State;

import java.io.Serializable;

import Excepciones.DomicilioRepetidoException;
import Factory.ContratacionFactory;
import Modelo.IContratacion;
import Modelo.TitularFisico;

public class ConContratacion implements IState, Serializable {
	private TitularFisico titular = null;

	public ConContratacion(TitularFisico titular) {
		this.titular = titular;
	}

	@Override
	public void addContratacion(IContratacion c) throws DomicilioRepetidoException {
		
		this.titular.getContrataciones().add(c);
	}


	@Override
	public void darDeBaja(int index) {
		
		this.titular.getContrataciones().remove(index);
		
		if( this.titular.getContrataciones().isEmpty() ) {
			this.titular.setEstado(new SinContratacion(this.titular));
		}
	}


	@Override
	public void pagarFactura(int index) {
		
		this.titular.getFacturas().get(index).pagar();
	}


	@Override
	public String toString() {
		
		return "Con contratacion";
	}
}
