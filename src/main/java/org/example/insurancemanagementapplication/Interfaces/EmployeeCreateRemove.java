package org.example.insurancemanagementapplication.Interfaces;

import Entity.InsuranceManager;
import Entity.InsuranceSurveyor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Random;

/**
 * @author Luong Thanh Trung
 * @version ${}
 * @created 27/04/2024 04:58
 * @project InsuranceManagementTeamProject
 */
public interface EmployeeCreateRemove {
    public static boolean createInsuranceManager(EntityManager entityManager, String Id,  String fullName, String address, String phoneNumber, String email, String password){
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            InsuranceManager insuranceManager = new InsuranceManager();
            insuranceManager.setFullName(fullName);
            insuranceManager.setPhoneNumber(phoneNumber);
            insuranceManager.setAddress(address);
            insuranceManager.setEmail(email);
            insuranceManager.setPassword(password);
            String id = "IM";
            insuranceManager.setId(id);
            entityManager.persist(insuranceManager);
            transaction.commit();

        }
        finally {
            if (transaction.isActive()){
                transaction.rollback();
            }

        }
        return true;
    }

    public static boolean createInsuranceSurveyor(EntityManager entityManager, String Id,  String fullName, String address, String phoneNumber, String email, String password, InsuranceManager insuranceManager){
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
            insuranceSurveyor.setFullName(fullName);
            insuranceSurveyor.setPhoneNumber(phoneNumber);
            insuranceSurveyor.setAddress(address);
            insuranceSurveyor.setEmail(email);
            insuranceSurveyor.setPassword(password);
            String id = "IM";
            Random random = new Random();
            for (int i = 0; i < 8; i++){
                id = id + random.nextInt(0, 10);
            }
            insuranceSurveyor.setId(id);
            insuranceSurveyor.setInsuranceManager(insuranceManager);
            entityManager.persist(insuranceSurveyor);
            transaction.commit();


        }
        finally {
            if (transaction.isActive()){
                transaction.rollback();
            }

        }
        return true;
    }

    public static boolean removeInsuranceManager(EntityManager entityManager, InsuranceManager insuranceManager){
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.remove(insuranceManager);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
        return true;
    }

    public static boolean removeInsuranceSurveyor(EntityManager entityManager, InsuranceSurveyor insuranceSurveyor){
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.remove(insuranceSurveyor);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
        return true;
    }
}
