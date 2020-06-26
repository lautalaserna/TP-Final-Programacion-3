package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

/**
 * Ventana que informa la llegada del AFIP y muetra por pantalla un reporte de las facturas clonadas.
 * 
 * @author G10
 *
 */
public class VentanaAFIP extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JTextPane textPane;
	private JLabel lblFacturasDuplicadas;

	/**
	 * Create the frame.
	 * 
	 * @param reporte Es el reporte que muestra AFIP de las facturas clonadas.
	 */
	public VentanaAFIP(String reporte) {
		super("Reporte AFIP");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 200, 600, 800);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(null);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panel.getLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.LEFT);
		this.contentPane.add(this.panel, BorderLayout.NORTH);
		
		this.lblFacturasDuplicadas = new JLabel("Facturas de titulares fisicos duplicadas:");
		this.lblFacturasDuplicadas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.lblFacturasDuplicadas);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1, BorderLayout.WEST);
		
		this.panel_2 = new JPanel();
		this.contentPane.add(this.panel_2, BorderLayout.EAST);
		
		this.panel_3 = new JPanel();
		this.contentPane.add(this.panel_3, BorderLayout.SOUTH);
		
		this.panel_4 = new JPanel();
		this.panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.contentPane.add(this.panel_4, BorderLayout.CENTER);
		this.panel_4.setLayout(new BorderLayout(0, 0));
		
		this.textPane = new JTextPane();
		this.textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.textPane.setEditable(false);
		this.panel_4.add(this.textPane, BorderLayout.CENTER);
		this.textPane.setText(reporte);
	}

}
