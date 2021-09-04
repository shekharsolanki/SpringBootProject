package com.ansu.SpringBootProject2.service;

import com.ansu.SpringBootProject2.controller.DepartmentController;
import com.ansu.SpringBootProject2.entity.Department;
import com.ansu.SpringBootProject2.error.DepartmentNotFoundException;
import com.ansu.SpringBootProject2.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Override
    public Department postDepartment(Department department) {
        LOGGER.info("Inside postDepartment of DepartmentServiceImpl");
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchAllDepartments() {
        LOGGER.info("Inside fetchAllDepartments of DepartmentServiceImpl");
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        LOGGER.info("Inside fetchDepartmentById of DepartmentServiceImpl");
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return department.get();
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        LOGGER.info("Inside fetchDepartmentByName of DepartmentServiceImpl");
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        LOGGER.info("Inside deleteDepartmentById of DepartmentServiceImpl");
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        LOGGER.info("Inside updateDepartment of DepartmentServiceImpl");
        Department depDB = departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
            depDB.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentAddress())&& !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }
        if(Objects.nonNull(department.getDepartmentCode())&& !"".equalsIgnoreCase(department.getDepartmentCode())){
            depDB.setDepartmentCode(department.getDepartmentCode());
        }
        return departmentRepository.save(depDB);
    }
}
