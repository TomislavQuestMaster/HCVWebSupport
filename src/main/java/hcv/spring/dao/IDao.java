package hcv.spring.dao;

import hcv.spring.model.Data;

import java.util.List;

public interface IDao {

    public void addData(Data data);
    public void updateData(Data data);
    public Data getData(int id);
    public void deleteData(int id);
    public List<Data> getAllData();

}