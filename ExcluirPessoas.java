package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcluirPessoas {

	public static void main(String[] args) throws SQLException {

		Scanner entrada = new Scanner(System.in);
		Connection conexao = FabricaConexao.getConexao();
		String select = "SELECT * FROM pessoas WHERE codigo = ?";
		String delete = "DELETE FROM pessoas WHERE codigo = ?";

		System.out.print("Informe o código para deletar: ");
		int codigo = entrada.nextInt();

		entrada.nextLine();
		PreparedStatement stmt = conexao.prepareStatement(select);
		stmt.setInt(1, codigo);
		ResultSet resultado = stmt.executeQuery();
		if (resultado.next()) {
			System.out.print("Deseja excluir o ID de numero " + codigo + " ?");
			String resposta = entrada.nextLine();

			if (resposta.equalsIgnoreCase("Sim")) {

				PreparedStatement stmtDelete = conexao.prepareStatement(delete);
				stmtDelete.setInt(1, codigo);
				stmtDelete.executeUpdate();
				System.out.print("Pessoa excluida com sucesso! ");
				stmtDelete.close();
			} else {
				System.out.println("Nada feito!");
			}

			conexao.close();
			entrada.close();

		}
	}
}
