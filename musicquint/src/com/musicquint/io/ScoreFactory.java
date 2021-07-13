package com.musicquint.io;

import com.musicquint.api.Score;
import com.musicquint.io.XMLTag.TagType;

public interface ScoreFactory {

    @XMLTag(tagName = "score-partwise", tagType = TagType.END_TAG)
    Score build();

    void setTitle(String string);

    void createPart();
    
    PartFactory getPartFactory();

}
