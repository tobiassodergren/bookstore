package org.sodergren.cart;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Unfriendly implementation of a session based cart. This should be replaced with a client-side
 * cart, stored either in local store or as a cookie.
 *
 * @param <A> The key for cache
 * @param <B> The cached value
 */
public class CartCache<A, B> extends LinkedHashMap<A, B> {

    private long maxDuration = 60 * 60 * 1000; // Default 60 min timeout for cart
    private Map<A, Long> sessionTimestamp = new HashMap<>();

    public CartCache() {
        super(16, 0.75f, true);
    }

    public CartCache(long maxDuration) {
        super(16, 0.75f, true);
        this.maxDuration = maxDuration;
    }


    @Override
    public B get(Object key) {
        updateTimestamp((A) key);
        return super.get(key);
    }


    @Override
    public B put(A key, B value) {
        updateTimestamp(key);
        return super.put(key, value);
    }


    @Override
    public void putAll(Map<? extends A, ? extends B> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public B remove(Object key) {
        sessionTimestamp.remove(key);
        return super.remove(key);
    }

    @Override
    public void replaceAll(BiFunction<? super A, ? super B, ? extends B> function) {
        throw new UnsupportedOperationException();
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<A, B> eldest) {
        if (hasTimedOutSession(eldest)) {
            sessionTimestamp.remove(eldest.getKey());
            return true;
        }
        return false;
    }

    private boolean hasTimedOutSession(Map.Entry<A, B> eldest) {
        if (sessionTimestamp.containsKey(eldest.getKey())) {
            Long timestamp = sessionTimestamp.get(eldest.getKey());
            // Remove item if access has passed maxDuration
            return System.currentTimeMillis() - timestamp > maxDuration;
        }
        return false;
    }

    private void updateTimestamp(A key) {
        sessionTimestamp.put(key, System.currentTimeMillis());
    }

}
