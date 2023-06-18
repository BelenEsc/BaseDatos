package aplicacion;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AccesoBD {

	public static void main(String[] args) {

		Marco marco = new Marco();
		marco.setDefaultCloseOperation(3);
		marco.setVisible(true);

	}
}

class Marco extends JFrame {

	public Marco() {

		setTitle("Consulta BBDD");
		setBounds(50, 50, 500, 500);

		setLayout(new BorderLayout());

		Lamina lamina = new Lamina();
		add(lamina, BorderLayout.NORTH);
		JTextArea areaMostrar = new JTextArea();
		add(areaMostrar, BorderLayout.CENTER);
		JButton buscar = new JButton("Buscar");
		add(buscar, BorderLayout.SOUTH);

	}
}

class Lamina extends JPanel {

	public Lamina() {
		JComboBox<String> caja1 = new JComboBox<String>();
		caja1.addItem("todos");

		JComboBox<String> caja2 = new JComboBox<String>();
		caja2.addItem("Todos");

		add(caja1);
		add(caja2);

		// conectar a la base de datos
		try {
			Connection miConexion = DriverManager.getConnection(
					"jdbc:mysql://sql7.freemysqlhosting.net:3306/information_schema", "sql7623827", "akYVms4umE");

			Statement miSentencia = miConexion.createStatement();

			String busca1 = "SELECT DISTINCTROW ROW_FORMAT FROM TABLES";
			ResultSet resultado = miSentencia.executeQuery(busca1);

			while (resultado.next()) {
				caja1.addItem(resultado.getString(1));
			}
			resultado.close();

			miSentencia = miConexion.createStatement();

			busca1 = "SELECT DISTINCTROW TABLE_NAME FROM TABLES";
			resultado = miSentencia.executeQuery(busca1);

			while (resultado.next()) {
				caja2.addItem(resultado.getString(1));
			}
			resultado.close();

		} catch (Exception e) {
		}

	}

}