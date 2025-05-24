package Vehicle_rental_app.model;

import java.time.LocalDateTime;

public class BaseModel {

    private Long id;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private User createdUser;

    private User updatedUser;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setCreatedDate(LocalDateTime createdDate) {

        this.createdDate = createdDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate)
    {
        this.updatedDate = updatedDate;
    }

    public User getCreatedUser()
    {
        return createdUser;
    }

    public void setCreatedUser(User createdUser)
    {
        this.createdUser = createdUser;
    }

    public User getUpdatedUser()
    {
        return updatedUser;
    }

    public void setUpdatedUser(User updatedUser)
    {
        this.updatedUser = updatedUser;
    }
}
