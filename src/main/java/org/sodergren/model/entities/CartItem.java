package org.sodergren.model.entities;

import java.math.BigDecimal;
import java.util.UUID;

public interface CartItem {
    CartItemDescription getDescription();

    BigDecimal getPrice();

    UUID getId();
}
