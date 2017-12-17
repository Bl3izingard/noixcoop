package noixcoopDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import DAO.DAO;

public class CommandeDAO extends DAO<Commande>
{
	/* AJout du commande */
	public boolean add(Commande commande) throws Exception
	{
		if (connexion() == true)
		{
			try
			{
				String sql = "INSERT INTO commande (prixHT, conditionnement, quantite, dateConditionnement) VALUES ('"
						+ commande.getPrixHt() + "', '" + commande.getConditionnement() + "', " + commande.getQuantité()
						+ ", '" + commande.getDateConditionnement().getTime() + "');";

				stmt.executeUpdate(sql);

				ProduitDAO pDAO = commande.getLeProduit().getInstanceDAO();
				
				pDAO.add(commande.getLeProduit(), commande.getId());
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

	public boolean add(Commande commande, int idDistributeur) throws Exception
	{
		if (connexion() == true)
		{
			try
			{
				String sql = "INSERT INTO commande (prixHT, conditionnement, quantite, dateConditionnement, idDistributeur) VALUES ('"
						+ commande.getPrixHt() + "', '" + commande.getConditionnement() + "', " + commande.getQuantité()
						+ ", '" + commande.getDateConditionnement().getTime() + "', " + idDistributeur + ");";

				stmt.executeUpdate(sql);

				ProduitDAO pDAO = commande.getLeProduit().getInstanceDAO();
				
				pDAO.add(commande.getLeProduit(), commande.getId());
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
	
	/* Modifie une commande */
	public boolean update(Commande commande) throws Exception
	{
		if (connexion() == true)
		{
			try
			{
				String sql = "UPDATE commande SET prixHT = " + commande.getPrixHt() + ", conditionnement = '"
						+ commande.getConditionnement() + "', quantite = " + commande.getQuantité()
						+ ", dateConditionnement = '" + commande.getDateConditionnement().getTime() + "'"
						+ ", dateEnvoie = '" + commande.getDateEnvoi().getTime() + "' WHERE id = "
						+ commande.getId() + ";";

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

	/* Supprimer une commande */
	public boolean delete(Commande commande) throws Exception
	{
		if (connexion() == true)
		{
			try
			{
				String sql = "DELETE FROM commande WHERE id = " + commande.getId() + ";";

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
	 * Affiche la liste des commandes
	 */

	/* Affiche la liste de TOUS les commandes */
	public ArrayList<Commande> getAll()
	{
		ArrayList<Commande> liste = new ArrayList<Commande>();
		Calendar dateConditionnement = Calendar.getInstance();
		Calendar dateEnvoie = Calendar.getInstance();

		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT c.id AS id, c.prixHT, c.conditionnement, c.quantite, c.dateConditionnement, c.dateEnvoie, p.id AS idProduit, p.variete, p.type, p.calibre from commande AS c, produit AS p WHERE c.id = p.idCommande;";
				rs = stmt.executeQuery(sql);
				while (rs.next())
				{

					int id = rs.getInt("id");
					Produit produit = new Produit(rs.getInt("idProduit"), rs.getString("variete"), rs.getString("type"),
							rs.getInt("calibre"));
					float prixHT = rs.getFloat("prixHT");
					String conditionnement = rs.getString("conditionnement");
					int quantite = rs.getInt("quantite");
					dateConditionnement.setTime(rs.getDate("dateConditionnement"));
					if(rs.getDate("dateEnvoie") != null)
					{
						dateEnvoie.setTime(rs.getDate("dateEnvoie"));
					}
					else
					{
						dateEnvoie = null;
					}
					if (dateEnvoie == null)
					{
						liste.add(new Commande(id, produit, prixHT, conditionnement, quantite, dateConditionnement));
					}
					else
					{
						liste.add(new Commande(id, produit, prixHT, conditionnement, quantite, dateConditionnement,
								dateEnvoie));
					}

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

	/* Affiche la liste de TOUS les commandes d'après une clause WHERE */
	public ArrayList<Commande> getAll(String clause, String value)
	{
		ArrayList<Commande> liste = new ArrayList<Commande>();
		Calendar dateConditionnement = Calendar.getInstance();
		Calendar dateEnvoie = Calendar.getInstance();

		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT c.id AS id, c.prixHT, c.conditionnement, c.quantite, c.dateConditionnement, c.dateEnvoie, p.id AS idProduit, p.variete, p.type, p.calibre from commande AS c, produit AS p WHERE c.id = p.idCommande AND "
						+ clause + " = '" + value + "';";
				rs = stmt.executeQuery(sql);
				while (rs.next())
				{

					int id = rs.getInt("id");
					Produit produit = new Produit(rs.getInt("idProduit"), rs.getString("variete"), rs.getString("type"),
							rs.getInt("calibre"));
					float prixHT = rs.getFloat("prixHT");
					String conditionnement = rs.getString("conditionnement");
					int quantite = rs.getInt("quantite");
					dateConditionnement.setTime(rs.getDate("dateConditionnement"));
					if(rs.getDate("dateEnvoie") != null)
					{
						dateEnvoie.setTime(rs.getDate("dateEnvoie"));
					}
					else
					{
						dateEnvoie = null;
					}
					
					if (dateEnvoie == null)
					{
						System.out.println("Tout va bien");
						liste.add(new Commande(id, produit, prixHT, conditionnement, quantite, dateConditionnement));
					}
					else
					{
						liste.add(new Commande(id, produit, prixHT, conditionnement, quantite, dateConditionnement,
								dateEnvoie));
					}
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

	/* Récupération des données d'une Commande */
	public Commande get(int idC)
	{
		Commande commande = null;
		Calendar dateConditionnement = Calendar.getInstance();
		Calendar dateEnvoie = Calendar.getInstance();

		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT c.id AS id, c.prixHT, c.conditionnement, c.quantite, c.dateConditionnement, c.dateEnvoie, p.id AS idProduit, p.variete, p.type, p.calibre from commande AS c, produit AS p WHERE c.id = p.idCommande AND c.id = "
						+ idC + ";";

				rs = stmt.executeQuery(sql);
				if (rs.first())
				{
					int id = rs.getInt("id");
					Produit produit = new Produit(rs.getInt("idProduit"), rs.getString("variete"), rs.getString("type"),
							rs.getInt("calibre"));
					float prixHT = rs.getFloat("prixHT");
					String conditionnement = rs.getString("conditionnement");
					int quantite = rs.getInt("quantite");
					dateConditionnement.setTime(rs.getDate("dateConditionnement"));
					if(rs.getDate("dateEnvoie") != null)
					{
						dateEnvoie.setTime(rs.getDate("dateEnvoie"));
					}
					else
					{
						dateEnvoie = null;
					}

					if (dateEnvoie == null)
					{
						commande = new Commande(id, produit, prixHT, conditionnement, quantite, dateConditionnement);
					}
					else
					{
						commande = new Commande(id, produit, prixHT, conditionnement, quantite, dateConditionnement,
								dateEnvoie);
					}
					this.rs.close();
					return commande;
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

	private int getLastCommandeId()
	{
		if (connexion() == true)
		{
			try
			{
				String sql = "SELECT id FROM commande ORDER BY id DESC;";

				rs = stmt.executeQuery(sql);
				if (rs.first())
				{
					int id = rs.getInt("id");

					this.rs.close();
					return id;
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

		return -1;
	}

	public static final CommandeDAO getInstanceDAO()
	{
		// TODO Auto-generated method stub
		return new CommandeDAO();
	}
}
