package aplicacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		Lamina lamina = new Lamina();
		add(lamina);


	}
}

class Lamina extends JPanel {
	
	JComboBox<String> caja1;
	JComboBox<String> caja2;
	PreparedStatement stat;
	String consulta = ("SELECT * FROM TABLES WHERE ROW_FORMAT=?");
	Connection miConexion;
	ResultSet rs = null;
	JTextArea areaMostrar; 

	public Lamina() {
		setLayout(new BorderLayout());
		
		JPanel menus = new JPanel();
		
		caja1 = new JComboBox<String>();
		caja1.addItem("todos");

		caja2 = new JComboBox<String>();
		caja2.addItem("Todos");

		areaMostrar= new JTextArea();
		JButton buscar = new JButton("Buscar");
		buscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ejecutar();
			}
		});
		menus.add(caja1);
		menus.add(caja2);
		add(menus,BorderLayout.NORTH);
		add(areaMostrar,BorderLayout.CENTER);
		add(buscar, BorderLayout.SOUTH);

		// conectar a la base de datos
		try {
			miConexion= DriverManager.getConnection(
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


	private void ejecutar() {
		try {
			String seccion = caja1.getSelectedItem().toString();
			stat=miConexion.prepareStatement(consulta);
			stat.setString(1, seccion);
			rs=stat.executeQuery();
			while (rs.next()) {
				areaMostrar.append(rs.getString(1));
				areaMostrar.append(", ");
				areaMostrar.append(rs.getString(2));
				areaMostrar.append(", ");
				areaMostrar.append(rs.getString(3));
				areaMostrar.append(", ");
				areaMostrar.append(rs.getString(4));
				areaMostrar.append(", ");
				areaMostrar.append(rs.getString(5));
				areaMostrar.append(", ");
				areaMostrar.append("\n");
				
			}
			
		} catch (Exception e) {
		}

	}

}