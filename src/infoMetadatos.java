import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class infoMetadatos {

	public static void main(String[] args) {
		mostrarDatos();
		mostrarInfoTabla();
	}

	static void mostrarDatos() {
		Connection miConexion = null;
		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827",
					"sql7623827", "akYVms4umE");
			// Guardar los metadatos de la coneccion
			DatabaseMetaData datosBBDD = miConexion.getMetaData();
			// mostrar metadatos en consola
			System.out.println("gestor de base de datos " + datosBBDD.getDatabaseProductName());

			System.out.println("version del gestor de base de datos " + datosBBDD.getDatabaseProductVersion());

			System.out.println("nombre del driver " + datosBBDD.getDriverName());

			System.out.println("version del driver " + datosBBDD.getDriverVersion());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				miConexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static void mostrarInfoTabla() {
		Connection miConexion = null;
		ResultSet rs = null;
		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827",
					"sql7623827", "akYVms4umE");
			// Guardar los metadatos de la coneccion
			DatabaseMetaData datosBBDD = miConexion.getMetaData();

			// Lista de tablas que hay en mi base de datos

			rs = datosBBDD.getTables(null, null, null, null);

			while (rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));

			}
			System.out.println("\n");
			rs = datosBBDD.getColumns(null, null, "productos", null);

			while (rs.next()) {
				System.out.println(rs.getString("COLUMN_NAME"));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				miConexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
