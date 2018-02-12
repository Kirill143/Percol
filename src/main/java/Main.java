
/******************************************************************************
 *  Compilation:  javac PercolationVisualizer.java
 *  Execution:    java PercolationVisualizer input.txt
 *  Dependencies: Percolation.java
 *
 *  This program takes the name of a file as a command-line argument.
 *  From that file, it
 *
 *    - Reads the grid size n of the percolation system.
 *    - Creates an n-by-n grid of sites (intially all blocked)
 *    - Reads in a sequence of sites (row, col) to open.
 *
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black,
 *  with with site (0, 0) in the upper left-hand corner.
 *
 ******************************************************************************/

import java.awt.Font;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class Main {
    private static final int DELAY = 1000;

    public Main() {
    }

    public static void draw(Percolation perc, int N) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-0.05D * (double)N, 1.05D * (double)N);
        StdDraw.setYscale(-0.05D * (double)N, 1.05D * (double)N);
        StdDraw.filledSquare((double)N / 2.0D, (double)N / 2.0D, (double)N / 2.0D);
        int opened = 0;

        for(int row = 1; row <= N; ++row) {
            for(int col = 1; col <= N; ++col) {
                if (perc.isFull(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    ++opened;
                } else if (perc.isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    ++opened;
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                }

                StdDraw.filledSquare((double)col - 0.5D, (double)(N - row) + 0.5D, 0.45D);
            }
        }

        StdDraw.setFont(new Font("SansSerif", 0, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.25D * (double)N, (double)(-N) * 0.025D, opened + " открытых пор");
        if (perc.percolates()) {
            StdDraw.text(0.75D * (double)N, (double)(-N) * 0.025D, "Перколяция достигнута!");
        } else {
            StdDraw.text(0.75D * (double)N, (double)(-N) * 0.025D, "Нет перколяции...");
        }

    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        StdDraw.show(0);
        Percolation perc = new Percolation(N);
        draw(perc, N);
        StdDraw.show(100);

        while(!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
            draw(perc, N);
            StdDraw.show(100);
        }

    }
}


