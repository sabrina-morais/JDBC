import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import entities.Aluno;
import jdbc.AlunoJDBC;
import jdbc.Db;

public class Program {

	public static void main(String[] args) throws IOException, SQLException {
		
		Connection conApp = Db.getConexao();
		int opcao = 0;
		
		Scanner console = new Scanner (System.in);
		try {
			do {
				Aluno a = new Aluno();
				AlunoJDBC acaoAluno = new AlunoJDBC ();
				
				System.out.println("####### Menu ######"
				                     +"\n1 - Cadastrar"
				                     +"\n2 - Listar"
				                     +"\n3 - Alterar"
				                     +"\n4 - Excluir"
				                     +"\n5 - Sair");
				System.out.println("\nOpção: ");
				opcao = Integer.parseInt(console.nextLine());
				
				if (opcao ==1) {
					System.out.println("\n ## Cadastrar Aluno ## \n\r");
					System.out.println("Nome: ");
					a.setNome(console.nextLine());
					acaoAluno.salvar(a);
					console.nextLine();
					System.out.println("\n\n\n");
					
				}
				
				if (opcao ==2) {
					System.out.println("\n ## Lista Alunos ## \n\r");
					acaoAluno.listar();
					System.out.println("\n\n\n");
				}
				
				if (opcao == 3) {
				    System.out.println("\n ## Alterar Aluno ## \n\r");
				    System.out.println("Informe o ID do aluno a ser alterado: ");
				    int idaluno = Integer.parseInt(console.nextLine());
				    System.out.println("Informe o novo nome do aluno: ");
				    String novoNome = console.nextLine();

				    Aluno alunoParaAlterar = new Aluno();
				    alunoParaAlterar.setIdaluno(idaluno);
				    alunoParaAlterar.setNome(novoNome);

				    acaoAluno.alterar(alunoParaAlterar);
				    System.out.println("\n\n\n");
				}
				
				if (opcao ==4) {
					System.out.println("\n ## Excluir Aluno ## \n\r");
					System.out.println("Informe o ID do aluno a ser ecluído: ");
					int idaluno = Integer.parseInt(console.nextLine());
					acaoAluno.apagar(idaluno);
					System.out.println("\n\n\n");
				}

		} while(opcao!=5);
		
		} catch (Exception e) {
			
		} finally {
			Db.fechaConexao();
		}
	}
}

