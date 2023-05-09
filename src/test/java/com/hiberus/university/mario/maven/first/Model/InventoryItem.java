package com.hiberus.university.mario.maven.first.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InventoryItem {
    private String name;
    private String descripcion;
    private String price;
}
