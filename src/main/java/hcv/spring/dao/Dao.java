package hcv.spring.dao;

import hcv.spring.model.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Dao implements IDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addData(Data data) {
        getCurrentSession().save(data);
    }

    public void updateData(Data data) {
        Data dataToUpdate = getData(data.getId());
        dataToUpdate.setLastUpdate(data.getLastUpdate());
        dataToUpdate.setUpdatingDeviceName(data.getUpdatingDeviceName());
        dataToUpdate.setName(data.getName());
        getCurrentSession().update(dataToUpdate);
    }

    public Data getData(int id) {
        return (Data) getCurrentSession().get(Data.class, id);
    }

    public void deleteData(int id) {
        Data data = getData(id);
        if (data != null)
            getCurrentSession().delete(data);
    }

    @SuppressWarnings("unchecked")
    public List<Data> getAllData() {
        return getCurrentSession().createQuery("from Data").list();
    }

}
