package com.igladkikh.storage;

import com.igladkikh.model.Fragment;
import com.igladkikh.model.Position;

import java.util.HashMap;
import java.util.Map;

public class PositionStorage {
    private final Map<Fragment, Position> fragmentToPosition;

    public PositionStorage() {
        this.fragmentToPosition = new HashMap<>();
    }

    public void add(Fragment fragment, Position position) {
        fragmentToPosition.put(fragment, position);
    }

    public Position get(Fragment fragment) {
        return fragmentToPosition.get(fragment);
    }

    public boolean containsFragment(Fragment fragment) {
        return fragmentToPosition.containsKey(fragment);
    }
}
