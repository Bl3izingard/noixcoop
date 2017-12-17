import noixcoopDAO.Distributeur;
import noixcoopDAO.DistributeurDAO;

public class PersistanceSQL<T>
{
	public boolean rangerDansBase(T o)
	{
		return false;
	}
	
	public T chargerDepuisBase(String id, String nomClasse)
	{
		T o = null;
		switch (nomClasse)
		{
			case "Produit":
				
				break;
			case "Commande":
				
				break;
				
				
			case "Distributeur":
				DistributeurDAO dDAO = new DistributeurDAO();
				
				try
				{
					return (T) dDAO.get(id);
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		
		return o;
	}
}
