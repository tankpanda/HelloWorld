package com.hhd.algorithm.primary.class04;

/**
 * Created by huhengda on 2021/1/26.
 */
public class NodeV2<V> {
    public V v;
    public NodeV2<V> pre;
    public NodeV2<V> next;

    public NodeV2(V v) {
        this.v = v;
    }
}
