package hcv.spring.service;

import hcv.spring.model.Data;

import java.util.List;

/**
 * Created by Tomo.
 */

public interface IDataService {

    public void addData(Data data);
    public void updateData(Data data);
    public Data getData(int id);
    public void deleteData(int id);
    public List<Data> getAllData();

}
