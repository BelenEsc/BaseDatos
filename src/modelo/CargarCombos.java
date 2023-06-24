package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CargarCombos {

	Conexion conex1;
	public ResultSet rs;
	public ResultSet rs2;

	public CargarCombos() {
		conex1 = new Conexion();

	}

	public String ejecutaConsultas() {
		Productos miProducto = null;
		Connection accesoBD = conex1.dameConexion();
		try {
			Statement statement = accesoBD.createStatement();
			Statement statement2 = accesoBD.createStatement();
			rs = statement.executeQuery("SELECT DISTINCTROW seccion FROM productos");
			rs2 = statement2.executeQuery("SELECT DISTINCTROW paisdeorigen FROM productos");
			miProducto = new Productos();
			miProducto.setSeccion(rs.getString(1));
			miProducto.setPaisOrigen(rs2.getString(1));
			rs.close();
			rs2.close();
			accesoBD.close();
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return miProducto.getSeccion();
	}

}
