package Controlador;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import Excepciones.AccionInvalidaException;
import Excepciones.DomicilioRepetidoException;
import Excepciones.TitularDuplicadoException;
import Factory.ContratacionFactory;
import Factory.TitularFactory;
import Modelo.AFIP;
import Modelo.Empresa;
import Modelo.Factura;
import Modelo.IContratacion;
import Modelo.Titular;
import Persistencia.IPersistencia;
import Persistencia.PersistenciaBIN;
import Util.EPT;
import Vista.VentanaAFIP;
import Vista.VentanaCreacionTitular;
import Vista.VentanaFactura;
import Vista.VentanaPrincipal;

public class Controlador implements ActionListener, MouseListener, WindowListener, Observer {
	private Empresa e = Empresa.getInstance();
	private EPT ept = EPT.getInstance();
	private AFIP afip = AFIP.getInstance();
	private VentanaPrincipal vPrincipal;
	private VentanaFactura vFactura;
	private VentanaCreacionTitular vCreacionTitular;
	private VentanaAFIP vAFIP;

	/**
	 * Como AFIP y Empresa son Singletons, ya tenemos una referencia a ambos, por lo que no tenemos un atributo "observado".
	 */
	public Controlador() {
		this.afip.addObserver(this);
		this.e.addObserver(this);
		this.vPrincipal = new VentanaPrincipal();
		this.vPrincipal.addActionListener(this);
		this.vPrincipal.addMouseListener(this);
		this.vPrincipal.setVisible(true);
		refreshListTitulares();
	}

	/**
	 * Metodo que actualiza la lista de titulares de la ventana principal.
	 */
	public void refreshListTitulares() { // Metodo para ver los Titulares
		vPrincipal.getModelTitulares().clear();
		Iterator<Titular> it = e.getTitulares().iterator();
		while (it.hasNext()) {
			Titular t = it.next();
			vPrincipal.getModelTitulares().addElement(t);
		}
		vPrincipal.getListTitulares().repaint();
	}
	
	private void abrirVentanaAFIP(String reporte) {
		vAFIP = new VentanaAFIP(reporte);
		vAFIP.setVisible(true);
		vAFIP.addWindowListener(this);
	}

	/**
	 * Clase interna al controlador que se encarga de emular la concurrencia y poder obtener el metodo run().
	 * Decidimos crear una clase interna para poder tener acceso a todos los atributos del controlador sin necesidad de pasarlos
	 * a otra clase por getters.
	 * 
	 * @author G10
	 *
	 */
	public class CreadorTitulares implements Runnable {
		@Override
		public void run() {
			Empresa.getInstance().adquiereBloqueo();
			abrirVentanaCreacion();
		}
	}
	
	/**
	 * Metodo del controlador que es llamado por la clase interna para abrir la ventana de creacion de titulares.
	 */
	private void abrirVentanaCreacion() {
		vCreacionTitular = new VentanaCreacionTitular();
		vCreacionTitular.setVisible(true);
		vCreacionTitular.addWindowListener(this);
		vCreacionTitular.addActionListener(this);
	}
	
	/**
	 * Comienza el hilo de creacion.
	 */
	private void iniciaCreacion() {
		Thread hilo = new Thread(new CreadorTitulares());
		hilo.start();
	}

	/**
	 * Al cerrar la ventana libera el bloqueo.
	 */
	public void cerrarVentana() {
		e.liberaBloqueo();
		this.serializar();
	}

	/**
	 * Metodo que agrega un titular en el modelo con los datos de la vista.
	 */
	private void agregaTitular() {

		Titular titular = TitularFactory.getTitular(vCreacionTitular.getTipo(), vCreacionTitular.getNombre(),
				vCreacionTitular.getDNI(), vCreacionTitular.getFormaDePago(), ept);
		try {
			e.addTitular(titular);
		} catch (TitularDuplicadoException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(vCreacionTitular, e.getMessage(), "Titular Duplicado", JOptionPane.ERROR_MESSAGE);
		}
		this.refreshListTitulares();
		cerrarVentana();
		vCreacionTitular.setVisible(false);
	}

	/**
	 * Metodo que elimina un titular en el modelo con los datos de la vista.
	 */
	private void eliminaTitular() {

		int respuesta = JOptionPane.showConfirmDialog(vPrincipal, "Esta seguro que desea Eliminar a este titular?",
				"Confirmación", JOptionPane.YES_OPTION);

		if (respuesta == 0) {
			Empresa e = Empresa.getInstance();
			int index = vPrincipal.getListTitulares().getSelectedIndex();
			e.eliminarTitular(index);
			this.refreshListTitulares();
			vPrincipal.actualizarInfo();
		}

	}

	/**
	 * Metodo que paga una factura en el modelo con los datos de la vista.
	 */
	private void pagarFactura() {
		int index = vPrincipal.getListaFacturas().getSelectedIndex();

		Titular t = (Titular) vPrincipal.getListTitulares().getSelectedValue();
		try {
			t.pagarFactura(index);
		} catch (AccionInvalidaException e) {
			JOptionPane.showMessageDialog(vPrincipal, e.getMessage(), "Accion Invalida", JOptionPane.ERROR_MESSAGE);
		}
		vPrincipal.actualizarInfo();
		vFactura.cerrar();
	}

	
	/**
	 * Metodo que elimina una contratacion en el modelo con los datos de la vista.
	 */
	private void eliminarContratacion() {

		int respuesta = JOptionPane.showConfirmDialog(vPrincipal, "Esta seguro que desea Eliminar esta contratacion?",
				"Confirmación", JOptionPane.YES_OPTION);

		if (respuesta == 0) {
			Titular t = (Titular) vPrincipal.getListTitulares().getSelectedValue();
			try {
				t.darDeBaja(vPrincipal.getListaContrataciones().getSelectedIndex());
			} catch (AccionInvalidaException e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(vPrincipal, e.getMessage(), "Accion Invalida", JOptionPane.ERROR_MESSAGE);
			}
			vPrincipal.actualizarInfo();
		}
	}

	/**
	 * Metodo que agrega una contratacion en el modelo con los datos de la vista.
	 */
	private void agregarContratacion() {
		try {
			IContratacion c = ContratacionFactory.getContratacion(vPrincipal.getDireccion(), vPrincipal.getInternet(),
					vPrincipal.getCelular(), vPrincipal.getTelFijo(), vPrincipal.getTV());
			Titular t = (Titular) vPrincipal.getListTitulares().getSelectedValue();
			t.addContratacion(c);
			vPrincipal.actualizarInfo();
		} catch (DomicilioRepetidoException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(vPrincipal, "El domicilio ya tiene una contratacion", "Imposible contratar",
					JOptionPane.ERROR_MESSAGE);
		} catch (AccionInvalidaException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(vPrincipal, e.getMessage(), "Accion Invalida", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	/**
	 * Metodo que serializa el sistema. Es llamado luego de realizar alguna accion.
	 */
	private void serializar() {
		IPersistencia persistencia = new PersistenciaBIN();
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

	/**
	 *Metodo que realiza una accion dependiendo del observado que se actualice.
	 *
	 *Si AFIP llama a notifyObservers() entonces se abre la ventana de AFIP.
	 *
	 *Si la empresa llama a notifyObservers() entonces muestra un mensaje de espera. 
	 *
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 == afip) {
			String reporte = (String) arg1;
			this.abrirVentanaAFIP(reporte);
		} else if(arg0 == e){
			String msg = (String) arg1;
			if (msg.equals("AFIP esta esperando")) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(vCreacionTitular, arg1, "Advertencia", JOptionPane.ERROR_MESSAGE);
			}
			else {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(vAFIP, arg1, "Advertencia", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getActionCommand().equals("AGREGATITULAR")) {
			agregaTitular();
		} else if (evento.getActionCommand().equals("INICIACREACION")) {
			iniciaCreacion();
		} else if (evento.getActionCommand().equals("ELIMINATITULAR")) {
			eliminaTitular();
		} else if (evento.getActionCommand().equals("AGREGACONTRATACION")) {
			agregarContratacion();
		} else if (evento.getActionCommand().equals("ELIMINACONTRATACION")) {
			eliminarContratacion();
		} else if (evento.getActionCommand().equals("EPT")) {
			this.ept.avanzarMes();
			vPrincipal.actualizarInfo();
		} else if (evento.getActionCommand().equals("PAGAR")) {
			pagarFactura();
		}
		serializar();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == vPrincipal.getListaFacturas()) {
			if (!vPrincipal.getListaFacturas().isSelectionEmpty()) {
				Factura factura = (Factura) vPrincipal.getListaFacturas().getSelectedValue();

				this.vFactura = new VentanaFactura(factura);
				this.vFactura.addActionListener(this);
				this.vFactura.setVisible(true);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		cerrarVentana();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}
