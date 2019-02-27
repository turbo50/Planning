/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Congregation;
import entite.Proclamateur;
import entite.Site;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author daniel
 */
public class JD_ListeProc_Site extends javax.swing.JDialog {
      private accesData.AccesData ad;
      private List<Site> listSite;
      private Site site;

    /**
     * Creates new form JD_ListeProc_Site
     */
    public JD_ListeProc_Site(java.awt.Frame parent, boolean modal,accesData.AccesData ad) {
        super(parent, modal);
        this.ad=ad;
        listSite=this.ad.getListe("Site.findAll");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxSite = new javax.swing.JComboBox(new Site().listSiteNom(listSite));
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListProc = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        JBImp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Liste des proclamateurs per site");

        jLabel2.setText("Site");

        jComboBoxSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSiteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxSite, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(599, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jTableListProc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nom", "E_mail", "N° Tel 1", "N° Tel 2", "N° Tel 3", "Sexe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableListProc);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        JBImp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pdf.gif"))); // NOI18N
        JBImp.setToolTipText("Imprimer la liste");
        JBImp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBImpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JBImp)
                .addContainerGap(735, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JBImp, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSiteActionPerformed
        // TODO add your handling code here:
        site=listSite.get(jComboBoxSite.getSelectedIndex());
        List<Proclamateur> l=(List)site.getProclamateurCollection();

        new utilites.table.GestionTable().chargeTable(jTableListProc, new entite.Proclamateur().dataTableL(l),new String[] { "Nom", "E_mail", "N° Tel. 1", "N° Tel. 2", "N° Tel. 3", "Sexe"},new Class[] {java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class});
    }//GEN-LAST:event_jComboBoxSiteActionPerformed

    private void JBImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBImpActionPerformed
        // TODO add your handling code here:
        String rapport="/propriete/List_Proc_Site.jrxml";
       HashMap map=new HashMap();
       map.put("SiteID",site.getIdsite());
       JD_Imp jdi=new JD_Imp(null, true, rapport, map);
       jdi.setVisible(true);
    }//GEN-LAST:event_JBImpActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBImp;
    private javax.swing.JComboBox jComboBoxSite;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListProc;
    // End of variables declaration//GEN-END:variables
}
