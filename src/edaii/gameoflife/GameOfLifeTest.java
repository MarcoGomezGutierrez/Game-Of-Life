package edaii.gameoflife;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class GameOfLifeTest {
	@Test
	void testCreateGrid() {
		final List<List<Cell>> grid = GameOfLife.createGrid(3, 3);
		assertEquals(3, grid.size());
		for (int r = 0; r < grid.size(); r++) {
			final List<Cell> row = grid.get(r);
			assertEquals(3, row.size());
			for (int c = 0; c < row.size(); c++) {
				final Cell cell = row.get(c);
				assertEquals(r, cell.getRow());
				assertEquals(c, cell.getColumn());
			}
		}
	}

	@Test
	void testAdvanceUnderpopulation0() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		final List<List<Cell>> expected = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, false), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		assertEquals(expected, GameOfLife.advance(grid));
		assertEquals(expected.toString(), GameOfLife.advance(grid).toString());
	}

	@Test
	void testAdvanceUnderpopulation1() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, true), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		final List<List<Cell>> expected = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, false), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		assertEquals(expected, GameOfLife.advance(grid));
		assertEquals(expected.toString(), GameOfLife.advance(grid).toString());
	}

	@Test
	void testAdvanceLives2() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, true), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, true)));
		final List<List<Cell>> expected = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		assertEquals(expected, GameOfLife.advance(grid));
		assertEquals(expected.toString(), GameOfLife.advance(grid).toString());
	}

	@Test
	void testAdvanceLives3() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, true), new Cell(0, 1, false), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, true)));
		final List<List<Cell>> expected = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, true), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, true)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		assertEquals(expected, GameOfLife.advance(grid));
		assertEquals(expected.toString(), GameOfLife.advance(grid).toString());
	}

	@Test
	void testAdvanceOverpopulation() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, true), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, true)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, true)));
		final List<List<Cell>> expected = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, true), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, false), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, true), new Cell(2, 2, true)));
		assertEquals(expected, GameOfLife.advance(grid));
		assertEquals(expected.toString(), GameOfLife.advance(grid).toString());
	}

	@Test
	void testAdvanceReproduction() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, false), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, false), new Cell(1, 2, true)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, true)));
		final List<List<Cell>> expected = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, true)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		assertEquals(expected, GameOfLife.advance(grid));
		assertEquals(expected.toString(), GameOfLife.advance(grid).toString());
	}

	@Test
	void testCountAliveNeighboursWith0ThisDead() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, false), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		assertEquals(0, GameOfLife.countAliveNeighbours(grid, 1, 1));
	}

	@Test
	void testCountAliveNeighboursWith0ThisAlive() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		assertEquals(0, GameOfLife.countAliveNeighbours(grid, 1, 1));
	}

	@Test
	void testCountAliveNeighboursWith3() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, true), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, true), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, true)));
		assertEquals(3, GameOfLife.countAliveNeighbours(grid, 1, 1));
	}

	@Test
	void testCountAliveNeighboursWithAllAlive() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, true), new Cell(0, 1, true), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, true), new Cell(1, 1, true), new Cell(1, 2, true)),
				Arrays.asList(new Cell(2, 0, true), new Cell(2, 1, true), new Cell(2, 2, true)));
		assertEquals(8, GameOfLife.countAliveNeighbours(grid, 1, 1));
	}
	
	
	@Test
	void testComputeGridColorsDead() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, false), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		final List<List<Color>> expected = Arrays.asList(Arrays.asList(Color.black, Color.black, Color.black),
				Arrays.asList(Color.black, Color.black, Color.black),
				Arrays.asList(Color.black, Color.black, Color.black));
		assertEquals(expected, GameOfLife.computeGridColors(grid));
	}

	// Propuesta de Mejora

	@Test
	void testComputeGridColors0() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, false), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		final List<List<Color>> expected = Arrays.asList(Arrays.asList(Color.black, Color.black, Color.black),
				Arrays.asList(Color.black, Color.white.darker().darker().darker().darker(), Color.black),
				Arrays.asList(Color.black, Color.black, Color.black));
		assertEquals(expected, GameOfLife.computeGridColors(grid));
	}

	@Test
	void testComputeGridColors1() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, true), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, false), new Cell(2, 2, false)));
		final List<List<Color>> expected = Arrays.asList(
				Arrays.asList(Color.black, Color.white.darker().darker().darker().darker(), Color.black),
				Arrays.asList(Color.black, Color.white.darker().darker().darker().darker(), Color.black),
				Arrays.asList(Color.black, Color.black, Color.black));
		assertEquals(expected, GameOfLife.computeGridColors(grid));
	}

	@Test
	void testComputeGridColors2() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, true), new Cell(0, 2, false)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, true), new Cell(2, 2, false)));
		final List<List<Color>> expected = Arrays.asList(
				Arrays.asList(Color.black, Color.white.darker().darker().darker().darker(), Color.black),
				Arrays.asList(Color.black, Color.white.darker().darker().darker(), Color.black),
				Arrays.asList(Color.black, Color.white.darker().darker().darker().darker(), Color.black));
		assertEquals(expected, GameOfLife.computeGridColors(grid));
	}

	@Test
	void testComputeGridColors3() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, true), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, true), new Cell(2, 2, false)));
		final List<List<Color>> expected = Arrays.asList(
				Arrays.asList(Color.black, Color.white.darker().darker().darker(),
						Color.white.darker().darker().darker()),
				Arrays.asList(Color.black, Color.white.darker().darker().darker(), Color.black),
				Arrays.asList(Color.black, Color.white.darker().darker().darker().darker(), Color.black));
		assertEquals(expected, GameOfLife.computeGridColors(grid));
	}

	@Test
	void testComputeGridColors4() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, true), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, false), new Cell(2, 1, true), new Cell(2, 2, true)));
		final List<List<Color>> expected = Arrays.asList(
				Arrays.asList(Color.black, Color.white.darker().darker().darker(),
						Color.white.darker().darker().darker()),
				Arrays.asList(Color.black, Color.white.darker().darker(), Color.black), Arrays.asList(Color.black,
						Color.white.darker().darker().darker(), Color.white.darker().darker().darker()));
		assertEquals(expected, GameOfLife.computeGridColors(grid));
	}

	@Test
	void testComputeGridColors5() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, false), new Cell(0, 1, true), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, true), new Cell(2, 1, true), new Cell(2, 2, true)));
		final List<List<Color>> expected = Arrays.asList(
				Arrays.asList(Color.black, Color.white.darker().darker().darker(),
						Color.white.darker().darker().darker()),
				Arrays.asList(Color.black, Color.white.darker().darker(), Color.black),
				Arrays.asList(Color.white.darker().darker().darker(), Color.white.darker().darker().darker(),
						Color.white.darker().darker().darker()));
		assertEquals(expected, GameOfLife.computeGridColors(grid));
	}

	@Test
	void testComputeGridColors6() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, true), new Cell(0, 1, true), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, false), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, true), new Cell(2, 1, true), new Cell(2, 2, true)));
		final List<List<Color>> expected = Arrays.asList(
				Arrays.asList(Color.white.darker().darker().darker(), Color.white.darker().darker().darker(),
						Color.white.darker().darker().darker()),
				Arrays.asList(Color.black, Color.white.darker(), Color.black),
				Arrays.asList(Color.white.darker().darker().darker(), Color.white.darker().darker().darker(),
						Color.white.darker().darker().darker()));
		assertEquals(expected, GameOfLife.computeGridColors(grid));
	}

	@Test
	void testComputeGridColors7() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, true), new Cell(0, 1, true), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, true), new Cell(1, 1, true), new Cell(1, 2, false)),
				Arrays.asList(new Cell(2, 0, true), new Cell(2, 1, true), new Cell(2, 2, true)));
		final List<List<Color>> expected = Arrays.asList(
				Arrays.asList(Color.white.darker().darker().darker(), Color.white.darker().darker(),
						Color.white.darker().darker().darker()),
				Arrays.asList(Color.white.darker().darker(), Color.white.darker(), Color.black),
				Arrays.asList(Color.white.darker().darker().darker(), Color.white.darker().darker(),
						Color.white.darker().darker().darker()));
		assertEquals(expected, GameOfLife.computeGridColors(grid));
	}

	@Test
	void testComputeGridColors8() {
		final List<List<Cell>> grid = Arrays.asList(
				Arrays.asList(new Cell(0, 0, true), new Cell(0, 1, true), new Cell(0, 2, true)),
				Arrays.asList(new Cell(1, 0, true), new Cell(1, 1, true), new Cell(1, 2, true)),
				Arrays.asList(new Cell(2, 0, true), new Cell(2, 1, true), new Cell(2, 2, true)));
		final List<List<Color>> expected = Arrays.asList(
				Arrays.asList(Color.white.darker().darker().darker(), Color.white.darker().darker(),
						Color.white.darker().darker().darker()),
				Arrays.asList(Color.white.darker().darker(), Color.white, Color.white.darker().darker()),
				Arrays.asList(Color.white.darker().darker().darker(), Color.white.darker().darker(),
						Color.white.darker().darker().darker()));
		assertEquals(expected, GameOfLife.computeGridColors(grid));
	}

}
