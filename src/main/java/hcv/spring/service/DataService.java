package hcv.spring.service;

import hcv.spring.dao.IDao;
import hcv.spring.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tomo.
 */
@Service
@Transactional
public class DataService implements IDataService {

    @Autowired
    private IDao IDao;

    public void addData(Data data) {
        IDao.addData(data);
    }

    public void updateData(Data data) {
        IDao.updateData(data);
    }

    public Data getData(int id) {
        return IDao.getData(id);
    }

    public void deleteData(int id) {
        IDao.deleteData(id);
    }

    public List<Data> getAllData() {
        return IDao.getAllData();
    }

}
