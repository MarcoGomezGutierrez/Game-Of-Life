package edaii.gameoflife;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameOfLife {
	
	/**
	 * Define el tablero de juego en su estado inicial, con el número de filas indicado por
		rows y de columnas indicado por columns. Cada celda estará viva o muerta de forma
		aleatoria
	 * @param rows (final int)
	 * @param columns (final int)
	 * @return List<List<Cell>>
	 */
	public static List<List<Cell>> createGrid(final int rows, final int columns) {
		return IntStream
			.range(0, rows)
			.mapToObj(row -> {
				return IntStream
					.range(0, columns)
					.mapToObj(colum -> new Cell(row, colum, getRandomBoolean()))
					.collect(Collectors.toList());
			})
			.collect(Collectors.toList());
	}
	
	/**
	 * Produce un nuevo tablero con la siguiente generación del juego a partir del tablero
		List<List<Cell>> grid. Evaluo si la celda esta viva o muerta, y dependiendo si esta viva o muerta se le aplica diferentes reglas
		implementadas en los métodos isAlive() y notAlive().
	 * @param grid (final List<List<Cell>>)
	 * @return List<List<Cell>>
	 */
	public static List<List<Cell>> advance(final List<List<Cell>> grid) {
		return IntStream
				.range(0, grid.size())
				.mapToObj(row -> {
					return IntStream
						.range(0, grid.get(row).size())
						.mapToObj(column -> grid.get(row).get(column).isAlive() ? isAlive(grid, row, column) : notAlive(grid, row, column))
						.collect(Collectors.toList());
				})
				.collect(Collectors.toList());
	}
	
	/**
	 * Devuelve el número de vecinos vivos de la celda del tablero en la fila y columna
		indicadas. Se consideran vecinos a cualquier casilla adyacente en horizontal, vertical
		o diagonal (es decir, que una casilla tiene un máximo de ocho vecinos). Para eso uso los métodos
		createAliveNeighbours() y createNeighbours(). 
		Creo el tablero de vecinos -> createNeighbours() 
		Elimino todas las celdas muertas -> createAliveNeighbours(). 
		De esta forma cuento el número de columnas de cada fila y luego sumo el resultado de cada fila para 
		optener el número de vecinos vivos.
	 * @param grid (final List<List<Cell>>)
	 * @param row (final int)
	 * @param column (final int)
	 * @return int
	 */
	public static int countAliveNeighbours(final List<List<Cell>> grid, final int row, final int column) {
		final List<List<Cell>> neighbours = createAliveNeighbours(createNeighbours(grid, row, column));
		return IntStream
				.range(0, neighbours.size())
				.mapToObj(r -> {
					return IntStream
							.range(0, neighbours.get(r).size())
							.reduce(0, (accum, elem) -> accum + 1);
				})
				.reduce(0, (accum, elem) -> accum + elem);
	}
	
	/**
	 * Genera una matriz con los colores de cada celda, en función de si dicha celda está
		viva o muerta. Es necesario importar la clase java.awt.Color. Si la celda está viva, su
		color correspondiente será Color.white. En caso contrario, será Color.black.
	 * @param grid (final List<List<Cell>>)
	 * @return List<List<Color>>
	 */
	public static List<List<Color>> computeGridColors(final List<List<Cell>> grid) {
		return IntStream
				.range(0, grid.size())
				.mapToObj(row -> {
					return IntStream
							.range(0, grid.get(row).size())
							.mapToObj(column -> grid.get(row).get(column).isAlive() ? computeGridColorsNeighbours(countAliveNeighbours(grid, row, column)) : Color.black)
							.collect(Collectors.toList());
				})
				.collect(Collectors.toList());
	}
	
	
	/**
	 * Crea un tablero de 3x3 con los vecinos. 
	 	- try -> me aseguro de que no se salga de los límites de mi matriz, si no me devuelve ArrayIndexOutOfBoundsException.
	 			Las 4 esquinas los que se salgan pasaran a estar muertas, puesto que se filtran las que estan muertas
	 			En estos casos sería como tener un tablero de 2x2
	  	- Filtro el que paso por parametros (row, column) 
	  	para que no se me incluya a la hora de contarlos
	 * @param grid (final List<List<Cell>>)
	 * @param row (final int)
	 * @param column (final int)
	 * @return List<List<Cell>>
	 */
	private static List<List<Cell>> createNeighbours(final List<List<Cell>> grid, final int row, final int column) {
		return IntStream
				.rangeClosed(row - 1, row + 1)
				.mapToObj(r -> {
					return IntStream
						.rangeClosed(column - 1, column + 1)
						.mapToObj(c -> {
							try {
								return grid.get(r).get(c);
							} catch(Exception e) {
								return new Cell(r, c, false);
							}
						})
						.filter(colum -> colum.getRow() != row || colum.getColumn() != column)
						.collect(Collectors.toList());
				})
				.collect(Collectors.toList());
		
	}
	
	/**
	 * Filtra todos las celdas que estan vivas, si no lo estan las descarta
	 * @param neighbours (final List<List<Cell>>)
	 * @return List<List<Cell>>
	 */
	private static List<List<Cell>> createAliveNeighbours(final List<List<Cell>> neighbours) {
		return neighbours
				.stream()
				.map(r -> {
					return r
						.stream()
						.filter(c -> c.isAlive())
						.collect(Collectors.toList());
				})
				.collect(Collectors.toList());
		
	}
	
	
	/**
	 * Celdas Vivas:
	 	 if (aliveNeighbours > 3) -> 		Una celda viva con menos de dos vecinos vivos morirá (“soledad”).
	 	 else if (aliveNeighbours < 2) -> 	Una celda viva con más de tres vecinos morirá (“superpoblación”).
	 	 else ->							Una celda viva con dos o tres vecinos vivos seguirá viva.
	 * @param grid (final List<List<Cell>>)
	 * @param row (final int)
	 * @param column (final int)
	 * @return
	 */
	private static Cell isAlive(final List<List<Cell>> grid, final int row, final int column) {
		final int aliveNeighbours = countAliveNeighbours(grid, row, column);
		if (aliveNeighbours > 3) return new Cell(row, column, false); 
		else if (aliveNeighbours < 2) return new Cell(row, column, false);
		else return new Cell(row, column, true);
	}
	
	
	/**
	 * Celdas Muertas:
	 	 if (aliveNeighbours == 3) -> Una celda muerta con exactamente tres vecinos vivos pasará a estar viva(“reproducción”)
		 else ->					  Seguira muerta
	 * @param grid (final List<List<Cell>>)
	 * @param row (final int)
	 * @param column (final int)
	 * @return Cell
	 */
	private static Cell notAlive(final List<List<Cell>> grid, final int row, final int column) {
		final int aliveNeighbours = countAliveNeighbours(grid, row, column);
		if (aliveNeighbours == 3) return new Cell(row, column, true);
		else return new Cell(row, column, false);
	}
	
	/**
	 * Método que hace una operación si la operacion da 4 cogera el valor Color.white, es decir, tiene 7 o 8 vecinos vivos,
	  	si tiene 5 o 6 vecinos vivos Color.white.darker() esto hasta un máximo de cuatro veces, la operacion mínima dara 0.5 luego lo casteo a (int)
	  	para que me tome también el valor 0. De esta forma aplico un máximo de cuatro veces Color.white.darker().darker().darker().darker()
	  	y el rango máximo es 4 para que si la operacion da 4:
	  	rangeClosed(4, 4) -> Color.white, 
	  	rangeClosed(3, 4) -> Color.white.darker() 							 (El resultado de aplicar 1 veces Color.darker())
	  	rangeClosed(2, 4) -> Color.white.darker().darker()					 (El resultado de aplicar 2 veces Color.darker())
	  	rangeClosed(1, 4) -> Color.white.darker().darker().darker()			 (El resultado de aplicar 3 veces Color.darker())
	  	rangeClosed(0, 4) -> Color.white.darker().darker().darker().darker() (El resultado de aplicar 4 veces Color.darker())
	 * @param neighbours (final int)
	 * @return Color
	 */
	private static Color computeGridColorsNeighbours(final int neighbours) {
		return IntStream
				.rangeClosed((int)(neighbours / 2), 4)
				.mapToObj(elem -> elem == 4 ? Color.white :  Color.black)
				.reduce(Color.white, (accum, elem) -> elem.equals(Color.white) ? accum : accum.darker());
	}
	
	/**
	 * Generación de un número aleatorio Boolean
	 * @return boolean
	 */
	private static boolean getRandomBoolean() {
		return new Random().nextBoolean();
	}
	
}
