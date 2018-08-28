package com.cencosud.scan_commons.store.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by carlos on 19-07-18.
 */

public class StoreJumboLocal extends RealmObject{


    @PrimaryKey
    public String local;
    public String nombre;
    public String direccion;
    public String telefono;
    public String mail;
    public String image;
    public String latitud;
    public String longitud;
    public String anexo;
    public boolean disponible;
}
