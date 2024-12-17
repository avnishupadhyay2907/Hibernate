package com.rays.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class UserModel {

	public void add(UserDTO dto) {
		// session factory ka obj banya
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		// session ka object banaya aur usme sf.openn session ko call kiya
		Session session = sf.openSession();

		// transaction ka object banaya aur fir transaction ko begin kiya
		Transaction tx = session.beginTransaction();

		// session ki save method ko call kia aur usme dto pass kiya
		session.save(dto);

		tx.commit();

		// session ko close kia
		session.close();

	}

	public void update(UserDTO dto) {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();

		session.update(dto);

		tx.commit();

		session.close();
	}

	public void delete(UserDTO dto) {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();

		session.delete(dto);

		tx.commit();

		session.close();

	}

	public UserDTO findByPk(int pk) {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session session = sf.openSession();

		UserDTO dto = (UserDTO) session.get(UserDTO.class, pk);

		session.close();

		return dto;

	}

	public UserDTO authenticate(String login, String password) {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session session = sf.openSession();

		Query q = session.createQuery("from UserDTO where loginId = ? and password = ?");

		q.setString(0, login);
		q.setString(1, password);

		List list = q.list();

		UserDTO dto = null;

		if (list.size() > 0) {
			dto = (UserDTO) list.get(0);
		}
		session.close();
		return dto;
	}

	public UserDTO authenticateByCriteria(String login, String password) {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session session = sf.openSession();

		Criteria criteria = session.createCriteria(UserDTO.class);

		criteria.add(Restrictions.eq("loginId", login));

		criteria.add(Restrictions.eq("password", password));

		List list = criteria.list();

		UserDTO dto = null;

		if (list.size() > 0) {

			dto = (UserDTO) list.get(0);

		}

		session.close();

		return dto;
	}

	public List search(UserDTO dto, int pageNo, int pageSize) {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session session = sf.openSession();

		Criteria criteria = session.createCriteria(UserDTO.class);

		if (dto != null) {
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}

		List list = criteria.list();

		session.close();

		return list;

	}

}
