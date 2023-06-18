import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Morificaciones {

	public static void main(String[] args) {
		try {

			// primero se crea coneccion

			Connection miConexion = DriverManager.getConnection(
					"jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827", "sql7623827", "akYVms4umE");
			System.out.println("si");
			// crear un statement

			Statement miStatement = miConexion.createStatement();

			// ejecutar SQL

			miStatement.executeUpdate("INSERT INTO tabla (nombre,edad) VALUES ('Carlota',33)");
//			miStatement.executeUpdate("UPDATE tabla SET edad = edad*2 WHERE nombre ='Pepa'");
			miStatement.executeUpdate("DELETE FROM tabla WHERE edad = 768");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
