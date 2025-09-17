package lapr.project.utils;

import java.util.Objects;

/**
 * Class Pair
 * @param <U>
 * @param <V>
 */
public class Pair<U,V> {

    /**
     * The Pair's first ship.
     */
    public final U first;

    /**
     * The Pair's second ship.
     */
    public final V second;

    /**
     * Builds a Pair with its parameters.
     * @param first
     * @param second
     */
    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }


    /**
     * Get's the first ship in the pair.
     * @return
     */
    public U getFirst() {
        return first;
    }

    /**
     * Get's the second ship in the pair.
     * @return
     */
    public V getSecond() {
        return second;
    }

    /**
     * Return a textual representation of the object, which contains all of its attributes.
     *
     * @return pair characteristics
     */
    @Override
    public String toString() {
        return "Pair: \n"+first+second+"\n";
    }

    public String toString2(){
        return "Circuit: "+first+" with distance="+second+"km";
    }

    /**
     * Verifies if there is an object equals to the @param object.
     *
     * @param o
     *
     * @return true if it founds an object equals to the @param.
     * @return false if there is not any object equals to the @param.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

}
