package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModificaBBDD {

	public static void main(String[] args) {

		try {

			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen", "root", "");
			Statement miStatement = miConexion.createStatement();

			String consulta = "DELETE FROM productos WHERE CODIGOARTICULO = 'AR77'";
			miStatement.executeUpdate(consulta);
			
			System.out.println("datos BORRADOS correctamente");

		} catch (SQLException e) {
			System.out.println("error de conexion en la base de datos");
			e.printStackTrace();
		}

	}

}
