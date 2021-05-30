/**
 * Copyright 2019-2021 Keldaria. Tous droits réservés.
 * Toute reproduction, diffusion, partage, distribution,
 * commercialisation sans autorisation explicite est interdite.
 */
package org.joml.sampling;

/**
 * Internal Math class used by the sampling package.
 * 
 * @author Kai Burjack
 */
class Math extends org.joml.Math {

    static final double PI = java.lang.Math.PI;
    static final double PI2 = PI * 2.0;
    static final double PIHalf = PI * 0.5;
    private static final double ONE_OVER_PI = 1.0 / PI;
    private static final double s5 = Double.longBitsToDouble(4523227044276562163L);
    private static final double s4 = Double.longBitsToDouble(-4671934770969572232L);
    private static final double s3 = Double.longBitsToDouble(4575957211482072852L);
    private static final double s2 = Double.longBitsToDouble(-4628199223918090387L);
    private static final double s1 = Double.longBitsToDouble(4607182418589157889L);

    /**
     * Reference: <a href=
     * "http://www.java-gaming.org/topics/joml-1-8-0-release/37491/msg/361815/view.html#msg361815">http://www.java-gaming.org/</a>
     * 
     * @author roquendm
     */
    static double sin_roquen_9(double v) {
        double i = java.lang.Math.rint(v * ONE_OVER_PI);
        double x = v - i * Math.PI;
        double qs = 1 - 2 * ((int) i & 1);
        double x2 = x * x;
        double r;
        x = qs * x;
        r = s5;
        r = r * x2 + s4;
        r = r * x2 + s3;
        r = r * x2 + s2;
        r = r * x2 + s1;
        return x * r;
    }

}
