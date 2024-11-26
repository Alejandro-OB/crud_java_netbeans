package edu.unicauca.apliweb.crud_java.persistence.entities;

import edu.unicauca.apliweb.crud_java.persistence.entities.TblAgricultorCliente;
import edu.unicauca.apliweb.crud_java.persistence.entities.TblAgricultorProducto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-11-26T17:56:41")
@StaticMetamodel(TblAgricultor.class)
public class TblAgricultor_ { 

    public static volatile CollectionAttribute<TblAgricultor, TblAgricultorProducto> tblAgricultorProductoCollection;
    public static volatile CollectionAttribute<TblAgricultor, TblAgricultorCliente> tblAgricultorClienteCollection;
    public static volatile SingularAttribute<TblAgricultor, String> telefono;
    public static volatile SingularAttribute<TblAgricultor, Integer> idAgricultor;
    public static volatile SingularAttribute<TblAgricultor, String> nombre;

}