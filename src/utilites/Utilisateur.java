package utilites;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Utilisateur {
   private String Nom,Fonction,Login,Mdp;
   private int Id; 
   private File Photo;//Pour l'insertion et la mise � jour 
   private Image image=null;//Pour l'affichage
   private CallableStatement cs=null;
   private ResultSet rs=null;
   private Vector<Utilisateur> VListe=null;//Il contient la liste de tous les utilisateurs
   
   
   //constructeur sans argument
   public Utilisateur(){
	   
   }
   //constructeur app�l� lorsqu'on veut ajouter un utilisateur
   public Utilisateur(String Nom,String Fonction,String Mdp,String Login,File Photo){
	   this.Fonction=Fonction;
	   this.Login=Login;
	   this.Mdp=Mdp;
	   this.Nom=Nom;
	   this.Photo=Photo;
   }
 //constructeur app�l� lorsqu'on veut afficher un utilisateur
   public Utilisateur(int Id,String Nom,String Fonction,String Mdp,String Login,File Photo){
	   this.Id=Id;
	   this.Fonction=Fonction;
	   this.Login=Login;
	   this.Mdp=Mdp;
	   this.Nom=Nom;
	   this.Photo=Photo;
   }
 //constructeur app�l� lorsqu'on veut se connecter
   public Utilisateur(String Login,String Mdp){
	   this.Login=Login;
	   this.Mdp=Mdp;
   }

   public String getNom() {
	return Nom;
   }

   public void setNom(String nom) {
	Nom = nom;
   }

   public String getFonction() {
	return Fonction;
   }

   public void setFonction(String fonction) {
	Fonction = fonction;
   }

   public String getLogin() {
	return Login;
   }

   public void setLogin(String login) {
	Login = login;
   }

   public String getMdp() {
	return Mdp;
   }

   public void setMdp(String mdp) {
	Mdp = mdp;
   }

   public File getPhoto() {
	return Photo;
   }

   public void setPhoto(File photo) {
	Photo = photo;
   }
   
   public int getId() {
	return Id;
   }

   public Image getImage() {
	return image;
   }
   public Vector<Utilisateur> getVListe() {
	return VListe;
   }
    public void setVListe() {
	VListe = getUserList();
   }
   public void setImage(Image image) {
	this.image = image;
   }
   public void ajouteUser(){
	   Connection con=new MaConnexion().getInstance();
	   try{
		   
		   FileInputStream istreamImage = new FileInputStream(this.getPhoto());
			  
		   cs=con.prepareCall("{call ajouteUser(?,?,?,?,?)}");
		   cs.setString(1, this.getNom());
		   cs.setString(2,this.getFonction());
		   cs.setString(3,this.getLogin());
		   cs.setString(4, this.Mdp);
		   cs.setBinaryStream(5, istreamImage, (int) this.getPhoto().length());
		   cs.executeUpdate();
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   try{
			   cs.close();
			  
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	   
   }
   //pour la mise � jour
   public void updateUser(){
	   Connection con=new MaConnexion().getInstance();
	   try{
		   
		   FileInputStream istreamImage = new FileInputStream(this.getPhoto());
			  
		   cs=con.prepareCall("{call updateUser(?,?,?,?,?,?)}");
		   cs.setString(1, this.getNom());
		   cs.setString(2,this.getFonction());
		   cs.setString(3,this.getLogin());
		   cs.setString(4, this.Mdp);
		   cs.setBinaryStream(5, istreamImage, (int) this.getPhoto().length());
		   cs.setInt(6,this.getId());
		   cs.executeUpdate();
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   try{
			   cs.close();
			  
		   }catch(Exception e){
			   e.printStackTrace();
		   }


	   }
	   
   }
   
   public void deleteUser(){
	   Connection con=new MaConnexion().getInstance();
	   try{
		   cs=con.prepareCall("{call deleteUser(?)}");
		   cs.setInt(1,this.getId());
		   cs.executeUpdate();
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   try{
			   cs.close();
			  
		   }catch(Exception e){
			   e.printStackTrace();
          
		   }
           finally{
           try{
               
           }catch(Exception e){

           }
       }
	   }
   }
   
   public Vector<Utilisateur> getUserList(){
	   Connection con=new MaConnexion().getInstance();
	   Utilisateur U=null;
	   File monImage=null;
	  
	   Vector<Utilisateur> VU=new Vector<Utilisateur>();
	   try{
		   cs=con.prepareCall("{call getUserList()}");
		   rs=cs.executeQuery();
		   while(rs.next()){
			   U=new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),null);
			   //traitement de la photo
			   InputStream is=rs.getBinaryStream(6);
			   byte[] buffer = new byte[1024];
		       int length = 0;	
		       try{
		    	    monImage = new File("C:"+File.separator+"Photo_Test_App_Fond_Doc"+File.separator+"nom.jpg");
		       }catch(Exception e){
		    	   if(!monImage.mkdir()){
					   monImage.delete();
					   monImage.mkdir();
				   }
		       }
		      
			   
			   FileOutputStream ostreamImage = new FileOutputStream(monImage);
			   
		       while((length = is.read(buffer)) != -1)		      	  
		      	    ostreamImage.write(buffer, 0, length);
		       ostreamImage.close();
		       File f=new File(monImage.getAbsolutePath());
			   Image im = ImageIO.read(f);
			   U.setImage(im);
			   VU.addElement(U);
		   }
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   try{
			   cs.close();
			   rs.close();
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	   return VU;
   }
   //charge les noms des utilisateurs
   public Vector<String> chargeNom(){
	      setVListe();
	      Vector<String> V=new Vector<String>();
	      for(int i=0; i<VListe.size(); i++)
	    	  V.addElement(VListe.elementAt(i).getNom());
	      return V;
   }
   public Utilisateur getUtilisateur(String Nom){
	   int i=0;
	     for(i=0; i<VListe.size();i++){
	    	 if(VListe.elementAt(i).getNom().equals(Nom))
	    		 return(Utilisateur)VListe.elementAt(i);
	     }
	     return null;
   }

   public Utilisateur getUtilisateurLog(String Log,String mdp){
	   int i=0;
	     for(i=0; i<VListe.size();i++){
	    	 if(VListe.elementAt(i).getLogin().equals(Log) && VListe.elementAt(i).getMdp().equals(mdp))
	    		 return(Utilisateur)VListe.elementAt(i);
	     }
	     return null;
   }


   
}
  