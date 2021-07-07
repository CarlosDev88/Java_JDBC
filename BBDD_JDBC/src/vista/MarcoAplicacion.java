package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controlador.BotonEjecuta;
import controlador.ControladorCargaMenus;

public class MarcoAplicacion extends JFrame{
	
	public JComboBox secciones;
	public JComboBox paises;
	public JTextArea resultado;
	
	public MarcoAplicacion() {
		this.setTitle("Consultas a BBDD");
		this.setBounds(250, 100, 400, 400);
		this.setLayout(new BorderLayout());

		JPanel menus = new JPanel();

		menus.setLayout(new FlowLayout());

		secciones = new JComboBox();
		secciones.setEditable(false);
		secciones.addItem("Secciones");

		paises = new JComboBox();
		paises.setEditable(false);
		paises.addItem("Paises");

		resultado = new JTextArea(4, 50);
		resultado.setEditable(false);

		this.add(resultado);

		menus.add(secciones);
		menus.add(paises);

		this.add(menus, BorderLayout.NORTH);
		this.add(resultado, BorderLayout.CENTER);

		JButton botonConsulta = new JButton("Consulta");
		this.add(botonConsulta, BorderLayout.SOUTH);
		
		botonConsulta.addActionListener(new BotonEjecuta(this));
		
		this.addWindowListener(new ControladorCargaMenus(this));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
