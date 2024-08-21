package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {
	
	private static final Aluno Aluno = null;
	private String sql;
	PreparedStatement pst;
	
	public void salvar (Aluno a) throws SQLException{
		try {
			Connection conApp = Db.getConexao();
			sql = "INSERT INTO aluno (nome) VALUES (?)";
			pst = conApp.prepareStatement(sql);
			pst.setString(1, a.getNome());
			if(1== pst.executeUpdate()){
				System.out.println("\nCadastro do aluno " +a.getNome()+ " realizado com sucesso!");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
		
	
	public void apagar (int idaluno) throws IOException {
		Connection conApp = null;
	    PreparedStatement pst = null;

	    try {
	    	conApp = Db.getConexao();
	        String sql = "DELETE FROM aluno WHERE idaluno = ?";
	        pst = conApp.prepareStatement(sql);
	        pst.setInt(1, idaluno);

	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Aluno com ID " + idaluno + " foi excluído com sucesso.");
	        } else {
	            System.err.println("Nenhum aluno encontrado com ID " + idaluno + ".");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao apagar aluno: " + e.getMessage());
	    } finally {
	        try {
	            if (pst != null) {
	                pst.close();
	            }
	            if (conApp != null && !conApp.isClosed()) {
	                conApp.close();
	            }
	        } catch (SQLException e) {
	            System.err.println("Erro ao fechar recursos: " + e.getMessage());
	        }
	    }
	}
	
	
	public List<Aluno> listar () throws IOException, SQLException{
		Connection conApp = Db.getConexao();
		ResultSet resultado = null;
		Statement st = conApp.createStatement();
		List<Aluno> listaAluno = new ArrayList<>();

		try {
			sql = "SELECT * FROM aluno";
			resultado = st.executeQuery(sql);
			while (resultado.next()) {
				Aluno aluno = new Aluno();
				aluno.setIdaluno(resultado.getInt("idaluno"));
				aluno.setNome(resultado.getString("nome"));
				listaAluno.add(aluno);
				//System.out.println("ID: " + aluno.getIdaluno() + ", Nome: " + aluno.getNome());
			}
			resultado.close();
			
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if(resultado!=null) {
				resultado.close();
			}
			st.close();
		}
		for (Aluno aluno : listaAluno) {
			System.out.println("ID: " + aluno.getIdaluno() + ", Nome: " + aluno.getNome());
		}
		return listaAluno;
	}
	
	public void alterar(Aluno a) throws IOException {
	    Connection conApp = null;
	    PreparedStatement pst = null;

	    try {
	        conApp = Db.getConexao();
	        String sql = "UPDATE aluno SET nome = ? WHERE idaluno = ?";
	        pst = conApp.prepareStatement(sql);
	        pst.setString(1, a.getNome()); 
	        pst.setInt(2, a.getIdaluno()); 

	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Aluno com ID " + a.getIdaluno() + " foi atualizado com sucesso.");
	        } else {
	            System.out.println("Nenhum aluno encontrado com ID " + a.getIdaluno() + ".");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao atualizar aluno: " + e.getMessage());
	    } finally {
	        try {
	            if (pst != null) {
	                pst.close();
	            }
	            if (conApp != null && !conApp.isClosed()) {
	                conApp.close();
	            }
	        } catch (SQLException e) {
	            System.err.println("Erro ao fechar recursos: " + e.getMessage());
	        }
	    }
	}

}
