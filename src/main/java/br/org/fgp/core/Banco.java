package br.org.fgp.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public class Banco {

	private static final String URL_CONNECTION = "jdbc:sqlserver://localhost;instanceName=MSSQLSERVER;databaseName=sistema;user=sa;password=sql";
	private Statement statement;
	private Connection connection;
	private ResultSet resultSet;
	
	private static enum TipoConsulta{
		CONSULTA("CONSULTA"),
		EXECUTE("EXECUTE");
		
		TipoConsulta(String codigo){
			this.codigo = codigo;
		}
		
		@SuppressWarnings("unused")
		private String codigo;
		
	}
	
	public Banco(){
		
	}
	
	public void sqlAtualiza(String sql){
		processarSql(sql, TipoConsulta.EXECUTE);
	}
	
	public ResultSet sqlConsulta(String sql){
		processarSql(sql, TipoConsulta.CONSULTA);
		return resultSet;
	}
	
	private void processarSql(String sql, TipoConsulta tipo){
		try{
			connection = DriverManager.getConnection(URL_CONNECTION);
			statement = connection.createStatement();
			if(statement != null){
				try{
					if(tipo.equals(TipoConsulta.EXECUTE)){
						statement.execute(sql);
						connection.commit();
					}else if(tipo.equals(TipoConsulta.CONSULTA)){
						resultSet = statement.executeQuery(sql);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				statement.close();
				connection.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
}