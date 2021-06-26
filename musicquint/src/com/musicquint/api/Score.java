package com.musicquint.api;

import java.util.Collection;
import java.util.List;

import com.musicquint.impl.MQScore;

/**
 * A Score is a list of musical parts. The order in which the parts are added is
 * the order the parts are represented from top to bottom. Additionally
 * informations about the composer and the musical piece itself are attached to
 * the object and can be accessed. The Score can be based on an mutable and
 * immutable list.
 */
public interface Score extends List<Part> {

    /**
     * Static factory method of an Score object that is mutable.
     *
     * @return A mutable Score.
     */
    public static Score create() {
        return new MQScore();
    }

    /**
     * Static factory method of an mutable Score object. All objects of the given
     * collection are added to the Score upon creation as per the {@link List}
     * {@code addAll()} method. The method can throw any exception that the
     * {@code addAll()} can throw.
     *
     * @param collection to be added to the Score.
     * @return A score containing the given collection.
     *
     * @see #addAll(Collection)
     */
    public static Score create(Collection<Part> collection) {
        return new MQScore(collection);
    }

    /**
     * Setter for the composers name.
     *
     * @param name of the composer.
     */
    void setComposer(String name);

    String getComposer();

    void setTitle(String title);

    String getTitle();

    void setSubtitle(String subtitle);

    String getSubtitle();

}
