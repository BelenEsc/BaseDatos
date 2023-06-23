package controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import modelo.CargarCombos;
import vista.MarcoAplicacion;

public class ControladorCargaCombo extends WindowAdapter {
	MarcoAplicacion marcoAplic;
	CargarCombos cargaComb = new CargarCombos();

	public ControladorCargaCombo(MarcoAplicacion marcoAplic) {
		this.marcoAplic = marcoAplic;

	}

	@Override
	public void windowOpened(WindowEvent e) {
		cargaComb.ejecutaConsultas();

		try {
			while (cargaComb.rs.next()) {
				marcoAplic.caja1.addItem(cargaComb.rs.getString(1));

			}
			
			while (cargaComb.rs2.next()) {
				marcoAplic.caja2.addItem(cargaComb.rs2.getString(1));

			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
