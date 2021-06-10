package com.musicquint.api;

import java.util.List;

public interface PrincipalSet extends ContentSet<PrincipalItem> {

    void appendOptional(OptionalSet optional);

    void insertOptional(int i, OptionalSet optional);

    void clearOptionalList();

    List<OptionalSet> getOptionalList();

}
