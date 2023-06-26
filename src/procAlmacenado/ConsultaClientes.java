package procAlmacenado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConsultaClientes {

	public static void main(String[] args) {

		try {

			Connection miConeccion = DriverManager.getConnection(
					"jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827", "sql7623827", "akYVms4umE");
			CallableStatement miSentencia = miConeccion.prepareCall("{call MUESTRA_CLIENTES}");
			ResultSet rs = miSentencia.executeQuery();
			while (rs.next()) {
				System.out.println(
						rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
			}
			rs.close();

		} catch (Exception e) {
		}

	}

}
