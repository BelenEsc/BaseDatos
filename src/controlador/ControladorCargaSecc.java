package controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import modelo.CargarCombos;
import vista.MarcoAplicacion;

public class ControladorCargaSecc extends WindowAdapter {
	MarcoAplicacion marcoAplic;
	CargarCombos cargaComb = new CargarCombos();

	public ControladorCargaSecc(MarcoAplicacion marcoAplic) {
	this.marcoAplic=marcoAplic;

	}

	@Override
	public void windowOpened(WindowEvent e) {
cargaComb.ejecutaConsultas();

try {
	while (cargaComb.rs.next()) {
	marcoAplic.caja1.addItem(cargaComb.rs.getString(1));
	
	
	
	}
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
	
	}

}
