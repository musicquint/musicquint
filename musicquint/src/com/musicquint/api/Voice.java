package com.musicquint.api;

import java.util.NavigableMap;

public interface Voice extends NavigableMap<BarTime, PrincipalSet> {

    @Override
    PrincipalSet put(BarTime key, PrincipalSet value);

    boolean fits(BarTime key, PrincipalItem value);

    BarTime lasting(BarTime key);

    BarTime next(BarTime key);

    BarTime length();

    BarTime capacity();
}
