package com.igladkikh.service;

import com.igladkikh.model.Fragment;
import com.igladkikh.model.Position;
import com.igladkikh.storage.GroupStorage;
import com.igladkikh.storage.PositionStorage;
import com.igladkikh.util.FragmentUtil;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Service {

    private final PositionStorage positionStorage;
    private final GroupStorage groupStorage;

    public Service(PositionStorage positionStorage, GroupStorage groupStorage) {
        this.positionStorage = positionStorage;
        this.groupStorage = groupStorage;
    }

    public void add(List<String> lines) {
        lines.forEach(this::add);
    }

    private void add(String line) {
        String[] fragments = line.split(";");

        // проверка строки на валидность
        if (!Arrays.stream(fragments).allMatch(FragmentUtil::isCorrect)) {
            return;
        }

        for (int ceil = 0; ceil < fragments.length; ceil++) {
            if (FragmentUtil.isEmpty(fragments[ceil]) ||
                    !FragmentUtil.isCorrect(fragments[ceil])) {
                continue;
            }

            Fragment fragment = new Fragment(fragments[ceil]);

            // текущие координаты слова
            Position position = new Position(line, ceil);
            // проверяем, встречалось ли слово ранее
            if (positionStorage.containsFragment(fragment)) {
                Position prevPosition = positionStorage.get(fragment);
                int prevCeil = prevPosition.getFragmentNumber();
                // если самые первые координаты не сохранены в группу,
                // получаем их и добавляем в группу
                if (!groupStorage.containsPhone(fragment)) {
                    groupStorage.add(fragment, prevCeil, prevPosition.getLine());
                }
                // добавляем в группу строку с текущими координатами
                groupStorage.add(fragment, ceil, line);
            }
            // обновляем координаты
            positionStorage.add(fragment, position);
        }
    }

    public List<Set<String>> getGroups() {
        // для группировки используем граф
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        groupStorage.getAll()
                .forEach((fragment, ceilToGroupMap) -> {
                    ceilToGroupMap.forEach((ceil, group) -> {
                        if (group.size() > 1) {
                            // добавляем данные в граф
                            List<String> lines = group.getLines();
                            for (int i = 0; i < lines.size(); i++) {
                                String line = lines.get(i);
                                // добавляем вершины
                                graph.addVertex(line);
                                if (i > 0) {
                                    // добавляем ребра
                                    graph.addEdge(lines.get(i - 1), line);
                                }
                            }
                        }
                    });
                });

        ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<>(graph);
        List<Set<String>> sets = inspector.connectedSets();

        return sets.stream()
                .sorted((o1, o2) -> o2.size() - o1.size())
                .toList();
    }
}
