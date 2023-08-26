package ConversorGeneral;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Conversor {

	private JFrame frmConversor;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JButton btnConvertir;
	private JTextField tf_cantidad1;
	private JLabel lblResultado;
	private JRadioButton rdbtnDivisas;
	private JRadioButton rdbtnTemperatura;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public enum Moneda{
		Pesos(1),
		Dolar(730),
		Euro(793),
		Libra(441.45),
		Yen(2.39),
		Won(0.26);

	    private double valor;
	    Moneda(double valor) {
	        this.valor = valor;
	    }
	}	
	public enum Temperatura{
		Celsius(-273.15, 100, 373.15),
		Kelvin(0, -373.15, 373.15),
		Fahrenheit(-459.67, 212, 671.67),
		Rankine(0, 671.67, 671.67),
		Reaumur(-218.52, 80, 298.52);

	    private double valorMin, valorMax, valorAcom;
	    Temperatura(double valormin, double valormax, double valoracom) {
	        this.valorMin = valormin;
	        this.valorMax = valormax;
	        this.valorAcom = valoracom;
	    }
	}
		
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversor window = new Conversor();
					window.frmConversor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Conversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmConversor = new JFrame();
		frmConversor.setTitle("Conversor");
		frmConversor.setBounds(100, 100, 400, 280);
		frmConversor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConversor.getContentPane().setLayout(null);
		
		tf_cantidad1 = new JTextField();
		tf_cantidad1.setBounds(149, 136, 86, 20);
		frmConversor.getContentPane().add(tf_cantidad1);
		tf_cantidad1.setColumns(10);
			
		btnConvertir = new JButton("Convertir");
		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btnConvertir.setBounds(149, 167, 86, 20);
		frmConversor.getContentPane().add(btnConvertir);
		
		JLabel lblTitulo = new JLabel("Seleccione la Divisa a convertir");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTitulo.setBounds(87, 53, 210, 16);
		frmConversor.getContentPane().add(lblTitulo);
		
		JLabel lblFlecha = new JLabel(">>>");
		lblFlecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFlecha.setBounds(169, 90, 46, 14);
		frmConversor.getContentPane().add(lblFlecha);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidad.setBounds(156, 118, 71, 14);
		frmConversor.getContentPane().add(lblCantidad);
			
		lblResultado = new JLabel("Resultado");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResultado.setBounds(71, 198, 242, 20);
		frmConversor.getContentPane().add(lblResultado);	
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBackground(new Color(255, 255, 255));
		comboBox_1.setModel(new DefaultComboBoxModel(Moneda.values()));
		comboBox_1.setBounds(70, 86, 86, 22);
		frmConversor.getContentPane().add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBackground(new Color(255, 255, 255));
		comboBox_2.setModel(new DefaultComboBoxModel(Moneda.values()));
		comboBox_2.setBounds(226, 86, 86, 22);
		frmConversor.getContentPane().add(comboBox_2);		 
		
		rdbtnDivisas = new JRadioButton("Divisas");
		rdbtnDivisas.setSelected(true);
		buttonGroup.add(rdbtnDivisas);
		rdbtnDivisas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnDivisas.setBounds(79, 19, 59, 23);
		frmConversor.getContentPane().add(rdbtnDivisas);
		rdbtnDivisas.addActionListener(new ActionListener() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				comboBox_1.setModel(new DefaultComboBoxModel(Moneda.values()));				
				comboBox_2.setModel(new DefaultComboBoxModel(Moneda.values()));
				lblTitulo.setText("Seleccione la Divisa a convertir.");
			}
		});	
		
		rdbtnTemperatura = new JRadioButton("Temperatura");
		buttonGroup.add(rdbtnTemperatura);
		rdbtnTemperatura.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnTemperatura.setBounds(216, 19, 97, 23);
		frmConversor.getContentPane().add(rdbtnTemperatura);
		rdbtnTemperatura.addActionListener(new ActionListener() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox_1.setModel(new DefaultComboBoxModel(Temperatura.values()));				
				comboBox_2.setModel(new DefaultComboBoxModel(Temperatura.values()));
				lblTitulo.setText("Seleccione la Temperatura a convertir.");
			}
		});	
		
	}
	
	// crear la funcion que convierta el valor en la unidad deseada
	private void Convertir() {	
		
		String cantidad_text = tf_cantidad1.getText();
		double cantidad_double = 123987;
		double resultado = 0;
		
        try {
        	// Convertir el string "cantidad" en double
        	cantidad_double = Double.parseDouble(cantidad_text);
            System.out.println("El número double es: " + cantidad_text);
        } catch (NumberFormatException e) {
            System.err.println("El formato del número no es válido.");            
        }        
        		
        if (cantidad_double != 123987) {
        	if (rdbtnDivisas.isSelected()) {		
    			Moneda moneda1 = (Moneda) comboBox_1.getSelectedItem();
    			Moneda moneda2 = (Moneda) comboBox_2.getSelectedItem();
    			resultado = cantidad_double * moneda1.valor / moneda2.valor;
    		}

    		else if (rdbtnTemperatura.isSelected()) {
    			Temperatura temperatura1 = (Temperatura) comboBox_1.getSelectedItem();
    			Temperatura temperatura2 = (Temperatura) comboBox_2.getSelectedItem();
    			resultado = (cantidad_double - temperatura1.valorMin) * (temperatura2.valorAcom / temperatura1.valorAcom) + temperatura2.valorMin;
    		}	        
	        DecimalFormat df = new DecimalFormat("#.######");
	        String valorRedondeado = df.format(resultado);	        
	        lblResultado.setText(valorRedondeado);	        
        }
        else {
        	lblResultado.setText("Introduzca un valor numerico");
        }
		
	} 	
	
}