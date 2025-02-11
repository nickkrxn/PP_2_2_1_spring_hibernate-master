package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   public void addUserWithCar(User user, String carModel, int carSeries) {
      Car car = new Car(carModel, carSeries);
      user.setCar(car);  // Связываем пользователя с машиной
      car.setUser(user); // Связываем машину с пользователем
      userDao.add(user); // Добавляем пользователя (вместе с машиной)
   }

   @Transactional(readOnly = true)
   public User getUserByCarDetails(String model, int series) {
      return userDao.getUserByCarDetails(model, series);
   }
}
