package noixcoopDAO;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAO;

public class ProduitDAO extends DAO<Produit>
{
	/* AJout du produit */
	public boolean add(Produit produit) throws Exception
	{
		if (connexion() == true)
		{
			try
			{
				String sql = "INSERT INTO produit (variete, type, calibre) VALUES ('" + produit.getVariete() + "', '"
						+ produit.getType() + "', " + produit.getCalibre() + ");";

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

	/* Ajout d'un produit + de son ID commande */
	public boolean add(Produit produit, int idCommande) throws Exception
	{
		if (connexion() == true)
		{
			try
			{
				String sql = "INSERT INTO produit (variete, type, calibre, idCommande) VALUES ('" + produit.getVariete()
						+ "', '" + produit.getType() + "', " + produit.getCalibre() + ", " + idCommande + ");";

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

	/* Modifie une produit */ //Faire une méthode update qui permette de mettre à jour l'idCommande
	public boolean update(Produit produit) throws Exception
	{
		if (connexion() == true)
		{
			try
			{
				String sql = "UPDATE produit SET variete = '" + produit.getVariete() + "', type = '" + produit.getType()
						+ "', calibre = " + produit.getCalibre() + " WHERE id = " + produit.getId() + ";";

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

	/* Supprimer une produit */
	public boolean delete(Produit produit) throws Exception
	{
		if (connexion() == true)
		{
			try
			{
				String sql = "DELETE FROM produit WHERE id = " + produit.getId() + ";";

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

	/*
	 * Affiche la liste des produits
	 */

	/* Affiche la liste de TOUS les produits */
	public ArrayList<Produit> getAll() throws Exception
	{
		ArrayList<Produit> liste = new ArrayList<Produit>();

		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT * from produit;";
				rs = stmt.executeQuery(sql);
				while (rs.next())
				{
					int id = rs.getInt("id");
					String variete = rs.getString("variete");
					String type = rs.getString("type");
					int calibre = rs.getInt("calibre");
					liste.add(new Produit(id, variete, type, calibre));
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

	/* Affiche la liste de TOUS les produits d'après une clause WHERE */
	public ArrayList<Produit> getAll(String clause, String value) throws Exception
	{
		ArrayList<Produit> liste = new ArrayList<Produit>();

		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT * from produit WHERE " + clause + " = '" + value + "';";
				rs = stmt.executeQuery(sql);
				while (rs.next())
				{
					int id = rs.getInt("id");
					String variete = rs.getString("variete");
					String type = rs.getString("type");
					int calibre = rs.getInt("calibre");
					liste.add(new Produit(id, variete, type, calibre));
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

	/* Récupération des données d'une Produit */
	public Produit get(int idP)
	{
		Produit produit = null;
		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT * from produit WHERE id=" + idP + ";";

				rs = stmt.executeQuery(sql);
				if (rs.first())
				{
					produit = new Produit(rs.getInt("id"), rs.getString("variete"), rs.getString("type"),
							rs.getInt("calibre"));
					this.rs.close();
					return produit;
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
