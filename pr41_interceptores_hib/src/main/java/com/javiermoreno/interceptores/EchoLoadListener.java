package com.javiermoreno.interceptores;

import org.hibernate.HibernateException;
import org.hibernate.event.LoadEvent;
import org.hibernate.event.def.DefaultLoadEventListener;

public class EchoLoadListener extends DefaultLoadEventListener {

    @Override
    public void onLoad(LoadEvent evt, LoadType type) throws HibernateException {
        System.out.println(evt.getEntityClassName());
        System.out.println(evt.getInstanceToLoad());
        System.out.println(type.getName());
        super.onLoad(evt, type);
    }
}
