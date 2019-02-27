package utilites;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Ecris_Utilites {
	private Properties prop;
	private String nomFichier;

	public Ecris_Utilites(String nomFichier) {
		this.nomFichier=nomFichier;
		this.prop=new Properties();
	}
	public void setValeurProperties(String cle,String valeur){
		this.prop.setProperty(cle, valeur);
	}
	
	public void saveProperties(){
		try {
    		prop.store(new FileOutputStream(nomFichier), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}
	

}
