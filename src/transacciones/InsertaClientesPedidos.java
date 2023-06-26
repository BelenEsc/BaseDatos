package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.management.remote.rmi.RMIConnection;

public class InsertaClientesPedidos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection miConeccion =null;
		try {
			miConeccion = DriverManager.getConnection(
					"jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827", "sql7623827", "akYVms4umE");

			// decir que trate como bloque
			miConeccion.setAutoCommit(false);

			Statement miStatement = miConeccion.createStatement();
			String instruccionSql1 = "INSERT INTO clientes (codigocliente, EMPRESA, direccion, poblacion, telefono, RESPONSABLE) VALUES ('CT84','EJEMPLO','P Botanico', 'hola','234','PEDRITO')";
			miStatement.executeUpdate(instruccionSql1);
			System.out.println("si");
			String instruccionSql2 = "INSERT INTO pedidos (N_MERO_DE_PEDIDO, C_DIGO_CLIENTE, FECHA_DE_PEDIDO,forma_de_pago, descuento, enviado) VALUES ('82' , 'CT84', '11/03/2000','corporal', 'por hora', 'si')";
			miStatement.executeUpdate(instruccionSql2);

			// commit si llega hasta aqui

			miConeccion.commit();

		} catch (Exception e) {
			
			try {
				miConeccion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		}

	}

}
