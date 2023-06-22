package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.Conexion;

public class CargarCombos {

	Conexion conex1;
	public ResultSet rs;

	public CargarCombos() {
		conex1 = new Conexion();

	}

	public String ejecutaConsultas() {
		Productos miProducto = null;
		Connection accesoBD = conex1.dameConexion();
		String nombre = null;
		try {
			Statement statement = accesoBD.createStatement();
			rs = statement.executeQuery("SELECT DISTINCTROW seccion FROM productos");
			while (rs.next()) {
				nombre = rs.getString(1);
				miProducto = new Productos();
				miProducto.setSeccion(nombre);
				return miProducto.getSeccion();
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return miProducto.getSeccion();
	} 

}
