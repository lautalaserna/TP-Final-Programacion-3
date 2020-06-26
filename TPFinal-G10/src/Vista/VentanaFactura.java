package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import Modelo.Factura;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

/**Esta clase representa una ventana que le permite al usuario visualizar los detalles de una factura y poder pagarla.
 * 
 * @author G10
 *
 */
public class VentanaFactura extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_sur;
	private JPanel panel_centro;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_sur_central;
	private JButton btnPagarFactura;
	private Factura factura = null;
	private JScrollPane scrollPane;
	private JTextPane textPane;


	/**
	 * Create the frame.
	 * 
	 * @param factura Es la facura que se mostrara por pantalla.
	 */
	public VentanaFactura(Factura factura) {
		super("Factura");
		this.factura = factura;
		setVisible(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 100, 700, 700);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.NORTH);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1, BorderLayout.WEST);
		
		this.panel_2 = new JPanel();
		this.contentPane.add(this.panel_2, BorderLayout.EAST);
		
		this.panel_sur = new JPanel();
		this.contentPane.add(this.panel_sur, BorderLayout.SOUTH);
		this.panel_sur.setLayout(new BorderLayout(0, 0));
		this.panel_sur.setPreferredSize(new Dimension(500,100));
		
		this.panel_3 = new JPanel();
		this.panel_sur.add(this.panel_3, BorderLayout.WEST);
		
		this.panel_4 = new JPanel();
		this.panel_sur.add(this.panel_4, BorderLayout.EAST);
		
		this.panel_5 = new JPanel();
		this.panel_sur.add(this.panel_5, BorderLayout.NORTH);
		
		this.panel_6 = new JPanel();
		this.panel_sur.add(this.panel_6, BorderLayout.SOUTH);
		
		this.panel_sur_central = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panel_sur_central.getLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(25);
		this.panel_sur.add(this.panel_sur_central, BorderLayout.CENTER);
		
		this.btnPagarFactura = new JButton("Pagar Factura");
		this.btnPagarFactura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.panel_sur_central.add(this.btnPagarFactura);
		this.btnPagarFactura.setPreferredSize(new Dimension(140,40));
		this.btnPagarFactura.setActionCommand("PAGAR");
		if( this.factura.isPaga() ) {
			this.btnPagarFactura.setEnabled(false);
		}
		
		this.panel_centro = new JPanel();
		this.panel_centro.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.contentPane.add(this.panel_centro, BorderLayout.CENTER);
		this.panel_centro.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane();
		this.panel_centro.add(this.scrollPane, BorderLayout.CENTER);
		
		this.textPane = new JTextPane();
		this.textPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.textPane.setEditable(false);
		this.scrollPane.setViewportView(this.textPane);
		this.textPane.setText(factura.detalleFactura());
	}
	
	public void addActionListener(ActionListener a) {
		this.btnPagarFactura.addActionListener(a);
	}

	public void cerrar() {
		this.setVisible(false);
	}
}
/*
 * this.textPane = new JTextPane();
		this.textPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.textPane.setEditable(false);
		this.panel_centro.add(this.textPane, BorderLayout.CENTER);
		this.textPane.setText(factura.detalleFactura());
 */
