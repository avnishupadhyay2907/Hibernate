package com.rays.crud;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rays.user.UserDTO;

public class TestUpdate {

	public static void main(String[] args) {

		UserDTO dto = new UserDTO();

		dto.setId(1);
		dto.setFirstName("Vansha");
		dto.setLastName("Upadhyay");
		dto.setLoginId("vansha2907@gmail.com");
		dto.setPassword("vanshaW@2028");
		dto.setDob(new Date());
		dto.setAddress("indore");

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();

		session.update(dto);
		tx.commit();

		session.close();

	}
}
