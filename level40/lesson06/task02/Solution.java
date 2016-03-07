package com.javarush.test.level40.lesson06.task02;

/* Принадлежность точки многоугольнику
Дан многоугольник, заданный координатами своих вершин.
Ребра многоугольника не пересекаются.
Необходимо реализовать метод isPointInPolygon(Point point, List<Point> polygon), который проверит,
принадлежит ли переданная точка многоугольнику.
*/

import java.util.ArrayList;
import java.util.List;

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(10, 10), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        //напишите тут ваш код
        int intersect_count = 0;
        for (int i = 0; i < polygon.size(); i++) {
            Point p1 = polygon.get(i > 0 ? i - 1 : polygon.size() - 1);
            Point p2 = polygon.get(i);

            if (isIntersect(point.x, point.y, p1, p2)) intersect_count++;
        }
        return intersect_count % 2 == 1;
    }

    private static boolean isIntersect(int x, int y, Point p1, Point p2) {
        int dy1 = p1.y - y;
        int dy2 = p2.y - y;

        if (Math.signum(dy1) == Math.signum(dy2)) return false;

        int dx1 = p1.x - x;
        int dx2 = p2.x - x;

        if (dx1 >= 0 && dx2 >= 0) return true;
        if (dx1 < 0 && dx2 < 0) return false;

        int dx0 = dy1 * (p1.x - p2.x) / (p1.y - p2.y);

        return dx0 <= dx1;
    }

}
