package dao;


import java.util.List;

public interface GenericDao<T> {
    boolean them(T entity);
    boolean sua(T entity);
    boolean xoa(String id);
    T timKiem(String id);
    List<T> layDanhSach();


}
