package aplicacionFinal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private FileReader entrada;

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

	public void sacaColumnas(String tabla, deberia recivir un archivo de coneccion que es connectaBBDD(), reemplazar arriba y abajo) {
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

					areaInformacion.append(rs.getString(x) + " ");
				}
				areaInformacion.append("\n");
			}
		} catch (Exception e) {

		}

	}

	public Connection conectarBBDD() {
		Connection miConexion = null;
		String[] datos = new String[4];
		String path = System.getProperty("user.dir");

		try {
			entrada = new FileReader(path + "/src/aplicacionFinal/patdh.txt");
		} catch (IOException e1) {

			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("config file", "txt");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					entrada = new FileReader(chooser.getSelectedFile().getAbsolutePath());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			BufferedReader buffer = new BufferedReader(entrada);
			for (int i = 0; i <= 3; i++) {
				datos[i] = buffer.readLine();
			}

			miConexion = DriverManager.getConnection(datos[0], datos[1], datos[2]);
			entrada.close();
		} catch (Exception e3) {
		}

		return miConexion;

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