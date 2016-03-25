package com.tpokora.projects.user.dao;

import com.tpokora.projects.common.model.TableDetails;
import com.tpokora.projects.common.model.TableDetailsProvider;
import com.tpokora.projects.user.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tomek on 2016-01-16.
 */
/*
@Repository("userDao")
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public UserDAOImpl() {}

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        if (listUser.isEmpty()) {
            logger.error("No USERS returned from database.");
            return Collections.emptyList();
        }

        return listUser;
    }

    @Override
    public User getUserById(int id) {
        String hql = "from User where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        return (User) query.list().get(0);
    }

    @Override
    public User getUserByUsername(String username) {
        String hql = "from User where username='" + username + "'";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        return (User) query.list().get(0);
    }

    @Override
    public void createOrUpdateUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void removeUserById(int id) {
        User userToDelete = new User();
        userToDelete.setId(id);
        sessionFactory.getCurrentSession().delete(userToDelete);
    }

    @Override
    public TableDetails getTableDetails() {
        return TableDetailsProvider.getTableDetails(User.class, sessionFactory);
    }
}
*/
