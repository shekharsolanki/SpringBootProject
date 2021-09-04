package com.ansu.SpringBootProject2.service;

import com.ansu.SpringBootProject2.entity.Department;
import com.ansu.SpringBootProject2.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public Department postDepartment(Department department);

    public List<Department> fetchAllDepartments();

    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public Department fetchDepartmentByName(String departmentName);

    void deleteDepartmentById(Long departmentId);

    Department updateDepartment(Long departmentId, Department department);
}
