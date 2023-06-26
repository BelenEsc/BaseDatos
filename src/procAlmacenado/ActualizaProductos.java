package procAlmacenado;

import java.sql.*;

import javax.swing.JOptionPane;

public class ActualizaProductos {

	public static void main(String[] args) {

		int nPrecio = Integer.parseInt(JOptionPane.showInputDialog("pon un precio"));
		String nArticulo = JOptionPane.showInputDialog("pon un articulo");

		try {
			Connection miConeccion = DriverManager.getConnection(
					"jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827", "sql7623827", "akYVms4umE");
			CallableStatement miSentencia = miConeccion.prepareCall("{call ACTUALIZA_PROD(?, ?)}");
			miSentencia.setInt(1, nPrecio);
			miSentencia.setString(2, nArticulo);
			miSentencia.execute();
		} catch (Exception e) {
			
			
			
			
		}

	}

}
