/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        ArrayList<MazeCell> path = new ArrayList<MazeCell>();
        Stack<MazeCell> sol = new Stack<MazeCell>();
        sol.push(maze.getEndCell());
        while(sol.peek() != maze.getStartCell()){
            sol.push(sol.peek().getParent());
        }
        while(!sol.isEmpty()){
            path.add(sol.pop());
        }
        return path;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> dfs = new Stack<MazeCell>();
        dfs.push(maze.getStartCell());
        maze.getStartCell().setExplored(true);

        while(dfs.peek() != maze.getEndCell()){
            MazeCell current = dfs.pop();
            current.setExplored(true);
            int row = current.getRow();
            int col = current.getCol();
            // This is for the north cell
            if(maze.isValidCell(row - 1,col)){
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(current);
                dfs.push(maze.getCell(row - 1, col));
            }
            // This is for the East Cell
            if(maze.isValidCell(row ,col + 1)){
                maze.getCell(row ,col + 1).setExplored(true);
                maze.getCell(row ,col + 1).setParent(current);
                dfs.push(maze.getCell(row ,col + 1));
            }
            // This is for the South Cell
            if(maze.isValidCell(row + 1,col)){
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(current);
                dfs.push(maze.getCell(row + 1, col));
            }
            // This is for the West Cell
            if(maze.isValidCell(row ,col - 1)){
                maze.getCell(row ,col - 1).setExplored(true);
                maze.getCell(row ,col - 1).setParent(current);
                dfs.push(maze.getCell(row ,col - 1));
            }
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> bfs = new LinkedList<MazeCell>();
        bfs.add(maze.getStartCell());
        maze.getStartCell().setExplored(true);

        while(bfs.peek() != maze.getEndCell()){
            MazeCell current = bfs.remove();
            current.setExplored(true);
            int row = current.getRow();
            int col = current.getCol();

            if(maze.isValidCell(row - 1,col)){
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(current);
                bfs.add(maze.getCell(row - 1, col));
            }
            // This is for the East Cell
            if(maze.isValidCell(row ,col + 1)){
                maze.getCell(row ,col + 1).setExplored(true);
                maze.getCell(row ,col + 1).setParent(current);
                bfs.add(maze.getCell(row ,col + 1));
            }
            // This is for the South Cell
            if(maze.isValidCell(row + 1,col)){
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(current);
                bfs.add(maze.getCell(row + 1, col));
            }
            // This is for the West Cell
            if(maze.isValidCell(row ,col - 1)){
                maze.getCell(row ,col - 1).setExplored(true);
                maze.getCell(row ,col - 1).setParent(current);
                bfs.add(maze.getCell(row ,col - 1));
            }
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
