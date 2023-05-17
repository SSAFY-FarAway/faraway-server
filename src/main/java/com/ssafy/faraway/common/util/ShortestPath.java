package com.ssafy.faraway.common.util;

import lombok.Getter;

@Getter
public class ShortestPath {
    private int[] result;
    private double min;
    private int[] path;
    private boolean[] visited;
    private double[][] map;

    public ShortestPath(double[][] map) {
        super();
        this.map = map;
    }

    private void init() {
        int size = map.length;
        this.result = new int[size];
        this.path = new int[size];
        this.visited = new boolean[size];
        path[0] = 0;
        visited[0] = true;
        this.min = Integer.MAX_VALUE;
    }

    public void findShortestPath() {
        init();
        dfs(0, 1, visited, map, path, 0);
    }

    private void dfs(int node, int cnt, boolean[] visited, double[][] map, int[] path, double sum) {
        if (cnt == map.length) {
            if (node != map.length - 1) {
                return;
            }
            for (int i = 0; i < map.length; i++) {
                if (!visited[i]) {
                    return;
                }
            }
            if (min > sum) {
                min = sum;
                result = path.clone();
            }
            return;
        }

        for (int j = 0; j < map[node].length; j++) {
            if (visited[j] || map[node][j] == 0.0) continue;
            visited[j] = true;
            path[cnt] = j;
            dfs(j, cnt + 1, visited, map, path, sum + map[node][j]);
            visited[j] = false;
        }
    }
}
