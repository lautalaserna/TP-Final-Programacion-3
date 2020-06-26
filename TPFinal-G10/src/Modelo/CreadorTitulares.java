package Modelo;

/**
 * Esta clase simula el acto de crear un titular, para poder implementar la concurrencia debimos darle la responsabilidad a esta clase
 * de que se encargue de implementar el metodo run(). Su unica funcionalidad es adquirir el bloqueo para que al llegar la AFIP, esta 
 * permaneza en espera hasta que se libere.
 * 
 * @author G10
 *
 */
public class CreadorTitulares implements Runnable{
	
	@Override
	public void run() {
		Empresa.getInstance().adquiereBloqueo();
		
	}

}
