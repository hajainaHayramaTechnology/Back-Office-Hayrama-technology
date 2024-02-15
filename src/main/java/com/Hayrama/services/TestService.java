package com.Hayrama.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;

import org.springframework.stereotype.Service;



@Service
public class TestService {
	
	public List<Appel> getById(int idAppel, Connection conn)throws Exception {
		List<Appel> listcursus=new ArrayList<Appel>();
		String requete = "select a.idAppel, a.nomAppelant, a.numeroAppelant, a.resumeAppel, a.idProjet,a.idCategireAppel , p.nomProjet, p.couleur , v.nomVolet, r.nomRegion, d.nomDistrique, d.commune, d.fokotany"
				+ "    from appel a"
				+ "        left join projet p on a.idProjet = p.idProjet"
				+ "        left join volet v on a.idVolet = v.idVolet"
				+ "        left join region r on a.idRegion = r.idRegion "
				+ "        left join distrique d on a.idDistrique = d.idDistrique"
				+ "        Where a.idAppel ="+idAppel;
		ResultSet rs2;
		try (Statement st2 = conn.createStatement()) {
			rs2 = st2.executeQuery(requete);
			while (rs2.next()) {
				System.out.println("nomAppelant: " + rs2.getString("nomAppelant"));
				Appel appel= new Appel();
				appel.setIdAppel(rs2.getInt("idAppel"));
				appel.setNomAppelant(rs2.getString("nomAppelant"));
				appel.setNumeroAppelant(rs2.getString("numeroAppelant"));
				appel.setResumeAppel(rs2.getString("resumeAppel"));
				appel.setIdCategireAppel(rs2.getInt("idCategireAppel"));
					Projet projet=new Projet();
					projet.setNomProjet(rs2.getString("nomProjet"));
					projet.setCouleur(rs2.getString("couleur"));
				appel.setProjet(projet);
					Volet volet=new Volet();
					volet.setNomVolet(rs2.getString("nomVolet"));
				appel.setVolet(volet);
					Region region=new Region();
					region.setNomRegion(rs2.getString("nomRegion"));
				appel.setRegion(region);
					Distrique distrique=new Distrique();
					distrique.setNomDistrique(rs2.getString("nomDistrique"));
					distrique.setCommune(rs2.getString("commune"));
					distrique.setFokotany(rs2.getString("fokotany"));
				appel.setDistrique(distrique);
				listcursus.add(appel);
			}
		}
		rs2.close();
		return listcursus;
	}
	
	
	public List<Appel> findByIdProjet(int id,Connection con ) throws Exception {
		try { 
			List<Appel> refs =  GenericRepo.find(Appel.class, con,"idProjet=?",id);
			return refs;
		} catch (Exception e) {
			if (con != null) {
			}
			e.printStackTrace();
			throw e;
		} 

	}
	
	public Appel findById(int id,Connection con ) throws Exception {
		 
		try { 
			List<Appel> refs =  GenericRepo.find(Appel.class, con, "idAppel=?",id );
			 
			return refs.isEmpty() ? null: refs.get(0);
		} catch (Exception e) {
			if (con != null) {
			}
			e.printStackTrace();
			throw e;
		} 

	}
	
	public List<Appel> findAll(Connection con ) throws Exception {
		try { 
			List<Appel> refs =  GenericRepo.find(Appel.class, con,"");
			return refs;
		} catch (Exception e) {
			if (con != null) {
			}
			e.printStackTrace();
			throw e;
		} 

	}
	
	public void save(Appel input,Connection conn) throws Exception {
		try { 
			GenericRepo.insert(input, conn);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		} finally {
				
		}

	}
	
	public void delete(Appel input,Connection conn) throws Exception {
		try { 
			GenericRepo.delete(input, conn);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		} finally {
				
		}

	}
	
	public void update(Appel input,Connection conn) throws Exception {
		try { 
			GenericRepo.update(input, conn, "idAppel = ?",input.getIdAppel());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
		}

	}

}
