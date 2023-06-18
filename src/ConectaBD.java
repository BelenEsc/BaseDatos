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

			int resultadoAdd = miStatement
					.executeUpdate("DELETE FROM `sql7623827`.`tabla` WHERE  `nombre`='Rod2' AND `edad`=80 LIMIT 1;");

			ResultSet resultado = miStatement.executeQuery("SELECT * FROM tabla;");
			// leer los resultados

			while (resultado.next()) {
//				String nombre = resultado.getString("nombre");
//				if (nombre.contains("ä")) {
//					String nuevoNombre = nombre.replace("ä", "a");
//					int resultadoUpdate = miStatement.executeUpdate(
//							"UPDATE tabla SET nombre = '" + nuevoNombre + "' WHERE nombre = '" + nombre + "'");
//					if (resultadoUpdate > 0) {
//						System.out.println("Reemplazo exitoso en la fila afectada: " + resultadoUpdate);
//					}
//				} 
				System.out.println(resultado.getString(1)+" " + resultado.getString("nombre")); //aqui se puede poner o inidice o nombre de la columna pero identico a la base columna 1 = nombre
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}

}
