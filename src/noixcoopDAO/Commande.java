package noixcoopDAO;
import java.util.Calendar;

import DAO.DAOInterface;

public class Commande implements DAOInterface<CommandeDAO>
{
	private int			id					= 0;
	private Produit		leProduit			= null;
	private float		prixHt				= (float) 0.0;
	private String		conditionnement		= "";
	private int			quantité;
	private Calendar	dateConditionnement	= null;
	private Calendar	dateEnvoi			= null;

	public Commande(int id, Produit produit, float prixHT, String conditionnement, int quantite,
			Calendar dateConditionnement, Calendar dateEnvoie)
	{
		this.id = id;
		this.leProduit = produit;
		this.prixHt = prixHT;
		this.conditionnement = conditionnement;
		this.quantité = quantite;
		this.dateConditionnement = dateConditionnement;
		this.dateEnvoi = dateEnvoie;
	}
	public Commande(Produit produit, float prixHT, String conditionnement, int quantite,
			Calendar dateConditionnement, Calendar dateEnvoie)
	{
		this.leProduit = produit;
		this.prixHt = prixHT;
		this.conditionnement = conditionnement;
		this.quantité = quantite;
		this.dateConditionnement = dateConditionnement;
		this.dateEnvoi = dateEnvoie;
	}
	public Commande(int id, Produit produit, float prixHT, String conditionnement, int quantite,
			Calendar dateConditionnement)
	{
		this.id = id;
		this.leProduit = produit;
		this.prixHt = prixHT;
		this.conditionnement = conditionnement;
		this.quantité = quantite;
		this.dateConditionnement = dateConditionnement;
	}

	public Commande(Produit produit, float prixHT, String conditionnement, int quantite,
			Calendar dateConditionnement)
	{
		this.id = id;
		this.leProduit = produit;
		this.prixHt = prixHT;
		this.conditionnement = conditionnement;
		this.quantité = quantite;
		this.dateConditionnement = dateConditionnement;
	}

	
	@Override
	public String toString()
	{
		return "Commande [id=" + id + ", leProduit=" + leProduit + ", prixHt=" + prixHt + ", conditionnement="
				+ conditionnement + ", quantité=" + quantité + ", dateConditionnement=" + dateConditionnement
				+ ", dateEnvoi=" + dateEnvoi + "]";
	}

	public int getId()
	{
		return id;
	}

	public Produit getLeProduit()
	{
		return leProduit;
	}

	public float getPrixHt()
	{
		return prixHt;
	}

	public String getConditionnement()
	{
		return conditionnement;
	}

	public int getQuantité()
	{
		return quantité;
	}

	public Calendar getDateConditionnement()
	{
		return dateConditionnement;
	}

	public Calendar getDateEnvoi()
	{
		return dateEnvoi;
	}

	@Override
	public CommandeDAO getInstanceDAO()
	{
		// TODO Auto-generated method stub
		return new CommandeDAO();
	}

}
