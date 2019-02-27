package spantable;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;

public class SpanTable extends JTable implements SpanListener {
	private static final long serialVersionUID = 2474734650553132129L;

	/**
	 * Constructor.
	 * @param tableModel Model of the table
	 */
	public SpanTable(SpanTableModel tableModel) {
		super(tableModel);
		tableModel.getSpanModel().addSpanListener(this);
		setUI(new SpanTableUI());
	}

	/**
	 * Overrides the superclass getCellRect() to return the rectangle for the spanned
	 * cell if the row and column fall into the spanned cell's boundaries.
	 * @param row The row
	 * @param column The column
	 * @param includeSpacing Include spacing between cells
	 * @return The rectangle boundaries of the cell or spanned cell.
	 */
	@Override
	public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
		// Required because getCellRect is used in JTable constructor:
		if (getSpanModel() == null)
			return super.getCellRect(row, column, includeSpacing);
		Rectangle rval;

		Span span = getSpanModel().getDefinedSpan(row, column);
		if (span != null) {
			// Get top-left corner, and union with bottom-right corner:
			rval = super.getCellRect(span.getRow(), span.getColumn(), includeSpacing);
			rval = rval.union(super.getCellRect(span.getRow() + span.getHeight() - 1,
				span.getColumn() + span.getWidth() - 1, includeSpacing));
		} else {
			rval = super.getCellRect(row, column, includeSpacing);
		}
		return rval;
	}

	@Override
	public int rowAtPoint(Point p) {
		int row = super.rowAtPoint(p);
		if (row < 0) {
			return row;
		}
		int col = super.columnAtPoint(p);
		Span span = getSpanModel().getDefinedSpan(row, col);
		return span != null ? span.getActiveRow() : row;
	}

	@Override
	public int columnAtPoint(Point p) {
		int col = super.columnAtPoint(p);
		// -1 is returned by columnAtPoint if the point is not in the table
		if (col < 0)
			return col;
		int row = super.rowAtPoint(p);
		Span span = getSpanModel().getDefinedSpan(row, col);
		return span != null ? span.getActiveColumn() : col;
	}

	public void spanAdded(SpanEvent sme) {
		Span span = sme.getSpan();
		tableChanged(new TableModelEvent(getModel(), span.getRow(),
			span.getRow() + span.getHeight() - 1));
	}

	public void spanDeleted(SpanEvent sme) {
		Span span = sme.getSpan();
		tableChanged(new TableModelEvent(getModel(), span.getRow(),
			span.getRow() + span.getHeight() - 1));
	}

	/**
	 * Gets the column span map.
	 * @return The column span map
	 */
	private SpanModel getSpanModel() {
		return ((SpanTableModel)getModel()).getSpanModel();
	}
}

