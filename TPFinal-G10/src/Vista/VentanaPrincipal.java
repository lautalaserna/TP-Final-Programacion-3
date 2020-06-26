package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Modelo.Factura;
import Modelo.IContratacion;
import Modelo.Titular;
import Util.EPT;

import java.awt.event.MouseListener;
import java.util.Iterator;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionEvent;

/**
 * Esta clase representa la ventana principal de la interfaz que permite visualizar una lista de todos los titulares registrados en la empresa
 * asi como agregar nuevos y eliminarlos, tambien permite visualizar los datos de un titular seleccionado, asi como un listado de sus contrataciones
 * y facturas. Permite agregar contrataciones nuevas y desplegar otras ventanas para pagar las facturas o agregar los datos de un nuevo titular
 * permite tambien realizar una simulacion del paso del tiempo con un JButton que avanza al siguiente mes.
 * 
 * @author G10
 *
 */
public class VentanaPrincipal extends JFrame implements MouseListener, KeyListener {
	private JPanel contentPane;
	private JPanel panelPPALCentral;
	private JPanel panelPPALDerecha;
	private JPanel panelListaTitulares;
	private JPanel panelAUXnorte;
	private JPanel panelAUXSur;
	private JPanel panelAUXoeste;
	private JPanel panelAUXEste;
	private JLabel lblTitulares;
	private JLabel lblNewLabel_1;
	private JPanel panelContieneLista;
	private JPanel panelCentralContenedorbtns;
	private JPanel panelbtnsizq;
	private JPanel panelbtnsder;
	private JPanel panelBtnsTitulares;
	private JPanel panel_2;
	private JButton btnEPT;
	private JLabel lblNewLabel;
	private JPanel panel_3;
	private JButton btnAgregarTitular;
	private JPanel panel_4;
	private JButton btnEliminarTitular;
	private JPanel panelListas;
	private JPanel panel_Info_general;
	private JPanel panel_Lista_factura;
	private JPanel panelContieneLista_F;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_Lista_Contratacion;
	private JPanel panelContieneListaC;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_Info_Titular;
	private JPanel panel_Info_Sur;
	private JPanel panel_Nueva_Contratacion;
	private JPanel panel_Info_Contratacion;
	private JTextField textContratacion;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblFecha;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_20;
	private JPanel panel_21;
	private JPanel panel_22;
	private JLabel lbl_Nombre;
	private JLabel lbl_dni;
	private JLabel lbl_NroID;
	private JLabel lbl_Forma_de_Pago;
	private JLabel lbl_Tipo;
	private JLabel lbl_Estado;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_15;
	private JPanel panel_23;
	private JPanel panel_24;
	private JPanel panel_25;
	private JPanel panel_26;
	private JRadioButton rdbtn_Internet100;
	private JRadioButton rdbtn_Internet500;
	private JRadioButton rdbtn_Celular;
	private JRadioButton rdbtn_Tel_Fijo;
	private JRadioButton rdbtn_TV_Cable;
	private JLabel label;
	private JLabel lblNewLabel_16;
	private JLabel lblNewLabel_17;
	private JPanel panel_27;
	private JPanel panel_28;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblNewLabel_18;
	private JTextField textField_Domicilio;
	private JLabel lblNewLabel_19;
	private JLabel lblNewLabel_20;
	private JButton btn_Agregar_Contratacion;
	private JButton btn_Eliminar_Contratacion;
	private JPanel panel_29;
	private JPanel panel_30;
	private JPanel panel_31;
	private JPanel panel_32;
	private DefaultListModel modelTitulares;
	private DefaultListModel modelFacturas;
	private DefaultListModel modelContrataciones;
	private JPanel panel_33;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JList listTitulares;
	private JList list_Contrataciones;
	private JList list_Facturas;

	public VentanaPrincipal() {
		super("Sistema de Contrataciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 50, 600, 900); // EL POSTA: setBounds(250, 50, 600, 900);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.panelPPALCentral = new JPanel();
		this.panelPPALCentral.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Titulares",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.contentPane.add(this.panelPPALCentral, BorderLayout.CENTER);
		this.panelPPALCentral.setLayout(new BorderLayout(0, 0));
		this.panelPPALCentral.setPreferredSize(new Dimension(600, 900)); //

		this.panelListaTitulares = new JPanel();
		this.panelListaTitulares.setBorder(null);
		this.panelPPALCentral.add(this.panelListaTitulares, BorderLayout.CENTER);
		this.panelListaTitulares.setLayout(new BorderLayout(0, 0));

		this.panelAUXnorte = new JPanel();
		this.panelListaTitulares.add(this.panelAUXnorte, BorderLayout.NORTH);
		this.panelAUXnorte.setLayout(new BorderLayout(0, 0));

		this.lblTitulares = new JLabel("  ");
		this.lblTitulares.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblTitulares.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.panelAUXnorte.add(this.lblTitulares);

		this.lblNewLabel_1 = new JLabel("              ");
		this.panelAUXnorte.add(this.lblNewLabel_1, BorderLayout.WEST);

		this.panelAUXSur = new JPanel();
		FlowLayout fl_panelAUXSur = (FlowLayout) this.panelAUXSur.getLayout();
		fl_panelAUXSur.setVgap(10);
		this.panelListaTitulares.add(this.panelAUXSur, BorderLayout.SOUTH);

		this.panelAUXoeste = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panelAUXoeste.getLayout();
		flowLayout.setHgap(20);
		this.panelListaTitulares.add(this.panelAUXoeste, BorderLayout.WEST);

		this.panelAUXEste = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) this.panelAUXEste.getLayout();
		flowLayout_1.setHgap(20);
		this.panelListaTitulares.add(this.panelAUXEste, BorderLayout.EAST);

		this.panelContieneLista = new JPanel();
		this.panelContieneLista.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.panelListaTitulares.add(this.panelContieneLista, BorderLayout.CENTER);
		this.panelContieneLista.setLayout(new BorderLayout(0, 0));

		// this.listTitulares = new JList();
		// this.listTitulares.setFont(new Font("Tahoma", Font.PLAIN, 14));
		// this.listTitulares.addMouseListener(this);

		this.scrollPane = new JScrollPane();
		this.panelContieneLista.add(this.scrollPane, BorderLayout.CENTER);

		this.listTitulares = new JList();
		this.scrollPane.setViewportView(this.listTitulares);
		this.modelTitulares = new DefaultListModel<Titular>();
		this.listTitulares.setModel(modelTitulares);
		this.listTitulares.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.listTitulares.addMouseListener(this);
		if (!this.listTitulares.isSelectionEmpty()) {
			setBounds(100, 50, 1500, 900);
		}

		this.panelCentralContenedorbtns = new JPanel();
		this.panelPPALCentral.add(this.panelCentralContenedorbtns, BorderLayout.SOUTH);
		this.panelCentralContenedorbtns.setLayout(new BorderLayout(0, 0));

		this.panelbtnsizq = new JPanel();
		FlowLayout flowLayout_18 = (FlowLayout) this.panelbtnsizq.getLayout();
		this.panelCentralContenedorbtns.add(this.panelbtnsizq, BorderLayout.WEST);
		this.panelbtnsizq.setPreferredSize(new Dimension(25, 25)); //

		this.panelbtnsder = new JPanel();
		this.panelCentralContenedorbtns.add(this.panelbtnsder, BorderLayout.EAST);
		this.panelbtnsder.setPreferredSize(new Dimension(35, 35)); //

		this.panelBtnsTitulares = new JPanel();
		this.panelBtnsTitulares.setPreferredSize(new Dimension(50, 50));
		this.panelCentralContenedorbtns.add(this.panelBtnsTitulares, BorderLayout.CENTER);
		this.panelBtnsTitulares.setLayout(new GridLayout(0, 4, 0, 0));

		this.panel_2 = new JPanel();
		FlowLayout flowLayout_19 = (FlowLayout) this.panel_2.getLayout();
		flowLayout_19.setHgap(3);
		flowLayout_19.setAlignment(FlowLayout.RIGHT);
		this.panelBtnsTitulares.add(this.panel_2);

		this.btnEPT = new JButton("Nuevo Mes");
		this.btnEPT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.panel_2.add(this.btnEPT);
		this.btnEPT.setActionCommand("EPT");
		this.btnEPT.setPreferredSize(new Dimension(110, 35));

		this.lblNewLabel = new JLabel("");
		this.panelBtnsTitulares.add(this.lblNewLabel);

		this.panel_3 = new JPanel();
		this.panelBtnsTitulares.add(this.panel_3);

		this.btnAgregarTitular = new JButton("Agregar");
		this.btnAgregarTitular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.panel_3.add(this.btnAgregarTitular);
		this.btnAgregarTitular.setActionCommand("INICIACREACION");
		this.btnAgregarTitular.setPreferredSize(new Dimension(110, 35));

		this.panel_4 = new JPanel();
		this.panelBtnsTitulares.add(this.panel_4);

		this.btnEliminarTitular = new JButton("Eliminar");
		this.btnEliminarTitular.addMouseListener(this);
		this.btnEliminarTitular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.panel_4.add(this.btnEliminarTitular);
		this.btnEliminarTitular.setActionCommand("ELIMINATITULAR");
		this.btnEliminarTitular.setPreferredSize(new Dimension(110, 35));
		this.btnEliminarTitular.setEnabled(false);

		this.panelPPALDerecha = new JPanel();
		this.contentPane.add(this.panelPPALDerecha, BorderLayout.EAST);
		this.panelPPALDerecha.setVisible(false);
		this.panelPPALDerecha.setPreferredSize(new Dimension(900, 900)); // cambiar de lugar
		this.panelPPALDerecha.setLayout(new BorderLayout(0, 0));

		this.panelListas = new JPanel();
		this.panelPPALDerecha.add(this.panelListas, BorderLayout.EAST);
		this.panelListas.setLayout(new GridLayout(2, 1, 0, 0));
		this.panelListas.setPreferredSize(new Dimension(320, 900));

		this.panel_Lista_factura = new JPanel();
		this.panel_Lista_factura
				.setBorder(new TitledBorder(null, "Facturas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelListas.add(this.panel_Lista_factura);
		this.panel_Lista_factura.setLayout(new BorderLayout(0, 0));

		this.panelContieneLista_F = new JPanel();
		this.panelContieneLista_F.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.panel_Lista_factura.add(this.panelContieneLista_F);
		this.panelContieneLista_F.setLayout(new BorderLayout(0, 0));

		this.scrollPane_1 = new JScrollPane();
		this.panelContieneLista_F.add(this.scrollPane_1, BorderLayout.CENTER);

		this.list_Facturas = new JList();
		this.scrollPane_1.setViewportView(this.list_Facturas);
		this.list_Facturas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.list_Facturas.addMouseListener(this);
		this.modelFacturas = new DefaultListModel<Factura>();
		this.list_Facturas.setModel(modelFacturas);

		this.panel = new JPanel();
		this.panel_Lista_factura.add(this.panel, BorderLayout.NORTH);

		this.panel_1 = new JPanel();
		this.panel_Lista_factura.add(this.panel_1, BorderLayout.SOUTH);

		this.panel_5 = new JPanel();
		this.panel_Lista_factura.add(this.panel_5, BorderLayout.WEST);

		this.panel_6 = new JPanel();
		this.panel_Lista_factura.add(this.panel_6, BorderLayout.EAST);

		this.panel_Lista_Contratacion = new JPanel();
		this.panel_Lista_Contratacion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Domicilios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.panelListas.add(this.panel_Lista_Contratacion);
		this.panel_Lista_Contratacion.setLayout(new BorderLayout(0, 0));

		this.panelContieneListaC = new JPanel();
		this.panelContieneListaC.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.panel_Lista_Contratacion.add(this.panelContieneListaC, BorderLayout.CENTER);
		this.panelContieneListaC.setLayout(new BorderLayout(0, 0));

		this.scrollPane_2 = new JScrollPane();
		this.panelContieneListaC.add(this.scrollPane_2, BorderLayout.CENTER);

		this.list_Contrataciones = new JList();
		this.scrollPane_2.setViewportView(this.list_Contrataciones);
		this.list_Contrataciones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.modelContrataciones = new DefaultListModel<IContratacion>();
		this.list_Contrataciones.addMouseListener(this);
		this.list_Contrataciones.setModel(modelContrataciones);

		this.panel_7 = new JPanel();
		this.panel_Lista_Contratacion.add(this.panel_7, BorderLayout.NORTH);

		this.panel_8 = new JPanel();
		this.panel_Lista_Contratacion.add(this.panel_8, BorderLayout.SOUTH);

		this.panel_9 = new JPanel();
		this.panel_Lista_Contratacion.add(this.panel_9, BorderLayout.WEST);

		this.panel_10 = new JPanel();
		this.panel_Lista_Contratacion.add(this.panel_10, BorderLayout.EAST);

		this.panel_Info_general = new JPanel();
		this.panelPPALDerecha.add(this.panel_Info_general, BorderLayout.CENTER);
		this.panel_Info_general.setLayout(new GridLayout(2, 1, 0, 0));

		this.panel_Info_Titular = new JPanel();
		this.panel_Info_Titular.setBorder(
				new TitledBorder(null, "Informacion del Titular", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_Info_general.add(this.panel_Info_Titular);
		this.panel_Info_Titular.setLayout(new GridLayout(8, 3, 0, 0));

		this.lblNewLabel_13 = new JLabel("   Datos Personales:");
		this.lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.panel_Info_Titular.add(this.lblNewLabel_13);

		this.lblNewLabel_14 = new JLabel("");
		this.panel_Info_Titular.add(this.lblNewLabel_14);

		this.lblNewLabel_15 = new JLabel("");
		this.panel_Info_Titular.add(this.lblNewLabel_15);

		this.panel_11 = new JPanel();
		this.panel_Info_Titular.add(this.panel_11);
		this.panel_11.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 20));

		this.lblNewLabel_2 = new JLabel("Numero ID:");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_11.add(this.lblNewLabel_2);

		this.panel_12 = new JPanel();
		this.panel_Info_Titular.add(this.panel_12);
		this.panel_12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));

		this.lbl_NroID = new JLabel("");
		this.lbl_NroID.setHorizontalAlignment(SwingConstants.LEFT);
		this.lbl_NroID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_12.add(this.lbl_NroID);

		this.panel_33 = new JPanel();
		this.panel_Info_Titular.add(this.panel_33);
		this.panel_33.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.lblFecha = new JLabel("");
		this.lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		this.panel_33.add(this.lblFecha);

		this.panel_13 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) this.panel_13.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setHgap(15);
		flowLayout_2.setVgap(20);
		this.panel_Info_Titular.add(this.panel_13);

		this.lblNewLabel_3 = new JLabel("Nombre:");
		this.panel_13.add(this.lblNewLabel_3);
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));

		this.panel_14 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) this.panel_14.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setVgap(20);
		this.panel_Info_Titular.add(this.panel_14);

		this.lbl_Nombre = new JLabel("");
		this.lbl_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_14.add(this.lbl_Nombre);

		this.lblNewLabel_9 = new JLabel("");
		this.panel_Info_Titular.add(this.lblNewLabel_9);

		this.panel_15 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) this.panel_15.getLayout();
		flowLayout_4.setHgap(15);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		flowLayout_4.setVgap(20);
		this.panel_Info_Titular.add(this.panel_15);

		this.lblNewLabel_4 = new JLabel("DNI:");
		this.panel_15.add(this.lblNewLabel_4);
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));

		this.panel_16 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) this.panel_16.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		flowLayout_5.setVgap(20);
		this.panel_Info_Titular.add(this.panel_16);

		this.lbl_dni = new JLabel("");
		this.lbl_dni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_16.add(this.lbl_dni);

		this.lblNewLabel_10 = new JLabel("");
		this.panel_Info_Titular.add(this.lblNewLabel_10);

		this.panel_17 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) this.panel_17.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		flowLayout_6.setHgap(15);
		flowLayout_6.setVgap(20);
		this.panel_Info_Titular.add(this.panel_17);

		this.lblNewLabel_5 = new JLabel("Forma de Pago:");
		this.panel_17.add(this.lblNewLabel_5);
		this.lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));

		this.panel_18 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) this.panel_18.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		flowLayout_7.setVgap(20);
		this.panel_Info_Titular.add(this.panel_18);

		this.lbl_Forma_de_Pago = new JLabel("");
		this.lbl_Forma_de_Pago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_18.add(this.lbl_Forma_de_Pago);

		this.lblNewLabel_11 = new JLabel("");
		this.panel_Info_Titular.add(this.lblNewLabel_11);

		this.panel_19 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) this.panel_19.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		flowLayout_8.setHgap(15);
		flowLayout_8.setVgap(20);
		this.panel_Info_Titular.add(this.panel_19);

		this.lblNewLabel_6 = new JLabel("Tipo:");
		this.panel_19.add(this.lblNewLabel_6);
		this.lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));

		this.panel_20 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) this.panel_20.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		flowLayout_9.setVgap(20);
		this.panel_Info_Titular.add(this.panel_20);

		this.lbl_Tipo = new JLabel("");
		this.lbl_Tipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_20.add(this.lbl_Tipo);

		this.lblNewLabel_7 = new JLabel("");
		this.panel_Info_Titular.add(this.lblNewLabel_7);

		this.panel_21 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) this.panel_21.getLayout();
		flowLayout_10.setHgap(15);
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		flowLayout_10.setVgap(20);
		this.panel_Info_Titular.add(this.panel_21);

		this.lblNewLabel_12 = new JLabel("Estado:");
		this.panel_21.add(this.lblNewLabel_12);
		this.lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));

		this.panel_22 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) this.panel_22.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		flowLayout_11.setVgap(20);
		this.panel_Info_Titular.add(this.panel_22);

		this.lbl_Estado = new JLabel("");
		this.lbl_Estado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_22.add(this.lbl_Estado);

		this.panel_Info_Sur = new JPanel();
		this.panel_Info_Sur.setBorder(null);
		this.panel_Info_general.add(this.panel_Info_Sur);
		this.panel_Info_Sur.setLayout(new BorderLayout(0, 0));

		this.panel_Nueva_Contratacion = new JPanel();
		this.panel_Nueva_Contratacion.setBorder(
				new TitledBorder(null, "Contrataciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_Info_Sur.add(this.panel_Nueva_Contratacion, BorderLayout.CENTER);
		this.panel_Nueva_Contratacion.setLayout(new GridLayout(2, 1, 0, 0));

		this.panel_23 = new JPanel();
		this.panel_Nueva_Contratacion.add(this.panel_23);
		this.panel_23.setLayout(new GridLayout(0, 2, 0, 0));

		this.panel_25 = new JPanel();
		this.panel_25.setBorder(new TitledBorder(null, "Internet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_23.add(this.panel_25);
		this.panel_25.setLayout(new GridLayout(2, 1, 0, 0));

		this.panel_27 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) this.panel_27.getLayout();
		flowLayout_12.setHgap(40);
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		flowLayout_12.setVgap(25);
		this.panel_25.add(this.panel_27);

		this.rdbtn_Internet100 = new JRadioButton("Internet 100");
		this.rdbtn_Internet100.addMouseListener(this);
		buttonGroup.add(this.rdbtn_Internet100);
		this.panel_27.add(this.rdbtn_Internet100);
		this.rdbtn_Internet100.setFont(new Font("Tahoma", Font.PLAIN, 15));

		this.panel_28 = new JPanel();
		FlowLayout flowLayout_13 = (FlowLayout) this.panel_28.getLayout();
		flowLayout_13.setAlignment(FlowLayout.LEFT);
		flowLayout_13.setVgap(25);
		flowLayout_13.setHgap(40);
		this.panel_25.add(this.panel_28);

		this.rdbtn_Internet500 = new JRadioButton("Internet 500");
		this.rdbtn_Internet500.addMouseListener(this);
		buttonGroup.add(this.rdbtn_Internet500);
		this.panel_28.add(this.rdbtn_Internet500);
		this.rdbtn_Internet500.setFont(new Font("Tahoma", Font.PLAIN, 15));

		this.panel_26 = new JPanel();
		this.panel_26
				.setBorder(new TitledBorder(null, "Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_23.add(this.panel_26);
		this.panel_26.setLayout(new GridLayout(3, 1, 0, 0));

		this.rdbtn_Celular = new JRadioButton("Celular");
		this.rdbtn_Celular.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_26.add(this.rdbtn_Celular);

		this.lblNewLabel_16 = new JLabel("");
		this.panel_26.add(this.lblNewLabel_16);

		this.rdbtn_Tel_Fijo = new JRadioButton("Telefono Fijo");
		this.rdbtn_Tel_Fijo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_26.add(this.rdbtn_Tel_Fijo);

		this.lblNewLabel_17 = new JLabel("");
		this.panel_26.add(this.lblNewLabel_17);

		this.rdbtn_TV_Cable = new JRadioButton("TV-Cable");
		this.rdbtn_TV_Cable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_26.add(this.rdbtn_TV_Cable);

		this.label = new JLabel("");
		this.panel_26.add(this.label);

		this.panel_24 = new JPanel();
		this.panel_24.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_Nueva_Contratacion.add(this.panel_24);
		this.panel_24.setLayout(new GridLayout(2, 3, 0, 0));

		this.panel_29 = new JPanel();
		FlowLayout flowLayout_14 = (FlowLayout) this.panel_29.getLayout();
		flowLayout_14.setVgap(30);
		this.panel_24.add(this.panel_29);

		this.lblNewLabel_18 = new JLabel("Domicilio:");
		this.panel_29.add(this.lblNewLabel_18);
		this.lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 15));

		this.panel_30 = new JPanel();
		FlowLayout flowLayout_15 = (FlowLayout) this.panel_30.getLayout();
		flowLayout_15.setVgap(30);
		this.panel_24.add(this.panel_30);

		this.textField_Domicilio = new JTextField();
		this.textField_Domicilio.addKeyListener(this);
		this.textField_Domicilio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_30.add(this.textField_Domicilio);
		this.textField_Domicilio.setColumns(13);

		this.lblNewLabel_19 = new JLabel("");
		this.panel_24.add(this.lblNewLabel_19);

		this.lblNewLabel_20 = new JLabel("");
		this.panel_24.add(this.lblNewLabel_20);

		this.panel_31 = new JPanel();
		FlowLayout flowLayout_16 = (FlowLayout) this.panel_31.getLayout();
		flowLayout_16.setHgap(10);
		flowLayout_16.setAlignment(FlowLayout.RIGHT);
		flowLayout_16.setVgap(20);
		this.panel_24.add(this.panel_31);

		this.btn_Agregar_Contratacion = new JButton("Agregar Contratacion");
		this.btn_Agregar_Contratacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.panel_31.add(this.btn_Agregar_Contratacion);
		this.btn_Agregar_Contratacion.setActionCommand("AGREGACONTRATACION");
		this.btn_Agregar_Contratacion.setPreferredSize(new Dimension(165, 40));
		this.btn_Agregar_Contratacion.setEnabled(false);

		this.panel_32 = new JPanel();
		FlowLayout flowLayout_17 = (FlowLayout) this.panel_32.getLayout();
		flowLayout_17.setAlignment(FlowLayout.RIGHT);
		flowLayout_17.setHgap(20);
		flowLayout_17.setVgap(20);
		this.panel_24.add(this.panel_32);

		this.btn_Eliminar_Contratacion = new JButton("Eliminar Contratacion");
		this.btn_Eliminar_Contratacion.addMouseListener(this);
		this.btn_Eliminar_Contratacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.panel_32.add(this.btn_Eliminar_Contratacion);
		this.btn_Eliminar_Contratacion.setActionCommand("ELIMINACONTRATACION");
		this.btn_Eliminar_Contratacion.setPreferredSize(new Dimension(165, 40));
		this.btn_Eliminar_Contratacion.setEnabled(false);

		this.panel_Info_Contratacion = new JPanel();
		this.panel_Info_Sur.add(this.panel_Info_Contratacion, BorderLayout.SOUTH);
		this.panel_Info_Contratacion.setLayout(new BorderLayout(0, 0));
		this.panel_Info_Contratacion.setPreferredSize(new Dimension(50, 60)); // Prefered size

		this.textContratacion = new JTextField();
		this.textContratacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_Info_Contratacion.add(this.textContratacion);
		this.textContratacion.setColumns(10);
	}

	/**
	 * Metodo que settea los action listeners de la ventana.
	 * 
	 * @param a Es el Controlador.
	 */
	public void addActionListener(ActionListener a) {
		this.btn_Eliminar_Contratacion.addActionListener(a);
		this.btn_Agregar_Contratacion.addActionListener(a);
		this.btnAgregarTitular.addActionListener(a);
		this.btnEliminarTitular.addActionListener(a);
		this.btnEPT.addActionListener(a);
	}

	/**
	 *Metodo que settea los mouse listeners de la ventana.
	 * 
	 * @param m Es el Controlador.
	 */
	public void addMouseListener(MouseListener m) {
		this.list_Facturas.addMouseListener(m);
	}

	public DefaultListModel getModelTitulares() {
		return this.modelTitulares;
	}

	public JList getListTitulares() {
		return this.listTitulares;
	}

	public JList getListaContrataciones() {
		return this.list_Contrataciones;
	}

	public JList getListaFacturas() {
		return this.list_Facturas;
	}
	
	public String getDireccion() {
		return this.textField_Domicilio.getText();
	}

	public String getInternet() {
		String rta = null;
		if (this.rdbtn_Internet100.isSelected())
			rta = "Internet100";
		else
			rta = "Internet500";
		return rta;
	}

	public boolean getCelular() {
		return this.rdbtn_Celular.isSelected();
	}

	public boolean getTelFijo() {
		return this.rdbtn_Tel_Fijo.isSelected();
	}

	public boolean getTV() {
		return this.rdbtn_TV_Cable.isSelected();
	}
	
	/**
	 * Metodo que produce una "transicion" al seleccionar un titular a la ventana completa.
	 */
	public void expandeVentana() {
		setSize(1500, 900);
		this.panelPPALDerecha.setVisible(true);
	}
	
	/**
	 * Metodo que vuelve el tamaño de la ventana al principal.
	 */
	private void contraeVentana() {
		this.setSize(new Dimension(600,900));
		this.panelPPALDerecha.setVisible(false);
	}
	
	/**Este metodo refresca la informacion que se visualiza en la lista de facturas respecto a un titular, o en
	 * caso de no haber ningun titular seleccionado, limpia la lista
	 * 
	 * @param t es el titular al cual pertenecen las facturas mostradas en la lista
	 */
	public void refreshFacturas(Titular t) {
		this.modelFacturas.clear();
		if (t != null) {
			Iterator<Factura> it = t.getFacturas().iterator();
			while (it.hasNext()) {
				Factura f = it.next();
				this.modelFacturas.addElement(f);
			}
		}

		this.list_Facturas.repaint();
	}

	/**Este metodo refresca la informacion que se visualiza en la lista de contrataciones respecto a un titular, o en
	 * caso de no haber ningun titular seleccionado, limpia la lista.
	 * 
	 * @param t es el titular al cual pertenecen las contrataciones mostradas en la lista.
	 */
	public void refreshContrataciones(Titular t) {
		this.modelContrataciones.clear();
		if (t != null) {
			Iterator<IContratacion> it = t.getContrataciones().iterator();
			while (it.hasNext()) {
				IContratacion c = it.next();
				this.modelContrataciones.addElement(c);
			}

		}
		this.list_Contrataciones.repaint();
		this.textContratacion.setText("");
		this.textField_Domicilio.setText("");
		this.rdbtn_Celular.setSelected(false);
		this.rdbtn_Internet100.setSelected(false);
		this.rdbtn_Internet500.setSelected(false);
		this.rdbtn_Tel_Fijo.setSelected(false);
		this.rdbtn_TV_Cable.setSelected(false);
	}

	/**Este metodo permite refrescar la informacion que se visualiza en la ventana principal acerca del titular seleccionado
	 * en el momento del llamado al metodo,o en caso de no haber ninguno seleccionado, setea la visualizacion en algun valor por defecto
	 * como puede ser vacio.
	 */
	public void actualizarInfo() {
		Titular t = (Titular) this.listTitulares.getSelectedValue();
		if (t != null) {
			this.lbl_NroID.setText(String.valueOf(t.getNroIdentacion()));
			this.lbl_Nombre.setText(t.getNombre());
			this.lbl_dni.setText(String.valueOf(t.getDni()));
			this.lbl_Forma_de_Pago.setText(t.getFormaDePago());
			this.lbl_Tipo.setText(t.getTipo());
			this.lbl_Estado.setText(t.getEstado());
			this.lblFecha.setText(EPT.getInstance().printFecha());
			refreshFacturas(t);
			refreshContrataciones(t);
		} else {
			this.lbl_NroID.setText("");
			this.lbl_Nombre.setText("");
			this.lbl_dni.setText("");
			this.lbl_Forma_de_Pago.setText("");
			this.lbl_Tipo.setText("");
			this.lbl_Estado.setText("");
			this.lblFecha.setText("");
			refreshFacturas(t);
			refreshContrataciones(t);
		}
		
		this.btn_Agregar_Contratacion.setEnabled(false);
		this.btn_Eliminar_Contratacion.setEnabled(false);
		
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
		if (arg0.getSource() == this.listTitulares) {
			if( !this.listTitulares.isSelectionEmpty() ) {
				expandeVentana();
				actualizarInfo();
			}else {
				contraeVentana();
			}
			
			if( !this.listTitulares.isSelectionEmpty() ) {
				this.btnEliminarTitular.setEnabled(true);
			}else {
				this.btnEliminarTitular.setEnabled(false);
			}
		} else if (arg0.getSource() == this.list_Contrataciones) {
			if( !this.list_Contrataciones.isSelectionEmpty() )
				this.btn_Eliminar_Contratacion.setEnabled(true);
			else
				this.btn_Eliminar_Contratacion.setEnabled(false);
			IContratacion c = (IContratacion) this.list_Contrataciones.getSelectedValue();
			if (c != null)
				this.textContratacion.setText(
						"   " + c.getDomicilio() + ": " + c.getDetalle() + " - $" + String.valueOf(c.getCosto()));
		}else if( arg0.getSource() == this.rdbtn_Internet100 || arg0.getSource() == this.rdbtn_Internet500) {
			if( !this.textField_Domicilio.getText().equals("") ) {
				this.btn_Agregar_Contratacion.setEnabled(true);
			}
		}else if( arg0.getSource() == this.btnEliminarTitular ) {
			this.btnEliminarTitular.setEnabled(false);
			this.contraeVentana();
		}
	
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		if( !this.listTitulares.isSelectionEmpty() && (this.rdbtn_Internet100.isSelected() || this.rdbtn_Internet500.isSelected()) && !this.textField_Domicilio.getText().equals("") ) {
			this.btn_Agregar_Contratacion.setEnabled(true);
		}else {
			this.btn_Agregar_Contratacion.setEnabled(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
