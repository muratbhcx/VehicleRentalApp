package Vehicle_rental_app.dao;

import java.util.List;

public interface BaseDAO<T> {

    long save(T t);

    void update(T t);

    T findById(Long id);

    List<T> findAll(int page);

    void delete(Long id);
}
