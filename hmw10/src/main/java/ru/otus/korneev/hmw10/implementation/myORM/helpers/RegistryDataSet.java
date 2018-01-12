package ru.otus.korneev.hmw10.implementation.myORM.helpers;

import ru.otus.korneev.hmw10.dataSets.DataSet;
import ru.otus.korneev.hmw10.dataSets.dao.DataSetDAO;
import ru.otus.korneev.hmw10.implementation.hibernate.userDataSet.UserDataSetH;
import ru.otus.korneev.hmw10.implementation.hibernate.userDataSet.dao.UserDataSetHDAO;
import ru.otus.korneev.hmw10.implementation.myORM.userDataSet.UserDataSet;
import ru.otus.korneev.hmw10.implementation.myORM.userDataSet.dao.UserDataSetDAO;

import java.util.HashMap;
import java.util.Map;

public class RegistryDataSet {

    private static final Map<Class<? extends DataSet>, Class<? extends DataSetDAO>> REGISTRY = new HashMap<>();

    public static void register(){
        REGISTRY.put(UserDataSet.class, UserDataSetDAO.class);
        REGISTRY.put(UserDataSetH.class, UserDataSetHDAO.class);
        // .....
    }

    public static Class<? extends DataSetDAO> getDataSetDAO(Class<? extends DataSet> dataSet) {
        return REGISTRY.get(dataSet);
    }

    public static Map<Class<? extends DataSet>, Class<? extends DataSetDAO>> getREGISTRY() {
        return REGISTRY;
    }
}
