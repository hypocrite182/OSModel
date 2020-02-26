package File_xitong;
 
import java.awt.Color;
import java.awt.Component;
 
import java.util.List;

 
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class EvenOddRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	private List<String[]> positioins;

	public EvenOddRenderer(List<String[]> _positioins) {
		this.positioins = _positioins;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {

		for (int i = 0; i < table.getRowCount(); i++) {
			if (row == i) {
				this.setBackground(Color.yellow);
			}
		}
		for (String[] rowAndCol : this.positioins) {
			int _row = Integer.valueOf(rowAndCol[0]);
			int _col = Integer.valueOf(rowAndCol[1]);

			if (_row == row && _col == col) {
				this.setBackground(Color.red);
			}
		}
		this.setText(value.toString());
		return this;
	}
}
