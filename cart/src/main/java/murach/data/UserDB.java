package murach.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import murach.business.User;

public class UserDB {
// Dùng cho Login
public static User selectUser(String email, String password) {

    EntityManager em =
            DBUtil.getEmFactory().createEntityManager();

    String qString =
            "SELECT u FROM User u "
            + "WHERE u.email = :email "
            + "AND u.password = :password";

    TypedQuery<User> q =
            em.createQuery(qString, User.class);

    q.setParameter("email", email);
    q.setParameter("password", password);

    try {

        return q.getSingleResult();

    } catch (NoResultException e) {

        return null;

    } finally {

        em.close();
    }
}


// Dùng để kiểm tra email khi Register
public static User selectUserByEmail(String email) {

    EntityManager em =
            DBUtil.getEmFactory().createEntityManager();

    String qString =
            "SELECT u FROM User u "
            + "WHERE u.email = :email";

    TypedQuery<User> q =
            em.createQuery(qString, User.class);

    q.setParameter("email", email);

    try {

        return q.getSingleResult();

    } catch (NoResultException e) {

        return null;

    } finally {

        em.close();
    }
}


// Dùng để thêm User mới vào database
public static boolean insert(User user) {

    EntityManager em =
            DBUtil.getEmFactory().createEntityManager();

    EntityTransaction trans =
            em.getTransaction();

    try {

        trans.begin();

        em.persist(user);

        trans.commit();

        return true;

    } catch (Exception e) {

        e.printStackTrace();

        if (trans.isActive()) {
            trans.rollback();
        }

        return false;

    } finally {

        em.close();
    }
}
}
