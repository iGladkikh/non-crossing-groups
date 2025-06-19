package com.igladkikh.service;

import com.igladkikh.storage.GroupStorage;
import com.igladkikh.storage.PositionStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceTest {

    private Service service;

    @BeforeEach
    void setUp() {
        service = new Service(new PositionStorage(), new GroupStorage());
    }

    @Test
    void oneGroupAnd3Lines() {
        List<String> lines = List.of("111;123;222", "200;123;100", "300;;100");

        service.add(lines);
        List<Set<String>> result = service.getGroups();

        assertEquals(1, result.size());
        assertEquals(3, result.getFirst().size());
    }

    @Test
    void twoGroupsAnd2Lines() {
        List<String> lines = List.of("111;123;222", "200;123;100", ";;101", "300;;101");

        service.add(lines);
        List<Set<String>> result = service.getGroups();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(2, result.getFirst().size());
        Assertions.assertEquals(2, result.getLast().size());
    }

    @Test
    void zeroGroups1() {
        List<String> lines = List.of("100;200;300", "200;300;100");

        service.add(lines);
        List<Set<String>> result = service.getGroups();

        Assertions.assertEquals(0, result.size());
    }

    @Test
    void zeroGroups2() {
        List<String> lines = List.of("100;;300", "200;;100");

        service.add(lines);
        List<Set<String>> result = service.getGroups();

        Assertions.assertEquals(0, result.size());
    }

}