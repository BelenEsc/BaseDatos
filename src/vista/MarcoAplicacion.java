package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class MarcoAplicacion extends JFrame {

	JComboBox<String> caja1;
	JComboBox<String> caja2;
	JButton buscar;
	JTextArea texto;
	Connection miConexion;
	String preparado = "SELECT * FROM productos WHERE seccion=?";
	String preparado2 = "SELECT * FROM productos WHERE importado=?";
	String preparado3 = "SELECT * FROM productos WHERE seccion =? AND importado=?";
	ResultSet rs;

	public MarcoAplicacion () {
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
//				ejecutar();
			}
		});

		menu.add(caja1);
		menu.add(caja2);

		add(menu, BorderLayout.NORTH);
		add(texto, BorderLayout.CENTER);
		add(buscar, BorderLayout.SOUTH);

	}

}
