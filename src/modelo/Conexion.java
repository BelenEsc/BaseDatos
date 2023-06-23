package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	Connection miConex=null;

	public Conexion() {
	}

	public Connection dameConexion() {
		try {
			miConex = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827",
					"sql7623827", "akYVms4umE");
		} catch (Exception e) {

			e.getMessage();
		}
		return miConex;
	}
}
