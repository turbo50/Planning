package utilites.table;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 * <b>Tableau est la classe qui d�finit un tableau en Swing</b>
 * <p>
 * <ul>
 * <li>Un tableau d'entetes</li>
 * <li>Un booleen editable ou non</li>
 * <li>Le style du tableau</b>
 * </ul>
 * </p>
 * <p>
 * De plus, cette classe permet d'ajouter et supprimer des lignes au tableau
 * </p>
 * 
 * @author Julien Gauchet
 * @version 1.0
 *
 */
public class Tableau extends JPanel implements ICustomTable, ConstantesStyles, SwingConstants {

	/**
	 * L'uid
	 */
	private static final long serialVersionUID = -1572744239267031822L;

	/**
	 * La JTable affich�e
	 */
	private JTable table;

	/**
	 * Le renderer
	 */
	private RendererDecorator renderer;
	
	/**
	 * Le style courant
	 */
	private int style;
	
	/**
	 * Constructeur de tableau
	 * @param entetes
	 * 	La ligne des entetes (definit entre autres le nombre de colonnes � afficher)
	 * @param editable
	 * 	booleen vrai si les cellules sont �ditables
	 */
	public Tableau(final Object[] entetes, final boolean editable){
		setLayout(new GridLayout(1, 0));
		Object[][] data = new Object[0][entetes.length];
		DefaultTableModel model = new DefaultTableModel(data, entetes){
			private static final long serialVersionUID = 8142475658077955728L;
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return editable;
			}
		};
		table=new JTable(model);
		renderer = new RendererDecorator();
		table.setDefaultRenderer(Object.class, renderer);
		table.setOpaque(false);
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
	}

	public Tableau(final Object[] entetes, final boolean editable, int style){
		this(entetes, editable);
		setStyle(style);
	}

	/**
	 * Procedure qui permet d'ajouter une ligne vide au tableau
	 */
	public void ajouter(){
		((DefaultTableModel)(table.getModel())).addRow(new String[table.getColumnCount()]);
	}

	/**
	 * Procedure qui supprime la ligne selectionnee
	 */
	public void supprimer(){
		if(table.getSelectedRow()!=-1){
			((DefaultTableModel)(table.getModel())).removeRow(table.getSelectedRow());
		}
	}

	/**
	 * Procedure qui permet d'ajouter une ligne
	 * @param donnees
	 * 	la ligne � ajouter
	 */
	public void ajouterLigne(Object[] donnees){
		((DefaultTableModel)(table.getModel())).addRow(donnees);
	}

	/**
	 * Methode d'acces � la table
	 * @return la table contenue dans le panel
	 */
	public JTable getTable() {
		return table;
	}

	public void setStyle(int style){
		this.style=style;
		switch(style){
		case STYLE_NORMAL :
			break;
		case STYLE_GRIS :
			setCouleurHeader(new Color(50, 50, 50));
			setForegroundHeader(Color.WHITE);
			setFontHeader(new Font("Calibri", Font.BOLD, 14));
			setAlternerLignes(true);
			setBackground1(new Color(130, 130, 130));
			setBackground2(Color.LIGHT_GRAY);
			setForeground2(Color.BLACK);
			setAlignement(CENTER);
			break;
		case STYLE_BLEU :
			setCouleurHeader(new Color(57,153,249));
			setForegroundHeader(Color.WHITE);
			setFontHeader(new Font("Calibri", Font.BOLD, 14));
			setAlternerLignes(true);
			setBackground2(new Color(197,224,252));
			setForeground2(Color.BLACK);
			setAlignement(CENTER);
			break;
		case STYLE_ROUGE :
			setCouleurHeader(new Color(192,10,23));
			setForegroundHeader(Color.WHITE);
			setFontHeader(new Font("Calibri", Font.BOLD, 14));
			setAlternerLignes(true);
			setBackground2(new Color(255,179,184));
			setForeground2(Color.BLACK);
			setAlignement(CENTER);
			break;
		}
	}
	
	public int getStyle(){
		return style;
	}
	
	@Override
	public Color getBackground2() {
		return renderer.getBackground2();
	}

	@Override
	public Color getBackgroundSelection() {
		return renderer.getBackgroundSelection();
	}

	@Override
	public Color getForeground2() {
		return renderer.getForeground2();
	}

	@Override
	public Color getForegroundSelection() {
		return renderer.getForegroundSelection();
	}

	@Override
	public void setBackground2(Color background2) {
		renderer.setBackground2(background2);
	}

	@Override
	public void setBackgroundSelection(Color couleur) {
		renderer.setBackgroundSelection(couleur);
	}

	@Override
	public void setForeground2(Color foreground2) {
		renderer.setForeground2(foreground2);
	}

	@Override
	public void setForegroundSelection(Color couleur) {
		renderer.setForegroundSelection(couleur);
	}

	@Override
	public Color getBackground1() {
		return renderer.getBackground1();
	}

	@Override
	public Border getBorderCellule() {
		return renderer.getBorderCellule();
	}

	@Override
	public Font getFontCellule() {
		return renderer.getFontCellule();
	}

	@Override
	public Color getForeground1() {
		return renderer.getForeground1();
	}

	@Override
	public void setBackground1(Color couleur) {
		renderer.setBackground1(couleur);	
	}

	@Override
	public void setBorderCellule(Border border) {
		renderer.setBorderCellule(border);
	}

	@Override
	public void setFontCellule(Font font) {
		renderer.setFontCellule(font);
	}

	@Override
	public void setForeground1(Color couleur) {
		renderer.setForeground1(couleur);
	}

	@Override
	public int getAlignement() {
		return renderer.getAlignement();
	}

	@Override
	public void setAlignement(int alignement) {
		renderer.setAlignement(alignement);
	}

	public Color getCouleurHeader() {
		return getTable().getTableHeader().getBackground();
	}

	public void setCouleurHeader(Color couleurHeader) {
		getTable().getTableHeader().setBackground(couleurHeader);
	}
	
	public Color getForegroundHeader() {
		return getTable().getTableHeader().getForeground();
	}

	public void setForegroundHeader(Color couleurHeader) {
		getTable().getTableHeader().setForeground(couleurHeader);
	}
	
	public void setFontHeader(Font font){
		getTable().getTableHeader().setFont(font);
	}
	
	public Font getFontHeader(Font font){
		return getTable().getTableHeader().getFont();
	}

	@Override
	public boolean isAlternerLignes() {
		return renderer.isAlternerLignes();
	}

	@Override
	public void setAlternerLignes(boolean alternerLignes) {
		renderer.setAlternerLignes(alternerLignes);		
	}

}
