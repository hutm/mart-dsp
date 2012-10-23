/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.math.stat.clustering;

import org.apache.commons.math.util.MathUtils;

import java.io.Serializable;
import java.util.Collection;

/**
 * A simple implementation of {@link Clusterable} for points with integer coordinates.
 * @version $Revision$ $Date$
 * @since 2.0
 */
public class EuclideanFloatPoint implements Clusterable<EuclideanFloatPoint>, Serializable {

    /** Serializable version identifier. */
    private static final long serialVersionUID = 3946024775784901360L;

    /** Point coordinates. */
    private final double[] point;

    /**
     * Build an instance wrapping an integer array.
     * <p>The wrapped array is referenced, it is <em>not</em> copied.</p>
     * @param point the n-dimensional point in integer space
     */
    public EuclideanFloatPoint(final double[] point) {
        this.point = point;
    }

    /**
     * Get the n-dimensional point in integer space.
     * @return a reference (not a copy!) to the wrapped array
     */
    public double[] getPoint() {
        return point;
    }

    /** {@inheritDoc} */
    public double distanceFrom(final EuclideanFloatPoint p) {
        return MathUtils.distance(point, p.getPoint());
    }

    /** {@inheritDoc} */
    public EuclideanFloatPoint centroidOf(final Collection<EuclideanFloatPoint> points) {
        double[] centroid = new double[getPoint().length];
        for (EuclideanFloatPoint p : points) {
            for (int i = 0; i < centroid.length; i++) {
                centroid[i] += p.getPoint()[i];
            }
        }
        for (int i = 0; i < centroid.length; i++) {
            centroid[i] /= points.size();
        }
        return new EuclideanFloatPoint(centroid);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EuclideanFloatPoint)) {
            return false;
        }
        final double[] otherPoint = ((EuclideanFloatPoint) other).getPoint();
        if (point.length != otherPoint.length) {
            return false;
        }
        for (int i = 0; i < point.length; i++) {
            if (point[i] != otherPoint[i]) {
                return false;
            }
        }
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Double i : point) {
            hashCode += i.hashCode() * 13 + 7;
        }
        return hashCode;
    }

}
