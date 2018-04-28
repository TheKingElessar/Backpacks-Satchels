package de.eydamos.guiadvanced.misc;

import java.util.List;

public interface GuiPartHolderInterface extends GuiStateInterface {
    /**
     * Returns the identifier of the part holder, which is used to add sub parts to it.
     *
     * @return The identifier.
     */
    String getIdentifier();

    /**
     * This will add the sub part to the holder itself.
     *
     * @param subPart The sub part to add.
     */
    void addSubPart(GuiPartInterface subPart);

    /**
     * This will add the sub part to the holder with the given identifier.
     *
     * @param subPart The sub part to add.
     * @param parent  The identifier of the holder.
     */
    void addSubPart(GuiPartInterface subPart, String parent);

    /**
     * Removes a sub part from the holder.
     *
     * @param subPart The sub part to remove.
     */
    void removeSubPart(GuiPartInterface subPart);

    /**
     * Removes all sub parts.
     */
    void clearSubParts();

    /**
     * Returns a list of all sub parts.
     *
     * @return The sub parts of the holder.
     */
    List<GuiPartInterface> getSubParts();
}
