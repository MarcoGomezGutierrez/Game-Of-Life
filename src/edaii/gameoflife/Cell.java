package edaii.gameoflife;

public class Cell {
	private final int row;
	private final int column;
	private final boolean alive;
	public Cell(final int row, final int column, final boolean alive) {
		this.row = row;
		this.column = column;
		this.alive = alive;
	}
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public boolean isAlive() {
		return this.alive;
	}
	
	public boolean equals(final Object obj) {
		try {
			final Cell cell = (Cell)obj;
			if (cell.getRow() == this.getRow() || cell.getColumn() == this.getColumn() || cell.isAlive() == this.isAlive()) return true;
			else return false;
		}catch (ClassCastException e) {
			return false;
		}
		
	}
	
	public String toString() {
		return "Cell(row=" + getRow() + ", columna=" + getColumn() + ", " + isAlive() +")";
	}
}
