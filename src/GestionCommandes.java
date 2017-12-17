import noixcoopDAO.Distributeur;

public class GestionCommandes
{
	private PersistanceSQL donnees = null;
	
	public GestionCommandes(PersistanceSQL lesDonnees)
	{
		this.donnees = lesDonnees;
	}
	
	public Distributeur getDistributeur(String idDistributeur)
	{
		return (Distributeur) donnees.chargerDepuisBase(idDistributeur, "Distributeur");
	}
}
