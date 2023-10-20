package br.com.isato.applojalivros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_items")
@Getter
@Setter
@NoArgsConstructor
public class Item extends AbstractItem{

}
