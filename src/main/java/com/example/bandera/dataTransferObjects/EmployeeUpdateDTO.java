package com.example.bandera.dataTransferObjects;

import com.example.bandera.entities.EmployeesEntity;

public class EmployeeUpdateDTO {
        private static String firstName;
        private static String lastName;
        private static String phoneNumber;
        private static String address;
        private static String email;
        private static String workOrder;

        /* --------------------------- Getters/Setters ---------------------------- */


        public static String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public static String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public static String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public static String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            EmployeesEntity.setAddress(address);
        }

        public static String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWorkOrder() {
            return workOrder;
        }

        public void setWorkOrder(String workOrder) {
            this.workOrder = workOrder;
        }

}
