
package com.chathupererajay.nadyafashion.model;

import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author CPere
 */
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int id;
    private String name;
    
    }