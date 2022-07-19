package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void add(User user) {
        if (user.getCar() != null) {
            sessionFactory.getCurrentSession().save(user.getCar());
        }
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @Transactional
    public User userByCar(String model, int series) {

        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE car.model = :model and car.series = :series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList().stream().findAny().orElse(null);

    }


}
