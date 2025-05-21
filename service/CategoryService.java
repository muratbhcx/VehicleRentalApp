package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.CategoryDAO;
import Vehicle_rental_app.exception.AnkaRentalException;
import Vehicle_rental_app.exception.ExceptionMessagesConstans;
import Vehicle_rental_app.model.Category;
import Vehicle_rental_app.model.User;

import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public void save(String name, User user) {
        categoryDAO.save(new Category(name, user, user));
        System.out.println("Kategori Başarıyla Oluşturuldu!");
    }

    public List<Category> getAll() {
        return categoryDAO.findAll(5);
    }

    public void deleteById(long id) {
        categoryDAO.delete(id);
        System.out.println("Kategori Silindi!");
    }

    public Category getById(Long categoryId) throws AnkaRentalException {
        Category foundCategory = categoryDAO.findById(categoryId);
        if (foundCategory == null) {
            throw new AnkaRentalException(ExceptionMessagesConstans.CATEGORY_NOT_FOUND);
        }
        System.out.println("Kategori Bulundu!");
        return foundCategory;

    }
}
