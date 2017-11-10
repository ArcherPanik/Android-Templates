package problems.maze;

import java.util.ArrayList;

import problems.maze.MazeAction;
import problems.maze.MazeState;

import search.Action;
import search.Node;
import search.SearchProblem;
import search.State;

/**
 * Extends SearchProblem and implements the functions which define the maze
 * problem.
 */
public class MazeProblem implements SearchProblem {

    // Penalty factor for each wood wall bitten.
    private static final double PENALTY = 3;
    // Maximum number of bites.
    private static final int MAX_BITES = 4;

    /* Maze */
    private Maze maze;

    /**
     * Constructors
     */
    public MazeProblem(int size, int seed) {
        this.maze = new Maze(size, seed);
    }

    public MazeProblem(Maze maze) {
        this.maze = maze;
    }

    /**
     * Returns an state representing the initial position of the hamster
     */
    @Override
    public State initialState() {
        return new MazeState(0, maze.inputX, 0, false);
    }

    /**
     * Returns the result of applying an action. If action movement goes to a
     * wall, returns null, as the action should not be allowed.
     */
    @Override
    public State applyAction(State state, Action action) {
        // Casts the parameters
        MazeState mazeState = (MazeState) state;
        MazeAction mazeAction = (MazeAction) action;
        //-----------------------------------------
        // If the action is EAT
        //-----------------------------------------
        if (mazeAction == MazeAction.EAT) {
            //********************************
            // TO DO (1)
            MazeState newState = new MazeState(mazeState.getY(), mazeState.getX(), mazeState.getNumBites(), true);
            return newState;
        }
        //********************************

        //-----------------------------------------
        // If the action is a MOVEMENT
        //-----------------------------------------
        int newPosX = mazeState.x; // New positions
        int newPosY = mazeState.y; // New positions
        MazeState newMazeState = mazeState;
        // Calculates the new position.
        switch (mazeAction) {
            case LEFT:
                newPosX--;
                break;
            case RIGHT:
                newPosX++;
                break;
            case UP:
                newPosY--;
                break;
            case DOWN:
                newPosY++;
                break;
        }
        newMazeState = new MazeState(newPosY, newPosX, mazeState.getNumBites(), mazeState.getHasEaten());

        //********************************
        // TO DO (2)
        int newCell = maze.cells[newPosY][newPosX];
        if ((newCell == 1) && (mazeState.getNumBites() < MAX_BITES)) {
            newMazeState = new MazeState(newPosY, newPosX, mazeState.getNumBites() + 1, mazeState.getHasEaten());
        }

        return newMazeState;
    }

    /**
     * Returns a list of the actions that can be applied over an state.
     */
    @Override
    public ArrayList<Action> getPossibleActions(State state) {
        MazeState mazeState = (MazeState) state;
        ArrayList<Action> possibleActions = new ArrayList<Action>();
        int currentCell = maze.cells[mazeState.getY()][mazeState.getX()];

        //************
        // TO DO (3)
        if (currentCell == 3) {
            possibleActions.add(MazeAction.EAT);
        }

        try {
            int newPosR = maze.cells[mazeState.getY()][mazeState.getX() + 1];
            if (newPosR != 2 && (newPosR != 1 || mazeState.getNumBites() < MAX_BITES)) {
                possibleActions.add(MazeAction.RIGHT);
            }
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            int newPosL = maze.cells[mazeState.getY()][mazeState.getX() - 1];
            if (newPosL != 2 && (newPosL != 1 || mazeState.getNumBites() < MAX_BITES)) {
                possibleActions.add(MazeAction.LEFT);
            }
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            int newPosU = maze.cells[mazeState.getY() - 1][mazeState.getX()];
            if (newPosU != 2 && (newPosU != 1 || mazeState.getNumBites() < MAX_BITES)) {
                possibleActions.add(MazeAction.UP);
            }
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            int newPosD = maze.cells[mazeState.getY() + 1][mazeState.getX()];
            if (newPosD != 2 && (newPosD != 1 || mazeState.getNumBites() < MAX_BITES)) {
                possibleActions.add(MazeAction.DOWN);
            }
        } catch (IndexOutOfBoundsException e) {
        }

        return possibleActions;
    }

    @Override
    public double cost(State state, Action action) {
        // Casts the parameters
        MazeState mazeState = (MazeState) state;
        MazeAction mazeAction = (MazeAction) action;

        // If the action is EAT
        if (mazeAction == MazeAction.EAT) {
            return 5.0;
        }

        // Otherwise, it is a MOVEMENT.
        int newPosX = mazeState.getX();
        int newPosY = mazeState.getY();
        // Calculates the new position.
        switch (mazeAction) {
            case LEFT:
                newPosX--;
                break;
            case RIGHT:
                newPosX++;
                break;
            case UP:
                newPosY--;
                break;
            case DOWN:
                newPosY++;
                break;
        }

        //********************************
        // TO DO (4)
        //
        // If the new position is empty or cheese, returns the cost of one step.
        //               
        int newCell = maze.cells[newPosY][newPosX];
        if (newCell == 3 || newCell == 0) {
            return 1;
        } //********************************
        // If the new position is wood, depends on the bites remaining
        //
        else if ((newCell == 1) && (mazeState.getNumBites() < MAX_BITES)) {
            return (mazeState.getNumBites() + 1) * PENALTY;
        }

        // Otherwise is a WALL or a WOOD that can't be bitten
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Tests if a state is the goal. Tests the position and hasEaten.
     */
    public boolean testGoal(State state) {

        MazeState mazeState = (MazeState) state;

        //Test the position
        if ((mazeState.getX() == maze.size - 1) && (mazeState.getY() == maze.size - 1)) {
            //Test if it has eaten
            if (mazeState.hasEaten) {
                return true;
            }
        }

        return false;

    }

    // Returns the heuristic value of an state.
    @Override
    public double heuristic(State state) {

        MazeState mazeState = (MazeState) state;

            int distanceX = maze.size - mazeState.getX() - 1;
            int distanceY = maze.size - mazeState.getY() - 1;

            int heuristic = distanceX + distanceY;
            if (mazeState.hasEaten) {
                return heuristic;
            } else {
                return heuristic + 5;
            }
        
        //********************************
        // TO DO (6) 
        // 
        // 
        //
        //********************************
    }
}
