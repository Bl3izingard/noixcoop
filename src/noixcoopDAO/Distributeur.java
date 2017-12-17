package noixcoopDAO;
import java.util.ArrayList;

import DAO.DAOInterface;

public class Distributeur implements DAOInterface<DistributeurDAO>
{
	@Override
	public String toString()
	{
		return "Distributeur [id=" + id + ", nom=" + nom + ", commandes=" + commandes + "]";
	}

	private int					idInt		= 0;
	private String				id			= "";
	private String				nom			= "";
	private ArrayList<Commande>	commandes	= null;

	public Distributeur(String unId, String unNom)
	{
		this.id = unId;
		this.nom = unNom;
	}

	public Distributeur(int id, String unId, String unNom)
	{
		this.idInt = id;
		this.id = unId;
		this.nom = unNom;
	}

	public Distributeur(int id, String unId, String unNom, ArrayList<Commande> commandes)
	{
		this.idInt = id;
		this.id = unId;
		this.nom = unNom;
		this.commandes = commandes;
	}

	public Distributeur(String unId, String unNom, ArrayList<Commande> commandes)
	{
		this.id = unId;
		this.nom = unNom;
		this.commandes = commandes;
	}

	public String getNom()
	{
		return nom;
	}

	public int getIdInt()
	{
		return idInt;
	}

	public String getId()
	{
		return id;
	}

	public ArrayList<Commande> getCommandes()
	{
		return commandes;
	}

	public ArrayList<Commande> getCommandesEnCours()
	{
		ArrayList<Commande> commandesEnCours = new ArrayList<Commande>();

		for (Commande c : this.commandes)
		{
			if (c.getDateEnvoi() == null)
			{
				commandesEnCours.add(c);
			}
		}

		return commandesEnCours;
	}

	@Override
	public DistributeurDAO getInstanceDAO()
	{
		// TODO Auto-generated method stub
		return new DistributeurDAO();
	}

}
