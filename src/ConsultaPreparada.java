import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaPreparada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7623827", "sql7623827", "akYVms4umE");
			PreparedStatement codigomysql = miConexion.prepareStatement("SELECT * FROM productos WHERE paisdeorigen =?");
//			codigomysql.setString(1, "Ana");
			codigomysql.setString(1, "ITALIA");
			ResultSet rs = codigomysql.executeQuery();
			
			while (rs.next()){
				System.out.println(rs.getString(1)+" "+rs.getString(2));
				
				
			}
			
//			codigomysql.setString(1, "Maria");
//			codigomysql.setInt(2, 34);
//			rs = codigomysql.executeQuery();
//			while (rs.next()){
//				System.out.println(rs.getString(1)+" "+rs.getString(2));
//				
//				
//			}
			rs.close();
				
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
