package aplicacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.sql.PreparedStatement;

public class AccesoBD {

	public static void main(String[] args) {

		Marco marco = new Marco();
		marco.setDefaultCloseOperation(3);
		marco.setVisible(true);

	}
}

class Marco extends JFrame {

	JComboBox<String> caja1;
	JComboBox<String> caja2;
	JButton buscar;
	JTextArea texto;
	Connection miConexion;
	String preparado = "SELECT * FROM productos WHERE seccion=?";
	String preparado2 = "SELECT * FROM productos WHERE importado=?";
	String preparado3 = "SELECT * FROM productos WHERE seccion =? AND importado=?";
	ResultSet rs;

	public Marco() {
		setTitle("Accesso a BBDD");
		setBounds(50, 50, 500, 500);
		JPanel menu = new JPanel();
		caja1 = new JComboBox<>();
		caja1.addItem("Todos");
		caja2 = new JComboBox<>();
		caja2.addItem("Todos");
		texto = new JTextArea();
		buscar = new JButton("Buscar");
		buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ejecutar();
			}
		});

		menu.add(caja1);
		menu.add(caja2);

		add(menu, BorderLayout.NORTH);
		add(texto, BorderLayout.CENTER);
		add(buscar, BorderLayout.SOUTH);

		try

		{
			// primero se crea coneccion
			miConexion = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827",
					"sql7623827", "akYVms4umE");

			// crear el statement
			Statement miStatement = miConexion.createStatement();

			// correr el statement y guardar el un result set
			ResultSet rs = miStatement.executeQuery("SELECT DISTINCTROW seccion FROM productos");

			while (rs.next()) {
				caja1.addItem(rs.getString(1));
			}

			rs.close();

			rs = miStatement.executeQuery("SELECT DISTINCTROW importado FROM productos");
			while (rs.next()) {
				caja2.addItem(rs.getString(1));
			}

			rs.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void ejecutar() {

		String seleccionado1 = caja1.getSelectedItem().toString();
		String seleccionado2 = caja2.getSelectedItem().toString();
		PreparedStatement prep;
		try {
			if (!seleccionado1.equals("Todos") && seleccionado2.equals("Todos")) {
				prep = miConexion.prepareStatement(preparado);
				prep.setString(1, seleccionado1);
				rs = prep.executeQuery();
			} else if (seleccionado1.equals("Todos") && !seleccionado2.equals("Todos")) {
				prep = miConexion.prepareStatement(preparado2);
				prep.setString(1, seleccionado2);
				rs = prep.executeQuery();
			} else {
				prep = miConexion.prepareStatement(preparado3);
				prep.setString(1, seleccionado1);
				prep.setString(2, seleccionado2);
				rs = prep.executeQuery();
			}
			texto.setText("");
			while (rs.next()) {
				texto.append(rs.getString(1));
				texto.append(", ");
				texto.append(rs.getString(2));
				texto.append(", ");
				texto.append(rs.getString(3));
				texto.append(", ");
				texto.append(rs.getString(4));
				texto.append(", ");
				texto.append(rs.getString(5));
				texto.append(", ");
				texto.append(rs.getString(6));
				texto.append("\n");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}