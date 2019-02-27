package metier;

import java.util.Vector;
import entite.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import utilites.Utilitaire;
import utilites.date.FDate;

         //ajouter une ligne dans le tableau de planning
public class Etat {
	private Proclamateur proclamateur;
	private String jour,heure_Deb,Heure_Fin,dateJour;
	private static entite.Site SITE;
        private static Vector<Planning> vecEtat=new Vector<Planning>();
        private static Vector vecListeEtat=new Vector();
        private accesData.AccesData ad;
        private Planning planning;

   
        public Etat() {
        }

    public Etat(Planning planning) {
        this.planning = planning;
    }
	
        
        
        public Etat(Proclamateur proclamateur, String jour, String heure_Deb) {
		super();
		this.proclamateur = proclamateur;
                this.dateJour=dateJour;
		this.heure_Deb = heure_Deb;
	}
	public Etat(Proclamateur proclamateur, String jour,String dateJour, String heure_Deb,accesData.AccesData ad) {
		super();
		this.proclamateur = proclamateur;
		this.jour = jour;
                this.dateJour=dateJour;
		this.heure_Deb = heure_Deb;
                this.ad=ad;
	}

    public String getDateJour() {
        return dateJour;
    }

    public void setDateJour(String dateJour) {
        this.dateJour = dateJour;
    }

        public Vector<Planning> getVecEtat() {
            return vecEtat;
        }

        public void setVecEtat(Vector<Planning> vecEtat) {
            this.vecEtat = vecEtat;
        }
	

        
	public static entite.Site getSITE() {
		return SITE;
	}


	public static void setSITE(entite.Site sITE) {
		SITE = sITE;
	}


	public Proclamateur getProclamateur() {
		return proclamateur;
	}

	public void setProclamateur(Proclamateur proclamateur) {
		this.proclamateur = proclamateur;
	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public String getHeure_Deb() {
		return heure_Deb;
	}

	public void setHeure_Deb(String heure_Deb) {
		this.heure_Deb = heure_Deb;
	}

	public String getHeure_Fin() {
		return Heure_Fin;
	}

	public void setHeure_Fin(String heure_Fin) {
		Heure_Fin = heure_Fin;
	}

    public Vector getVecListeEtat() {
        return vecListeEtat;
    }

    public void setVecListeEtat(Vector vecListeEtat) {
        this.vecListeEtat = vecListeEtat;
    }
	
    //pour la fusion dans le tableur excele
    public int getTailleBlock(Vector<Planning> ve,String jour,Date heure_deb,String dateJ){
        int taille=0;
        for(int i=0; i<ve.size(); i++){
            if(ve.elementAt(i).getIdjour().getNomjour().equalsIgnoreCase(jour) && ve.elementAt(i).getIdtHoraire().getHeureDebut().compareTo(heure_deb)==0 && ve.elementAt(i).getIdjour().getNomjour().equalsIgnoreCase(dateJ))
                taille++;
        }
        return taille;
    }
    //methode qui extrait les dates distincte du planning
    public Vector getDateFromPlanning(Vector<Planning> ve){
        Vector<Date> vs=new Vector<Date>();
        for(int i=0; i<ve.size(); i++){
            if(!existeDoublonDate(vs, ve.elementAt(i).getDatej()))
                vs.addElement(ve.elementAt(i).getDatej());
        }
        return vs;
        
                
    }
    
    //methode qui extrait les jours du planning
    public String getJourFromPlanning(Vector<Planning> ve,Date date){
        String jour="";
        for(int i=0; i<ve.size(); i++){
            if(ve.elementAt(i).getDatej().compareTo(date)==0)
                jour=ve.elementAt(i).getIdjour().getNomjour();
        }
        return jour;
        
                
    }
    
    //methode qui fournit les proclamateurs d'un jour et d'une tranche horaire specifie
    public String getProclamateurs(Vector<Planning> ve,Date heure_deb,Date dateJ){
        StringBuffer sb=new StringBuffer();
        sb.append("");
        for(int i=0; i<ve.size(); i++){
            if(ve.elementAt(i).getDatej().compareTo(dateJ)==0 && ve.elementAt(i).getIdtHoraire().getHeureDebut().compareTo(heure_deb)==0){
                     sb.append(ve.elementAt(i).getIdproclamateur().getNom());
                     sb.append(" \n");
            }
        }
        return sb.toString();
        
    }
        
    
     public boolean existeDoublonDate(Vector<Date> vs,Date date){
            boolean retour=false;
            String d;
            for(int i=0; i<vs.size(); i++){
                if(vs.elementAt(i).compareTo(date)==0)
                    return true;
            }
          
           return retour;
            
            
        }
        public boolean existeDoublon(Vector<Etat> ve){
            boolean retour=false;
            Etat e;
           if(ve.size()>1){
                for(int i=0; i<ve.size()-1; i++){
                    e=ve.elementAt(i);
                     for(int j=i+1; j<ve.size(); j++){
                          if(e.getProclamateur().getIdproclamateur()==ve.elementAt(j).getProclamateur().getIdproclamateur())
                               retour =true;
                     }
                   
                }
           }
           return retour;
            
            
        }
        
         public boolean existeDoublon(Etat e){
            boolean retour=false;
           
           if(vecEtat.size()>0){
                for(int i=0; i<vecEtat.size(); i++){
                   if(vecEtat.elementAt(i).getIdproclamateur().getIdproclamateur()==e.getProclamateur().getIdproclamateur()){
                       if(vecEtat.elementAt(i).getDatej().toString().equalsIgnoreCase(e.getDateJour()))
                           retour=true;
                   }
               }
            
           }
           return retour;
     
        }
//         public void updateEtat(int pos,String NomP,String NomC,String jour,String date,String heureDeb){
//             Vector v=new Vector();
//             Etat e=vecEtat.elementAt(pos);
//             e.setDateJour(jour);
//             e.setJour(date);
//             e.setHeure_Deb(heureDeb);             
//             e.setHeure_Fin(ad.requeteNativeQuery("select heure_fin from t_Horaire t,site_T_horaire sth where t.idsite=sth.idsite and sth.idsite="+SITE.getIdsite()+" and heure_debut="+heureDeb).toString());
//             vecEtat.set(pos, e);
//             v.addElement(NomP);
//             v.addElement(NomC);
//             v.addElement(jour.concat(", "+date));
//             v.addElement(heureDeb);
//             v.addElement(e.getHeure_Fin());
//             vecListeEtat.set(pos,v);
//             
//            
//         }
         //suprimer un proclamateur du planning depuis sa position dans la table des plannings manuels
         public void deleteEtat(int pos){
             vecEtat.removeElementAt(pos);
             vecListeEtat.removeElementAt(pos);
         }
         
         public boolean addEtat(Etat e){
             Vector v=new Vector();
             if(!existeDoublon(e)){
                 
                 v.addElement(e.getProclamateur().getNom());
                 v.addElement(e.getProclamateur().getIdcongregation().getNom());
                 v.addElement(e.getDateJour().concat(", "+e.getJour()));
                 v.addElement(e.getHeure_Deb());
                 v.addElement(ad.requeteNativeQuery("select heure_fin from t_Horaire t,site_T_horaire sth where t.idsite=sth.idsite and sth.idsite="+SITE.getIdsite()+" and heure_debut="+e.getHeure_Deb()).toString());
                 
                 vecListeEtat.addElement(v);
//                 vecEtat.addElement(e);
                 return true;
             }else{
                 return false;
             }
             
         }
         
         public void sauvegarderPlanning(){
//		 Connection con=null; CallableStatement cs=null; PreparedStatement ps=null; ResultSet rs=null;
//		String req="insert into Planning(idproclamateur,datej,idthoraire,idjour) values(?,?,?,?) ";
//                int idplanning=0;
//		try{
//			con= new MaConnexion().getInstance();
//			ps=con.prepareCall("select max(idplanning) from planning");
//                        rs=ps.executeQuery();
//                        while(rs.next()){
//                            idplanning=rs.getInt(1);
//                        }
//                        
//			for(int i=0; i<vecEtat.size(); i++){
//				ps=con.prepareStatement(req);
//                                ps.setInt(1,idplanning+1);
//				ps.setInt(2,vecEtat.elementAt(i).getSITE().getIdsite());
//				ps.setInt(3,vecEtat.elementAt(i).getProclamateur().getIdproclamateur());
//				ps.setString(4,vecEtat.elementAt(i).getDateJour());
//                                ps.setString(5,new utilites.date.FDate().formatDateFrom_FR_to_SQL(vecEtat.elementAt(i).getJour(),"-"));
//				ps.setString(6,vecEtat.elementAt(i).getHeure_Deb());
//				ps.setString(7,vecEtat.elementAt(i).getHeure_Fin());
//                                ps.setString(8,vecEtat.elementAt(i).getSITE().getLieu());
//                                ps.setString(9,vecEtat.elementAt(i).getProclamateur().getNom());
//				
//				ps.executeUpdate();
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			try{  ps.close(); con.close();}
//			catch(Exception e){
//				e.printStackTrace();
//			}
//		}
	}
       
         
     public  void doExcelFile(Vector<Planning> ve,Vector<Date> v_THoraire,Vector<Date> v_THoraireFin,String cheminFichier, Site site) {
         HSSFCell cell,cellL,cellEntete;
         HSSFRow row,rowL;
         HSSFCellStyle cellStyle=null,cellStyleN= null,cellStyleTH= null;
         DateFormat df=DateFormat.getDateInstance();

         //on obtient le vecteur des dates deja trie
         Vector<Date> vecteurDeDate=getDateFromPlanning(ve);
         
         Vector<Date> T_H=v_THoraire;
         Vector<Date> T_HFin=v_THoraireFin;
         
         
    
          HSSFWorkbook wb = new HSSFWorkbook();
          HSSFSheet sheet = wb.createSheet("ma feuille");
          
          HSSFFont fonte = wb.createFont();
          fonte.setFontHeightInPoints((short) 12);
          fonte.setFontName("Castellar");
          fonte.setBoldweight(HSSFFont.U_SINGLE);
          
          HSSFFont fonte1 = wb.createFont();
          fonte1.setFontHeightInPoints((short) 10);
          fonte1.setFontName("Candara");
          fonte1.setBoldweight(HSSFFont.ANSI_CHARSET);
          
          int e=0;
          row = sheet.createRow(0);
          cell = row.createCell((short)0);
          cell.setCellValue("Programme speciale de temoignage public dans les grandes villes\n. Du "+df.format(vecteurDeDate.firstElement())+" Au "+df.format(vecteurDeDate.lastElement())+". Site:"+site.getLieu());
          sheet.addMergedRegion(new Region(0,(short)0,0,(short)vecteurDeDate.size()));
          cellStyle = wb.createCellStyle();
          cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
          cellStyle.setFont(fonte);
          
          cellStyleN = wb.createCellStyle();
          cellStyleN.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
          cellStyleN.setFont(fonte1);
          
          cellStyleTH = wb.createCellStyle();
          cellStyleTH.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
          cellStyleTH.setFont(fonte1);
         
          cell.setCellStyle(cellStyle);
          
          rowL = sheet.createRow(1);
          cellL = rowL.createCell((short)0);
          cell.setCellStyle(cellStyle);
          cellL.setCellValue("Tranches Horaires");
          
          
          for(int j=0; j<T_H.size(); j++){
               String minDeb=  (T_H.elementAt(j).getMinutes()==0 )? "00":""+T_H.elementAt(j).getMinutes();
               String minFin=  (T_HFin.elementAt(j).getMinutes()==0 )? "00":""+T_H.elementAt(j).getMinutes();
               
               row = sheet.createRow(j+2);
               cell = row.createCell((short)0);
               cell.setCellStyle(cellStyleTH);
               cell.setCellValue(T_H.elementAt(j).getHours()+":"+minDeb+" - "+T_HFin.elementAt(j).getHours()+":"+minFin);
               for(int i=0; i<vecteurDeDate.size(); i++){
                   cell.setCellStyle(cellStyle);
                   cellL = rowL.createCell((short)i+1);
                   cellL.setCellValue(getJourFromPlanning(ve,vecteurDeDate.elementAt(i))+", \n"+df.format(vecteurDeDate.elementAt(i)));
                   
                   cell.setCellStyle(cellStyleN);
                   cell = row.createCell((short)i+1);
                   cell.setCellValue(getProclamateurs(ve, T_H.elementAt(j),vecteurDeDate.elementAt(i)));
                   
                
                   
               }   
          }
          FileOutputStream fileOut;
          try {
               fileOut = new FileOutputStream(cheminFichier+".xls");
               wb.write(fileOut);
               fileOut.close();  
          } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
            } catch (IOException e2) {
                  e2.printStackTrace();
            }
          
        
          
    }
       
     
    public  void makeExcelFile(Vector<Vector<Planning>> ve,Vector<Date> v_THoraire,Vector<Date> v_THoraireFin,String cheminFichier, Site site,Vector<Jour> vecJour) {
         HSSFCell cell,cellL,cellEntete;
         HSSFRow row,rowL;
         HSSFCellStyle cellStyle=null,cellStyleN= null,cellStyleTH= null;
         DateFormat df=DateFormat.getDateInstance();

         
         
    
          HSSFWorkbook wb = new HSSFWorkbook();
          HSSFSheet sheet = wb.createSheet("ma feuille");
          
          HSSFFont fonte = wb.createFont();
          fonte.setFontHeightInPoints((short) 12);
          fonte.setFontName("Castellar");
          fonte.setBoldweight(HSSFFont.U_SINGLE);
          
          HSSFFont fonte1 = wb.createFont();
          fonte1.setFontHeightInPoints((short) 10);
          fonte1.setFontName("Candara");
          fonte1.setBoldweight(HSSFFont.ANSI_CHARSET);
          
          int e=0;
          row = sheet.createRow(0);
          cell = row.createCell((short)0);
          cell.setCellValue("Programme speciale de temoignage public dans les grandes villes. Site:"+site.getLieu());
          sheet.addMergedRegion(new Region(0,(short)0,0,(short)8));//la feuille contient en tout 8 colones
          cellStyle = wb.createCellStyle();
          cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
          cellStyle.setFont(fonte);
          
          cellStyleN = wb.createCellStyle();
          cellStyleN.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
          cellStyleN.setFont(fonte1);
          
          cellStyleTH = wb.createCellStyle();
          cellStyleTH.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
          cellStyleTH.setFont(fonte1);
         
          cell.setCellStyle(cellStyle);
          int nbreLigne=1;
          
          System.out.println("Taille vec "+ve.size());
          for(int k=0; k<ve.size(); k++){
//              System.out.println("Bloc planning n "+k+1);
              //on obtient le vecteur des dates deja trie
              int avance=2;
              Vector<Date> vecteurDeDate=getDateFromPlanning(ve.elementAt(k));
         
              Vector<Date> T_H=v_THoraire;
              Vector<Date> T_HFin=v_THoraireFin;
              System.out.println("Taille vec horaire :"+T_H.size());
              for(int j=0; j<T_H.size(); j++){
                      String minDeb=  (T_H.elementAt(j).getMinutes()==0 )? "00":""+T_H.elementAt(j).getMinutes();
                      String minFin=  (T_HFin.elementAt(j).getMinutes()==0 )? "00":""+T_H.elementAt(j).getMinutes();
                      
                      rowL = sheet.createRow(nbreLigne);
                      cellL = rowL.createCell((short)0);
                      cell.setCellStyle(cellStyle);
                      cellL.setCellValue("Periode du :"+df.format(vecteurDeDate.firstElement())+" Au "+df.format(vecteurDeDate.lastElement()));
                      sheet.addMergedRegion(new Region(nbreLigne,(short)0,nbreLigne,(short)8));//la feuille contient en tout 8 colones
                      
                      rowL = sheet.createRow(nbreLigne+1);
                      cellL = rowL.createCell((short)0);
                      cell.setCellStyle(cellStyle);
                      cellL.setCellValue("Tranches Horaires");
                      
                      row = sheet.createRow(nbreLigne+avance);
                      cell = row.createCell((short)0);
                      cell.setCellStyle(cellStyleTH);
                      cell.setCellValue(T_H.elementAt(j).getHours()+":"+minDeb+" - "+T_HFin.elementAt(j).getHours()+":"+minFin);
                      for(int i=0; i<vecteurDeDate.size(); i++){
                             
                             cell.setCellStyle(cellStyle);
                             cellL = rowL.createCell((short)i+1);
                             cellL.setCellValue(getJourFromPlanning(ve.elementAt(k),vecteurDeDate.elementAt(i))+", \n"+df.format(vecteurDeDate.elementAt(i)));
                   
//                             cell.setCellStyle(cellStyleN);
                             cell = row.createCell((short)i+1);
                             cell.setCellValue(getProclamateurs(ve.elementAt(k), T_H.elementAt(j),vecteurDeDate.elementAt(i)));                   
                      }
                      avance+=1;
                }
              nbreLigne=nbreLigne+5;//Pour tenir compte du nombre de ligne parcourus dans le rapport
          }
          
          FileOutputStream fileOut;
          try {
               fileOut = new FileOutputStream(cheminFichier+".xls");
               wb.write(fileOut);
               fileOut.close();  
          } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
            } catch (IOException e2) {
                  e2.printStackTrace();
            }
          
        
          
    }
       
	

}
