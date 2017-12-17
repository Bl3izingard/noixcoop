package noixcoopDAO;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAO;

public class DistributeurDAO extends DAO<Distributeur>
{

	@Override
	public boolean add(Distributeur o) throws Exception
	{
		// TODO Auto-generated method stub

		if (connexion() == true)
		{
			try
			{
				String sql = "INSERT INTO distributeur (idString, nom) VALUES ('" + o.getId() + "', '" + o.getNom() + "');";

				stmt.executeUpdate(sql);

			}
			catch (SQLException e)
			{
				System.out.println("Erreur de requête SQL");
				e.printStackTrace();
				return false;
			}

			deconnexion();
		}
		else
		{
			return false;
		}

		return true;
	}

	@Override
	public boolean update(Distributeur o) throws Exception
	{
		// TODO Auto-generated method stub
		if (connexion() == true)
		{
			try
			{
				String sql = "UPDATE distributeur SET idString = '" + o.getId() + "', nom = '" + o.getNom() + "' WHERE id = "
						+ o.getIdInt() + ";";

				stmt.executeUpdate(sql);

			}
			catch (SQLException e)
			{
				System.out.println("Erreur de requête SQL");
				e.printStackTrace();
				return false;
			}

			deconnexion();
		}
		else
		{
			return false;
		}

		return true;
	}

	@Override
	public boolean delete(Distributeur o) throws Exception
	{
		// TODO Auto-generated method stub
		if (connexion() == true)
		{
			try
			{
				String sql = "DELETE FROM `distributeur` WHERE id = " + o.getIdInt();
				stmt.executeUpdate(sql);

			}
			catch (SQLException e)
			{
				System.out.println("Erreur de requête SQL");
				e.printStackTrace();
				return false;
			}

			deconnexion();
		}
		else
		{
			return false;
		}

		return true;
	}

	@Override
	public ArrayList<Distributeur> getAll() throws Exception
	{
		// TODO Auto-generated method stub
		ArrayList<Distributeur> liste = new ArrayList<Distributeur>();

		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT * from distributeur;";
				rs = stmt.executeQuery(sql);
				while (rs.next())
				{
					int id = rs.getInt("id");
					String idString = rs.getString("idString");
					String nom = rs.getString("nom");
					ArrayList<Commande> listeCommande = new CommandeDAO().getAll("idDistributeur", String.valueOf(id));
					liste.add(new Distributeur(id, idString, nom, listeCommande));
				}

				this.rs.close();
			}
			catch (SQLException e)
			{
				System.out.println("Erreur de connexion à la base de données");
				e.printStackTrace();
			}

			// Déconnexion
			deconnexion();
		}
		else
		{
			return null;
		}

		return liste;
	}

	@Override
	public ArrayList<Distributeur> getAll(String clause, String value) throws Exception
	{
		// TODO Auto-generated method stub
		ArrayList<Distributeur> liste = new ArrayList<Distributeur>();

		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT * from distributeur WHERE " + clause + " = '" + value + "';";
				rs = stmt.executeQuery(sql);
				while (rs.next())
				{
					int id = rs.getInt("id");
					String idString = rs.getString("idString");
					String nom = rs.getString("nom");
					ArrayList<Commande> listeCommande = new CommandeDAO().getAll("idDistributeur", String.valueOf(id));
					liste.add(new Distributeur(id, idString, nom, listeCommande));
				}

				this.rs.close();
			}
			catch (SQLException e)
			{
				System.out.println("Erreur de connexion à la base de données");
				e.printStackTrace();
			}

			// Déconnexion
			deconnexion();
		}
		else
		{
			return null;
		}

		return liste;
	}

	@Override
	public Distributeur get(int id) throws Exception
	{
		Distributeur distributeur = null;
		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT * from distributeur WHERE id=" + id + ";";

				rs = stmt.executeQuery(sql);
				if (rs.first())
				{
					ArrayList<Commande> listeCommande = new CommandeDAO().getAll("idDistributeur", String.valueOf(rs.getInt("id")));
					
					distributeur = new Distributeur(rs.getInt("id"), rs.getString("idString"), rs.getString("nom"), listeCommande);
					this.rs.close();
					return distributeur;
				}
			}
			catch (SQLException e)
			{
				System.out.println("Erreur de connexion à la base de données");
				e.printStackTrace();
			}

		}
		// Déconnexion
		deconnexion();

		return null;
	
	}

	public Distributeur get(String id) throws Exception
	{
		Distributeur distributeur = null;
		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT * from distributeur WHERE idString='" + id + "';";

				rs = stmt.executeQuery(sql);
				if (rs.first())
				{
					ArrayList<Commande> listeCommande = new CommandeDAO().getAll("idDistributeur", String.valueOf(rs.getInt("id")));
					
					distributeur = new Distributeur(rs.getInt("id"), rs.getString("idString"), rs.getString("Nom"), listeCommande);
					this.rs.close();
					return distributeur;
				}
			}
			catch (SQLException e)
			{
				System.out.println("Erreur de connexion à la base de données");
				e.printStackTrace();
			}

		}
		// Déconnexion
		deconnexion();

		return null;
	}
}
