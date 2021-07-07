package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaPreparada {

	public static void main(String[] args) {

		try {

			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen", "root", "");
			Statement miStatement = miConexion.createStatement();

			String consulta = "SELECT * FROM PRODUCTOS WHERE SECCION=? AND PAISDEORIGEN=?";

			PreparedStatement sentencia = miConexion.prepareStatement(consulta);
			sentencia.setString(1, "Deportes");
			sentencia.setString(2, "USA");

			ResultSet rs = sentencia.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | "
						+ rs.getString(3) + " | " + rs.getDouble(4) + " | " + rs.getDate(5));				
			}
			
			rs.close();

		} catch (SQLException e) {
			System.out.println("error de conexion en la base de datos");
			e.printStackTrace();
		}

	}

}
