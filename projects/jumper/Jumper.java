package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;


public class Jumper extends Actor
{
    /**
     * Constructs a green Jumper.
     */
    public Jumper()
    {
        setColor(Color.GREEN);
    }

    /**
     * Constructs a jumper of a given color.
     * @param jumperColor the color for this jumper
     */
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
        if (canMove())
            move();
        else
            turn();
    }

    /**
     * Turns the jumper 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the jumper forward
     * occupied.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nextnext = next.getAdjacentLocation(getDirection());
        if (gr.isValid(nextnext))
            moveTo(nextnext);
        else
            removeSelfFromGrid();

    }

    /**
     * Tests whether this jumper can move forward into a location that is empty or
     * contains a flower.
     * @return true if this jumper can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nextnext = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(nextnext))
            return false;
        Actor nextneighbor = gr.get(nextnext);
        return (nextneighbor == null) || (nextneighbor instanceof Flower);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}
