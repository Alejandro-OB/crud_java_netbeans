package edu.unicauca.apliweb.crud_java.persistence.entities;

import edu.unicauca.apliweb.crud_java.persistence.entities.TblAgricultorCliente;
import edu.unicauca.apliweb.crud_java.persistence.entities.TblProductoCliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-11-22T16:18:09")
@StaticMetamodel(TblCliente.class)
public class TblCliente_ { 

    public static volatile SingularAttribute<TblCliente, Integer> cedula;
    public static volatile SingularAttribute<TblCliente, String> direccion;
    public static volatile CollectionAttribute<TblCliente, TblAgricultorCliente> tblAgricultorClienteCollection;
    public static volatile SingularAttribute<TblCliente, String> telefono;
    public static volatile SingularAttribute<TblCliente, String> nombre;
    public static volatile CollectionAttribute<TblCliente, TblProductoCliente> tblProductoClienteCollection;

}