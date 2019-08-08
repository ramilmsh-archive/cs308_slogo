package interpreter.core.interfaces;

import interpreter.core.elements.Element;

/**
 * Interface of named objects
 *
 * @author ramilmsh
 */
public interface Named {
    /**
     * Get the object's name
     *
     * @return name
     */
    String getName();

    /**
     * Get the element
     *
     * @return element
     */
    Element getSelf();
}
