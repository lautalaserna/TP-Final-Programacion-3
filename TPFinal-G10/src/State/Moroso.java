package State;

import java.io.Serializable;
import java.util.Iterator;

import Excepciones.AccionInvalidaException;
import Excepciones.DomicilioRepetidoException;
import Modelo.Factura;
import Modelo.IContratacion;
import Modelo.TitularFisico;

public class Moroso implements IState, Serializable{
	private TitularFisico titular = null;

	public Moroso(TitularFisico titular) {
		this.titular = titular;
	}

	@Override
	public void addContratacion(IContratacion c) throws DomicilioRepetidoException, AccionInvalidaException {
		
		throw new AccionInvalidaException("Estado Moroso: No se puede contratar este servicio");
	}

	@Override
	public void darDeBaja(int index) throws AccionInvalidaException{
		
		throw new AccionInvalidaException("Estado Moroso: No se puede dar de baja este servicio");
	}

	@Override
	public void pagarFactura(int index) {
		
		this.titular.getFacturas().get(index).setCostoFinal(this.titular.getFacturas().get(index).getCostoFinal() * 1.3);
		
		this.titular.getFacturas().get(index).pagar();
		
		if( this.titular.facturasPendientes() < 2 ) {
			this.actualizaCostos();
			this.titular.setEstado(new ConContratacion(this.titular));
		}
	}
	
	@Override
	public String toString() {
		
		return "Moroso";
	}

	public void actualizaCostos() {
		Iterator<Factura> itFacturas = titular.getFacturas().iterator();
		while( itFacturas.hasNext() ) {
			Factura factura = itFacturas.next();
			if( !factura.isPaga() ) {
				factura.setCostoFinal(factura.getCostoFinal()/1.3);
				factura.setRecargo(null);
			}
				
		}
	}
	

}
