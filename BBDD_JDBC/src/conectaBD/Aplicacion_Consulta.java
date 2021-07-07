package conectaBD;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Aplicacion_Consulta {

	public static void main(String[] args) {
		JFrame mimarco = new Marco_Aplicacion();
	}
}

class Marco_Aplicacion extends JFrame {
	private JComboBox secciones;
	private JComboBox paises;
	private JTextArea resultado;
	private PreparedStatement enviaConsultaSeccion;
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaTodos;
	private Connection miConexion;
	private final String consultaSeccion = "SELECT NOMBREARTICULO, SECCION, PRECIO, PAISDEORIGEN FROM PRODUCTOS WHERE SECCION=?";
	private final String consultaPaisOrigen = "SELECT NOMBREARTICULO, SECCION, PRECIO, PAISDEORIGEN FROM PRODUCTOS WHERE PAISDEORIGEN=?";
	private final String consultaTodos = "SELECT NOMBREARTICULO, SECCION, PRECIO, PAISDEORIGEN FROM PRODUCTOS WHERE SECCION=? AND PAISDEORIGEN=?";

	public Marco_Aplicacion() {

		this.setTitle("Consulta BBDD");
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
		botonConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutaConsulta();
			}
		});
		this.add(botonConsulta, BorderLayout.SOUTH);

		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen", "root", "");
			Statement sentencia = miConexion.createStatement();

			String consultaSecciones = "SELECT DISTINCTROW SECCION FROM PRODUCTOS";
			ResultSet rs = sentencia.executeQuery(consultaSecciones);

			while (rs.next()) {
				secciones.addItem(rs.getString(1));
			}
			rs.close();

			String consultapaises = "SELECT DISTINCTROW PAISDEORIGEN FROM PRODUCTOS";
			rs = sentencia.executeQuery(consultapaises);

			while (rs.next()) {
				paises.addItem(rs.getString(1));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	private void ejecutaConsulta() {
		ResultSet rs = null;

		try {
			resultado.setText("");
			String seccion = (String) secciones.getSelectedItem();
			String pais = (String) paises.getSelectedItem();

			if (!seccion.equals("Secciones") && pais.equals("Paises")) {

				enviaConsultaSeccion = miConexion.prepareStatement(consultaSeccion);
				enviaConsultaSeccion.setString(1, seccion);
				rs = enviaConsultaSeccion.executeQuery();

			} else if (seccion.equals("Secciones") && !pais.equals("Paises")) {
				enviaConsultaPais = miConexion.prepareStatement(consultaPaisOrigen);
				enviaConsultaPais.setString(1, pais);
				rs = enviaConsultaPais.executeQuery();

			} else if (!seccion.equals("Secciones") && !pais.equals("Paises")) {
				enviaConsultaTodos = miConexion.prepareStatement(consultaTodos);
				enviaConsultaTodos.setString(1, seccion);
				enviaConsultaTodos.setString(2, pais);
				rs = enviaConsultaTodos.executeQuery();
			}

			while (rs.next()) {
				resultado.append(rs.getString(1));
				resultado.append(", ");
				resultado.append(rs.getString(2));
				resultado.append(", ");
				resultado.append(rs.getString(3));
				resultado.append(", ");
				resultado.append(rs.getString(4));
				resultado.append("\n");
			}

		} catch (Exception e) {

		}
	}

}
