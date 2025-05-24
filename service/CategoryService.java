package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.CategoryDAO;
import Vehicle_rental_app.exception.AnkaRentalException;
import Vehicle_rental_app.exception.ExceptionMessagesConstans;
import Vehicle_rental_app.model.Category;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {

        this.categoryDAO = new CategoryDAO();
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
