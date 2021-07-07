package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectaPruebas {

	public static void main(String[] args) {

		try {
			// crear la coneccion
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/curso_sql", "root", "");

			// crear objeto statement
			Statement miStatement = miConexion.createStatement();

			// ejecutar la instruccion sql
			ResultSet miResulset = miStatement.executeQuery("SELECT * FROM productos");

			// recorrer el resulset
			while (miResulset.next()) {
				System.out.println(miResulset.getString(1) + " | " + miResulset.getString(2) + " | "
						+ miResulset.getString(3) + " | " + miResulset.getDouble(4)*2 + " | " + miResulset.getDate(5));
				
			}

		} catch (SQLException e) {
			System.out.println("error de conexion en la base de datos");
			e.printStackTrace();
		}

	}

}
