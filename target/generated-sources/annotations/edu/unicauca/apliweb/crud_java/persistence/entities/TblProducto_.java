package edu.unicauca.apliweb.crud_java.persistence.entities;

import edu.unicauca.apliweb.crud_java.persistence.entities.TblAgricultorProducto;
import edu.unicauca.apliweb.crud_java.persistence.entities.TblProductoCliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-11-26T18:23:26")
@StaticMetamodel(TblProducto.class)
public class TblProducto_ { 

    public static volatile SingularAttribute<TblProducto, Integer> codigo;
    public static volatile CollectionAttribute<TblProducto, TblAgricultorProducto> tblAgricultorProductoCollection;
    public static volatile SingularAttribute<TblProducto, String> temporada;
    public static volatile SingularAttribute<TblProducto, String> nombre;
    public static volatile CollectionAttribute<TblProducto, TblProductoCliente> tblProductoClienteCollection;

}