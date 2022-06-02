package edaii.gameoflife;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

public class GridWindow extends Frame {
	private static final long serialVersionUID = -7698484730166471503L;

	private class GridCanvas extends Canvas {
		private static final long serialVersionUID = 9167438723891211271L;
		Image buffer;
		List<List<Color>> grid;

		public GridCanvas(int width, int height) {
			super();
			setBackground(Color.black);
			buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			grid = null;
		}

		@Override
		public synchronized void paint(Graphics g) {
			super.paint(g);
			if (grid != null && grid.size() > 0 && grid.get(0).size() > 0) {
				paintGrid(buffer.getGraphics(), buffer.getWidth(this), buffer.getHeight(this));
				final Dimension size = getSize();
				g.drawImage(buffer, 0, 0, (int) size.getWidth(), (int) size.getHeight(), this);
			}
		}

		private void paintGrid(Graphics g, int width, int height) {
			final int numRows = grid.size();
			final int numColumns = grid.get(0).size();
			final int tileMargin = 2;
			final int tileWidth = (width - tileMargin * numColumns) / numColumns;
			final int tileHeight = (height - tileMargin * numRows) / numRows;
			for (int row = 0; row < numRows; row++) {
				for (int col = 0; col < grid.get(row).size(); col++) {
					final int x = col * (tileWidth + tileMargin);
					final int y = row * (tileHeight + tileMargin);
					g.setColor(grid.get(row).get(col));
					g.fillRect(x, y, tileWidth, tileHeight);
				}
			}
		}

		public synchronized void setGrid(List<List<Color>> grid) {
			this.grid = grid;
		}
	}

	private final GridCanvas canvas;

	public GridWindow(int width, int height) {
		setSize(width, height);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		canvas = new GridCanvas(width, height);
		add(canvas);
		setVisible(true);
	}

	@Override
	public void repaint() {
		super.repaint();
		canvas.repaint();
	}

	public void setGrid(List<List<Color>> grid) {
		canvas.setGrid(grid);
	}
}