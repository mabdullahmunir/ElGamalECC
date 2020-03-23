package com.cryptography.ecceg.cipher;

import lombok.Data;

import java.math.BigInteger;

@Data
public class EllipticCurve {
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);

    private BigInteger a;
    private BigInteger b;
    private BigInteger p;

    EllipticCurve() {
        this.a = BigInteger.valueOf(1);
        this.b = BigInteger.valueOf(6);
        this.p = BigInteger.valueOf(11);
    }

    public Point addPoint(Point p1, Point p2) {
        BigInteger gradient = this.gradient(p1, p2);

        BigInteger x = gradient.multiply(gradient).subtract(p1.getX()).subtract(p2.getX());
        x = x.mod(this.p);

        BigInteger y = gradient.multiply(p1.getX().subtract(x)).subtract(p1.getY());
        y = y.mod(this.p);

        return new Point(x, y);
    }

    public Point negatePoint(Point p) {
        BigInteger x = p.getX();
        BigInteger y = p.getY().negate().mod(this.p);

        return new Point(x, y);
    }

    public Point minusPoint(Point p1, Point p2) {
        return addPoint(p1, negatePoint(p2));
    }

    public Point doublePoint(Point p) {
        if (!p.getY().equals(BigInteger.ZERO)) {
            BigInteger gradient = gradient(p);

            BigInteger x = gradient.multiply(gradient).subtract(p.getX().multiply(TWO));
            x = x.mod(this.p);

            BigInteger y = gradient.multiply(p.getX().subtract(x)).subtract(p.getY());
            y = y.mod(this.p);

            return new Point(x, y);
        }
        return p;
    }

    public Point multiplyPoint(Point p1, long n) {
        if (n <= 0)
            return null;

        if (n == 1) {
            return p1;
        } else if (n % 2 == 1) {
            return addPoint(p1, multiplyPoint(p1, n-1));
        } else {
            return multiplyPoint(doublePoint(p1), n/2);
        }
    }

    private BigInteger gradient(Point p) {
        BigInteger n1 = p.getX().pow(2).multiply(THREE).add(this.a);
        BigInteger n2 = p.getY().multiply(TWO);

        BigInteger g = n1.multiply(n2.modInverse(this.p));
        return g.mod(this.p);
    }

    private BigInteger gradient(Point p1, Point p2) {
        BigInteger n1 = p2.getY().subtract(p1.getY());
        BigInteger n2 = p2.getX().subtract(p1.getX());

        BigInteger g = n1.multiply(n2.modInverse(this.p));
        return g.mod(this.p);
    }

    public boolean isInCurve(int x) {
        // TODO
        return true;
    }

    public static void main(String[] args) {
        EllipticCurve ec = new EllipticCurve();
        Point p1 = new Point(2, 4);
        Point p2 = new Point(5, 9);
        Point p3 = ec.addPoint(p1, p2);
        Point p4 = ec.doublePoint(p1);
        Point p5 = ec.minusPoint(p3, p2);

        System.out.print(p3);
        System.out.print(p4);
        System.out.print(p5);

        for (long i=1; i<15; i++) {
            Point p6 = ec.multiplyPoint(p1, i);

            System.out.print(p6);
        }
    }
}
