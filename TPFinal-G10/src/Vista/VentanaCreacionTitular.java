package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**Esta clase representa una ventana que le permitira al usuario ingresar los datos necesarios para agregar un nuevo titular a la Empresa.
 * 
 * @author G10
 *
 */
public class VentanaCreacionTitular extends JFrame implements KeyListener, MouseListener {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNombre;
	private JTextField textField_Nombre;
	private JLabel lblDNI;
	private JTextField textField_DNI;
	private JPanel panel_4;
	private JPanel panel_5;
	private JRadioButton rdbtnFisico;
	private JRadioButton rdbtnJuridico;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnEfectivo;
	private JRadioButton rdbtnTarjeta;
	private JRadioButton rdbtnCheque;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JButton btnAgregar;


	/**
	 * Create the frame.
	 */
	public VentanaCreacionTitular() {
		super("Nuevo Titular");
		this.setVisible(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 100, 400, 600);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		this.contentPane.add(this.panel);
		this.panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		this.lblNombre = new JLabel("Nombre:");
		this.lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNombre.setFont(new Font("Dialog", Font.PLAIN, 14));
		this.panel.add(this.lblNombre);
		
		this.panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) this.panel_4.getLayout();
		flowLayout_2.setVgap(15);
		this.panel.add(this.panel_4);
		
		this.textField_Nombre = new JTextField();
		this.textField_Nombre.addKeyListener(this);
		this.textField_Nombre.setFont(new Font("Dialog", Font.PLAIN, 14));
		this.panel_4.add(this.textField_Nombre);
		this.textField_Nombre.setColumns(15);
		
		this.lblDNI = new JLabel("DNI:");
		this.lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblDNI.setFont(new Font("Dialog", Font.PLAIN, 14));
		this.panel.add(this.lblDNI);
		
		this.panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) this.panel_5.getLayout();
		flowLayout_1.setVgap(15);
		this.panel.add(this.panel_5);
		
		this.textField_DNI = new JTextField();
		this.textField_DNI.addKeyListener(this);
		this.textField_DNI.setFont(new Font("Dialog", Font.PLAIN, 14));
		this.panel_5.add(this.textField_DNI);
		this.textField_DNI.setColumns(15);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(null, "Tipo de Titular", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.contentPane.add(this.panel_1);
		this.panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		this.rdbtnFisico = new JRadioButton("Titular Fisico");
		this.rdbtnFisico.addMouseListener(this);
		this.rdbtnFisico.setFont(new Font("Dialog", Font.PLAIN, 14));
		buttonGroup.add(this.rdbtnFisico);
		this.panel_1.add(this.rdbtnFisico);
		
		this.rdbtnJuridico = new JRadioButton("Titular Juridico");
		this.rdbtnJuridico.addMouseListener(this);
		this.rdbtnJuridico.setFont(new Font("Dialog", Font.PLAIN, 14));
		buttonGroup.add(this.rdbtnJuridico);
		this.panel_1.add(this.rdbtnJuridico);
		
		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new TitledBorder(null, "Forma de Pago", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.contentPane.add(this.panel_2);
		this.panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		this.rdbtnEfectivo = new JRadioButton("Efectivo");
		this.rdbtnEfectivo.addMouseListener(this);
		this.rdbtnEfectivo.setFont(new Font("Dialog", Font.PLAIN, 14));
		buttonGroup_1.add(this.rdbtnEfectivo);
		this.panel_2.add(this.rdbtnEfectivo);
		
		this.rdbtnTarjeta = new JRadioButton("Tarjeta");
		this.rdbtnTarjeta.addMouseListener(this);
		this.rdbtnTarjeta.setFont(new Font("Dialog", Font.PLAIN, 14));
		buttonGroup_1.add(this.rdbtnTarjeta);
		this.panel_2.add(this.rdbtnTarjeta);
		
		this.rdbtnCheque = new JRadioButton("Cheque");
		this.rdbtnCheque.addMouseListener(this);
		this.rdbtnCheque.setFont(new Font("Dialog", Font.PLAIN, 14));
		buttonGroup_1.add(this.rdbtnCheque);
		this.panel_2.add(this.rdbtnCheque);
		
		this.panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panel_3.getLayout();
		flowLayout.setVgap(70);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setHgap(30);
		this.contentPane.add(this.panel_3);
		this.panel_3.setPreferredSize(new Dimension(200,100));
		
		this.btnAgregar = new JButton("Agregar Titular");
		this.btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.panel_3.add(this.btnAgregar);
		this.btnAgregar.setPreferredSize(new Dimension(150,40));
		this.btnAgregar.setActionCommand("AGREGATITULAR");
		this.btnAgregar.setEnabled(false);
	}
	
	public void addActionListener(ActionListener a) {
		this.btnAgregar.addActionListener(a);
	}
	
	public String getTipo() {
		
		String respuesta = null;
		
		if(this.rdbtnFisico.isSelected())
			respuesta = "Fisico";
		else
			respuesta = "Juridico";
		
		return respuesta;
	}
	
	public String getNombre() {
		return this.textField_Nombre.getText();
	}
	
	public int getDNI() {
		return Integer.parseInt(this.textField_DNI.getText());
	}
	
	public String getFormaDePago() {
		
		String respuesta = null;
		
		if(this.rdbtnEfectivo.isSelected())
			respuesta = "Efectivo";
		else if( this.rdbtnCheque.isSelected() )
			respuesta = "Cheque";
		else
			respuesta = "Tarjeta";
		
		return respuesta;
	}
	
	/**
	 * Metodo que confirma si los datos son validos.
	 * 
	 * @return true si son validos, false si no lo son.
	 */
	public boolean infoOk() {
		boolean respuesta = false;
		
		respuesta = (this.rdbtnFisico.isSelected() || this.rdbtnJuridico.isSelected()) && (this.rdbtnCheque.isSelected() || this.rdbtnEfectivo.isSelected() || this.rdbtnTarjeta.isSelected());
		
		respuesta &= (!this.textField_Nombre.getText().isEmpty() && !this.textField_DNI.getText().isEmpty());
		
		return respuesta;
	}
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if( infoOk() ) {
			this.btnAgregar.setEnabled(true);
		}else {
			this.btnAgregar.setEnabled(false);
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
		if( infoOk() ) {
			this.btnAgregar.setEnabled(true);
		}else {
			this.btnAgregar.setEnabled(false);
		}
	}
}
