package modelo;

import java.sql.*;

public class EjecutaConsultas {

	private Conexion miConexion;
	private ResultSet rs;
	private PreparedStatement enviaConsultaSeccion;
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaTodos;
	private final String consultaSeccion = "SELECT NOMBREARTICULO, SECCION, PRECIO, PAISDEORIGEN FROM PRODUCTOS WHERE SECCION=?";
	private final String consultaPaisOrigen = "SELECT NOMBREARTICULO, SECCION, PRECIO, PAISDEORIGEN FROM PRODUCTOS WHERE PAISDEORIGEN=?";
	private final String consultaTodos = "SELECT NOMBREARTICULO, SECCION, PRECIO, PAISDEORIGEN FROM PRODUCTOS WHERE SECCION=? AND PAISDEORIGEN=?";

	public EjecutaConsultas() {
		miConexion = new Conexion();
	}

	public ResultSet filtraBBDD(String seccion, String pais) {
		Connection conecta = miConexion.dameConexion();
		rs = null;

		try {

			if (!seccion.equals("Secciones") && pais.equals("Paises")) {
				enviaConsultaSeccion = conecta.prepareStatement(consultaSeccion);
				enviaConsultaSeccion.setString(1, seccion);
				rs = enviaConsultaSeccion.executeQuery();

			} else if (seccion.equals("Secciones") && !pais.equals("Paises")) {
				enviaConsultaPais = conecta.prepareStatement(consultaPaisOrigen);
				enviaConsultaPais.setString(1, pais);
				rs = enviaConsultaPais.executeQuery();

			} else {
				enviaConsultaTodos = conecta.prepareStatement(consultaTodos);
				enviaConsultaTodos.setString(1, seccion);
				enviaConsultaTodos.setString(2, pais);
				rs = enviaConsultaTodos.executeQuery();

			}

		} catch (Exception e) {

		}

		return rs;
	}

}
