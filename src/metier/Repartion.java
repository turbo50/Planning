package metier;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import entite.*;
import java.util.Iterator;
import java.util.List;
import utilites.MaConnexion;
import utilites.date.EquivalenceDesDates;

public class Repartion {
	private Proclamateur proclamateur=new Proclamateur();
	private Vector<Jour> jours=new Vector<Jour>();// liste des jours compris entre datedebut et datefin avec, le jour de la semaine et la date pour chaque occurences
	private accesData.AccesData ad;
	
	private String dateDebut;//jour pour lequel on effectue la repartition
	private String dateFin;
	private entite.Site site;
	private int tailleMatin,tailleMidi,tailleSoir;
	
	
	private Vector<Etat> etat=new Vector<Etat>();
        private Vector horaire;
	private Vector<Vector<Planning>> vecPlanning=new Vector<Vector<Planning>>();
	
    public Repartion(String dateDebut, String dateFin, entite.Site site, int tailleMatin, int tailleMidi,int tailleSoir,accesData.AccesData ad) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.site = site;
        this.tailleMatin = tailleMatin;
        this.tailleMidi = tailleMidi;
        this.tailleSoir = tailleSoir;
        this.ad=ad;
    }

    public Vector<Vector<Planning>> getVecPlanning() {
        return vecPlanning;
    }

    public void setVecPlanning(Vector<Vector<Planning>> vecPlanning) {
        this.vecPlanning = vecPlanning;
    }
    
   
	

    

	public Vector<Etat> getEtat() {
		return etat;
	}


	public void setEtat(Vector<Etat> etat) {
		this.etat = etat;
	}

       public void initialiseJours(){
           ResultSet rs=null; Connection con=null; PreparedStatement ps=null; CallableStatement cs=null;
           String req="select jour,date_format(datej,'%d-%m-%Y') from tempjour";
           String req1="{call getDays(?,?)}";
           
           try{
               con= new MaConnexion().getInstance();
               cs=con.prepareCall(req1);
               cs.setString(1,new EquivalenceDesDates().getDateEnglish(this.dateDebut));
               cs.setString(2,new EquivalenceDesDates().getDateEnglish(this.dateFin));
               cs.executeUpdate();
               //recuperation du resultat
               ps=con.prepareCall(req);
               rs=ps.executeQuery();
               while(rs.next()){
                   jours.addElement(new Jour(rs.getString(1),rs.getString(2)));
               }
           }catch(Exception e){
			e.printStackTrace();
		}finally{
			try{ rs.close(); ps.close(); con.close();}
			catch(Exception e){
				e.printStackTrace();
			}
		}
           horaire=ad.requeteNative_Vector("select heure_debut from t_Horaire t,site_T_horaire sth where t.idT_horaire=sth.idT_horaire and sth.idsite="+site.getIdsite());
       }
       public Vector getDateRepartion(){
           Vector v=new Vector();
           for(int i=0; i<this.jours.size(); i++){
               v.addElement(jours.elementAt(i).getDate());
           }
           return v;
       }
       public String getJourRepartionFromPosition(int pos){
           return this.jours.elementAt(pos).getJour();
       }
       
      

	//mothode qui effectue une repartition automatique des proclamateurs
	//en se basant sur des criteres choisis au prealable
	//proximite avec le lieu de presentoire
	//temps libre
	//gere aussi les exceptions, c'est-a-dire certains proclamateurs peuvent precher partout
	public void doRepartionAuto(){
             ResultSet rs=null; Connection con=null; PreparedStatement ps=null; CallableStatement cs=null;
           
             String req="{call doPlanningAuto(?,?,?,?)}";
           
           try{
               con= new MaConnexion().getInstance();
               cs=con.prepareCall(req);
               cs.setInt(1,site.getIdsite());
               cs.setInt(2,tailleMatin);
               cs.setInt(3,tailleMidi);
               cs.setInt(4,tailleSoir);
               cs.executeUpdate();
               //recuperation du resultat
               
           }catch(Exception e){
			e.printStackTrace();
		}finally{
			try{  cs.close(); con.close();}
			catch(Exception e){
				e.printStackTrace();
			}
		}
                
	}
         public Vector getDonneeTable(){
            Vector<Vector> GV=new Vector<Vector>();
            Vector V;
            for(int i=0; i<etat.size(); i++){
                V=new Vector();
                V.addElement(etat.elementAt(i).getProclamateur().getNom());
                V.addElement(etat.elementAt(i).getProclamateur().getIdcongregation().getNom());
                V.addElement(etat.elementAt(i).getJour());
                V.addElement(etat.elementAt(i).getHeure_Deb());
                V.addElement(ad.requeteNativeQuery("select heure_fin from t_Horaire t,site_T_horaire sth where t.idT_horaire=sth.idT_horaire and sth.idsite="+site.getIdsite()+" and heure_debut="+etat.elementAt(i).getHeure_Deb()));
                
                GV.addElement(V);
            }
            return GV;
        }
	
	public void ajouteRepar(){
		 Connection con=null; CallableStatement cs=null; PreparedStatement ps=null; ResultSet rs=null;
		String req="insert into Planning(idplanning,idsite,idproclamateur,jour,datej,heure_deb,heure_fin) values(?,?,?,?,?,?,?) ";
                int idplanning=0;
		try{
			con= new MaConnexion().getInstance();
			ps=con.prepareCall("select max(idplanning) from planning");
                        rs=ps.executeQuery();
                        while(rs.next()){
                            idplanning=rs.getInt(1);
                        }
                        
			for(int i=0; i<etat.size(); i++){
				ps=con.prepareStatement(req);
                                ps.setInt(1,idplanning);
				ps.setInt(2,etat.elementAt(i).getSITE().getIdsite());
				ps.setInt(3,etat.elementAt(i).getProclamateur().getIdproclamateur());
				ps.setString(4,etat.elementAt(i).getJour());
                                ps.setString(5,etat.elementAt(i).getDateJour());
				ps.setString(6,etat.elementAt(i).getHeure_Deb());
				ps.setString(7,etat.elementAt(i).getHeure_Fin());
				
				ps.executeUpdate();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{  ps.close(); con.close();}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
        //planning generer automatiquement
	public  void setPlanningAuto(List<Planning> lp){
		
//		Vector vp;
//                Iterator<Planning> it=lp.iterator();
//		while(it.hasNext()){
//                    vecPlanning.addElement(it.next());
//                }
            vecPlanning=new Planning().getDataPlanningForEtatAll(lp);
		
	}
		

        class Jour{
            private String jour;
            private String date;
            
        private Jour(String jour,String date){
            this.jour=jour;
            this.date=date;
        }

        private String getJour() {
            return jour;
        }

        private String getDate() {
            return date;
        }

        private void setJour(String jour) {
            this.jour = jour;
        }

        private void setDate(String date) {
            this.date = date;
        }
            
            
        }
}
