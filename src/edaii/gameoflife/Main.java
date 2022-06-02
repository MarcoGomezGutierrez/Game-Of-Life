package edaii.gameoflife;

import java.util.List;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		final GridWindow win = new GridWindow(800, 600);
		List<List<Cell>> grid = GameOfLife.createGrid(100, 100);
		while (true) {
			grid = GameOfLife.advance(grid);
			win.setGrid(GameOfLife.computeGridColors(grid));
			win.repaint();
			Thread.sleep(100);
		}
	}
}
