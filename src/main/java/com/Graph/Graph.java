package com.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    //存储顶点集合
    private ArrayList<String> vertexList;
    //存储图对应的邻接矩阵
    private int[][] edges;
    //边的数目
    private int numOfEdges;
    //记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        String VertexValue[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(5);
        for (String v : VertexValue) {
            graph.insertVertex(v);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.showGraph();
        //
        System.out.println("深度遍历");
        graph.dfs();
        System.out.println("广度优先遍历");
        graph.bfs();
    }

    /**
     * @param n 顶点个数
     */
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;

    }

    /**
     * @param index 要获取邻接节点的结点的下标
     * @return 得到第一个邻接节点的下标w的下标
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            //如果值大于0,该下标的结点就是邻接节点
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * @param v2 v1的邻接节点
     * @param v1 要查找邻接节点的结点
     * @return 根据前一个邻接节点的下标来获取下一个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }

        }
        return -1;
    }

    /**
     * @param isVisited 是否已访问
     * @param index     节点下标
     */
    public void dfs(boolean[] isVisited, int index) {
        //首先访问该节点
        System.out.print(getValueByIndex(index) + "->");
        //把该节点设置已访问
        isVisited[index] = true;
        //查找节点index的第一个邻接节点w
        int w = getFirstNeighbor(index);
        while (w != -1) {
            //有邻接节点
            //该节点未访问
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //被访问过,查找邻接节点的下一个邻接节点
            w = getNextNeighbor(index, w);
        }
    }

    //对dfs,进行一个重载,遍历我们所有的结点,并
    public void dfs() {
        //初始化顶点
        isVisited = new boolean[vertexList.size()];
        //遍历所有节点,进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }

        }
    }

    public void bfs(boolean[] isVisited, int index) {
        //表示队列的头结点对应的下标
        int u;
        //邻接节点w
        int w;
        //队列,记录结点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //访问结点,输出信息
        System.out.print(getValueByIndex(index) + "->");
        //标记已访问
        isVisited[index] = true;
        //将结点加入队列
        queue.addLast(index);
        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                //找到
                if (!isVisited[w]){
                    //未访问
                    System.out.print(getValueByIndex(w)+"=>");
                    //标记已访问
                    isVisited[w]=true;
                    //入队
                    queue.addLast(w);
                }
                //已访问,查找由u为当前结点,获取w结点后面的邻接节点
                w=getNextNeighbor(u,w);
            }
        }
    }

    //遍历所有节点,广度优先
    public void bfs(){
        //初始化顶点
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    /**
     * 插入结点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * @param v1     顶点
     * @param v2     邻接节点
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        //添加权值
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        //添加一条边
        numOfEdges++;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * @param index 下标
     * @return 获取下标的值
     */
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    /**
     * @param v1 顶点
     * @param v2 邻接节点
     * @return 获取权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        for (int[] edge : edges) {

            System.err.println(Arrays.toString(edge));
        }
    }
}
