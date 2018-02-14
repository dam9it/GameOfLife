package gameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main extends JFrame {
	static int Width = 40;
	static int Height = 40;
	static int CellSize = 10;
	static int[][] grid, nextGen;
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
		Run();
	}

	private static void Run() {
		// Run the Game Of Life
		nextGen = grid;
		for (int h = 0; h < 2; h++) {		//change for how many generations you want, now its set as 2
			s.setTitle("Game Of Life - Generation: " + h);
			grid = nextGen;
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < Width; i++) {
				for (int j = 0; j < Height; j++) {
					int count = CountNeighbors(j, i);
					Update(count, j, i);
				}
			}		
			s.repaint();
			System.out.println(CountNeighbors(12, 11) + " - " + grid[12][11]);
		}
	}

	private static void Update(int count, int x, int y) {
		// Update
		// Check
		if (grid[x][y] == 1 && count < 2) {
			nextGen[x][y] = 0;
		}else if (grid[x][y] == 1 && count > 3) {
			nextGen[x][y] = 0;
		}else if (grid[x][y] == 0 && count == 3) {
			nextGen[x][y] = 1;
		}else {
			nextGen[x][y] = grid[x][y];
		}
	}

	private static int CountNeighbors(int x, int y) {	// The bug is most likely here
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

				count += grid[k][o];
			}
		}
		count -= grid[x][y];
		if(count > 3) {
		System.out.println(x + " - " + y + " -- " + count + " == " + grid[x][y]);}
		return count;
	}

	private static void FormatValues() {
		grid = new int[Width][Height];
		nextGen = new int[Width][Height];
		makeBeacon();			// Beacon is good for testing, can be changed to makeGlider(), makeBlinker(), makeBlock()
		// or the line can be removed completely and just allow the randomization below
		// Randomize grid values
		/*
		for (int i = 0; i < Height; i++) {
			for (int j = 0; j < Width; j++) {
				grid[i][j] = new Random().nextInt(2);
			}
		}
		*/
	}

	public void paint(Graphics g) {
		for (int i = 0; i < Width-1; i++) {
			for (int j = 0; j < Height-1; j++) {
				if (grid[i][j] == 0) {
					g.setColor(Color.WHITE);
				} else {
					g.setColor(Color.BLACK);
				}
				g.fillRect(i * CellSize, j * CellSize, CellSize, CellSize);
			}
		}
	}
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
				grid[11][10] = 1;
						grid[10][11] = 1;
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
