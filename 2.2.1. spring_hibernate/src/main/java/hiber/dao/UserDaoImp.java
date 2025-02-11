package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private Session session;

   @Override
   public void add(User user) {
      session.save(user);
   }

   @Override
   public List<User> listUsers() {
      return session.createQuery("from User", User.class).getResultList();
   }

   @Override
   public User getUserByCarDetails(String model, int series) {
      String hql = "FROM User u WHERE u.car.model = :model AND u.car.series = :series";
      Query<User> query = session.createQuery(hql, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.uniqueResult();
   }
}
