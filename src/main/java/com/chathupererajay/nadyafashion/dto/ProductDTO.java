
package com.chathupererajay.nadyafashion.dto;

import lombok.Data;

/**
 *
 * @author CPere
 */
@Data
public class ProductDTO {

    private int id;
    private String name;
    private double price;
    private String description;
    private String imageUrl;
    private int categoryId;
}
