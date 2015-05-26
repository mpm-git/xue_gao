package org.cz.project.entity.table;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "USER_T")
@NamedQueries({ @NamedQuery(name = "Goods.findAll", query = "SELECT g FROM Goods g")})
public class Goods extends IdEntity {

}
