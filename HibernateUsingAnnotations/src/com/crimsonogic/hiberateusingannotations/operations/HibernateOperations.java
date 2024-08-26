package com.crimsonogic.hiberateusingannotations.operations;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.crimsonogic.hiberateusingannotations.entity.HibernateUtil;
import com.crimsonogic.hiberateusingannotations.entity.StudentInfo;

public class HibernateOperations {
	Scanner sc = new Scanner(System.in);

	public void insertIntoStudent() {
		Transaction transaction = null;
		try (Session dbSession = HibernateUtil.getSessionFactory().openSession()) {
			StudentInfo st = new StudentInfo();
			System.out.println("Enter student name");
			String name=sc.next();
			st.setStudentName(name);
			System.out.println("Enter student email");
			String email=sc.next();
			st.setStudentEmail(email);
			System.out.println("Inserted: Student Name "+name+"\tStudent Email"+email);
			transaction = dbSession.beginTransaction();
			dbSession.save(st);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); // Consider using a logger instead
		}
	}

	public void deleteAllFromStudent() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			Query<StudentInfo> deleteQuery = session.createQuery("delete from StudentInfo");
			int rowCount = deleteQuery.executeUpdate();
			if (rowCount != 0) {
				System.out.println("Rows deleted: " + rowCount);
			} else {
				System.out.println("No rows found");
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace(); // Consider using a logger instead
		}
	}

	public void deleteUsingId(int id) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Query deleteQuery = session.createQuery("delete from StudentInfo where studentId = :id");
			deleteQuery.setParameter("id", id);
			int rowCount = deleteQuery.executeUpdate();
			if (rowCount != 0) {
				System.out.println("Rows deleted: " + rowCount);
			} else {
				System.out.println("No rows found");
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); // Consider using a logger instead
		}
	}

	public void displayAllStudents() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<StudentInfo> studentList = session.createQuery("from StudentInfo", StudentInfo.class).list();
			studentList.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace(); // Consider using a logger instead
		}
	}
}
