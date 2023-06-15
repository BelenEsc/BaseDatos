import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConectaBD {

	public static void main(String[] args) {

		try {

			// primero se crea coneccion

			Connection miConexion = DriverManager.getConnection(
					"jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827", "sql7623827", "akYVms4umE");
			System.out.println("si");
			// crear un statement

			Statement miStatement = miConexion.createStatement();

			// ejecutar SQL

			ResultSet resultado = miStatement.executeQuery("SELECT * FROM tabla;");

			// leer los resultados

			while (resultado.next()) {
				System.out.println(resultado.getString("nombre"));

			}
		} catch (Exception e) {
		}

	}

}
