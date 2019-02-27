/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

/**
 *
 * @author daniel
 */
public class Plan {
    private String date;
    private int idplan;

    public Plan( String date, int idplan) {
        this.date = date;
        
        this.idplan = idplan;
    }
    
    

 

    public String getDatemax() {
        return date;
    }

    public void setDatemax(String datemax) {
        this.date = datemax;
    }

    public int getIdplan() {
        return idplan;
    }

    public void setIdplan(int idplan) {
        this.idplan = idplan;
    }
    
    
}
