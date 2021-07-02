package com.musicquint.api;

import java.util.List;

/**
 * A Score is a list of musical parts. The order in which the parts are added is
 * the order the parts are represented from top to bottom. Additionally
 * informations about the composer and the musical piece itself are attached to
 * the object and can be accessed. The Score can be based on an mutable and
 * immutable list.
 */
public interface Score extends List<Part> {

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
