package com.cryptography.ecceg.cipher;

import lombok.Data;

import java.math.BigInteger;

@Data
public class EllipticCurve {
    private BigInteger a;
    private BigInteger b;
    private BigInteger p;

    EllipticCurve() {

    }
}
