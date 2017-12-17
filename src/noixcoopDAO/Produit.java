package noixcoopDAO;

import DAO.DAOInterface;

public class Produit implements DAOInterface<ProduitDAO>
{
	private String	variete	= "";
	private String	type	= "";
	private int		calibre	= 0;
	private int		id		= 0;

	public Produit(int id, String variete, String type, int calibre)
	{
		this.id = id;
		this.variete = variete;
		this.type = type;
		this.calibre = calibre;
	}

	public Produit(String variete, String type, int calibre)
	{
		this.id = id;
		this.variete = variete;
		this.type = type;
		this.calibre = calibre;
	}

	public String getVariete()
	{
		return variete;
	}

	public String getType()
	{
		return type;
	}

	public int getCalibre()
	{
		return calibre;
	}

	public int getId()
	{
		return id;
	}

	@Override
	public String toString()
	{
		return "Produit [variete=" + variete + ", type=" + type + ", calibre=" + calibre + ", id=" + id + "]";
	}

	@Override
	public ProduitDAO getInstanceDAO()
	{
		// TODO Auto-generated method stub
		return new ProduitDAO();
	}
}
