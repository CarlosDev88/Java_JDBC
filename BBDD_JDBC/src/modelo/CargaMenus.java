package modelo;

import java.sql.*;

//import controlador.Conexion;

public class CargaMenus {
	public Conexion miConexion;
	public ResultSet rs;
	public ResultSet rs2;

	public CargaMenus() {
		miConexion = new Conexion();
	}

	public void ejecutaConsultas() {
		Productos miProducto ;
		Connection accesoBBDD = miConexion.dameConexion();

		try {
			Statement secciones = accesoBBDD.createStatement();
			Statement paises = accesoBBDD.createStatement();
			
			rs = secciones.executeQuery("SELECT DISTINCTROW SECCION FROM productos");
			rs2 = paises.executeQuery("SELECT DISTINCTROW PAISDEORIGEN FROM productos");
			
			miProducto = new Productos();
			miProducto.setSeccion(rs.getString(1));
			miProducto.setPaisOrigen(rs2.getString(1));

			rs.close();
			rs2.close();
			accesoBBDD.close();

		} catch (Exception e) {
			// e.printStackTrace();
			//System.out.println("aca hay problemas");
		}
		
	}

}
