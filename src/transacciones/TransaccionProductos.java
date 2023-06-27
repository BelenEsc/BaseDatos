package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TransaccionProductos {

	public static void main(String[] args) {
		Connection miConeccion = null;
		try {
			miConeccion = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827",
					"sql7623827", "akYVms4umE");

			// decir que trate como bloque
			miConeccion.setAutoCommit(false);

			Statement miStatement = miConeccion.createStatement();
			String instruccionSql1 = "DELETE FROM productos WHERE paisdeorigen ='ITALIA'";
			String instruccionSql2 = "DELETE FROM productos WHERE precio>300";
			String instruccionSql3 = "UPDATE productos SET precio = precio*1.5";

			if (ejecutarTransaccion()) {
				miStatement.executeUpdate(instruccionSql1);
				miStatement.executeUpdate(instruccionSql2);
				miStatement.executeUpdate(instruccionSql3);
				miConeccion.commit();
				System.out.println("si");
			} else {
				System.out.println("no se hizo nada1");
			}

		} catch (Exception e) {
			try {
				miConeccion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
			System.out.println("no se hizo nada2");
		}
	}

	static boolean ejecutarTransaccion() {
		String ejecucion = JOptionPane.showInputDialog("hacer trans?");
		if (ejecucion.equals("si")) {
			return true;
		} else {
			return false;
		}

	}

}
