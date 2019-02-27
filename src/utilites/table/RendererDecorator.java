package utilites.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class RendererDecorator implements TableCellRenderer, SwingConstants, ICustomTable {

	private DefaultTableCellRenderer support = new DefaultTableCellRenderer();

	private Font font;
	private Color background;
	private Color foreground;
	private Color background2;
	private Color foreground2;
	private Border border;
	private Color backgroundSelection;
	private Color foregroundSelection;
	private int alignement;
	private boolean alternerLignes;
	
	public RendererDecorator(){
		font = new Font("Calibri", Font.PLAIN, 13);
		background = Color.WHITE;
		foreground = Color.BLACK;
		backgroundSelection = Color.LIGHT_GRAY;
		foregroundSelection = Color.BLACK;
		alignement = LEFT;
		alternerLignes = false;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = support.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if(alternerLignes){
			miseEnFormeAlternee((JLabel)c, isSelected, row);
		}
		else {
			miseEnFormeStandard((JLabel)c, isSelected);	
		}
		return c;
	}

	private void miseEnFormeStandard(JLabel label, boolean isSelected){
		label.setBorder(border);
		label.setFont(font);
		label.setHorizontalAlignment(alignement);
		if (!isSelected){
			label.setBackground(background);
			label.setForeground(foreground);
		}
		else {
			label.setBackground(backgroundSelection);
			label.setForeground(foregroundSelection);
		}
		
	}

	private void miseEnFormeAlternee(JLabel label, boolean isSelected, int row){
		label.setBorder(border);
		label.setFont(font);
		label.setHorizontalAlignment(alignement);
		if (!isSelected){
			if(row%2==1){
				label.setBackground(background2);
				label.setForeground(foreground2);
			}
			else{
				label.setBackground(background);
				label.setForeground(foreground);
			}
		}
	}

	@Override
	public Font getFontCellule() {
		return font;
	}

	@Override
	public void setFontCellule(Font font) {
		this.font = font;
	}

	@Override
	public Color getBackground1() {
		return background;
	}

	@Override
	public void setBackground1(Color background) {
		this.background = background;
	}

	@Override
	public Color getForeground1() {
		return foreground;
	}

	@Override
	public void setForeground1(Color foreground) {
		this.foreground = foreground;
	}

	@Override
	public Border getBorderCellule() {
		return border;
	}

	@Override
	public void setBorderCellule(Border border) {
		this.border = border;
	}

	@Override
	public Color getBackgroundSelection() {
		return backgroundSelection;
	}

	@Override
	public void setBackgroundSelection(Color backgroundSelection) {
		this.backgroundSelection = backgroundSelection;
	}

	@Override
	public Color getForegroundSelection() {
		return foregroundSelection;
	}

	@Override
	public void setForegroundSelection(Color foregroundSelection) {
		this.foregroundSelection = foregroundSelection;
	}

	@Override
	public Color getBackground2() {
		return background2;
	}

	@Override
	public void setBackground2(Color background2) {
		this.background2 = background2;
	}

	@Override
	public Color getForeground2() {
		return foreground2;
	}

	@Override
	public void setForeground2(Color foreground2) {
		this.foreground2 = foreground2;
	}
	
	@Override
	public int getAlignement() {
		return alignement;
	}

	@Override
	public void setAlignement(int alignement) {
		this.alignement = alignement;
	}

	@Override
	public boolean isAlternerLignes() {
		return alternerLignes;
	}

	@Override
	public void setAlternerLignes(boolean alternerLignes) {
		this.alternerLignes = alternerLignes;
	}

}
