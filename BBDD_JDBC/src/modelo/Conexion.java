package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexion {
	private Connection miConexion;

	public Conexion() {

	}

	public Connection dameConexion() {
		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("aca hay problemas");
		}

		return miConexion;
	}
}
