package com.hv.userMgmt.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hv.userMgmt.domain.User;

/**
 * 
 * @author harshul.varshney
 *
 */
@Repository
public class UserRepository extends AbstractRepository<Integer, User> {
	
	public User findById(int id) {
        return getByKey(id);
    }
 
    public User findByUsername(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userName", username));
        return (User) crit.uniqueResult();
    }
}
