import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import noixcoopDAO.Commande;
import noixcoopDAO.CommandeDAO;
import noixcoopDAO.Distributeur;
import noixcoopDAO.DistributeurDAO;
import noixcoopDAO.Produit;
import noixcoopDAO.ProduitDAO;

public class Main
{
	private static Scanner sc;

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub

		/* Déclaration & Initialisation des Attributs */
		int choix = 0;
		sc = new Scanner(System.in);

		/* Menu Principal */
		printMenuPrincipal();
	}

	private static void printMenuPrincipal()
	{
		int choix = 0;

		/* Affichage du menu */
		do
		{

			System.out.print(
					"****** MENU PRINCIPAL ******\n1. Lister les distributeurs\n2. Charger un distributeur\n4. Entrer dans le menu de gestion\n5. Quitter\nVotre choix ? ");

			choix = sc.nextInt();

			switch (choix)
			{
				case 1:
					/*
					 * Affiche la liste des Distributeurs
					 * 
					 */
					DistributeurDAO nDAO = new DistributeurDAO();
					try
					{
						for (Distributeur x : nDAO.getAll())
						{
							System.out.println(x.toString());
						}
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
					}
					break;
				case 2:
					/* Chargement d'un distributeur et du menu dédié */
					System.out.print("Entre la référence du distributeur : ");
					printMenuDistributeur(sc.next());
					break;
				case 3:
					/* Vide */

					break;
				case 4:
					/* Chargement du menu de gestion */
					printMenuGestion();
					break;
				case 5:
					;
					break;
				default:
					/* On reboucle */
					choix = 0;
					break;
			}
		} while (choix != 5);
	}

	private static void printMenuGestion()
	{
		int choix = 0;

		/* Affichage du Menu Gestion */
		do
		{
			System.out.print(
					"****** MENU GESTION ******\nQue souhaitez vous gérer ?\n1. Distributeur\n2. Commande\n3. Produit\n4. Sortir\nVotre Choix ? ");

			choix = sc.nextInt();

			switch (choix)
			{
				case 1:
					printMenuGestion("Distributeur");
					break;
				case 2:
					printMenuGestion("Commande");
					break;
				case 3:
					printMenuGestion("Produit");
					break;
				case 4:
					;
					break;
				default:
					/* On reboucle */
					choix = 0;
					break;
			}
		} while (choix != 4);
	}

	private static void printMenuGestion(String classe)
	{
		int choix = 0;
		DistributeurDAO dDAO = null;
		CommandeDAO cDAO = null;
		ProduitDAO pDAO = null;

		switch (classe)
		{
			case "Distributeur":
				dDAO = new DistributeurDAO();
				break;
			case "Commande":
				cDAO = new CommandeDAO();
				break;
			case "Produit":
				pDAO = new ProduitDAO();
				break;
			default:
				break;
		}

		do
		{
			System.out.println("****** MENU GESTION " + classe
					+ " ******\nQue souhaitez vous faire ?\n1. Lister tous les champs\n2. Lister un champn\n3. Ajouter un champ\n4. Modifier un champ\n5. Supprimer un champ\n6. Sortir\nVotre choix ? ");
			choix = sc.nextInt();

			if (dDAO == null && cDAO == null && pDAO == null)
			{
				System.out.println("Cette classe n'existe pas, impossible de continuer. Retour au menu précédent.");
				break;
			}

			switch (choix)
			{
				case 1:
					/* Lister tous les champs */
					if (dDAO != null)
					{
						try
						{
							ArrayList<Distributeur> list = dDAO.getAll();

							for (Distributeur x : dDAO.getAll())
							{
								System.out.println(x.toString());
							}
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
						}

					}
					else if (cDAO != null)
					{
						ArrayList<Commande> list = cDAO.getAll();

						for (Commande x : cDAO.getAll())
						{
							System.out.println(x.toString());
						}
					}
					else if (pDAO != null)
					{
						try
						{
							ArrayList<Produit> list = pDAO.getAll();

							for (Produit x : pDAO.getAll())
							{
								System.out.println(x.toString());
							}
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
						}
					}
					break;
				case 2:
					/* Lister un champ */
					System.out.print("ID du champ à lister (INT) ? ");
					int id = sc.nextInt();

					if (dDAO != null)
					{
						try
						{
							Distributeur element = dDAO.get(id);
							System.out.println(element.toString());
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
						}
					}
					else if (cDAO != null)
					{
						try
						{
							Commande element = cDAO.get(id);
							System.out.println(element.toString());
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
						}
					}
					else if (pDAO != null)
					{
						try
						{
							Produit element = pDAO.get(id);
							System.out.println(element.toString());
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
						}
					}
					break;
				case 3:
					/* Ajouter un champ */
					if (dDAO != null)
					{
						System.out.print("Quel est la référence ? ");
						String ref = sc.next();
						System.out.print("Quel est le nom ? ");
						String nom = sc.next();
						Distributeur distrib = new Distributeur(ref, nom);

						try
						{
							dDAO.add(distrib);
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
						}
					}
					else if (cDAO != null)
					{
						Calendar cal = Calendar.getInstance();

						System.out.print("Quel est l'id du produit commandé ? ");
						int idProduit = sc.nextInt();
						System.out.print("Quel est le prix hors taxe ? ");
						float prixHT = sc.nextFloat();
						System.out.print("Quel est le conditionnement ? ");
						if (sc.hasNextLine())
						{
							sc.nextLine();
						}
						String conditionnement = sc.nextLine();
						System.out.print("Quel est la quantite ? ");
						int quantite = sc.nextInt();
						System.out.print("Quel est l'année du conditionnement ? (Format YYYY");
						cal.set(Calendar.YEAR, sc.nextInt());
						System.out.print("Quel est le mois du conditionnement ? (Format MM");
						cal.set(Calendar.MONTH, sc.nextInt());
						System.out.print("Quel est le jour du conditionnement ? (Format DD");
						cal.set(Calendar.DAY_OF_MONTH, sc.nextInt());

						Commande commande = new Commande(pDAO.get(idProduit), prixHT, conditionnement, quantite, cal);

						try
						{
							cDAO.add(commande);
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if (pDAO != null)
					{
						System.out.print("Quel est la variété ? ");
						String variete = sc.nextLine();
						System.out.print("Quel est le type ? ");
						String type = sc.nextLine();
						System.out.print("Quel est le calibre ? ");
						int calibre = sc.nextInt();

						Produit produit = new Produit(variete, type, calibre);

						try
						{
							pDAO.add(produit);
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
						}
					}
					break;
				case 4:
					/* Modifier un champ */
					System.out.print("Quel est l'ID du champs que vous souhaitez modifier ? ");
					int idChamps = sc.nextInt();
					System.out.println("Si vous ne souhaitez pas modifier un champ, entrer 0");

					if (dDAO != null)
					{
						Distributeur distributeur;
						try
						{
							distributeur = dDAO.get(idChamps);

							System.out.println("Vous êtes actuellement en train de modifier le distributeur "
									+ distributeur.getId());
							System.out.print(
									"Sa référence est " + distributeur.getId() + ". Quel est sa nouvelle référence ? ");
							String ref = sc.next();
							System.out.print("Son nom est " + distributeur.getNom() + ". Quel est le nom ? ");
							String nom = sc.next();
							distributeur = new Distributeur(distributeur.getIdInt(), ref, nom);

							dDAO.update(distributeur);
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
						}

					}
					else if (cDAO != null)
					{
						Calendar cal = Calendar.getInstance();
						Calendar cal2 = Calendar.getInstance();

						Commande commande = cDAO.get(idChamps);

						System.out.println(
								"Vous êtes actuellement en train de modifier la commande n° " + commande.getId());
						System.out.print("L'id du produit commandé est " + commande.getLeProduit().getId()
								+ ". Quel est le nouvel id du produit commandé ? ");
						int idProduit = sc.nextInt();
						System.out.print("Le prix hors taxe est " + commande.getPrixHt()
								+ ". Quel est le nouveau prix hors taxe ? ");
						float prixHT = sc.nextFloat();
						System.out.print("Le conditionnement est " + commande.getConditionnement()
								+ ". Quel est le nouveau conditionnement ? ");
						String conditionnement = sc.nextLine();
						System.out.print("La quantité est " + commande.getQuantité() + ". Quel est la quantite ? ");
						int quantite = sc.nextInt();

						System.out
								.print("La date de conditionnement est " + commande.getDateConditionnement().toString()
										+ "\nChanger la date (Entrer oui pour accepter, autre pour refuser) ? ");
						if (sc.next().equals("oui"))
						{
							System.out.print("Quel est la nouvelle année du conditionnement ? (Format YYYY)");

							System.out.print("Quel est le nouveau mois du conditionnement ? (Format MM");
							cal.set(Calendar.MONTH, sc.nextInt());
							System.out.print("Quel est le nouveau jour du conditionnement ? (Format DD");
							cal.set(Calendar.DAY_OF_MONTH, sc.nextInt());
						}

						System.out.print("La date d'envoie est " + commande.getDateEnvoi().toString()
								+ "\nChanger la date (Entrer oui pour accepter, autre pour refuser) ? ");
						if (sc.next().equals("oui"))
						{
							System.out.print("Quel est la nouvelle année d'envoie (Format YYYY) ? ");
							cal2.set(Calendar.YEAR, sc.nextInt());
							System.out.print("Quel est le nouveau mois d'envoie (Format MM) ? ");
							cal2.set(Calendar.MONTH, sc.nextInt());
							System.out.print("Quel est le nouveau jour d'envoie ? (Format DD) ? ");
							cal2.set(Calendar.DAY_OF_MONTH, sc.nextInt());

						}

						/* On verifie quel données change */
						if (idProduit == 0)
						{
							idProduit = commande.getLeProduit().getId();
						}
						if (prixHT == 0)
						{
							prixHT = commande.getPrixHt();
						}
						if (quantite == 0)
						{
							quantite = commande.getQuantité();
						}
						if (conditionnement.equals("0"))
						{
							conditionnement = commande.getConditionnement();
						}

						commande = new Commande(commande.getId(), pDAO.get(idProduit), prixHT, conditionnement,
								quantite, cal, cal2);

						try
						{
							cDAO.update(commande);
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
						}
					}
					else if (pDAO != null)
					{
						Produit produit = pDAO.get(idChamps);

						System.out.println(
								"Vous êtes actuellement en train de modifier le produit n° " + produit.getId());
						System.out
								.print("La variété est " + produit.getVariete() + ". Quel est la nouvelle variété ? ");
						if (sc.hasNextLine())
						{
							sc.nextLine();
						}
						String variete = sc.nextLine();
						System.out.print("Le type est " + produit.getType() + ". Quel est le nouveau type ? ");
						String type = sc.nextLine();
						System.out.print("Le calibre est " + produit.getType() + ". Quel est le nouveau calibre ? ");
						int calibre = sc.nextInt();

						if (variete.equals("0"))
						{
							variete = produit.getVariete();
						}
						if (type.equals("0"))
						{
							type = produit.getType();
						}
						if (calibre == 0)
						{
							calibre = produit.getCalibre();
						}

						produit = new Produit(produit.getId(), variete, type, calibre);

						try
						{
							pDAO.update(produit);
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				case 5:
					/* Supprimer un champ */
					System.out.print("Quel est l'ID du champs que vous souhaitez supprimer ? ");
					int idChamp = sc.nextInt();
					System.out.print("Etes vous sur ? Entrer 'oui' pour confirmer : ");

					if (sc.next().equals("oui"))
					{
						if (dDAO != null)
						{
							try
							{
								System.out.println("Champs numéro n°" + idChamp + " en cours de suppression.");

								Distributeur dib = dDAO.get(idChamp);

								dDAO.delete(dib);
							}
							catch (Exception e)
							{
								// TODO Auto-generated catch block

								e.printStackTrace();
							}
						}
						else if (cDAO != null)
						{
							try
							{
								cDAO.delete(cDAO.get(idChamp));
							}
							catch (Exception e)
							{
								// TODO Auto-generated catch block
							}
						}
						else if (pDAO != null)
						{
							try
							{
								pDAO.delete(pDAO.get(idChamp));
							}
							catch (Exception e)
							{
								// TODO Auto-generated catch block
							}
						}
					}
					break;
				case 6:
					;
					break;
				default:
					/* On reboucle */
					choix = 0;
					break;
			}
		} while (choix != 6);

	}

	private static void printMenuDistributeur(String idDistributeur)
	{
		PersistanceSQL pSQL = new PersistanceSQL();
		GestionCommandes gCommande = new GestionCommandes(pSQL);

		Distributeur distributeur = (Distributeur) gCommande.getDistributeur(idDistributeur);

		ArrayList<Commande> commandeEnCours = distributeur.getCommandesEnCours();

		System.out.println("Commande en cours : ");
		printListCommande(commandeEnCours);
	}

	private static void printListCommande(ArrayList<Commande> L)
	{
		try
		{
			if (L != null)
			{
				for (Commande x : L)
				{
					System.out.println(x.toString());
				}
			}
			else
			{
				System.out.println("Aucune Commande.");
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
