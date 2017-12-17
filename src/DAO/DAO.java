package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import noixcoopDAO.*;

public abstract class DAO<T>
{
	/* Déclaration & Initialisation de la connexion */
	private String		url		= "jdbc:mysql://127.0.0.1:3306/mysql";
	private String		user	= "root";
	private String		psw		= "";
	protected Connection	cnx;
	protected Statement	stmt;
	protected ResultSet	rs;
	
	protected boolean connexion()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			cnx = DriverManager.getConnection(url, user, psw);
			stmt = cnx.createStatement();

		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Problème de driver");
			e.printStackTrace();
			return false;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur de connexion à la base de données");
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	protected boolean deconnexion()
	{

		try
		{
			this.stmt.close();
			this.cnx.close();
		}
		catch (SQLException e)
		{
			System.out.println("Erreur de connexion à la base de données");
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	//public abstract T getInstanceDAO();
	
	public abstract boolean add(T o) throws Exception;
	public abstract boolean update(T o) throws Exception;
	public abstract boolean delete(T o) throws Exception;
	public abstract ArrayList<T> getAll() throws Exception; 
	public abstract ArrayList<T> getAll(String clause, String value) throws Exception;
	public abstract T get(int id) throws Exception;
	
}
