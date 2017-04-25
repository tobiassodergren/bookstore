package org.sodergren.model.entity;

import java.math.BigDecimal;
import java.util.UUID;

public interface CartItem {
    CartItemDescription getDescription();

    BigDecimal getCost();

    UUID getId();
}
