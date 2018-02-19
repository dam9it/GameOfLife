package gameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main extends JFrame {
	static int Width = 300;
	static int Height = 300;
	static int CellSize = 3;
	static int[][] grid;
	static int[][] nextGen;
	static Main s;

	public Main() {
		// Create the frame.
		setTitle("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Width * CellSize, Height * CellSize);
		setVisible(true);
		setLayout(null);
		setResizable(false);
	}

	public static void main(String[] args) {
		s = new Main();
		FormatValues();
		for (int h = 0; h < 200000; h++) { // change for how many generations you want
			s.setTitle("Game Of Life - Generation: " + h);
			try {
				TimeUnit.MILLISECONDS.sleep(10);
				Run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			s.repaint();
			for (int i = 0; i < Width; i++) {
				for (int j = 0; j < Height; j++) {
					grid[i][j] = nextGen[i][j];
				}
			}
		}
	}

	private static void Run() {
		// Run the Game Of Life
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				Update(CountNeighbors(i, j), i, j);
			}
		}
	}

	private static void Update(int count, int x, int y) {
		// Update cell for the next generation
		if (count < 2 || count > 3) {
			nextGen[x][y] = 0;
		}
		if (count == 2) {
			nextGen[x][y] = grid[x][y];
		}
		if (count == 3) {
			nextGen[x][y] = 1;
		}
	}

	private static int CountNeighbors(int x, int y) {
		// Count the neighbors
		int count = 0;
		int o = 0;
		int k = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			if (i < 0) {
				o = Width + i;
			} else if (i > Width - 1) {
				o = i - Width;
			} else {
				o = i;
			}
			for (int j = y - 1; j <= y + 1; j++) {
				if (j < 0) {
					k = Height + j;
				} else if (j > Height - 1) {
					k = j - Height;
				} else {
					k = j;
				}
				count += grid[o][k];
			}
		}
		count -= grid[x][y];
		return count;
	}

	private static void FormatValues() {
		grid = new int[Width + 1][Height + 1];
		nextGen = new int[Width + 1][Height + 1];
		// Randomize grid values and set nextGen to 0
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				grid[i][j] = new Random().nextInt(2);
				nextGen[i][j] = 0;
			}
		}
		// makeBeacon(); // Beacon is good for testing, can be changed to makeGlider(), makeBlinker(), makeBlock() , makeBeacon()
	}

	public void paint(Graphics g) {
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				if (grid[i][j] == 0) {
					g.setColor(Color.WHITE);
				} else {
					g.setColor(Color.BLACK);
				}
				g.fillRect(i * CellSize, j * CellSize, CellSize, CellSize);
			}
		}
	}

	// Below are preset structures for testing
	public static void makeGlider() {
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				grid[i][j] = 0;
			}
		}
		grid[15][10] = 1;
		grid[16][10] = 1;
		grid[17][10] = 1;
		grid[17][9] = 1;
		grid[16][8] = 1;
	}

	public static void makeBlinker() {
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				grid[i][j] = 0;
			}
		}
		grid[15][10] = 1;
		grid[16][10] = 1;
		grid[17][10] = 1;
	}

	public static void makeBeacon() {
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				grid[i][j] = 0;
			}
		}
		grid[10][10] = 1;
		grid[10][11] = 1;
		grid[11][10] = 1;
		grid[11][11] = 1;
		grid[12][12] = 1;
		grid[13][12] = 1;
		grid[12][13] = 1;
		grid[13][13] = 1;
	}

	public static void makeBlock() {
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				grid[i][j] = 0;
			}
		}
		grid[10][10] = 1;
		grid[11][10] = 1;
		grid[10][11] = 1;
		grid[11][11] = 1;
	}
}
