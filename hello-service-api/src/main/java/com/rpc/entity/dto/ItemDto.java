package com.rpc.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 */
@Data
public class ItemDto implements Serializable {
    private String itemId;
    private String title;
    private String sellPoint;
    private BigDecimal price;
    private Integer stock;
    private String[] image;
    private Long cid;
    private Boolean status;
    private String[] itemDesc;
}
