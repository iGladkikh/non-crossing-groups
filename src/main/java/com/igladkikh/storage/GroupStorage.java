package com.igladkikh.storage;

import com.igladkikh.model.Fragment;
import com.igladkikh.model.Group;

import java.util.HashMap;
import java.util.Map;

public class GroupStorage {
    private final Map<Fragment, Map<Integer, Group>> fragmentToGroup;

    public GroupStorage() {
        this.fragmentToGroup = new HashMap<>();
    }

    public void add(Fragment fragment, int ceil, String line) {
        fragmentToGroup.putIfAbsent(fragment, new HashMap<>());
        fragmentToGroup.get(fragment).putIfAbsent(ceil, new Group());
        fragmentToGroup.get(fragment).get(ceil).add(line);
    }

    public boolean containsPhone(Fragment fragment) {
        return fragmentToGroup.containsKey(fragment);
    }

    public Map<Fragment, Map<Integer, Group>> getAll() {
        return fragmentToGroup;
    }
}
