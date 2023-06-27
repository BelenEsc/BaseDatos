package aplicacionFinal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.sql.ResultSetMetaData;

public class AplicacionUniversal {

	public static void main(String[] args) {
		Marco marco = new Marco();
		marco.setDefaultCloseOperation(3);
		marco.setVisible(true);
	}

}

class Marco extends JFrame {

	public Marco() {

		setTitle("Aplicacion universal");
		setBounds(50, 50, 500, 500);
		Lamina lamina = new Lamina();
		add(lamina);
	}

}

class Lamina extends JPanel {

	private JComboBox<String> comboTabla;
	private JTextArea areaInformacion;

	public Lamina() {
		setLayout(new BorderLayout());
		comboTabla = new JComboBox<String>();
		obtenerTablas();
		comboTabla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreTabla = comboTabla.getSelectedItem().toString();
				sacaColumnas(nombreTabla);
			}
		});

		areaInformacion = new JTextArea();
		add(comboTabla, BorderLayout.NORTH);
		add(areaInformacion, BorderLayout.CENTER);
	}

	public void sacaColumnas(String tabla) {
		ArrayList<String> campos = new ArrayList<>();

		String consulta = "SELECT * FROM " + tabla;

		try {
			areaInformacion.setText("");
			Statement miStatement = conectarBBDD().createStatement();

			ResultSet rs = miStatement.executeQuery(consulta);
			ResultSetMetaData DBMD = rs.getMetaData();

			for (int i = 1; i <= DBMD.getColumnCount(); i++) {

				campos.add(DBMD.getColumnLabel(i));

			}
			while (rs.next()) {
				for (String x : campos) {

					areaInformacion.append(rs.getString(x) +" ");
				}
				areaInformacion.append("\n");
			}
		} catch (Exception e) {

		}

	}

	public Connection conectarBBDD() {

		try {
			Connection miConexion = DriverManager.getConnection(
					"jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827", "sql7623827", "akYVms4umE");
			return miConexion;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	public void obtenerTablas() {

		try {
			ResultSet rs = conectarBBDD().getMetaData().getTables(null, null, null, null);

			while (rs.next()) {
				comboTabla.addItem(rs.getString("TABLE_NAME"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}