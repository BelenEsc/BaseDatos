package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EjecutaConsultas {

	private Conexion miConeccion;
	private PreparedStatement enviaConsultaSeccion;
	private ResultSet rs;
	private final String consultaSeccion = "SELECT * FROM productos WHERE seccion=?";
	private final String consultaPais = "SELECT * FROM productos WHERE paisdeorigen=?";
	private final String consultaAmbos = "SELECT * FROM productos WHERE seccion =? AND paisdeorigen=?";

//	private String pruebas;
	public EjecutaConsultas() {
		miConeccion = new Conexion();

	}

	public ResultSet filtraBBDD(String seccion, String pais) {
		Connection conecta = miConeccion.dameConexion();
		rs = null;
		try {
			if (!seccion.equals("Todos") && pais.equals("Todos")) {
				enviaConsultaSeccion = conecta.prepareStatement(consultaSeccion);
				enviaConsultaSeccion.setString(1, seccion);
				rs = enviaConsultaSeccion.executeQuery();

			} else if (seccion.equals("Todos") && !pais.equals("Todos")) {
				enviaConsultaSeccion = conecta.prepareStatement(consultaPais);
				enviaConsultaSeccion.setString(1, pais);
				rs = enviaConsultaSeccion.executeQuery();

			} else {
				enviaConsultaSeccion = conecta.prepareStatement(consultaAmbos);
				enviaConsultaSeccion.setString(1, seccion);
				enviaConsultaSeccion.setString(2, pais);
				rs = enviaConsultaSeccion.executeQuery();

			}

		} catch (Exception e) {
		}
		return rs;
	}

}
