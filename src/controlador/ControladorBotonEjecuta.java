package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.EjecutaConsultas;
import vista.MarcoAplicacion;

public class ControladorBotonEjecuta implements ActionListener {
	MarcoAplicacion cajaTemp;
	ResultSet filtrado;
	EjecutaConsultas temp = new EjecutaConsultas();

	public ControladorBotonEjecuta(MarcoAplicacion elMarco) {
		cajaTemp = elMarco;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sexion = cajaTemp.caja1.getSelectedItem().toString();
		String paix = cajaTemp.caja2.getSelectedItem().toString();
		filtrado = temp.filtraBBDD(sexion, paix);

		try {
			cajaTemp.texto.setText("");
			while (filtrado.next()) {
				cajaTemp.texto.append(filtrado.getString(1));
				cajaTemp.texto.append(", ");
				cajaTemp.texto.append(filtrado.getString(2));
				cajaTemp.texto.append(", ");
				cajaTemp.texto.append(filtrado.getString(3));
				cajaTemp.texto.append(", ");
				cajaTemp.texto.append(filtrado.getString(4));
				cajaTemp.texto.append(", ");
				cajaTemp.texto.append(filtrado.getString(5));
				cajaTemp.texto.append("\n");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
